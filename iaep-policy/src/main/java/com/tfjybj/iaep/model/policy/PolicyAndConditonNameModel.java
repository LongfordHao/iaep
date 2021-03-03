package com.tfjybj.iaep.model.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 孙彤
 * @Date 2020/11/160:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyAndConditonNameModel {
    private String policyId;
    private String policyName;
    private List<ConditionIdAndName> condition;
}
