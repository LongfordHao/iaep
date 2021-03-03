package com.tfjybj.iaep.model.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Yara
 * @Date 2021/1/26
 * @Time 20:35
 * @Version 1.0
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageIdNameModel {
    private String messageId;
    private String touchChannel;
    private String optionalTemplate;
    private String receivePerson;
}
