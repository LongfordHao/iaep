package com.tfjybj.iaep.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/1/26
 * Time: 8:48
 * Description:${DESCRIPTION}
 */
@Service
public interface IPushMessage {
    ItooResult pushMessage(String dataName,String beganTime,String endTime);
}
