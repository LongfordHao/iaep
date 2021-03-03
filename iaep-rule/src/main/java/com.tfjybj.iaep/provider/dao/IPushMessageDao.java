package com.tfjybj.iaep.provider.dao;

import com.rabbitmq.client.impl.VariableLinkedBlockingQueue;
import com.tfjybj.iaep.entity.ProcessGraphEntity;
import com.tfjybj.iaep.model.PushMessageUserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: LangFordHao
 * Version:
 * Date: 2021/1/26
 * Time: 8:49
 * Description:${DESCRIPTION}
 */
public interface IPushMessageDao extends JpaRepository<ProcessGraphEntity,String>, JpaSpecificationExecutor<ProcessGraphEntity> {

    //@Query(value = "select sel from CONCAT(:dbname)"
    //         +"where 1=1",nativeQuery = true)
    //List queryById(@Param("sel") String sel,@Param("dbname") String dbname);

    /*
    * @author: 郝龙飞
    * @version:
    * @param: [dataName]
    * @date: 2021/1/26
    * @time: 18:29
    * @description:根据规则名称查询规则id
    */
    @Query(value = "SELECT ruleId FROM rule where ruleName=?1 LIMIT 1",nativeQuery = true)
    List queryByName(String dataName);


    //@Query(value = "SELECT COUNT(title),student_id FROM tb_blog WHERE writing_time BETWEEN ?1 AND ?2 GROUP BY student_id if(?3 = '&gt',HAVING COUNT(title) >= ?4) ",nativeQuery = true)
//=================博客数量======================
    //大于号
    @Query(value = "SELECT COUNT(title),student_id FROM tb_blog WHERE  writing_time BETWEEN ?1 AND ?2 GROUP BY student_id HAVING COUNT(title) > ?3",nativeQuery = true)
    List queryblogNumGt(String beginTime,String endTime,String conditionExpres);
    //小于号
    @Query(value = "SELECT COUNT(title),student_id FROM tb_blog WHERE  writing_time BETWEEN ?1 AND ?2 GROUP BY student_id HAVING COUNT(title) < ?3",nativeQuery = true)
    List queryblogNumLt(String beginTime,String endTime,String conditionExpres);
    //等于号
    @Query(value = "SELECT COUNT(title),student_id FROM tb_blog WHERE  writing_time BETWEEN ?1 AND ?2 GROUP BY student_id HAVING COUNT(title) = ?3",nativeQuery = true)
    List queryblogNumS(String beginTime,String endTime,String conditionExpres);

//===============博客评论数===========================

    //大于号  SELECT SUM(comment_number),student_id FROM tb_blog_comment WHERE  comment_date BETWEEN '2020-12-25' AND '2021-12-25' GROUP BY student_id HAVING SUM(comment_number) > 120
    @Query(value = "SELECT SUM(comment_number),student_id FROM tb_blog_comment WHERE  comment_date BETWEEN ?1 AND ?2 GROUP BY student_id HAVING SUM(comment_number) > ?3",nativeQuery = true)
    List queryblogCommentGt(String beginTime,String endTime,String conditionExpres);
    //小于号
    @Query(value = "SELECT SUM(comment_number),student_id FROM tb_blog_comment WHERE  comment_date BETWEEN ?1 AND ?2 GROUP BY student_id HAVING SUM(comment_number) < ?3",nativeQuery = true)
    List queryblogCommentLt(String beginTime,String endTime,String conditionExpres);
    //等于号
    @Query(value = "SELECT SUM(comment_number),student_id FROM tb_blog_comment WHERE  comment_date BETWEEN ?1 AND ?2 GROUP BY student_id HAVING SUM(comment_number) = ?3",nativeQuery = true)
    List queryblogCommentS(String beginTime,String endTime,String conditionExpres);


    //根据IAEP中的受众的钉钉id 查询 dtc中的tb_student表中的id字段 select s.id FROM tb_student s RIGHT JOIN audience a on s.ding_id=a.user_id  WHERE a.user_id='163709445621041972' GROUP BY s.id
    @Query(value = "select s.id as id ,s.name as name FROM tb_student s inner join audience a on s.ding_id=a.user_id  WHERE a.user_id=?1 GROUP BY s.id",nativeQuery = true)
    List queryblogUser(String dingId);

}
