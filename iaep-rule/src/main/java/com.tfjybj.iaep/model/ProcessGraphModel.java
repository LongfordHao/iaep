package com.tfjybj.iaep.model;

import lombok.Data;

import java.util.List;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

@Data
public class ProcessGraphModel {
    //流程画布表主键id
    private String prgId;
    //受众表主键id
    private String dataId;
    //受众集合
    private List<AudienceModel> userIdList;
    //策略关系集合
    private List<PolicyrRelevanceModel> policyrRelevanceModelList;
}
