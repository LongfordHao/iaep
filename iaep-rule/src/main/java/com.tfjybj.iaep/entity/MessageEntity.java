package com.tfjybj.iaep.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/20
 * Time: 10:12
 * Description:${DESCRIPTION}
 */
@Entity
@Table(name = "message", schema = "iaep_dev", catalog = "")
public class MessageEntity {
    private String id;
    private String messageId;
    private String touchChannel;
    private String optionalTemplate;
    private String receivePerson;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String creator;
    private String operator;
    private String remark;
    private Byte isDelete;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "touch_channel")
    public String getTouchChannel() {
        return touchChannel;
    }

    public void setTouchChannel(String touchChannel) {
        this.touchChannel = touchChannel;
    }

    @Basic
    @Column(name = "optional_template")
    public String getOptionalTemplate() {
        return optionalTemplate;
    }

    public void setOptionalTemplate(String optionalTemplate) {
        this.optionalTemplate = optionalTemplate;
    }

    @Basic
    @Column(name = "receive_person")
    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

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
    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        MessageEntity that = (MessageEntity) o;
//
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
//        if (messageName != null ? !messageName.equals(that.messageName) : that.messageName != null) return false;
//        if (messageExpress != null ? !messageExpress.equals(that.messageExpress) : that.messageExpress != null)
//            return false;
//        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
//        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
//        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
//        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
//        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
//        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
//        result = 31 * result + (messageName != null ? messageName.hashCode() : 0);
//        result = 31 * result + (messageExpress != null ? messageExpress.hashCode() : 0);
//        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
//        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
//        result = 31 * result + (creator != null ? creator.hashCode() : 0);
//        result = 31 * result + (operator != null ? operator.hashCode() : 0);
//        result = 31 * result + (remark != null ? remark.hashCode() : 0);
//        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
//        return result;
//    }
}
