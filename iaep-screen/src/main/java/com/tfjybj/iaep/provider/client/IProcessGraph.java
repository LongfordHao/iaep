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
@FeignClient(value = "iaep-processGraph")
public interface IProcessGraph {

    /*
    * 功能描述:根据数据源标签查询全部条件标签
    * [Title]
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:50
    */
    @GetMapping("/iaep-processGraph/conditionTitle/{Title}")
    ItooResult selectConditionTitle(@ApiParam(name = "Title")@PathVariable String Title);

    /*
    * 功能描述:查询全部数据源
    * []
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:53
    */
    @GetMapping("/iaep-processGraph/dataResource")
    ItooResult selectData();

    /*
    * 功能描述:查询流程画布
    * [processGraphId]
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:53
    */
    @GetMapping(value="/iaep-processGraph/processGraph/{processGraphId}")
    ItooResult queryProcessGraph(@ApiParam(name ="processGraphId")@PathVariable String processGraphId );

    /*
    * 功能描述:受众人员
    * []
    * @return
    * @author HaoLongfei
    * @since 2021/2/26 11:54
    */
    @GetMapping("/iaep-processGraph/user")
    ItooResult queryOrganizationUser();
}
