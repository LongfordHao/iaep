package com.tfjybj.iaep.model;

import lombok.Data;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/19
 * @Time 19:56
 * @Version 1.0
 * @Description
 */
@Data
public class AllDataModel {
    private List<ConditionTitleModel> conditionTitleModelList;
    private List<DataResourceModel> dataResourceModelList;
}
