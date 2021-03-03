package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.ConTitleModel;
import com.tfjybj.iaep.model.ConditionTitleModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Yara
 * @Date 2021/1/20
 * @Time 14:19
 * @Version 1.0
 * @Description
 */
@Service
public interface IConditionTitleService {
    /**
     * 插入条件标签
     * @param conditionTitleModelList
     * @return
     */
    ItooResult insertConditionTitle(List<ConditionTitleModel> conditionTitleModelList);

    /**
     * 删除条件标签
     * @param ids
     * @return
     */
    ItooResult deleteConditionTitle(List<String> ids);

    /**
     * 修改条件标签
     * @param conTitleModelList
     * @return
     */
    ItooResult updateConditionTitle(List<ConTitleModel> conTitleModelList);

    /**
     * 查询条件标签
     * @return
     */
    ItooResult selectConditionTitle(String Title);
}
