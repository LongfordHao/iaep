package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.DataResourceEntity;
import com.tfjybj.iaep.model.LabelDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/19
 * @Time 19:26
 * @Version 1.0
 * @Description
 */
@Transactional
public interface IDataResourceDao extends JpaRepository<DataResourceEntity,String>, JpaSpecificationExecutor<DataResourceEntity> {

    //查询全部的数据源，返回id和表名
    @Query(value ="select new com.tfjybj.iaep.model.LabelDataModel(dr.title,dr.title) from DataResourceEntity dr where dr.isDelete=0")
    List<LabelDataModel> selectData();

    @Query(value = "select dtc_table_name from data_resource where title =? and is_delete=0 ",nativeQuery = true)
    List<String> selectTable(String Title);
}
