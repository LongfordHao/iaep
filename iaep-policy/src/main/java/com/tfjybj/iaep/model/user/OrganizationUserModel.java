package com.tfjybj.iaep.model.user;

import lombok.Data;

/**
 * @Author 孙彤
 * @Date 2021/1/2218:47
 * @Version 1.0
 */
@Data
public class OrganizationUserModel {


    //用户姓名

    private String name;

    //用户dingId

    private String dingId;

    //用户userCode--msg调用消息时使用该字段

    private String  userCode;
}
