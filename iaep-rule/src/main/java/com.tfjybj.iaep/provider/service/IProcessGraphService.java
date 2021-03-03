package com.tfjybj.iaep.provider.service;

import com.tfjybj.iaep.model.ProcessGraphModel;
import org.springframework.stereotype.Service;

/**
 * Author: Sarah-hjf
 * Version:
 * Date: 2020/11/15
 * Time: 7:57
 * Description:${DESCRIPTION}
 */

@Service
public interface IProcessGraphService {
    String save(ProcessGraphModel insertModel);
    void delete(String processGraphId);
    ProcessGraphModel findAllByproGraphId(String processGraphId);
}
