package com.tfjybj.iaep.model;

import lombok.Data;

/**
 * @Author Yara
 * @Date 2020/11/25
 * @Time 17:15
 * @Version 1.0
 * @Description
 */
@Data
public class RuleAllModel {
    private String ruleId;
    private String ruleName;
    private String ruleMessage;
    private String processGraphId;
}
