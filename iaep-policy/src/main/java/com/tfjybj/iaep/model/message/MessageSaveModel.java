package com.tfjybj.iaep.model.message;

import lombok.Data;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/24
 * @Time 9:49
 * @Version 1.0
 * @Description
 */
@Data
public class MessageSaveModel {
    private String messageId;
    private String touchChannel;
    private String optionalTemplate;
    private String receivePerson;
    private List<MessageReceiveModel> messageReceiveModelList;
}
