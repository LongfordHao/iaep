package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.provider.service.IConditionTitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Yara
 * @Date 2021/1/20
 * @Time 13:57
 * @Version 1.0
 * @Description
 */
@Api(tags = {"条件标签"})
@RequestMapping(value = "/conditionTitle")
@RestController
@CrossOrigin
public class ConditionTitleController {
    @Resource
    private IConditionTitleService iConditionTitleService;

    @ApiOperation(value = "根据数据源标签查询全部条件标签")
    @GetMapping("/{Title}")
    public ItooResult selectConditionTitle(@ApiParam(name = "Title")@PathVariable String Title){
        return iConditionTitleService.selectConditionTitle(Title);
    }

}
