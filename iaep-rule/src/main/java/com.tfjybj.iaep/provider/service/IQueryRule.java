package com.tfjybj.iaep.provider.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jeff
 * @description ${description}
 * @date 2021/1/26 9:08
 */
@Service
public interface IQueryRule {

    /**
     * 查询全部规则内容
     * @return list
     */

    Map<String,Object> QueryAllRuleContent(String RuleId);
}
