package com.tfjybj.iaep.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Yara
 * @Date 2021/1/24
 * @Time 15:38
 * @Version 1.0
 * @Description
 */
@Entity
@Table(name = "message_receive", schema = "iaep_dev", catalog = "")
public class MessageReceiveEntity {
    private String id;
    private String organizationId;
    private String receivePerson;
    private String messageDingId;
    private String userGroupId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String creator;
    private String operator;
    private String remark;
    private byte isDelete;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "organization_id")
    public String getOrganizationId(){ return organizationId;}

    public void setOrganizationId(String organizationId){this.organizationId=organizationId;}

    @Basic
    @Column(name = "user_group_id")
    public String getUserGroupId(){return userGroupId;}
    public void setUserGroupId(String userGroupId){this.userGroupId=userGroupId;}

    @Basic
    @Column(name = "receive_person")
    public String getReceivePerson(){return receivePerson;}
    public void setReceivePerson(String receivePerson){this.receivePerson=receivePerson;}

    @Basic
    @Column(name = "message_ding_id")
    public String getMessageDingId(){return messageDingId;}
    public void setMessageDingId(String messageDingId){this.messageDingId=messageDingId;}

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "is_delete")
    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

}
