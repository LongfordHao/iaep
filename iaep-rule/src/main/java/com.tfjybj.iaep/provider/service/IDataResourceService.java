package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.AllDataModel;
import com.tfjybj.iaep.model.DataModel;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 插入数据源
     * @param allDataModel
     * @return
     */
    ItooResult insertData(AllDataModel allDataModel);

    /**
     * 删除数据源
     * @param ids
     * @return
     */
    ItooResult deleteData(List<String> ids);

    /**
     * 查询全部数据源
     * @return
     */
    ItooResult selectData();

    /**
     * 修改数据源
     * @return
     */
    ItooResult updateData(List<DataModel> dataModelList);
}
