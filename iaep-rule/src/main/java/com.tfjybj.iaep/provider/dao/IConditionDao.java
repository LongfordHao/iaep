package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.ConditionEntity;
import com.tfjybj.iaep.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 17:15
 * @Version 1.0
 * @Description
 */
@Transactional
public interface IConditionDao extends JpaRepository<ConditionEntity,String>, JpaSpecificationExecutor<ConditionEntity> {


    @Modifying
    @Query(value = "update iaep_condition set is_delete = 1 where condition_id =?",nativeQuery = true)
    int deleteCon(String conditionId);



    /**
     * 查询condition_id对应的主键id
     * @param conId
     * @return
     */
    @Query(value = "select id from iaep_condition where is_delete = 0 and condition_id = ?",nativeQuery = true)
    String queryId(String conId);

    @Modifying
    @Query(value = "update policy_can set is_delete=1 where condition_id = ?",nativeQuery = true)
    void deletePolicyCan(String conditionId);
}
