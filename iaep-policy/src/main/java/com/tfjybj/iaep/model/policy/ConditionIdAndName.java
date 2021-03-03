package com.tfjybj.iaep.model.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 孙彤
 * @Date 2020/11/1520:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionIdAndName {
    private String conditionId;
    private String conditionName;
    private String conditionSelect;
    private String conditionExpress;

}
