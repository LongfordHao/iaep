package com.tfjybj.iaep.provider.service.imp;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.provider.service.IPushMessage;
import com.tfjybj.iaep.provider.service.IScreenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.HttpUtils;
import utils.ResponseWrap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/1/26
 * Time: 8:48
 * Description:${DESCRIPTION}
 */
@Service
public class PushMessageServiceImpl implements IPushMessage {

    @Value("${permission.approvaldetails}")
    private String approvalDetailsAddress;

    @Value("${permission.approvaldetailsWeChat}")
    private String approvalDetailsWeChat;

    @Value("${permission.ruleIds}")
    private String ruleIds;

    @Autowired
    private IScreenData screenData;

    /**
    * @author: 郝龙飞
    * @version:
    * @param: [String ruleId]
    * @date: 2021/1/26
    * @time: 8:55
    * @description:根据规则查询想要的数据
    */
    public List messageInfo(String ruleId,String beganTime,String endTime,Map map){

        List listOut=new ArrayList();
        //返回数据
        return listOut;
    }

    @Override
    public ItooResult pushMessage(String dataName,String beganTime,String endTime){

        screenData.returnList();
        return ItooResult.build(ItooResult.SUCCESS, "推送成功");
    }

    //调取消息接口
    private void pushMessageDing(List userList,String content){
        String url=approvalDetailsAddress;
        HttpUtils post = HttpUtils.post(url);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("msgtype","oa");
        jsonObject.put("userList",userList);//接收信息用户
        jsonObject.put("sender","132458164035488397");
        jsonObject.put("templateId","404");
        jsonObject.put("bodyAuthor", "OMO项目组");
        jsonObject.put("bodyContent", content);//名单
        jsonObject.put("bodyTitle", "博客检查");//标题

        post.setParameterJson(jsonObject);
        post.addHeader("Content-Type", "application/json; charset=utf-8");
        ResponseWrap execute = post.execute();
    }

    private void pushMessageWeChat(){
        String url=approvalDetailsWeChat;
        HttpUtils post = HttpUtils.post(url);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("batchNo","string");
        json.put("keyword1","信息技术000");
        json.put("keyword2","000");
        json.put("keyword3","刘晓伟");
        json.put("remark","你好");
        jsonObject.put("messageText",json);
        jsonObject.put("messageTitle","博客通知");
        jsonObject.put("operator","string");
        jsonObject.put("operatorID","Up1sbM1bhWtSz9TvmKZqe1");
        jsonObject.put("templateId","string");
        jsonObject.put("templateWeChatId","SrY7F0h7K-1fEweI8LlsrUNcggXv0KUP0wEjoUN_i1I");
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("18232530109");
        jsonObject.put("userCode",arrayList);

        post.setParameterJson(jsonObject);
        post.addHeader("Content-Type", "application/json; charset=utf-8");
        ResponseWrap execute = post.execute();
    }

    //整合字符串
    private String stringContent(List listContent){


        Map map=(Map) listContent.get(0);
        String content= "";
        for(Object key :  map.keySet()){
            Object value = map.get(key);
            content+=key.toString()+"："+value.toString();
        }

        return content;
    }
}
