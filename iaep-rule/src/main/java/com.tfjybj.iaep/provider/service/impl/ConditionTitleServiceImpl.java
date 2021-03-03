package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.ConTitleModel;
import com.tfjybj.iaep.model.ConditionTitleModel;
import com.tfjybj.iaep.model.LabelDataModel;
import com.tfjybj.iaep.provider.dao.IConditionDao;
import com.tfjybj.iaep.provider.dao.IConditionTitleDao;
import com.tfjybj.iaep.provider.dao.IDataResourceDao;
import com.tfjybj.iaep.provider.service.IConditionTitleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Yara
 * @Date 2021/1/20
 * @Time 14:33
 * @Version 1.0
 * @Description
 */
@Service
public class ConditionTitleServiceImpl implements IConditionTitleService {
    @Resource
    private IConditionTitleDao iConditionTitleDao;
    @Resource
    private IDataResourceDao iDataResourceDao;

    /**
     * 插入条件标签
     * @param conditionTitleModelList
     * @return
     */
    @Override
    public ItooResult insertConditionTitle(List<ConditionTitleModel> conditionTitleModelList){
        int con = 0;
        if (conditionTitleModelList!=null){
            for (ConditionTitleModel conditionTitleModel:conditionTitleModelList){
                String id = IdWorker.getIdStr();
                String titleName = conditionTitleModel.getTitleName();
                String titleField = conditionTitleModel.getTitleField();
                con = iConditionTitleDao.insertConditionTitle(id,titleField,titleName);
            }
        }
        if (con!=0){
            return ItooResult.build(ItooResult.SUCCESS,"插入成功");
        }
        return ItooResult.build(ItooResult.FAIL,"插入失败");
    }

    /**
     * 删除条件标签
     * @param ids
     * @return
     */
    @Override
    public ItooResult deleteConditionTitle(List<String> ids) {
        int con = 0;
        if (ids!=null){
            for (String id : ids)
            con = iConditionTitleDao.deleteConditionTitle(id);
        }
        if (con!=0){
            return ItooResult.build(ItooResult.SUCCESS,"删除成功");
        }
        return ItooResult.build(ItooResult.FAIL,"删除失败");
    }

    /**
     * 修改条件标签
     * @param conTitleModelList
     * @return
     */
    @Override
    public ItooResult updateConditionTitle(List<ConTitleModel> conTitleModelList){
        int con = 0;
        if (conTitleModelList!=null){
            for (ConTitleModel conTitleModel:conTitleModelList){
                String titleName = conTitleModel.getTitleName();
                String titleField = conTitleModel.getTitleField();
                String id = conTitleModel.getId();
                con = iConditionTitleDao.updateConditionTitle(titleName,titleField,id);
            }
        }
        if (con!=0){
            return ItooResult.build(ItooResult.SUCCESS,"修改成功");
        }
        return ItooResult.build(ItooResult.FAIL,"修改失败");
    }

    /**
     * 查询条件标签
     * @return
     */
    @Override
    public ItooResult selectConditionTitle(String title) {
        //查询所有未删除的条件标签的id和标签名字，进行put到map的操作
        List<ConTitleModel> titleModelList= iConditionTitleDao.selectCondition();
        List<String> table = iDataResourceDao.selectTable(title);
        List<LabelDataModel> labelDataModelList = new ArrayList<>();
        List<ConTitleModel> titleList =new ArrayList<>();
//        titleModelList.stream().filter(item -> item.getTitleField().equals(table)).collect(Collectors.toList());
        for (String t : table){
            List<ConTitleModel> conTitleModels=titleModelList.stream().filter(item -> item.getTitleField().contains(t)).collect(Collectors.toList());
            titleList.addAll(conTitleModels);
        }
        List<ConTitleModel> titleModels =  titleList.stream().distinct().collect(Collectors.toList());
        for (ConTitleModel conTitleModel:titleModels){
            String label = conTitleModel.getTitleName();
            String value= conTitleModel.getTitleField();
            LabelDataModel labelDataModel = new LabelDataModel();
            labelDataModel.setLabel(label);
            labelDataModel.setValue(value);
            labelDataModelList.add(labelDataModel);
        }
        if (CollectionUtils.isNotEmpty(labelDataModelList)){
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",labelDataModelList);
        }
        return ItooResult.build(ItooResult.FAIL,"查询失败");
    }

}
