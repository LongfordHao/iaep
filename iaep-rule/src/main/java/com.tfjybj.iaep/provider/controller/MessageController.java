package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.MessageEntity;
import com.tfjybj.iaep.model.MessageModel;
import com.tfjybj.iaep.model.MessageSaveModel;
import com.tfjybj.iaep.model.PMessageModel;
import com.tfjybj.iaep.provider.service.IMessageService;
import com.tfjybj.iaep.provider.service.impl.MessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 15:58
 * @Version 1.0
 * @Description
 */
@Api(tags = {"消息"})
@RequestMapping(value = "/message")
@RestController
@CrossOrigin
public class MessageController {
    @Resource
    private IMessageService iMessageService;

    @ApiOperation(value = "插入/修改消息")
    @PostMapping
    public ItooResult insertMess(@RequestBody List<MessageSaveModel> messageSaveModelList){
        return iMessageService.insert(messageSaveModelList);
    }

    @ApiOperation(value = "删除消息")
    @PutMapping
    public ItooResult deleteMess(@RequestBody  List<String> messageId){
        return iMessageService.update(messageId);
    }


}
