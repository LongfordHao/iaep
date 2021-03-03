package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.ConditionTitleEntity;
import com.tfjybj.iaep.entity.DataResourceEntity;
import com.tfjybj.iaep.model.ConTitleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/20
 * @Time 10:27
 * @Version 1.0
 * @Description
 */
@Transactional
public interface IConditionTitleDao  extends JpaRepository<ConditionTitleEntity,String>, JpaSpecificationExecutor<ConditionTitleEntity> {
    @Modifying
    @Query(value = "insert into condition_title(id,title_field,title_name) values(?1,?2,?3)",nativeQuery = true)
    int insertConditionTitle(String id,String titleField,String titleName);

    @Modifying
    @Query(value = "update condition_title set is_delete = 1 where id = ?",nativeQuery = true)
    int deleteConditionTitle(String id);

    @Query(value = "select id from condition_title where title_field like ?% ",nativeQuery = true)
    List<String> selectConditionId(String tableName);

    @Modifying
    @Query(value = "update condition_title set title_name = ?1,title_field = ?2 where id = ?3",nativeQuery = true)
    int updateConditionTitle(String titleName, String titleField, String id);

    @Modifying
    @Query(value = "update condition_title set title_field = replace(title_field,?1,?2) where title_field like ?1 '%' ",nativeQuery = true)
    int updateTableTitle(String dtcTableName,String newTableName);

    @Query(value = "select new com.tfjybj.iaep.model.ConTitleModel(ct.id,ct.titleName,ct.titleField) from ConditionTitleEntity ct where ct.isDelete=0")
    List<ConTitleModel> selectCondition();

}
