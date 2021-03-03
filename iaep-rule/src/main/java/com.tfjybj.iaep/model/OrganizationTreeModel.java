package com.tfjybj.iaep.model;

import lombok.Data;

import java.util.List;

/**
 * @Author 孙彤
 * @Date 2021/1/2219:27
 * @Version 1.0
 */
@Data
public class OrganizationTreeModel {
    //组织id

    private String id;

    //组织名称

    private String name;

    //自组织id

    private String pid;

    //子组织list

    private List<OrganizationTreeModel> children;
}
