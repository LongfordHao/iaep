package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.AllDataModel;
import com.tfjybj.iaep.model.DataModel;
import com.tfjybj.iaep.provider.service.IDataResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/18
 * @Time 20:57
 * @Version 1.0
 * @Description
 */
@Api(tags = {"数据源"})
@RequestMapping(value = "/dataResource")
@RestController
@CrossOrigin
public class DataResourceController {

    @Resource
    private IDataResourceService iDataResourceService;

    @ApiOperation(value = "插入数据源")
    @PostMapping
    public ItooResult insertData(@RequestBody AllDataModel allDataModel){
        return iDataResourceService.insertData(allDataModel);
    }

    @ApiOperation(value = "删除数据源")
    @PutMapping("/delete")
    public ItooResult deleteData(@RequestBody List<String> ids){
        return iDataResourceService.deleteData(ids);
    }

    @ApiOperation(value = "修改数据源")
    @PutMapping("/update")
    public ItooResult updateData(@RequestBody List<DataModel> dataModelList){
        return iDataResourceService.updateData(dataModelList);
    }

    @ApiOperation(value = "查询全部数据源")
    @GetMapping
    public ItooResult selectData(){
        return iDataResourceService.selectData();
    }
}
