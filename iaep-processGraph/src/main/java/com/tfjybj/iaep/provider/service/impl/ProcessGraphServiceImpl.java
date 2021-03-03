package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.iaep.entity.AudienceEntity;
import com.tfjybj.iaep.entity.PolicyRelevanceEntity;
import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.model.AudienceModel;
import com.tfjybj.iaep.model.PolicyrRelevanceModel;
import com.tfjybj.iaep.model.ProcessGraphModel;
import com.tfjybj.iaep.provider.dao.IAudienceDao;
import com.tfjybj.iaep.provider.dao.IPolicyRelevanceDao;
import com.tfjybj.iaep.provider.dao.IProcessGraphDao;
import com.tfjybj.iaep.provider.service.IProcessGraphService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

@Service
public class ProcessGraphServiceImpl implements IProcessGraphService {
    @Resource
    private IProcessGraphDao iProcessGraphDao;
    @Resource
    private IAudienceDao iAudienceDao;
    @Resource
    private IPolicyRelevanceDao iPolicyRelevanceDao;

    ProcessGraphEntity processGraphEntity =new ProcessGraphEntity();

    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 插入流程画布
     * @params: ProcessGraphModel
     * @return: processGraphId
     * @Date:   2020年11月17日15:52:53
     * @Modified By:
    */
    @Override
    public String  save(ProcessGraphModel insertModel) {
        //插入流程画布表
        processGraphEntity.setId(IdWorker.getIdStr());
        processGraphEntity.setDataId(insertModel.getDataId());
        iProcessGraphDao.save(processGraphEntity);

        //插入受众表
        List<AudienceModel> audienceModelList=insertModel.getUserIdList();
        List<AudienceEntity>audienceEntities=new ArrayList<>();
        //循环audienceModelList 添加到受众实体
        for (AudienceModel audienceModel:audienceModelList) {
            AudienceEntity aduEntity=new AudienceEntity();
            aduEntity.setId(IdWorker.getIdStr());
            aduEntity.setOrganizationId(audienceModel.getOrganizationId());
            aduEntity.setUserId(audienceModel.getUserId());
            aduEntity.setProcessGraphId(processGraphEntity.getId());
            audienceEntities.add(aduEntity);
        }
        //调用saveAll把list中的数据插入到表中
        iAudienceDao.saveAll(audienceEntities);

        //插入策略关系表
        List<PolicyrRelevanceModel> policyrRelevanceModelList=insertModel.getPolicyrRelevanceModelList();
        List<PolicyRelevanceEntity> policyRelevanceEntities=new ArrayList<>();
        //循环policyrRelevanceModelList 添加到策略关系实体
        for(PolicyrRelevanceModel policyrRelevanceModel:policyrRelevanceModelList) {
            PolicyRelevanceEntity porRelEntity=new PolicyRelevanceEntity();
            porRelEntity.setId(IdWorker.getIdStr());
            porRelEntity.setProcessGraphId(processGraphEntity.getId());
            porRelEntity.setSerialNumber(policyrRelevanceModel.getSerialNumber());
            porRelEntity.setHierarchy(policyrRelevanceModel.getHierarchy());
            porRelEntity.setPolicyCanId(policyrRelevanceModel.getPolicyCanId());
            policyRelevanceEntities.add(porRelEntity);
        }
        //调用saveAll把list中的数据插入到表中
        iPolicyRelevanceDao.saveAll(policyRelevanceEntities);

        // 返回流程画布id
        String  processGraphId= processGraphEntity.getId();
        return processGraphId;
    }


    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 删除流程画布
     * @params: ProcessGraphModel
     * @return: bool
     * @Date:   2020年11月17日15:52:53
     * @Modified By:
     */
    @Override
    public void delete(String processGraphId) {
        //更新流程画布表
        iProcessGraphDao.delete(processGraphId);
        //更新受众表
        iAudienceDao.delete(processGraphId);
        //更新策略关系表
        iPolicyRelevanceDao.delete(processGraphId);
    }

    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 查询流程画布
     * @params: processGraphId
     * @return: ProcessGraphModel
     * @Date:   2020年11月17日15:57:34
     * @Modified By:
    */
    @Override
    public ProcessGraphModel findAllByproGraphId(String processGraphId) {
        //根据流程画布id查询流程画布表
        ProcessGraphEntity processgraph = iProcessGraphDao.queryById(processGraphId);

        //根据流程画布id查询受众表
        List<AudienceEntity> audienceEntities = iAudienceDao.findAudience(processGraphId);
        //将list<Entity>给list<Model>
        List<AudienceModel> audienceModelsList = new ArrayList<>();
        for (AudienceEntity audienceEntity : audienceEntities) {
            AudienceModel audienceModel = new AudienceModel();
            audienceModel.setOrganizationId(audienceEntity.getOrganizationId());
            audienceModel.setUserId(audienceEntity.getUserId());
            audienceModelsList.add(audienceModel);
        }
        //根据流程画布id查询策略器关系表
        List<PolicyRelevanceEntity> policyRelevanceEntities = iPolicyRelevanceDao.findPolicyRelevance(processGraphId);
        //将list<Entity>给list<Model>
        List<PolicyrRelevanceModel> policyrRelevanceModelList = new ArrayList<>();
        for (PolicyRelevanceEntity policyRelevanceEntity : policyRelevanceEntities) {
            PolicyrRelevanceModel policyrRelevanceModel = new PolicyrRelevanceModel();
            policyrRelevanceModel.setSerialNumber(policyRelevanceEntity.getSerialNumber());
            policyrRelevanceModel.setHierarchy(policyRelevanceEntity.getHierarchy());
            policyrRelevanceModel.setPolicyCanId(policyRelevanceEntity.getPolicyCanId());
            policyrRelevanceModelList.add(policyrRelevanceModel);
        }

        //综合三个标的数据给ProcessGraphModel赋值
        ProcessGraphModel processGraphModel=new ProcessGraphModel();
        processGraphModel.setPrgId(processGraphId);
        processGraphModel.setDataId(processgraph.getDataId());
        processGraphModel.setUserIdList(audienceModelsList);
        processGraphModel.setPolicyrRelevanceModelList(policyrRelevanceModelList);
        return processGraphModel;
    }

}
