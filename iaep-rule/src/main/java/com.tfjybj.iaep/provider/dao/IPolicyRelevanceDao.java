package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.PolicyRelevanceEntity;
import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.model.PolicyrRelevanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

public interface IPolicyRelevanceDao extends JpaRepository<PolicyRelevanceEntity,String>, JpaSpecificationExecutor<PolicyRelevanceEntity> {
    @Query(value = "select *  from policy_relevance where process_graph_id=? and is_delete=0 ",nativeQuery = true)
    List<PolicyRelevanceEntity> findPolicyRelevance(String processGraphId);

    @Transactional
    @Modifying
    @Query(value = "update policy_relevance set is_delete=1 where process_graph_id=?",nativeQuery = true)
    void delete(String processGraphId);
}
