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
 * Time: 9:33
 * Description:${DESCRIPTION}
 */
@FeignClient(value = "iaep-regulation")
public interface IRegulation {

    /**
    * 功能描述:查询某条规则
    * [ruleId]
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 14:14
    */
    @GetMapping(value = "/iaep-regulation/rule/{ruleId}")
    public ItooResult queryOneRule(@ApiParam(name ="ruleId")@PathVariable String ruleId);

    /**
    * 功能描述:查询全部规则
    * []
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 14:14
    */
    @GetMapping("/iaep-regulation/rule")
    public ItooResult queryRules();
}
