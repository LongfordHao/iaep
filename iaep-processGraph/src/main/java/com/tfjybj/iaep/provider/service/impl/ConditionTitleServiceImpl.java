package com.tfjybj.iaep.provider.service.impl;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.ConTitleModel;
import com.tfjybj.iaep.model.LabelDataModel;
import com.tfjybj.iaep.provider.dao.IConditionTitleDao;
import com.tfjybj.iaep.provider.dao.IDataResourceDao;
import com.tfjybj.iaep.provider.service.IConditionTitleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
