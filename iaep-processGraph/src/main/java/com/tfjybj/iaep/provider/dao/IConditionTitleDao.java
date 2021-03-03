package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.ConditionTitleEntity;
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

    @Query(value = "select new com.tfjybj.iaep.model.ConTitleModel(ct.id,ct.titleName,ct.titleField) from ConditionTitleEntity ct where ct.isDelete=0")
    List<ConTitleModel> selectCondition();

}
