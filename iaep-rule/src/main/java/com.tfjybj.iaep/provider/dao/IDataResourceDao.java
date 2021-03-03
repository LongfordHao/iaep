package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.DataResourceEntity;
import com.tfjybj.iaep.model.DataModel;
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
    //传入id，表名以及标签，进行插入操作
    @Modifying
    @Query(value = "insert into data_resource(id,dtc_table_name,title) values(?1,?2,?3)",nativeQuery = true)
    int insertDataResource (String id,String dtcTableName,String title);

    //根据id进行删除操作
    @Modifying
    @Query(value = "update data_resource set is_delete = 1 where id = ?",nativeQuery = true)
    int deleteDataResource(String id);

    //根据id查询表名
    @Query(value = "select dtc_table_name from data_resource where id = ? and is_delete=0",nativeQuery = true)
    String selectTableName(String id);

    //查询全部的数据源，返回id和表名
    @Query(value ="select new com.tfjybj.iaep.model.LabelDataModel(dr.title,dr.title) from DataResourceEntity dr where dr.isDelete=0")
    List<LabelDataModel> selectData();

    @Modifying
    @Query(value = "update data_resource set title = ?2,dtc_table_name =?3 where id = ?1",nativeQuery = true)
    int updateData(String id, String title, String newTableName);

    @Query(value = "select dtc_table_name from data_resource where title =? and is_delete=0 ",nativeQuery = true)
    List<String> selectTable(String Title);
}
