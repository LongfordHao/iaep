package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.PolicyCanEntity;
import com.tfjybj.iaep.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/14
 * Time: 8:19
 * Description:${DESCRIPTION}
 */

@Transactional
public interface IPolicyDao extends JpaRepository<PolicyCanEntity,String>,JpaSpecificationExecutor<PolicyCanEntity> {


    @Modifying
    @Query(value = "update policy_can set policy_name=?1,remark=?2 where policy_id=?3",nativeQuery = true)
    void updateNameAndRemark(String policyName,String remark,String policyId);

    @Query(value = "select  * from policy_can", nativeQuery = true)
    List<PolicyCanEntity> findAll();


    /**
     * 查询策略器名字是否重复
     * @param name
     * @return
     */
    @Query(value = "select  * from policy_can where policy_name=? ",nativeQuery = true)
    PolicyCanEntity findByName(String name);

    /**
     * （假）删除策略器
     * @param policyId
     * @return
     */
    @Modifying
    @Query(value = "update policy_can set is_delete=1 where policy_id=?",nativeQuery = true)
    int updateIsDelete(String policyId);

    @Query(value = "select id from policy_can where is_delete = 0 and policy_id = ?1 and condition_id= ?2",nativeQuery = true)
    String queryId(String policyId,String conID);

    @Query(value = "select id from policy_can where is_delete = 0 and policy_id = ?1 and message_id= ?2",nativeQuery = true)
    String queryMsgId(String policyId,String msgID);

    @Query(value = "select condition_id from policy_can where policy_id=? and is_delete=1",nativeQuery = true)
    List<String> queryConId(String policyId);

    @Query(value = "select message_id from policy_can where policy_id = ? and is_delete=1",nativeQuery = true)
    List<String> queryMessId(String policyId);

    /**
     * 查询未被删除的条件id和条件名称
     * @return
     */
    @Query(value = "select new com.tfjybj.iaep.model.ConditionNameModel(cd.conditionId,cd.conditionName) from ConditionEntity  cd where cd.isDelete=0")
    List<ConditionNameModel> queryConditionName();


    /**
     * 以条件id查询对应条件信息
     * @return
     */
    @Query(value = "select condition_id, condition_name,condition_select,condition_express from `iaep_condition`  WHERE condition_id=? and is_delete=0", nativeQuery = true)
    List<Map<String, Object>> queryConditionAll(String conditionId);

    /**
     * 以策略器id查询出该策略器的policyName和备注remark
     * @param policyId 策略器id
     * @return
     */

    @Query(value = "select new com.tfjybj.iaep.model.PolicyNameAndReamrkModel(pc.policyName,pc.remark) from PolicyCanEntity pc  where pc.policyId=:policyId and pc.isDelete=0 ")
    List<PolicyNameAndReamrkModel> queryPolicyIdAndName(String policyId);

    @Query(value="select policy_name from policy_can where policy_id=? limit 1", nativeQuery = true)
    String queryPolicyName(String policyId);

    /**
     * 根据条件id查询出条件的id和条件名称和具体约束条件
     * @param conditionId 条件id
     * @return
     */
    @Query(value="select new com.tfjybj.iaep.model.ConditionIdAndName(cd.conditionId,cd.conditionName,cd.conditionSelect,cd.conditionExpress) from ConditionEntity  cd where cd.conditionId=:conditionId and cd.isDelete=0")
    ConditionIdAndName queryConditionIdAndName(String conditionId);

    /**
     * 根据消息id查询任务推送（消息)的id,名称和详细内容
     * @param messageId
     * @return
     */
    @Query(value="select new  com.tfjybj.iaep.model.MessageIdNameModel(ms.messageId,ms.touchChannel,ms.optionalTemplate,ms.receivePerson)from MessageEntity  ms where ms.messageId=:messageId and ms.isDelete=0")
    MessageIdNameModel queryMessageIdAndName(String messageId);

    /**
     * 以流程画布id查询该流程画布下的策略器id
     * @param processId
     * @return
     */
//    @Query(value ="select new com.tfjybj.iaep.model.PolicyIdModel( policy_id,policy_name)   FROM PolicyRelevanceEntity pr where pr.processGraphId=:processId and pr.isDelete=0")
//    List<PolicyIdModel> queryPolicyId (String processId);

    @Query(value ="SELECT  policy_id,policy_name\n" +
            "from policy_can\n" +
            "WHERE policy_id in (\n" +
            "\tSELECT policy_can_id \n" +
            "\tFROM policy_relevance\n" +
            "\tWHERE process_graph_id =:processId\n" +
            "\tand is_delete = 0 \n" +
            ")\n" +
            "GROUP BY policy_id",nativeQuery=true)
    List<Map<String, String>> queryPolicyId (String processId);

    @Query(value ="select condition_id,condition_name,title_name,condition_select,condition_express\n" +
            "from iaep_condition \n" +
            "left join condition_title\n" +
            "on condition_name=title_field\n" +
            "where condition_id in (\n" +
            "\tselect  condition_id\n" +
            "\tfrom policy_can \n" +
            "\twhere policy_id =:policyId\n" +
            "\tand condition_id is not null\n" +
            "\tand is_delete=0\n" +
            ")",nativeQuery=true)
    List<Map<String, String>> queryCondition (String policyId);

    /**
     * 根据策略器id查询该id下对应的条件id和消息id
     * @param policyId
     * @return
     */
    @Query(value="select new com.tfjybj.iaep.model.ConditionAndMessageId(pc.conditionId,pc.messageId) from PolicyCanEntity  pc where pc.policyId=:policyId and pc.isDelete=0")
    List<ConditionAndMessageId> queryConditionIdAndMessageId(String policyId);



}
