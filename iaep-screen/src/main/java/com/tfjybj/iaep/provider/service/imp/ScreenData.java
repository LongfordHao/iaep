package com.tfjybj.iaep.provider.service.imp;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.iaep.provider.client.IPolicy;
import com.tfjybj.iaep.provider.client.IProcessGraph;
import com.tfjybj.iaep.provider.client.IRegulation;
import com.tfjybj.iaep.provider.service.IScreenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.swing.text.rtf.RTFEditorKit;
import java.util.*;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/2/25
 * Time: 8:31
 * Description:查询规则的全部信息，组装所有规则信息成sql，通过组装的sql查询数据
 */

@Service
public class ScreenData implements IScreenData {

    //远程调用其他服务生产者
    @Autowired
    private IPolicy iPolicy;
    @Autowired
    private IRegulation iRegulation;
    @Autowired
    private IProcessGraph iProcessGraph;

    //1、规则id，规则名称  得到 流程画布id
    private String graphId(){

        return "";
    }

    //2、流程画布id 得到 数据源 受众 策略器id  及策略器id的关系
    private StringBuffer processGraphString(String processGraphId){
        StringBuffer stringBuffer=new StringBuffer();
        ItooResult processData= iProcessGraph.queryProcessGraph(processGraphId);
        HashMap processMap=(HashMap) processData.getData();
        List policyList=(List) processMap.get("policyrRelevanceModelList");
        List listAll=new ArrayList();
        if (policyList !=null){
            //策略器组合的数组
            String temp="";
            for (int i = 0; i <policyList.size() ; i++) {
                HashMap policyHashMap=(HashMap) policyList.get(i);
                String policyFather=(String) policyHashMap.get("hierarchy");
                if (temp.equals("")){
                    temp=policyFather;
                    List listChild = ergodicList(policyList, policyFather, policyList);
                    listAll.addAll(listChild);
                }else if (!temp.equals(policyFather)) {
                    List listChild = ergodicList(policyList, policyFather, policyList);
                    listAll.addAll(listChild);
                }
                temp=policyFather;
            }
        }
        //循环遍历listAll中的所有策略器id并根据id查询字符串
        if (listAll.size()!=0){
            for (int i = 0; i < listAll.size(); i++) {
                List list=(List) listAll.get(i);
                for (int j = 0; j < list.size(); j++) {
                    HashMap hashMap=(HashMap) list.get(j);
                    String policyId=(String)hashMap.get("policyCanId");
                    stringBuffer.append(policyString(policyId));
                }
            }
        }
        return stringBuffer;
    }
    /*
    * 功能描述:遍历数组得到新的数组
    * [childList]
    * @return
    * @author HaoLongfei
    * @since 2021/3/1 8:32
    */
    private List ergodicList(List childList,String rootNode,List fatherList){
        List listChild=new ArrayList();
        List returnList=new ArrayList();
        for (int i = 0; i <childList.size() ; i++) {
            List newList=new ArrayList();
            HashMap processHash=(HashMap)childList.get(i);
            //获取当前策略器的hierarchy编号作为父项编号
            String hierarchyNum=(String) processHash.get("hierarchy");
            //hierachyNum是否与根节点相同如果相同说明 hierarchyNum是根节点的一个子节点，将这个子节点插入到新的数组中
            if (rootNode.equals(hierarchyNum)){
                listChild.add(processHash);
                //当前策略器本身
                newList.add(processHash);
                //当前策略器的父节点策略器
                List list= ergodic(fatherList,hierarchyNum);
                newList.addAll(list);
            }
            if (newList.size() != 0){
                returnList.add(newList);
            }
        }
        return returnList;
    }

    /**
    * 功能描述:递归,通过叶子节点找到当前分支上的所有的父节点并存储到一个list
    * [fatherList, hierarchyNum]
    * @return 当前叶子节点所在分支上的所有父节点
    * @author HaoLongfei
    * @since 2021/3/3 7:57
    */
    private List ergodic(List fatherList,String hierarchyNum){
        List newList=new ArrayList();
        String temp=hierarchyNum;
        for (int j = 0; j < fatherList.size(); j++) {
            HashMap processHashFather=(HashMap)fatherList.get(j);
            //获取当前策略器的serialNumber作为比对条件，进行条件的拼接
            String serialFatherA=(String) processHashFather.get("serialNumber");
            //获取当前策略器的hierarchy编号作为父项编号
            String hierarchyNumA=(String) processHashFather.get("hierarchy");
            if (hierarchyNum.equals(serialFatherA)){
                newList.add(processHashFather);
                temp=hierarchyNumA;
            }
            //让此方法迭代循环找到同一条线上的所有父节点
            if (!temp.equals("0") && (j==fatherList.size()-1)){
                j=-1;
                hierarchyNum=temp;
            }
        }
        return newList;
    }
    //3、策略器id  得到 条件信息

    private StringBuffer policyString(String policyId){
        StringBuffer str=new StringBuffer();
        ItooResult itooResult=iPolicy.queryConditionAndMessage(policyId);
        List policyList=(List) itooResult.getData();
        if (policyList != null){
            for (int i = 0; i < policyList.size(); i++) {
                HashMap list = (HashMap) policyList.get(i);
                List conditionList =(List) list.get("condition");
                for (int j = 0; j <conditionList.size() ; j++) {
                    HashMap conditionMap=(HashMap) conditionList.get(j);
                    String name=(String) conditionMap.get("conditionName");
                    String select=characterConversion((String) conditionMap.get("conditionSelect"));
                    String express=(String) conditionMap.get("conditionExpress");
                    str.append(conditionMap.get("conditionName")).append(" ").append(select).append(" ").append(express).append(" ");
                    if (j !=conditionList.size()-1){
                        str.append("and ");
                    }
                }
            }
        }

        return str;
    }

    /*
    * 功能描述:转换字符，将&gt字符转换成比较字符 > < =
    * [str]
    * @return
    * @author HaoLongfei
    * @since 2021/3/3 9:10
    */
    private String characterConversion(String str){
        String returnstr="=";
        if (str.equals("&gt;")){
            returnstr=">";
        }
        if (str.equals("&lt;")){
            returnstr="<";
        }
        if (str.equals("=")){
            returnstr=str;
        }
        return returnstr;
    }
    //4、拼接成sql

    //5、返回数据
    @Override
    public List returnList(){
        List list=new ArrayList();
        String id="1366738927423127553";
        //条件拼接的字符串
        processGraphString(id);

        return list;
    }
}
