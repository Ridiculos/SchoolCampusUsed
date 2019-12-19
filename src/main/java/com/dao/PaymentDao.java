package com.dao;

import com.domain.Payment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao {
    /*添加功能*/
    //添加支付信息
    @Insert("insert into payments values(#{userId},#{payId},#{price},#{status},#{payTime},#{goodsId},#{payStatus})")
    int createPayment(Payment payment);

    /*修改功能*/
    //根据用户ID修改费用信息
    @Update("update payments set price = #{price} where user_id = #{userId}")
    int changePriceByUid(@Param("userId") String userId, @Param("price") double price);
    //根据用户ID修改费用状态信息
    @Update("update payments set status = #{status} where user_id = #{userId}")
    int changePriceStatusByUid(@Param("userId") String userId, @Param("status") int status);
    //根据用户ID修改支付时间
    @Update("update payments set pay_time = #{payTime} where user_id = #{userId}")
    int changePayTimeByUid(@Param("userId") String userId, @Param("payTime") String payTime);
    //根据用户ID修改支付状态信息
    @Update("update payments set pay_status = #{status} where user_id = #{userId}")
    int changePayStatusByUid(@Param("userId") String userId, @Param("status") int status);
    //根据用户ID修改费用、费用状态、支付时间、支付状态信息
    @Update("update payments set price = #{price},status = #{status},pay_time = #{payTime},pay_status = #{payStatus} where user_id = #{userId}")
    int changePriceAndStatusPayTimeAndStatusByUid(@Param("userId") String userId, @Param("price") double price, @Param("status") int status, @Param("payTime") String payTime, @Param("payStatus") String payStatus);

    /*删除功能*/
    //根据用户ID删除支付记录集合
    @Delete("delete from payments where user_id = #{userId}")
    int deleteAllByUid(String userId);
    //根据商品ID删除支付记录集合
    @Delete("delete from payments where goods_id = #{goodsId}")
    int deleteAllByGid(String goodsId);
    //根据支付状态删除支付记录集合
    @Delete("delete from payments where pay_status = #{status}")
    int deleteAllByPayStatus(int status);
    //根据支付ID删除单条支付记录
    @Delete("delete from payments where pay_id = #{payId}")
    int deleteOneByPayId(String payId);

    /*查询功能*/
    //根据用户ID查询支付记录集合
    @Select("select * from payments where user_id = #{userId}")
    List<Payment> findAllByUid(String userId);
    //根据商品ID查询支付记录集合
    @Select("select * from payments where goods_id = #{goodsId}")
    List<Payment> findAllByGid(String goodsId);
    //根据支付状态查询支付记录集合
    @Select("select * from payments where pay_status = #{status}")
    List<Payment> findAllByPayStatus(int status);
    //根据费用状态查询支付记录集合
    @Select("select * from payments where status = #{status}")
    List<Payment> findAllByStatus(int status);
    //根据支付ID查询单条支付记录
    @Select("select * from payments where pay_id = #{payId}")
    Payment findOneByPayId(String payId);
    //查询总数
    @Select("select count(*) from payments")
    int findCount();
    //查询所有
    @Select("select * from payments")
    List<Payment> findAll();
}
