package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.entity.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

public interface IProcessGraphDao extends JpaRepository<ProcessGraphEntity,String>, JpaSpecificationExecutor<ProcessGraphEntity> {
    @Query(value = "select * from process_graph where id=? and is_delete=0",nativeQuery = true)
    ProcessGraphEntity queryById(String processGraphId);

    @Transactional
    @Modifying
    @Query(value = "update process_graph set is_delete=1 where id=?",nativeQuery = true)
     void delete(String processGraphId);
}
