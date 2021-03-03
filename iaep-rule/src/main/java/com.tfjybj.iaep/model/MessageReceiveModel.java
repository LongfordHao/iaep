package com.tfjybj.iaep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Yara
 * @Date 2021/1/24
 * @Time 9:51
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageReceiveModel {
    private String receivePerson;
    private String messageDingId;
    private String organizationId;
}
