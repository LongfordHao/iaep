package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import org.springframework.stereotype.Service;

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
     * 查询条件标签
     * @return
     */
    ItooResult selectConditionTitle(String Title);
}
