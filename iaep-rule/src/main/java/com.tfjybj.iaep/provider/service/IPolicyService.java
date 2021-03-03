package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.PolicyCanEntity;
import com.tfjybj.iaep.model.ConditionIdAndName;
import com.tfjybj.iaep.model.ConditionNameModel;
import com.tfjybj.iaep.model.PolicyCanModel;

import com.tfjybj.iaep.model.PolicyModel;

import com.tfjybj.iaep.model.PolicyIdAndNameModel;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/14
 * Time: 8:15
 * Description:${DESCRIPTION}
 */
@Service
public interface IPolicyService {
    PolicyCanEntity queryName(String policyName);


    /**
     * 插入策略器
     * @author Yara
     * @param policyCanModel
     * @return
     */
    ItooResult insertPolicy(PolicyCanModel policyCanModel);;

    /**
     * 删除策略器
     * @author Yara
     * @param policyIds
     * @return
     */
    ItooResult delete(List<String> policyIds);

    /**
     * 修改策略器
     * @author Yara
     * @param policyModel
     * @return
     */
    ItooResult update(PolicyModel policyModel);

    //PolicyCanModel insertPolicy(PolicyCanModel policyCanModel);

    /**
     * 查询未删除的条件id和条件名称
     * @author 孙彤
     * @return
     */
    List<ConditionNameModel> queryConditionName();

    /**以条件id查询对应条件信息
     * @author 孙彤
     * @return
     */
    List<Map<String,Object>> queryConditionAll(String conditionId);

    /**以策略器id查询出该侧录器的policyId和policyName以及对应的条件conditionId和conditionName
     * @author 孙彤
     * @param policyId  策略器id
     * @return
     */
    List<PolicyIdAndNameModel> queryConditionAndMessage(String policyId);

    List<Object> queryPolicyAndCondition(String processId);



}
