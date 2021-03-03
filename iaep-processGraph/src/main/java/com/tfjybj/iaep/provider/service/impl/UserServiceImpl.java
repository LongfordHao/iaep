package com.tfjybj.iaep.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tfjybj.iaep.model.AllUsersModel;
import com.tfjybj.iaep.model.CompanyOrganizationModel;
import com.tfjybj.iaep.model.OrganizationTreeModel;
import com.tfjybj.iaep.model.OrganizationUserModel;
import com.tfjybj.iaep.provider.service.IUserService;
import com.tfjybj.iaep.utils.http.HttpUtils;
import com.tfjybj.iaep.utils.http.ResponseWrap;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import utils.HttpUtils;
//import utils.ResponseWrap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 孙彤
 * @Date 2021/1/2217:12
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    //通过@value注解将配置文件中的值取出来

    //获取配置文件中的--调用组织结构树接口

    @Value("${permission.organization}")
    private String organization;

    //获取配置文件中的--调用传组织下对应人员接口

    @Value("${permission.personAndOrganization}")
    private String personAndOrganization;

@Override
public List<CompanyOrganizationModel> queryOrganizationUser(){
    //可以添加redis
    //调用查询组织结构树的接口
    String requestOrganizationStr = organization;
    HttpUtils http = HttpUtils.get(requestOrganizationStr);
    ResponseWrap responseWrap = http.execute();
    //获取大米时代下所有的组织
    List<OrganizationTreeModel> organizationTree = JSONObject.parseArray(responseWrap.getString(),OrganizationTreeModel.class);
    //声明空arraylist做数据中转方
    List<CompanyOrganizationModel> companyOrganizationModels = Lists.newArrayList();
    //如果调用权限--组织树查询未返回空值，则进行数据筛选
    if(!CollectionUtils.isEmpty(organizationTree)&&organizationTree.size()!=0){
        companyOrganizationModels =organizationTree.get(0).getChildren().stream().filter(child->child.getName().equals("全体在校生")).collect(Collectors.toList()).get(0).getChildren().stream().filter(children->!children.getName().equals("中学部")&&!children.getName().equals("暂停使用人员")).map(childrenDetailed->{
            CompanyOrganizationModel companyOrganizationModel = new CompanyOrganizationModel();
            companyOrganizationModel.setOrgId(childrenDetailed.getId());
            companyOrganizationModel.setOrgName(childrenDetailed.getName());
            return companyOrganizationModel;
        }).collect(Collectors.toList());
    }

    //根据组织查询该组织下的成员
    for(CompanyOrganizationModel companyOrganizationModel:companyOrganizationModels){
        String personAndOrganizationStr = personAndOrganization+companyOrganizationModel.getOrgId()+"/23VAJAVAXT492QKFsfUBru";
        HttpUtils httpTwo = HttpUtils.get(personAndOrganizationStr);
        ResponseWrap responseWrapTwo = httpTwo.execute();
        List<AllUsersModel> allUsersModels =JSONObject.parseArray(responseWrapTwo.getString(),AllUsersModel.class);
        if(!CollectionUtils.isEmpty(allUsersModels)&&allUsersModels.size()!=0){
            List<OrganizationUserModel> organizationUserModels = allUsersModels.stream().map(child->{
                OrganizationUserModel organizationUserModel = new OrganizationUserModel();
                organizationUserModel.setName(child.getUserName());
                organizationUserModel.setDingId(child.getDingId());
                organizationUserModel.setUserCode(child.getUserCode());
                return organizationUserModel;
            }).collect(Collectors.toList());
            companyOrganizationModel.setUserList(organizationUserModels);
        }
    }
    return companyOrganizationModels;
}
}
