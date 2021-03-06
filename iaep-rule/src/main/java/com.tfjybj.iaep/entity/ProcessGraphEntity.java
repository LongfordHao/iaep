package com.tfjybj.iaep.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/9
 * Time: 18:27
 * Description:${DESCRIPTION}
 */
@Entity
@Table(name = "process_graph", schema = "iaep_dev", catalog = "")
public class ProcessGraphEntity {
    private String id;
    private String dataId;
//    private Timestamp createTime;
//    private Timestamp updateTime;
    private Date createTime;
    private Date updateTime;
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
    @Column(name = "data_id")
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }


    @Basic
    @Column(name = "create_time")
//    public Timestamp getCreateTime(Date date) {
//        return createTime;
//    }
//    public void setCreateTime(Timestamp createTime) {
//        this.createTime = createTime;
//    }
    public Date getCreateTime(Date date) {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
//    public Timestamp getUpdateTime() {
//        return updateTime;
//    }
//    public void setUpdateTime(Timestamp updateTime) {
//        this.updateTime = updateTime;
//    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessGraphEntity that = (ProcessGraphEntity) o;

        if (isDelete != that.isDelete) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dataId != null ? !dataId.equals(that.dataId) : that.dataId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dataId != null ? dataId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
