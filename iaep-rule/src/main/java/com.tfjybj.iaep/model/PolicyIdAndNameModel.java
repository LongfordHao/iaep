package com.tfjybj.iaep.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.persistence.Column;

/**
 * @Author 孙彤
 * @Date 2020/11/15 16:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyIdAndNameModel {

    private String policyId;
    private String policyName;
    private String remark;
    private List<ConditionIdAndName> condition;
    private List<MessageIdAndNameModel> message;


}
