package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.ProcessGraphModel;
import com.tfjybj.iaep.provider.service.IProcessGraphService;
import com.tfjybj.iaep.provider.service.IPushMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/1/26
 * Time: 8:42
 * Description:${DESCRIPTION}
 */
@Api(tags = {"推送消息"})
@RequestMapping(value = "/UniversalController")
@RestController
@CrossOrigin
public class PushMessageController {

    @Resource
    private IPushMessage pushMessage;

    @ApiOperation(value = "推送消息")
    @GetMapping (value="/PushMessageController/{dataName}/{beganTime}/{endTime}")
    public ItooResult insertProcessGraph(@ApiParam(name ="dataName")@PathVariable String dataName,@ApiParam(name ="beganTime")@PathVariable String beganTime,@ApiParam(name ="endTime")@PathVariable String endTime) {
        try{
            //调取接口
            pushMessage.pushMessage(dataName,beganTime,endTime);
            return ItooResult.build(ItooResult.SUCCESS,"查询成功");
        }catch (Exception e){

            return ItooResult.build(ItooResult.SUCCESS,"查询失败");
        }
    }

}
