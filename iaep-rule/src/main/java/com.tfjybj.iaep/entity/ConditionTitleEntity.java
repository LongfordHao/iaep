package com.tfjybj.iaep.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Yara
 * @Date 2021/1/21
 * @Time 13:02
 * @Version 1.0
 * @Description
 */
@Entity
@Table(name = "condition_title", schema = "iaep_dev", catalog = "")
public class ConditionTitleEntity {
    private String id;
    private String titleField;
    private String titleName;
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
    @Column(name = "title_field")
    public String getTitleField() {return titleField;}

    public void setTitleField(String titleField){this.titleField = titleField;}

    @Basic
    @Column(name = "title_name")
    public String getTitleName(){return titleName;}
    public void setTitleName(String titleName){this.titleName=titleName;}

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
}
