package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import org.springframework.stereotype.Service;

/**
 * @Author Yara
 * @Date 2021/1/19
 * @Time 19:43
 * @Version 1.0
 * @Description
 */
@Service
public interface IDataResourceService {

    /**
     * 查询全部数据源
     * @return
     */
    ItooResult selectData();
}
