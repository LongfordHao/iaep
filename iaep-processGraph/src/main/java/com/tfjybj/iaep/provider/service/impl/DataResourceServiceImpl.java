package com.tfjybj.iaep.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.*;
import com.tfjybj.iaep.provider.dao.IConditionTitleDao;
import com.tfjybj.iaep.provider.dao.IDataResourceDao;
import com.tfjybj.iaep.provider.service.IDataResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Yara
 * @Date 2021/1/19
 * @Time 19:44
 * @Version 1.0
 * @Description
 */
@Service
public class DataResourceServiceImpl implements IDataResourceService {

    @Resource
    private IDataResourceDao iDataResourceDao;

    /**
     * 查询全部数据源
     * @return
     */
    @Override
    public ItooResult selectData() {
        List<LabelDataModel> dataModelList = iDataResourceDao.selectData();
        List<LabelDataModel> dataModels = dataModelList.stream().distinct().collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(dataModels)){
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",dataModels);
        }
        return ItooResult.build(ItooResult.FAIL,"查询失败");
    }

}
