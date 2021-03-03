package com.tfjybj.iaep.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/9
 * Time: 18:27
 * Description:${DESCRIPTION}
 */
@Entity
@Table(name = "iaep_condition", schema = "iaep_dev", catalog = "")
public class ConditionEntity {
    private String id;
    private String conditionId;
    private String conditionName;
    private String conditionSelect;
    private String conditionExpress;
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
    @Column(name = "condition_id")
    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    @Basic
    @Column(name = "condition_name")
    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    @Basic
    @Column(name = "condition_select")
    public String getConditionSelect() {
        return conditionSelect;
    }

    public void setConditionSelect(String conditionSelect) {
        this.conditionSelect = conditionSelect;
    }

    @Basic
    @Column(name = "condition_express")
    public String getConditionExpress() {
        return conditionExpress;
    }

    public void setConditionExpress(String conditionExpress) {
        this.conditionExpress = conditionExpress;
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

        ConditionEntity that = (ConditionEntity) o;

        if (isDelete != that.isDelete) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (conditionId != null ? !conditionId.equals(that.conditionId) : that.conditionId != null) return false;
        if (conditionName != null ? !conditionName.equals(that.conditionName) : that.conditionName != null)
            return false;
        if (conditionSelect != null ? !conditionSelect.equals(that.conditionSelect) : that.conditionSelect != null)
            return false;
        if (conditionExpress != null ? !conditionExpress.equals(that.conditionExpress) : that.conditionExpress != null)
            return false;
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
        result = 31 * result + (conditionId != null ? conditionId.hashCode() : 0);
        result = 31 * result + (conditionName != null ? conditionName.hashCode() : 0);
        result = 31 * result + (conditionSelect != null ? conditionSelect.hashCode() : 0);
        result = 31 * result + (conditionExpress != null ? conditionExpress.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (int) isDelete;
        return result;
    }
}
