package com.tfjybj.iaep.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.policy.PolicyIdAndNameModel;
import com.tfjybj.iaep.model.policy.PolicyModel;
import com.tfjybj.iaep.provider.service.IPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//import sun.net.idn.Punycode;

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
