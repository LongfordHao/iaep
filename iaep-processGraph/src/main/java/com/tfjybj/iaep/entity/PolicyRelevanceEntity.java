package com.tfjybj.iaep.entity;

import javax.persistence.*;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/9
 * Time: 18:27
 * Description:${DESCRIPTION}
 */
@Entity
@Table(name = "policy_relevance", schema = "iaep_dev", catalog = "")
public class PolicyRelevanceEntity {
    private String id;
    private String serialNumber;
    private String hierarchy;
    private String processGraphId;
    private String policyCanId;
    private String createTime;
    private String updateTime;
    private String remark;
    private String operator;
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
    @Column(name = "serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "hierarchy")
    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Basic
    @Column(name = "process_graph_id")
    public String getProcessGraphId() {
        return processGraphId;
    }

    public void setProcessGraphId(String processGraphId) {
        this.processGraphId = processGraphId;
    }

    @Basic
    @Column(name = "policy_can_id")
    public String getPolicyCanId() {
        return policyCanId;
    }

    public void setPolicyCanId(String policyCanId) {
        this.policyCanId = policyCanId;
    }

    @Basic
    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

        PolicyRelevanceEntity that = (PolicyRelevanceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;
        if (hierarchy != null ? !hierarchy.equals(that.hierarchy) : that.hierarchy != null) return false;
        if (processGraphId != null ? !processGraphId.equals(that.processGraphId) : that.processGraphId != null)
            return false;
        if (policyCanId != null ? !policyCanId.equals(that.policyCanId) : that.policyCanId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (hierarchy != null ? hierarchy.hashCode() : 0);
        result = 31 * result + (processGraphId != null ? processGraphId.hashCode() : 0);
        result = 31 * result + (policyCanId != null ? policyCanId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }
}
