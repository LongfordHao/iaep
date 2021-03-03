package com.tfjybj.iaep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 孙彤
 * @Date 2020/11/1523:14
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageIdAndNameModel {
    private String messageId;
    private String touchChannel;
    private String optionalTemplate;
    private String receivePerson;
    private List<MessageReceiveModel> messageReceiveModels;
}
