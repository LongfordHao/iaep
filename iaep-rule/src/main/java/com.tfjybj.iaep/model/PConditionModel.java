package com.tfjybj.iaep.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 9:10
 * @Version 1.0
 * @Description
 */
@Data
public class PConditionModel {
    private String conditionId;
    private String conditionName;
    private String conditionSelect;
    private String conditionExpress;
}
