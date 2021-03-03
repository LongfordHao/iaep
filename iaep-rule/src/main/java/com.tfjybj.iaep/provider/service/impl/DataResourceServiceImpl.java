package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.*;
import com.tfjybj.iaep.provider.dao.IConditionTitleDao;
import com.tfjybj.iaep.provider.dao.IDataResourceDao;
import com.tfjybj.iaep.provider.service.IDataResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Yara
 * @Date 2021/1/19
 * @Time 19:44
 * @Version 1.0
 * @Description
 */
@Service
public class DataResourceServiceImpl implements IDataResourceService {

    @Resource
    private IDataResourceDao iDataResourceDao;
    @Resource
    private IConditionTitleDao iConditionTitleDao;

    /**
     * 插入数据源（插入表名和表字段）
     * @param allDataModel
     * @return
     */
    @Override
    public ItooResult insertData(AllDataModel allDataModel) {
        List<ConditionTitleModel> conditionTitleModels = allDataModel.getConditionTitleModelList();
        List<DataResourceModel> dataResourceModels = allDataModel.getDataResourceModelList();
        int data = 0;
        int condition = 0;
        if (conditionTitleModels!=null && dataResourceModels!=null) {
            //遍历数据源的表名以及表标签，进行插入操作
            for (DataResourceModel dataResourceModel : dataResourceModels) {
                String id = IdWorker.getIdStr();
                String dtcTableName = dataResourceModel.getDtcTableName();
                String title = dataResourceModel.getTitle();
                data = iDataResourceDao.insertDataResource(id, dtcTableName, title);
            }
            //遍历条件标签的字段名以及字段标签，进行插入操作
            for (ConditionTitleModel conditionTitleModel : conditionTitleModels) {
                String id = IdWorker.getIdStr();
                String titleField = conditionTitleModel.getTitleField();
                String titleName = conditionTitleModel.getTitleName();
                condition = iConditionTitleDao.insertConditionTitle(id, titleField, titleName);
            }
        }
        //如果影响的数据为0，则判定插入失败，如果影响的数据不为0，则判定插入成功
        if (data!=0 || condition !=0){
            return ItooResult.build(ItooResult.SUCCESS,"插入成功");
        }
        return ItooResult.build(ItooResult.FAIL,"插入失败");
    }

    /**
     * 删除数据源
     * @param ids
     * @return
     */
    @Override
    public ItooResult deleteData(List<String> ids) {
        int data = 0;
        if (ids!=null){
            for (String id:ids){
                //根据id查询数据源中的表名
                String tableName = iDataResourceDao.selectTableName(id);
                //根据表名模糊匹配‘表字段’查询到条件标签表中的所有主键id
                List<String> conId = iConditionTitleDao.selectConditionId(tableName);
                //如果id不为null
                if (conId!=null){
                    //将查到的id都进行删除操作
                    for (String conditionId:conId){
                        iConditionTitleDao.deleteConditionTitle(conditionId);
                    }
                }
                //对数据源中的数据进行删除
                data = iDataResourceDao.deleteDataResource(id);
            }
        }
        if (data!= 0){
            return ItooResult.build(ItooResult.SUCCESS,"删除成功");
        }
        return ItooResult.build(ItooResult.FAIL,"删除失败");
    }

    /**
     * 查询全部数据源
     * @return
     */
    @Override
    public ItooResult selectData() {
        List<LabelDataModel> dataModelList = iDataResourceDao.selectData();
        List<LabelDataModel> dataModels = dataModelList.stream().distinct().collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(dataModels)){
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",dataModels);
        }
        return ItooResult.build(ItooResult.FAIL,"查询失败");
    }

    /**
     * 修改数据源
     * @param dataModelList
     * @return
     */
    @Override
    public ItooResult updateData(List<DataModel> dataModelList) {
        int data = 0;
        if (CollectionUtils.isNotEmpty(dataModelList)){
            for (DataModel dataModel:dataModelList){
                //根据数据源id获取数据表名
                String dtcTableName = iDataResourceDao.selectTableName(dataModel.getId());
                String newTableName = dataModel.getDtcTableName();
                //判断表名是否有修改，若没有修改则无需对条件标签表进行操作，如果表名有改变，则需要修改条件标签表中的tableTitle字段
                if (dtcTableName.equals(newTableName)){
                    //传入新旧两个表名，对一个字段中的部分值进行替换操作
                    iConditionTitleDao.updateTableTitle(dtcTableName,newTableName);
                }
                String id = dataModel.getId();
                String title = dataModel.getTitle();
                data = iDataResourceDao.updateData(id,title,newTableName);
            }
        }
        if (data!=0){
            return ItooResult.build(ItooResult.SUCCESS,"修改成功");
        }
        return ItooResult.build(ItooResult.FAIL,"修改失败");
    }
}
