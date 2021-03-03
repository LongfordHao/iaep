package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.provider.service.IDataResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation(value = "查询全部数据源")
    @GetMapping
    public ItooResult selectData(){
        return iDataResourceService.selectData();
    }
}
