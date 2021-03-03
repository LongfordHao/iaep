package com.tfjybj.iaep.provider.client;

import com.dmsdbj.itoo.tool.business.ItooResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/2/25
 * Time: 9:32
 * Description:${DESCRIPTION}
 */
@FeignClient(name = "iaep-policy")
public interface IPolicy {

    /*
    * 功能描述:受众查询
    * []
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:46
    */
    @GetMapping("/user")
    ItooResult user();

    /*
    * 功能描述:以策略器id查询对应条件和消息
    * [policyId]
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:45
    */
    @GetMapping("/policy/{policyId}")
    ItooResult queryConditionAndMessage(@ApiParam(name ="policyId")@PathVariable String policyId);

    /*
    * 功能描述:以流程画布id查询策略器，条件信息
    * [processId]
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:47
    */
    @GetMapping("/policy/process/{processId}")
    ItooResult queryPolicyAndCondition(@ApiParam(name ="processId")@PathVariable String processId);

}
