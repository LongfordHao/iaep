package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.RuleAllModel;
import com.tfjybj.iaep.model.RuleModel;
import org.springframework.stereotype.Service;

/**
 * Author: Yara
 * Version: 1.0
 * Date: 2020年11月15日
 * Time: 20:27:26
 * Description:规则接口
 */

@Service
public interface IRuleService {
    /**
     * 插入规则
     * @author Yara
     * @param ruleModel
     * @return
     */
    ItooResult save(RuleModel ruleModel);

    /**
     * 删除规则
     * @author Yara
     * @param ruleId
     * @return
     */
    ItooResult delete(String ruleId);

    /**
     * 修改规则
     * @author Yara
     * @param ruleAllModel
     * @return
     */
    ItooResult update(RuleAllModel ruleAllModel);

    /**
     * 查询某条规则
     * @author Yara
     * @param ruleId
     * @return
     */
    ItooResult queryRule(String ruleId);

    /**
     * 查询全部规则
     * @author Yara
     * @return
     */
    ItooResult queryAllRules();
}
