package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.ConditionEntity;
import com.tfjybj.iaep.model.ConditionModel;
import com.tfjybj.iaep.model.PConditionModel;
import com.tfjybj.iaep.provider.service.IConditionService;
import com.tfjybj.iaep.provider.service.impl.ConditionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 17:12
 * @Version 1.0
 * @Description
 */
@Api(tags = {"条件"})
@RequestMapping(value = "/condition")
@RestController
@CrossOrigin
public class ConditionController {
    @Resource
    private IConditionService iConditionService;

    @ApiOperation(value = "插入/修改条件")
    @PostMapping
    public ItooResult insertCon(@RequestBody List<PConditionModel> conditionModel)
    {
        return iConditionService.insert(conditionModel);
    }

    @ApiOperation(value = "删除条件")
    @PutMapping
    public ItooResult deleteCon(@RequestBody List<String> conditionId)
    {
        return iConditionService.delete(conditionId);
    }

}
