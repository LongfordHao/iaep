package com.tfjybj.iaep.model.user;

import lombok.Data;

import java.util.List;

/**
 * @Author 孙彤
 * @Date 2021/1/22 18:43
 * @Version 1.0
 */
@Data
public class CompanyOrganizationModel   {
    //组织id

    private String orgId;

    //组织名称

    private  String orgName;

    //该组织内的人员

    private List<OrganizationUserModel> userList;
}

