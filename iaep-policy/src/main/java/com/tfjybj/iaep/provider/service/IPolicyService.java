package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.policy.PolicyCanModel;
import com.tfjybj.iaep.model.policy.PolicyIdAndNameModel;
import com.tfjybj.iaep.model.policy.PolicyModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/14
 * Time: 8:15
 * Description:${DESCRIPTION}
 */
@Service
public interface IPolicyService {



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





    /**以策略器id查询出该侧录器的policyId和policyName以及对应的条件conditionId和conditionName
     * @author 孙彤
     * @param policyId  策略器id
     * @return
     */
    List<PolicyIdAndNameModel> queryConditionAndMessage(String policyId);

    List<Object> queryPolicyAndCondition(String processId);



}
