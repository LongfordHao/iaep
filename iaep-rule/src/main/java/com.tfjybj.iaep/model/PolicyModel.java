package com.tfjybj.iaep.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 9:06
 * @Version 1.0
 * @Description
 */
@Data
public class PolicyModel {
    private String policyId;
    private String policyName;
    private String remark;
    private List<String> messIds;
    private List<String> conIds;

}
