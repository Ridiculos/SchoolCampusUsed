package com.dao;

import com.domain.Feedback;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackDao {
    /*添加功能*/
    //添加投诉、建议、反馈信息
    @Insert("insert into feedbacks values(#{userId},#{goodsId},#{payId},#{value},#{feedbacksTime},#{feedbacksStatus},#{ordersId},#{feedbacksId},#{solveStatus},#{solveValue},#{name})")
    int createFeedback(Feedback feedback);

    /*修改功能*/
    //根据反馈ID修改文字信息、时间信息、类型信息
    @Update("update feedbacks set value = #{value},feedbacks_time = #{time},feedbacks_status = #{status} where feedbacks_id = #{feedbackId}")
    int changeValueTimeStatusById(@Param("feedbackId") String feedbackId, @Param("value") String value, @Param("time") String time, @Param("status") int status);
    //根据反馈ID修改管理员处理状态信息、管理员处理结果信息
    @Update("update feedbacks set solve_value = #{value},solve_status = #{status} where feedbacks_id = #{feedbackId}")
    int changeAdminStatusValueById(@Param("feedbackId") String feedbackId, @Param("value") String value, @Param("status") int status);

    /*删除功能*/
    //根据用户ID和反馈类型删除所有对应的反馈集合
    @Delete("delete from feedbacks where user_id = #{userId} and feedbacks_status = #{status}")
    int deleteAllByUidAndStatus(@Param("userId") String userId, @Param("status") int status);
    //根据管理员处理状态删除所有反馈集合
    @Delete("delete from feedbacks where solve_status = #{status}")
    int deleteAllByAdminStatus(int status);
    //根据用户ID删除所有反馈集合
    @Delete("delete from feedbacks where user_id = #{userId}")
    int deleteAllByUid(String userId);
    //根据商品ID删除所有反馈集合
    @Delete("delete from feedbacks where goods_id = #{goodsId}")
    int deleteAllByGid(String goodsId);
    //根据支付ID删除所有反馈集合
    @Delete("delete from feedbacks where pay_id = #{payId}")
    int deleteAllByPid(String payId);
    //根据反馈ID删除单个反馈
    @Delete("delete from feedbacks where feedbacks_id = #{id}")
    int deleteOneById(String id);

    /*查询功能*/
    //根据用户ID和反馈类型查询所有反馈集合
    @Select("select * from feedbacks where user_id = #{userId} and feedbacks_status = #{status}")
    List<Feedback> findAllByUidAndStatus(@Param("userId") String userId, @Param("status") int status);
    //根据商品ID和反馈类型查询所有反馈集合
    @Select("select * from feedbacks where goods_id = #{goodsId} and feedbacks_status = #{status}")
    List<Feedback> findAllByGidAndStatus(@Param("goodsId") String goodsId, @Param("status") int status);
    //根据支付ID和反馈类型查询所有反馈集合
    @Select("select * from feedbacks where pay_id = #{payId} and feedbacks_status = #{status}")
    List<Feedback> findAllByPidAndStatus(@Param("payId") String payId, @Param("status") int status);
    //根据反馈类型查询所有反馈集合
    @Select("select * from feedbacks where feedbacks_status = #{status}")
    List<Feedback> findAllByStatus(int status);
    //根据反馈ID查询单个反馈
    @Select("select * from feedbacks where feedbacks_id = #{feedbackId}")
    Feedback findOneById(String feedbackId);
    //根据管理员处理状态查询所有反馈集合
    @Select("select * from feedbacks where solve_status = #{status}")
    List<Feedback> findAllByAdminStatus(int status);
    //查询总数
    @Select("select count(*) from feedbacks")
    int findCount();
    //查询所有反馈
    @Select("select * from feedbacks")
    List<Feedback> findAll();
}
