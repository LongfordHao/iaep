package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.MessageEntity;
import com.tfjybj.iaep.model.MessageModel;
import com.tfjybj.iaep.model.MessageSaveModel;
import com.tfjybj.iaep.model.PMessageModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 16:03
 * @Version 1.0
 * @Description
 */
@Service
public interface IMessageService {
    /**
     * 插入消息
     * @param messageSaveModelList
     * @return
     */
    ItooResult insert(List<MessageSaveModel> messageSaveModelList);

    /**
     * 删除消息
     * @param messageId
     * @return
     */
    ItooResult update(List<String> messageId);
}
