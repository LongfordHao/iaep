package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.PolicyCanEntity;
import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.entity.RuleEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Yara
 * Version: 1.0
 * Date: 2020年11月15日
 * Time: 20:27:26
 * Description:规则接口
 */

@Transactional
public interface IRuleDao extends JpaRepository<RuleEntity,String>, JpaSpecificationExecutor<RuleEntity> {


    /**
     * 修改规则
     * @param ruleId
     * @return
     */

    @Modifying
    @Query(value = "update rule set is_delete = 1 where rule_id = ? ",nativeQuery = true)
     int updateIsDelete(String ruleId);

    /**
     * 查询全部规则列表
     * @return
     */
    @Query(value ="select * from rule where is_delete = 0",nativeQuery = true)
    List<RuleEntity> findAll();

    /**
     * 查询某条规则
     * @param ruleId
     * @return
     */
    @Query(value = "select * from rule where rule_id  = ? ",nativeQuery = true)
    RuleEntity queryOneRule(String ruleId);

}
