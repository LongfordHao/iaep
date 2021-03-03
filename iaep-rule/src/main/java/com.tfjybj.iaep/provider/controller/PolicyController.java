package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.entity.PolicyCanEntity;
import com.tfjybj.iaep.model.ConditionIdAndName;
import com.tfjybj.iaep.model.ConditionNameModel;
import com.tfjybj.iaep.model.PolicyCanModel;

import com.tfjybj.iaep.model.PolicyModel;

import com.tfjybj.iaep.model.PolicyIdAndNameModel;

import com.tfjybj.iaep.provider.service.IPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

//import sun.net.idn.Punycode;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2020/11/14
 * Time: 8:12
 * Description:${DESCRIPTION}
 */
@Api(tags = {"策略器"})
@RequestMapping(value = "/policy")
@RestController
@CrossOrigin
//@Controller
public class PolicyController {

    @Resource
    private IPolicyService iPolicy;

    @ApiOperation(value = "策略器查询重名")
    @GetMapping(value = "/getPolicyName/{policyName}")
    public ItooResult querRule(@ApiParam(name ="policyName")@PathVariable String policyName) {
        PolicyCanEntity policyCanEntity= iPolicy.queryName(policyName);
        if (policyCanEntity!=null){
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",policyCanEntity);}
        else {
            return ItooResult.build(ItooResult.SUCCESS,"查询失败");
        }
    }
//
//    @ApiOperation(value="添加策略器，消息，条件到对应的表中")
//    @PostMapping("")
//    public ItooResult insertPolicy(@RequestBody PolicyCanModel policyCanModel){
//        return iPolicy.insertPolicy(policyCanModel);
//    }

    @ApiOperation(value="删除策略器")
    @PutMapping(value = "/delete")
    public ItooResult deletePolicy(@RequestBody List<String> policyIds){
        return iPolicy.delete(policyIds);
    }

    @ApiOperation(value="插入/修改策略器")
    @PutMapping(value = "/update")
    public ItooResult updatePolicy(@RequestBody PolicyModel policyModel){
        return iPolicy.update(policyModel);
    }
    /**查询未删除的条件id和条件名称
     * @author 孙彤
     * @return
     */
    @ApiOperation(value = "查询未删除的条件id和条件名称")
    @GetMapping("/condition")
    public ItooResult queryConditionName (){
        try{
            List<ConditionNameModel> conditionNameList = iPolicy.queryConditionName();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",conditionNameList);
        }catch(Exception e){

            return  ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }
    /**以条件id查询对应条件信息
     * @author 孙彤
     * @return
     */
    @ApiOperation(value="以条件id查询对应条件信息")
    @GetMapping("condition/{conditionId}")
    public ItooResult queryConditionAll(@ApiParam(name ="conditionId")@PathVariable String conditionId){
        try{
            List<Map<String, Object>> conditionAllList=iPolicy.queryConditionAll(conditionId);
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",conditionAllList);
        }catch(Exception e){
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }
    /**以策略器id查询对应条件id和消息id
     * @author 孙彤
     * @return
     */
    @ApiOperation(value="以策略器id查询对应条件和消息")
    @GetMapping("/{policyId}")
    public ItooResult queryConditionAndMessage(@ApiParam(name ="policyId")@PathVariable String policyId){

        try{
            List<PolicyIdAndNameModel> policyIdAndNameModels =iPolicy.queryConditionAndMessage(policyId);
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",policyIdAndNameModels);
        }catch(Exception e){
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }

    @ApiOperation(value="以流程画布id查询策略器，条件信息")
    @GetMapping("/process/{processId}")
    public ItooResult queryPolicyAndCondition(@ApiParam(name ="processId")@PathVariable String processId){

        try{
            List<Object> allres =iPolicy.queryPolicyAndCondition(processId);
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",allres);
        }catch(Exception e){
            return ItooResult.build(ItooResult.FAIL,"查询失败");
        }
    }


}
