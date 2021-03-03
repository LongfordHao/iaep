package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.user.CompanyOrganizationModel;
import com.tfjybj.iaep.provider.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 孙彤
 * @Date 2021/1/239:46
 * @Version 1.0
 */
@Api(tags={"受众人员"})
@RequestMapping(value = "/user")
@RestController
@CrossOrigin
public class UserController {
    @Resource
    private IUserService iUser;

    @ApiOperation(value="组织结构人员")
    @GetMapping
    public ItooResult queryOrganizationUser(){
        try{
            List<CompanyOrganizationModel> companyOrganizationModelList = iUser.queryOrganizationUser();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",companyOrganizationModelList);
        }catch(Exception e){
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }


}
