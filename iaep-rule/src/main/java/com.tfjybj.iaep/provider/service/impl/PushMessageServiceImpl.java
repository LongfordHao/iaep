package com.tfjybj.iaep.provider.service.impl;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.model.*;
import com.tfjybj.iaep.provider.dao.IPushMessageDao;
import com.tfjybj.iaep.provider.service.IPushMessage;

import com.tfjybj.iaep.provider.service.IQueryRule;
import com.tfjybj.iaep.utils.http.HttpUtils;
import com.tfjybj.iaep.utils.http.ResponseWrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.persistence.*;
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

    @Resource
    private IQueryRule queryRule;
    @Resource
    private IPushMessageDao pushMessageDao;
    /**
    * @author: 郝龙飞
    * @version:
    * @param: [String ruleId]
    * @date: 2021/1/26
    * @time: 8:55
    * @description:根据规则查询想要的数据
    */
    public List messageInfo(String ruleId,String beganTime,String endTime,Map map){


        //拼接规则字符串
        //1、根据受众和传入的时间段 规则中策略器条件 查询博客篇数
        //受众list
       List<PushMessageUserModel> user=userList(map);

        //策略器条件
        List condition=(List) map.get("ConditionAndMessage");

        //根据受众id筛选符合条件的数据
        Map listblog=new HashMap();
        for (int i = 0; i < condition.size(); i++) {
            List list=(List) condition.get(i);
            for (int j = 0; j <list.size() ; j++) {

                PolicyIdAndNameModel nameModel=(PolicyIdAndNameModel) list.get(i);

                List conditionList=nameModel.getCondition();

                for (int k = 0; k <conditionList.size() ; k++) {
                    ConditionIdAndName conditionIdAndName=(ConditionIdAndName) conditionList.get(k);
                    //DTC的数据表和字段
                    String str=conditionIdAndName.getConditionName();
                    //拆分字符串
                    String[] temp;
                    String delimeter = "\\.";  // 指定分割字符
                    temp = str.split(delimeter); // 分割字符串
                    //数据库
                    String dbName=temp[0];
                    //关键字段
                    String infoName=temp[1];

                    //判断符大于小于等于
                    String conditionSelect=conditionIdAndName.getConditionSelect();

                    //判断符后的参数
                    String conditionExpres=conditionIdAndName.getConditionExpress();
                    //博客条数
                    List listblogNum=new ArrayList();
                    //博客评论数
                    List listblogComment=new ArrayList();
                    //==============博客数量==================
                    //判断符号大于
                    if (conditionSelect.equals("&gt;") && dbName.equals("tb_blog")){
                        listblogNum=pushMessageDao.queryblogNumGt(beganTime,endTime,conditionExpres);
                    }
                    //判断符号小于
                    if (conditionSelect.equals("&lt") && dbName.equals("tb_blog")){
                        listblogNum=pushMessageDao.queryblogNumLt(beganTime,endTime,conditionExpres);
                    }
                    //判断符号等于
                    if (conditionSelect.equals("=") && dbName.equals("tb_blog")){
                        listblogNum=pushMessageDao.queryblogNumS(beganTime,endTime,conditionExpres);
                    }

                    //==============博客评论数=================
                    //判断符号大于
                    if (conditionSelect.equals("&gt;") && dbName.equals("tb_blog_comment")){
                        listblogNum=pushMessageDao.queryblogCommentGt(beganTime,endTime,conditionExpres);
                    }
                    //判断符号小于
                    if (conditionSelect.equals("&lt") && dbName.equals("tb_blog_comment")){
                        listblogNum=pushMessageDao.queryblogCommentLt(beganTime,endTime,conditionExpres);
                    }
                    //判断符号等于
                    if (conditionSelect.equals("=") && dbName.equals("tb_blog_comment")){
                        listblogNum=pushMessageDao.queryblogCommentS(beganTime,endTime,conditionExpres);
                    }

                    //listblogNum这个是符合条件数量的博客
                    List listAddbolg=new ArrayList();
                    //for (int l = 0; l <listblogNum.size() ; l++) {
                        //listblogNum集合中得到一条数据，list集合中嵌套了Objec类型的数据，只能通过for循环来得到object中的数据
                        for (Object o : listblogNum) {
                            Object[] obj = (Object[])o;
                            //user是受众集合，符合条件数量的博客集合，受众集合与博客集合求交集就是最后需要的结果
                            for (int f = 0; f <user.size(); f++) {
                                String listblogNumid= obj[1].toString();
                                String userid=user.get(f).getUserid();
                                if (listblogNumid.equals(userid)){
                                    //将符合条件的用户的姓名放到集合中作为最后的返回值
                                    listAddbolg.add(user.get(f).getUsername());
                                    //listblog.put(dbName,user.get(f).getUsername());
                                }
                            }
                        }
                    //}
                    if (dbName.equals("tb_blog")){
                        listblog.put("博客数量合格",listAddbolg);
                    }
                    if (dbName.equals("tb_blog_comment")){
                        listblog.put("博客评论数量合格",listAddbolg);
                    }

                }
                }



        }

        //根据拼接的字符串的查询结果
       //List list= pushMessageDao.queryById("ruleId","rule");

        List listOut=new ArrayList();
        listOut.add(listblog);

        //返回数据
        return listOut;
    }

    @Override
    public ItooResult pushMessage(String dataName,String beganTime,String endTime){
        //根据dataName查询出规则id
        //List ruleIdList=pushMessageDao.queryByName(dataName);

        String ruleId=ruleIds;

        //查询规则
        Map map= queryRule.QueryAllRuleContent(ruleId);

        //调用messageInfo方法查询出所有数据
       List list= messageInfo(ruleId,beganTime,endTime,map);

        //查询消息接收人
        List listmessaegResceive=messageResceiveList(map);
        //调用MSG接口将数据推送出去
        String messageJson=stringContent(list);

        pushMessageDing(listmessaegResceive,messageJson);
//        pushMessageWeChat();
        return ItooResult.build(ItooResult.SUCCESS, "推送成功");
    }

    //受众
    private List userList(Map map){
        //受众list
        List<AudienceModel> userList=(List) map.get("userIdList");
        List<PushMessageUserModel> user=new ArrayList<>();
        for (int i = 0; i <userList.size() ; i++) {
            String userId= userList.get(i).getUserId();
            //userid对应钉钉id
            //通过钉钉id 去dtc项目的tb_student表中找到对应的id
            List list = pushMessageDao.queryblogUser(userId);
            list.remove(null);
            if (list !=null){
                for (Object o : list) {
                    Object[] obj = (Object[])o;
                    PushMessageUserModel pushMessageUserModel=new PushMessageUserModel();
                    pushMessageUserModel.setUserid(obj[0].toString());
                    pushMessageUserModel.setUsername(obj[1].toString());
                    user.add(pushMessageUserModel);
                }
            }

        }

        return  user;
    }
    //消息接收的人
    private List messageResceiveList(Map map){
        //策略器条件
        List condition=(List) map.get("ConditionAndMessage");
        List list=(List) condition.get(0);
        PolicyIdAndNameModel nameModel=(PolicyIdAndNameModel) list.get(0);
        //消息发送人
        MessageIdAndNameModel messageResceive=nameModel.getMessage().get(0);
        List<MessageReceiveModel> meList=messageResceive.getMessageReceiveModels();
        List messageList=new ArrayList();
        for (int i = 0; i <meList.size() ; i++) {
            messageList.add(meList.get(i).getMessageDingId());
        }
        return messageList;
    }

    //调取消息接口
    private void pushMessageDing(List userList,String content){
        String url=approvalDetailsAddress;
        com.tfjybj.iaep.utils.http.HttpUtils post = HttpUtils.post(url);
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
        com.tfjybj.iaep.utils.http.HttpUtils post = HttpUtils.post(url);
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
