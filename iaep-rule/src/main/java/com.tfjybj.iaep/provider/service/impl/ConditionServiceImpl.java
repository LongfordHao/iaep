package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.ConditionEntity;
import com.tfjybj.iaep.model.ConditionModel;
import com.tfjybj.iaep.model.PConditionModel;
import com.tfjybj.iaep.provider.dao.IConditionDao;
import com.tfjybj.iaep.provider.service.IConditionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 18:18
 * @Version 1.0
 * @Description
 */
@Service
public class ConditionServiceImpl implements IConditionService {

    @Resource
    private IConditionDao iConditionDao;

    /**
     * 插入(修改)条件
     * @param conditionModel
     * @return
     */
    @Override
    public ItooResult insert(List<PConditionModel> conditionModel) {
        try {
            ConditionEntity condition = new ConditionEntity();
            List<String> Idlist=new ArrayList<>();
            //插入的条件进行遍历，通过save方法插入每个条件，返回conditionId的集合
            for (int i = 0;i<conditionModel.size();i++){
                PConditionModel con = conditionModel.get(i);
                if(con.getConditionId()==null||con.getConditionId()=="") {
                    condition.setId(IdWorker.getIdStr());
                    condition.setConditionId(IdWorker.getIdStr());
                    condition.setConditionName(con.getConditionName());
                    condition.setConditionSelect(con.getConditionSelect());
                    condition.setConditionExpress(con.getConditionExpress());
                    ConditionEntity conEntity = iConditionDao.save(condition);
                    String IconId = conEntity.getConditionId();
                    Idlist.add(IconId);
                } else {
                    String id = iConditionDao.queryId(con.getConditionId());
                    String conId=con.getConditionId();
                    if (id==null){
                        id=IdWorker.getIdStr();
                        conId=IdWorker.getIdStr();
                    }
                    condition.setId(id);
                    condition.setConditionId(conId);
                    condition.setConditionName(con.getConditionName());
                    condition.setConditionSelect(con.getConditionSelect());
                    condition.setConditionExpress(con.getConditionExpress());
                    ConditionEntity conEntity = iConditionDao.save(condition);
                    String UconId = conEntity.getConditionId();
                    Idlist.add(UconId);
                }
            }
            return ItooResult.build(ItooResult.SUCCESS,"插入成功",Idlist);
        } catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"插入失败");
        }
    }

    /**
     * 删除条件
     * @param conditionId
     * @return
     */
    @Override
    public ItooResult delete(List<String> conditionId) {
        try {
            //调用删除方法删除条件
            if (CollectionUtils.isEmpty(conditionId)){
                return null;
            }
            for (String conId : conditionId) {
                if (StringUtils.isNotBlank(conId)){
                    int num = iConditionDao.deleteCon(conId);
                    if (num!=0){
                        iConditionDao.deletePolicyCan(conId);
                    }
                }
            }
            return ItooResult.build(ItooResult.SUCCESS, "删除成功");
        } catch (Exception e) {
            return ItooResult.build(ItooResult.FAIL, "删除失败");
        }
    }
}
