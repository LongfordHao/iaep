package com.tfjybj.iaep.provider.controller;


import com.dmsdbj.itoo.tool.business.ItooResult;
//import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.entity.RuleEntity;
import com.tfjybj.iaep.model.ProcessGraphModel;
import com.tfjybj.iaep.provider.service.IProcessGraphService;
import com.tfjybj.iaep.provider.service.IRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

@Api(tags = {"流程画布"})
@RequestMapping(value = "/processGraph")
@RestController
@CrossOrigin
public class ProcessGraphController {

    @Resource
    private IProcessGraphService processGraphService;

    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 插入流程画布
     * @params: ProcessGraphModel
     * @return: ItooResult
     * @Date: 2020年11月17日15:46:36
     * @Modified By:
    */
    @ApiOperation(value = "插入流程画布")
    @PostMapping(value="")
    public ItooResult insertProcessGraph(@RequestBody ProcessGraphModel insertModel) {
        try{
            String processGraphId=processGraphService.save(insertModel);
            return ItooResult.build(ItooResult.SUCCESS,"添加成功",processGraphId);
        }catch (Exception e){
            String processGraphId=processGraphService.save(insertModel);
            return ItooResult.build(ItooResult.SUCCESS,"添加失败");
        }
    }

    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 删除流程画布
     * @params: ProcessGraphModel
     * @return: ItooResult
     * @Date: 2020年11月17日15:46:36
     * @Modified By:
     */
    @ApiOperation(value = "删除流程画布")
    @PutMapping(value="/delete/{processGraphId}")
    public ItooResult deleteProcessGraph(@ApiParam(name ="processGraphId")@PathVariable String processGraphId) {
        try {
            processGraphService.delete(processGraphId);
            return ItooResult.build(ItooResult.SUCCESS, "删除成功");
        }catch (Exception e){
            return ItooResult.build(ItooResult.FAIL,"删除失败");
        }
    }

    /**
     * @Author Sarah-hjf
     * @DESCRIPTION: 根据流程画布查询流程画布所有信息
     * @params: ProcessGraphModel
     * @return: ItooResult
     * @Date: 2020年11月17日15:46:36
     * @Modified By:
     */
    @ApiOperation(value = "查询流程画布")
    @GetMapping(value="/{processGraphId}")
public ItooResult queryProcessGraph(@ApiParam(name ="processGraphId")@PathVariable String processGraphId ) {
        try {
            ProcessGraphModel processGraphModel = processGraphService.findAllByproGraphId(processGraphId);
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", processGraphModel);
        }catch (Exception e){
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
}
}