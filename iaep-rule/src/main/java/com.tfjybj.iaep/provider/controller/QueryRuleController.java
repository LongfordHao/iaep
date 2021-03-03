package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.provider.service.IQueryRule;
import com.tfjybj.iaep.provider.service.IRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jeff
 * @description ${description}
 * @date 2021/1/26 11:51
 */
@Api(tags = {"查询所有规则内容"})
@RequestMapping(value = "/queryRuleContent")
@RestController
@CrossOrigin
public class QueryRuleController {

    @Resource
    private IQueryRule iQueryRule;

    @ApiOperation(value = "查询规则全部内容")
    @GetMapping(value = "/{ruleId}")
    public ItooResult queryOneRule(@ApiParam(name ="ruleId")@PathVariable String ruleId){
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",iQueryRule.QueryAllRuleContent(ruleId));
    }
}
