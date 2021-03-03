package com.tfjybj.iaep.provider.dao;

import com.tfjybj.iaep.entity.MessageReceiveEntity;
import com.tfjybj.iaep.model.MessageReceiveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/24
 * @Time 19:57
 * @Version 1.0
 * @Description
 */
@Transactional
public interface IMessageReceiveDao extends JpaRepository<MessageReceiveEntity,String>, JpaSpecificationExecutor<MessageReceiveEntity> {
    @Modifying
    @Query(value = "update message_receive set is_delete=1 where user_group_id=?",nativeQuery = true)
    void deleteReceive(String receivePerson);

    @Query(value = "select new com.tfjybj.iaep.model.MessageReceiveModel(mr.receivePerson,mr.messageDingId,mr.organizationId) from MessageReceiveEntity mr where mr.userGroupId=?1")
    List<MessageReceiveModel> selectGroup(String receivePerson);

//    @Modifying
//    @Query(value = "insert into message_receive(id,organization_id,receive_person,message_ding_id,user_group_id) values(?1,?2,?3,?4,?5)",nativeQuery = true)
//    int insertPerson(String id,String organizationId,String person,String dingId,String userGroupId);
}
