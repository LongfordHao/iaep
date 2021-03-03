package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.AllDataModel;
import com.tfjybj.iaep.model.ConTitleModel;
import com.tfjybj.iaep.model.ConditionTitleModel;
import com.tfjybj.iaep.provider.dao.IConditionTitleDao;
import com.tfjybj.iaep.provider.service.IConditionTitleService;
import com.tfjybj.iaep.provider.service.IDataResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation(value = "添加条件标签")
    @PostMapping
    public ItooResult insertConditionTitle(@RequestBody List<ConditionTitleModel> conditionTitleModelList){
        return iConditionTitleService.insertConditionTitle(conditionTitleModelList);
    }

    @ApiOperation(value = "删除条件标签")
    @PutMapping("/delete")
    public ItooResult deleteConditionTitle(@RequestBody List<String> ids){
        return iConditionTitleService.deleteConditionTitle(ids);
    }

    @ApiOperation(value = "修改条件标签")
    @PutMapping("/updata")
    public ItooResult updateConditionTitle(@RequestBody List<ConTitleModel> conTitleModelList){
        return iConditionTitleService.updateConditionTitle(conTitleModelList);
    }

    @ApiOperation(value = "根据数据源标签查询全部条件标签")
    @GetMapping("/{Title}")
    public ItooResult selectConditionTitle(@ApiParam(name = "Title")@PathVariable String Title){
        return iConditionTitleService.selectConditionTitle(Title);
    }

}
