package com.tfjybj.iaep.model;

import lombok.Data;

/**
 * @Author 孙彤
 * @Date 2021/1/2220:05
 * @Version 1.0
 */
@Data
public class AllUsersModel {
    //用户姓名

    private String userName;

    //用户dingId

    private String dingId;

    //用户usercode--msg调用消息时需要用到该字段

    private String userCode;
}
