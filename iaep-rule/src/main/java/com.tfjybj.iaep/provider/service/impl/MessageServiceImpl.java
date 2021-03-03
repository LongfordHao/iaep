package com.tfjybj.iaep.provider.service.impl;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.MessageEntity;
import com.tfjybj.iaep.entity.MessageReceiveEntity;
import com.tfjybj.iaep.model.MessageModel;
import com.tfjybj.iaep.model.MessageReceiveModel;
import com.tfjybj.iaep.model.MessageSaveModel;
import com.tfjybj.iaep.model.PMessageModel;
import com.tfjybj.iaep.provider.dao.IMessageDao;
import com.tfjybj.iaep.provider.dao.IMessageReceiveDao;
import com.tfjybj.iaep.provider.service.IMessageService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 16:02
 * @Version 1.0
 * @Description
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Resource
    private IMessageDao iMessageDao;

    @Resource
    private IMessageReceiveDao iMessageReceiveDao;

    /**
     * 插入(修改)消息
     *
     * @param messageSaveModelList
     * @return
     */
    @Override
    public ItooResult insert(List<MessageSaveModel> messageSaveModelList) {
        try {
            List<String> ids = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(messageSaveModelList)) {
                for (MessageSaveModel messageSaveModel : messageSaveModelList) {
                    //判断是否获取到了messageId,如果未获取到，则进行插入操作。
                    if (StringUtils.isBlank(messageSaveModel.getMessageId())) {
                        MessageEntity messageEntity = new MessageEntity();
                        //对message进行插入操作
                        String receivePerson = IdWorker.getIdStr();
                        messageEntity.setReceivePerson(receivePerson);
                        messageEntity.setOptionalTemplate(messageSaveModel.getOptionalTemplate());
                        messageEntity.setTouchChannel(messageSaveModel.getTouchChannel());
                        messageEntity.setId(IdWorker.getIdStr());
                        messageEntity.setIsDelete((byte)0);
                        messageEntity.setMessageId(IdWorker.getIdStr());
                        iMessageDao.save(messageEntity);
                        //将消息id和接收人用户组id对应的存储到map中，并返回给前端
                        ids.add(messageEntity.getMessageId());
                        //拿到所有接收人的集合,对receiveMessage进行插入操作
                        List<MessageReceiveModel> messageReceiveModelList = messageSaveModel.getMessageReceiveModelList();
                        for (MessageReceiveModel messageReceiveModel : messageReceiveModelList) {
                            MessageReceiveEntity messageReceiveEntity = new MessageReceiveEntity();
                            messageReceiveEntity.setMessageDingId(messageReceiveModel.getMessageDingId());
                            messageReceiveEntity.setReceivePerson(messageReceiveModel.getReceivePerson());
                            messageReceiveEntity.setOrganizationId(messageReceiveModel.getOrganizationId());
                            messageReceiveEntity.setUserGroupId(receivePerson);
                            messageReceiveEntity.setId(IdWorker.getIdStr());
                            iMessageReceiveDao.save(messageReceiveEntity);
                        }
                    } else {
                        //如果messageId存在，则表示进行更新操作，以下操作对message进行更新
                        String messId = messageSaveModel.getMessageId();
                        String touchChannel = messageSaveModel.getTouchChannel();
                        String optional = messageSaveModel.getOptionalTemplate();
                        String receive = IdWorker.getIdStr();
                        iMessageDao.updateMessage(messId, touchChannel, receive,optional);
                        //将更新后的messageId和对应的接收人用户组id存到map中，并返回给前端
                        ids.add(messId);
                        iMessageReceiveDao.deleteReceive(messageSaveModel.getReceivePerson());
                        //拿到所有接收人的集合,进行插入操作
                        List<MessageReceiveModel> messageReceiveModels = messageSaveModel.getMessageReceiveModelList();
                        for (MessageReceiveModel messageReceiveModel : messageReceiveModels) {
                            MessageReceiveEntity messageReceiveEntity = new MessageReceiveEntity();
                            messageReceiveEntity.setMessageDingId(messageReceiveModel.getMessageDingId());
                            messageReceiveEntity.setReceivePerson(messageReceiveModel.getReceivePerson());
                            messageReceiveEntity.setOrganizationId(messageReceiveModel.getOrganizationId());
                            messageReceiveEntity.setUserGroupId(receive);
                            messageReceiveEntity.setId(IdWorker.getIdStr());
                            iMessageReceiveDao.save(messageReceiveEntity);
                        }
                    }
                }
            }
         if (CollectionUtils.isNotEmpty(ids)){
             return ItooResult.build(ItooResult.SUCCESS,"操作成功",ids);
         }
         return ItooResult.build(ItooResult.FAIL,"操作失败");
        }
        catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"操作失败");
        }
    }

    /**
     * 删除消息
     * @param messageId
     * @return
     */
    @Override
    public ItooResult update(List<String> messageId) {
        try{
            if (CollectionUtils.isEmpty(messageId)){
                return null;
            }
            //调用删除消息的接口删除消息
            for (String messId: messageId) {
                if (StringUtils.isNotBlank(messId)) {
                    int num =iMessageDao.update(messId);
                    if (num!=0) {
                        iMessageDao.updatePolicyCan(messId);
                    }
                }
            }
            return ItooResult.build(ItooResult.SUCCESS, "删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ItooResult.build(ItooResult.FAIL,"删除失败");
        }
    }
}
