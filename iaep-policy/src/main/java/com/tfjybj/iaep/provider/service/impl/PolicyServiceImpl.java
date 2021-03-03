package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.PolicyCanEntity;
import com.tfjybj.iaep.model.message.MessageReceiveModel;
import com.tfjybj.iaep.model.policy.*;
import com.tfjybj.iaep.provider.dao.IConditionDao;
import com.tfjybj.iaep.provider.dao.IMessageDao;
import com.tfjybj.iaep.provider.dao.IMessageReceiveDao;
import com.tfjybj.iaep.provider.dao.IPolicyDao;
import com.tfjybj.iaep.provider.service.IPolicyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class PolicyServiceImpl implements IPolicyService{//extends absPolicyService {

    @Resource
    private IMessageReceiveDao iMessageReceiveDao;

    @Resource
    private IPolicyDao iPolicyDao;

    @Resource
    private IConditionDao iConditionDao;

    @Resource
    private IMessageDao iMessageDao;



    /**
     * 插入策略器
     * @param policyCanModel
     * @return
     */
    @Override
    public ItooResult insertPolicy(PolicyCanModel policyCanModel){
        try {
            //生成唯一的策略器id
            String policyId = IdWorker.getIdStr();

            //循环获取每个条件id，并保存到策略器表中
            for (String conId : policyCanModel.getConditionIds()) {
                PolicyCanEntity policyCanEntity = new PolicyCanEntity();
                policyCanEntity.setId(IdWorker.getIdStr());
                policyCanEntity.setPolicyId(policyId);
                policyCanEntity.setPolicyName(policyCanModel.getPolicyName());
                policyCanEntity.setRemark(policyCanModel.getPolicyRemark());
                policyCanEntity.setConditionId(conId);
                iPolicyDao.save(policyCanEntity);
            }
            //循环获取每个消息id，并保存到策略器表中
            for (String messId : policyCanModel.getMessageIds()) {
                PolicyCanEntity policyCan = new PolicyCanEntity();
                policyCan.setId(IdWorker.getIdStr());
                policyCan.setPolicyId(policyId);
                policyCan.setPolicyName(policyCanModel.getPolicyName());
                policyCan.setConditionId(null);
                policyCan.setRemark(policyCanModel.getPolicyRemark());
                policyCan.setMessageId(messId);
                iPolicyDao.save(policyCan);
            }
            return ItooResult.build(ItooResult.SUCCESS, "插入成功",policyId);
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"插入失败");
        }
    }

    /**
     * 删除策略器
     * @author Yara
     * @param policyIds
     * @return
     */
    @Override
    public ItooResult delete(List<String> policyIds) {
        try {
            for (String policyId:policyIds){
                //掉接口对策略器进行删除
                int num = iPolicyDao.updateIsDelete(policyId);
                List<String> conIds= iPolicyDao.queryConId(policyId);
                for (String conId:conIds){
                    iConditionDao.deleteCon(conId);
                }
                List<String> messIds=iPolicyDao.queryMessId(policyId);
                for (String messId:messIds){
                    iMessageDao.update(messId);
                }
            }
            //policyIds==null || policyIds.equals("")
            if (CollectionUtils.isEmpty(policyIds)){
                return ItooResult.build(ItooResult.FAIL,"并无删除数据");
            }
            return ItooResult.build(ItooResult.SUCCESS,"删除成功");

        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"删除失败");
        }
    }

    /**
     * 修改策略器
     * @author Yara 
     * @param policyModel
     * @return ItooResult
     */
    @Override
    public ItooResult update(PolicyModel policyModel) {
        try{
            //最后根据policyId,对策略器表中的数据进行更新
            PolicyCanEntity policy =new PolicyCanEntity();
            String policyName=policyModel.getPolicyName();
            String remark= policyModel.getRemark();
            String policyId = policyModel.getPolicyId();

            //获取条件id，并更新到策略器表中
            if (StringUtils.isBlank(policyId)){
                policyId=IdWorker.getIdStr();
            } else {
                iPolicyDao.updateNameAndRemark(policyName,remark,policyId);
            }
            if (CollectionUtils.isNotEmpty(policyModel.getConIds())) {
                for (String conID : policyModel.getConIds()) {
                    //查询策略器主键id（策略器id和条件id唯一对应的某条数据的主键id）
                    String ID = iPolicyDao.queryId(policyId, conID);
                    //如果在数据库中查到了对应的信息，进行修改操作
                    if (StringUtils.isBlank(ID)) {
                        //如果在数据库中没有查到相对应的信息，则进行插入操作
                        policy.setId(IdWorker.getIdStr());
                        policy.setPolicyId(policyId);
                        policy.setPolicyName(policyModel.getPolicyName());
                        policy.setRemark(policyModel.getRemark());
                        policy.setConditionId(conID);
                        iPolicyDao.save(policy);
                    } else {
                        policy.setId(ID);
                        policy.setPolicyId(policyId);
                        policy.setRemark(policyModel.getRemark());
                        policy.setPolicyName(policyModel.getPolicyName());
                        policy.setConditionId(conID);
                        iPolicyDao.save(policy);
                    }
                }
            }
            //获取消息id,并更新到策略器表中
            if (CollectionUtils.isNotEmpty(policyModel.getMessIds())) {
                for (String messID : policyModel.getMessIds()) {
                    //查询策略器主键id（策略器id和消息id唯一对应的某条数据的主键id）
                    String conditionId = null;
                    String ID = iPolicyDao.queryMsgId(policyId, messID);
                    //如果数据库中可以查到对应的信息，则进行修改操作
                    if (StringUtils.isNotBlank(ID)) {
                        policy.setId(ID);
                        policy.setPolicyId(policyId);
                        policy.setRemark(policyModel.getRemark());
                        policy.setPolicyName(policyModel.getPolicyName());
                        policy.setMessageId(messID);
                        policy.setConditionId(conditionId);
                        iPolicyDao.save(policy);
                    } else {
                        //如果数据库中无法查到对应的信息，则进行插入操作
                        policy.setId(IdWorker.getIdStr());
                        policy.setPolicyId(policyId);
                        policy.setPolicyName(policyModel.getPolicyName());
                        policy.setRemark(policyModel.getRemark());
                        policy.setConditionId(conditionId);
                        policy.setMessageId(messID);
                        iPolicyDao.save(policy);
                    }
                }
            }
            if (StringUtils.isNotBlank(policyId)){
                return ItooResult.build(ItooResult.SUCCESS,"修改成功",policyId);
            }
            return ItooResult.build(ItooResult.FAIL,"修改失败");
        }catch (Exception e) {
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL, "修改失败");
        }
    }



    /**以策略器id查询出该策略器的policyId和policyName以及对应的条件和消息。进入策略器流使用。
     * @author 孙彤
     * @param policyId  策略器id
     * @return
     */
    @Override
    public List<PolicyIdAndNameModel> queryConditionAndMessage(String policyId){

        List<PolicyIdAndNameModel> policyIdAndNameModel =new ArrayList<>();
        ArrayList allConditionResult = new ArrayList();
        ArrayList allMessageResult = new ArrayList();
        List<MessageReceiveModel> messageReceiveModelList = new ArrayList<>();
        PolicyIdAndNameModel policyIdAndNameModels = new PolicyIdAndNameModel();
        //获取策略器名称，策略器备注+条件id+消息id
        List<Map<String, String>> policyConditionIdMessgeIdLists=iPolicyDao.queryPolicyConditonIdMessageId(policyId);

        if(policyConditionIdMessgeIdLists.size()>0){
            for(Map<String,String>policyConditionIdMessgeIdList:policyConditionIdMessgeIdLists){
                //条件id不为空？根据条件id查询条件信息：无操作
                if(StringUtils.isNotBlank(policyConditionIdMessgeIdList.get("condition_id"))){
                    ConditionIdAndName conditionIdAndName = iPolicyDao.queryConditionIdAndName(policyConditionIdMessgeIdList.get("condition_id"));
                    allConditionResult.add(conditionIdAndName);
                }
                //消息id不为空？根据消息id查询消息信息：无操作
                if(StringUtils.isNotBlank(policyConditionIdMessgeIdList.get("message_id"))){
                    MessageIdNameModel messageIdNameModel = iPolicyDao.queryMessageIdAndName(policyConditionIdMessgeIdList.get("message_id"));
                    messageReceiveModelList = iMessageReceiveDao.selectGroup(messageIdNameModel.getReceivePerson());
                    MessageIdAndNameModel messageIdAndNameModel =new MessageIdAndNameModel();
                    messageIdAndNameModel.setMessageId(messageIdNameModel.getMessageId());
                    messageIdAndNameModel.setOptionalTemplate(messageIdNameModel.getOptionalTemplate());
                    messageIdAndNameModel.setReceivePerson(messageIdNameModel.getReceivePerson());
                    messageIdAndNameModel.setTouchChannel(messageIdNameModel.getTouchChannel());
                    messageIdAndNameModel.setMessageReceiveModels(messageReceiveModelList);
                    allMessageResult.add(messageIdAndNameModel);
                }
            }
        }
        //放到要返回前端的model中
        policyIdAndNameModels.setPolicyId(policyId);
        policyIdAndNameModels.setPolicyName(policyConditionIdMessgeIdLists.get(0).get("policy_name"));
        policyIdAndNameModels.setRemark(policyConditionIdMessgeIdLists.get(0).get("remark"));
        policyIdAndNameModels.setCondition(allConditionResult);
        policyIdAndNameModels.setMessage(allMessageResult);
        policyIdAndNameModel.add(policyIdAndNameModels);

        return policyIdAndNameModel;
    }
    /**以流程画布id查询出该流程画布下的策略器的policyId和policyName以及对应的条件信息。渲染流程画布时使用
     * @author 孙彤
     * @param processId  策略器id
     * @return
     */
    @Override
    public List<Object> queryPolicyAndCondition(String processId){
        ArrayList allResult = new ArrayList();
        //以流程画布id查询策略器id和名称
        List<Map<String, String>> policyIdList = iPolicyDao.queryPolicyId(processId);
        if(policyIdList.size()>0){
            for(Map<String, String> policyMap :policyIdList){
                PolicyAndConditonNameModel policyAndConditonNameModel = new PolicyAndConditonNameModel();
                //以策略器id查询对应条件信息
                List<Map<String, String>> conditionList = iPolicyDao.queryCondition(policyMap.get("policy_id"));
                ArrayList allConditionResult = new ArrayList();
                //查询条件信息不为空，则赋值
                if(conditionList.size()>0){
                    for(Map<String, String> conditionMap:conditionList){
                        ConditionAllModel conditionAllModel = new ConditionAllModel();
                        conditionAllModel.setConditionId(conditionMap.get("condition_id"));
                        conditionAllModel.setConditionName(conditionMap.get("condition_name"));
                        conditionAllModel.setTitleName(conditionMap.get("title_name"));
                        //改接口被其他接口调用，后两项，如调用接口不再调用可酌情删除，前端渲染时并没有使用到。
                        conditionAllModel.setConditionSelect(conditionMap.get("condition_select"));
                        conditionAllModel.setConditionExpress(conditionMap.get("condition_express"));
                        allConditionResult.add(conditionAllModel);
                    }
                }
                policyAndConditonNameModel.setPolicyId(policyMap.get("policy_id"));
                policyAndConditonNameModel.setPolicyName(policyMap.get("policy_name"));
                policyAndConditonNameModel.setCondition(allConditionResult);
                allResult.add(policyAndConditonNameModel);
            }
        }
        return allResult;
    }
}
