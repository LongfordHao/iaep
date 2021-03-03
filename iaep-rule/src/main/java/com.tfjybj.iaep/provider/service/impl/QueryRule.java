package com.tfjybj.iaep.provider.service.impl;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.PolicyIdAndNameModel;
import com.tfjybj.iaep.model.PolicyrRelevanceModel;
import com.tfjybj.iaep.model.ProcessGraphModel;
import com.tfjybj.iaep.model.RuleModel;
import com.tfjybj.iaep.provider.service.IQueryRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jeff
 * @description ${description}
 * @date 2021/1/26 9:12
 */
@Service
public class QueryRule implements IQueryRule {
    @Resource
    private RuleServiceImpl ruleService;
    @Resource
    private ProcessGraphServiceImpl processGraphService;

    @Resource
    private PolicyServiceImpl policyService;




    /**
     * 通过规则id返回所有规则里面的内容
     * @param RuleId
     */
    @Override

    public  Map<String,Object> QueryAllRuleContent(String RuleId){
        Map<String,Object> map = new HashMap<>();
        //1.查询规则id  拿到规则名称 流程画布id
        ItooResult queryRule = ruleService.queryRule(RuleId);
        RuleModel RuleModelData = (RuleModel)queryRule.getData();
        map.put("RuleName",RuleModelData.getRuleName());
        String processGraphId = RuleModelData.getProcessGraphId();
        //2.流程画布id 查询 数据源  受众id 策略器
        ProcessGraphModel allByproGraphId = processGraphService.findAllByproGraphId(processGraphId);
         //筛选出受众，放到map中  暂时传入list
        map.put("userIdList",allByproGraphId.getUserIdList());
         //根据数据源获取，数据源id  暂时传入数据源id
//        map.put("dataId",allByproGraphId.getDataId());
         //拿到对应的策略器id
        List<PolicyrRelevanceModel> policyrRelevanceModelList = allByproGraphId.getPolicyrRelevanceModelList();
        List Polices =new ArrayList();
        for (PolicyrRelevanceModel policyrRelevanceModel :policyrRelevanceModelList){
            Polices.add(policyrRelevanceModel.getPolicyCanId());
        }
        //3.策略器拿到 下面的消息和条件
        ArrayList ConditionAndMessageList =new ArrayList<>();
        for(int i=0;i<Polices.size();i++){
            String pId=(String) Polices.get(i);
            List<PolicyIdAndNameModel> policyIdAndNameModelList = policyService.queryConditionAndMessage(pId);
            ConditionAndMessageList.add(policyIdAndNameModelList);
        }
        //消息和条件
        map.put("ConditionAndMessage",ConditionAndMessageList);
        return map;
    }
}
