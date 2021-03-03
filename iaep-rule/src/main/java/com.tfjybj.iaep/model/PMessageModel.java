package com.tfjybj.iaep.model;

import lombok.Data;

import javax.persistence.Column;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 9:11
 * @Version 1.0
 * @Description
 */
@Data
public class PMessageModel {
    private String messageId;
    private String messageName;
    private String messageExpress;
}
