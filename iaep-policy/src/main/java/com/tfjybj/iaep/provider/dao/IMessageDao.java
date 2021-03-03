package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 16:00
 * @Version 1.0
 * @Description
 */

@Transactional
public interface IMessageDao  extends JpaRepository<MessageEntity,String>, JpaSpecificationExecutor<MessageEntity> {

    @Modifying
    @Query(value = "update message set is_delete = 1 where message_id = ?",nativeQuery = true)
    int update(String messageId);

    @Query(value = "select id from message where  is_delete = 0 and message_id = ?",nativeQuery = true)
    String queryId(String messId);

    @Modifying
    @Query(value = "update policy_can set is_delete=1 where message_id = ?",nativeQuery = true)
    void updatePolicyCan(String messageId);

    @Modifying
    @Query(value = "update message set touch_channel = ?2,receive_person=?3,optional_template = ?4 where message_id =?1",nativeQuery = true)
    int updateMessage(String messId, String touchChannel, String receivePerson,String optional);
}
