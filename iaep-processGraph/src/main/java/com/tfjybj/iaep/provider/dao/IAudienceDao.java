package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.AudienceEntity;
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

public interface IAudienceDao extends JpaRepository<AudienceEntity,String>, JpaSpecificationExecutor<AudienceEntity> {
    @Query(value = "select * from audience where process_graph_id=? and is_delete=0",nativeQuery = true)
    List<AudienceEntity> findAudience(String processGraphId);

    @Transactional
    @Modifying
    @Query(value = "update audience set is_delete=1 where process_graph_id=?",nativeQuery = true)
    void delete(String processGraphId);
}
