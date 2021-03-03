package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.RuleEntity;
import com.tfjybj.iaep.model.RuleAllModel;
import com.tfjybj.iaep.model.RuleModel;
import com.tfjybj.iaep.model.RulesModel;
import com.tfjybj.iaep.provider.dao.IRuleDao;
import com.tfjybj.iaep.provider.service.IRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Yara
 * Version: 1.0
 * Date: 2020年11月15日
 * Time: 20:27:26
 * Description:规则接口
 */

@Service
public class RuleServiceImpl implements IRuleService {
    @Resource
    private IRuleDao iRuleDao;

    /**
     * 插入规则
     * @param ruleModel
     * @return
     */
    @Override
    public ItooResult save(RuleModel ruleModel){
        try {
            //调接口进行插入规则操作
            RuleEntity ruleEntity= new RuleEntity();
            ruleEntity.setRuleName(ruleModel.getRuleName());
            ruleEntity.setProcessGraphId(ruleModel.getProcessGraphId());
            ruleEntity.setRuleMessage(ruleModel.getRuleMessage());
            ruleEntity.setRuleId(IdWorker.getIdStr());
            RuleEntity rule = iRuleDao.save(ruleEntity);
            if (rule!=null){
                return ItooResult.build(ItooResult.SUCCESS,"插入成功");
            }
            return ItooResult.build(ItooResult.FAIL,"插入失败");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"插入失败");
        }
    }

    /**
     * 删除规则，将is_delete字段设置为1
     * @param ruleId
     * @return
     */
    @Override
    public ItooResult delete(String ruleId) {
        try{
            //调用接口进行删除规则操作
            int num= iRuleDao.updateIsDelete(ruleId);
            if (num!=0){
                return ItooResult.build(ItooResult.SUCCESS,"删除成功",num);
            }
            return ItooResult.build(ItooResult.FAIL,"删除失败");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"删除失败");
        }
    }

    /**
     * 修改规则，主键ruldId不变，更新其他数据
     * @param ruleAllModel
     */
    @Override
    public ItooResult update(RuleAllModel ruleAllModel) {
        try {
            RuleEntity ruleEntity = new RuleEntity();
            ruleEntity.setProcessGraphId(ruleAllModel.getProcessGraphId());
            ruleEntity.setRuleMessage(ruleAllModel.getRuleMessage());
            ruleEntity.setRuleName(ruleAllModel.getRuleName());
            ruleEntity.setRuleId(ruleAllModel.getRuleId());
            //调用接口对规则进行修改操作
            RuleEntity rule = iRuleDao.save(ruleEntity);
            if (rule!=null){
                return ItooResult.build(ItooResult.SUCCESS,"修改成功");
            }
            return ItooResult.build(ItooResult.FAIL,"修改失败");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"修改失败");
        }
    }


    /**
     * 查询某条规则
     * @param ruleId
     * @return
     */
    @Override
    public ItooResult queryRule(String ruleId) {
        try{
            //调用查询接口进行查询操作
            RuleEntity ruleEntity = iRuleDao.queryOneRule(ruleId);
            if (ruleEntity!=null){
                RuleModel ruleModel = new RuleModel();
                ruleModel.setProcessGraphId(ruleEntity.getProcessGraphId());
                ruleModel.setRuleMessage(ruleEntity.getRuleMessage());
                ruleModel.setRuleName(ruleEntity.getRuleName());
                return ItooResult.build(ItooResult.SUCCESS,"查询成功",ruleModel);
            }
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }

    /**
     * 查询全部规则
     * @return
     */
    @Override
    public ItooResult queryAllRules() {
        try {
            //调用接口进行查询全部规则操作
            List<RuleEntity> ruleEntityList = iRuleDao.findAll();
            List<RulesModel> rulesModelList = new ArrayList<>();


            if (ruleEntityList!=null){
                for (int i = 0; i<ruleEntityList.size();i++){
                    RulesModel rulesModel = new RulesModel();
                    RuleEntity ruleEntity= ruleEntityList.get(i);
                    rulesModel.setRuleId(ruleEntity.getRuleId());
                    rulesModel.setRuleMessage(ruleEntity.getRuleMessage());
                    rulesModel.setRuleName(ruleEntity.getRuleName());
                    rulesModelList.add(rulesModel);
                }
                return ItooResult.build(ItooResult.SUCCESS,"查询成功",rulesModelList);
            }
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }
}
