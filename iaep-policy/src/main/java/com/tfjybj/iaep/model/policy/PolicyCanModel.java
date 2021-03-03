package com.tfjybj.iaep.model.policy;

import lombok.Data;

import java.util.List;

/**
 * @author jeff
 * @create 2020-11-14 20:26
 */
@Data
public class PolicyCanModel {
    private String policyName;
    private String policyRemark;
    private List<String> messageIds;
    private List<String> conditionIds;

}
