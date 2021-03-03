package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.condition.PConditionModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Yara
 * @Date 2020/11/16
 * @Time 18:18
 * @Version 1.0
 * @Description
 */

@Service
public interface IConditionService {
    /**
     * 插入条件
     * @author Yara
     * @param conditionModel
     * @return
     */
    ItooResult insert(List<PConditionModel> conditionModel);

    /**
     * 删除条件
     * @author Yara
     * @param conditionId
     * @return
     */
    ItooResult delete(List<String> conditionId);

}
