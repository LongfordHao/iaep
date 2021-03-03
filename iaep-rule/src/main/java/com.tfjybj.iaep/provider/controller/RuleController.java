package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.RuleEntity;
import com.tfjybj.iaep.model.RuleAllModel;
import com.tfjybj.iaep.model.RuleModel;
import com.tfjybj.iaep.provider.service.IRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: Yara
 * Version: 1.0
 * Date: 2020年11月15日
 * Time: 20:27:26
 * Description:规则接口
 */

@Api(tags = {"规则"})
@RequestMapping(value = "/rule")
@RestController
@CrossOrigin
public class RuleController {

    @Resource
    private IRuleService ruleService;

    @ApiOperation(value = "添加规则")
    @PostMapping
    public ItooResult insertRule(@RequestBody RuleModel ruleModel) {
        return ruleService.save(ruleModel);
    }

    @ApiOperation(value="删除规则")
    @PutMapping(value = "/delete/{ruleId}")
    public ItooResult deleteRule(@ApiParam(name ="ruleId")@PathVariable String ruleId){
        return ruleService.delete(ruleId);
    }

    @ApiOperation(value="修改规则")
    @PutMapping(value = "/update")
    public ItooResult updateRule(@RequestBody RuleAllModel ruleAllModel){
        return ruleService.update(ruleAllModel);
    }


    @ApiOperation(value = "查询某条规则")
    @GetMapping(value = "/{ruleId}")
    public ItooResult queryOneRule(@ApiParam(name ="ruleId")@PathVariable String ruleId){
        return ruleService.queryRule(ruleId);
    }

    @ApiOperation(value = "查询全部规则")
    @GetMapping
    public ItooResult queryRules(){
        return ruleService.queryAllRules();
    }
}
