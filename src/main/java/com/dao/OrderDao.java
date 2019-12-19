package com.dao;

import com.domain.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    /*添加功能*/
    //新增订单
    @Insert("insert into orders values(#{userId},#{goodsId},#{ordersStatus},#{price},#{payId},#{ordersId},#{adminStatus},#{time})")
    int createOrder(Order order);

    /*修改功能*/
    //根据订单ID修改订单状态
    @Update("update orders set orders_status = #{status} where orders_id = #{ordersId}")
    int changeStatusByOrderId(@Param("ordersId") String ordersId, @Param("status") int status);
    //根据订单ID修改支付ID信息
    @Update("update orders set pay_id = #{payId} where orders_id = #{ordersId}")
    int changePayIdByOrderId(@Param("ordersId") String ordersId, @Param("payId") String payId);

    /*删除功能*/
    //根据订单ID删除单个订单
    @Delete("delete from orders where orders_id = #{ordersId}")
    int deleteOneByOrderId(String ordersId);
    //根据用户ID删除所有该用户订单
    @Delete("delete from orders where user_id = #{userId}")
    int deleteAllByUid(String userId);
    //根据订单状态删除所有对应状态订单
    @Delete("delete from orders where orders_status = #{status}")
    int deleteAllByStatus(int status);

    /*查询功能*/
    //根据订单ID查询单个订单
    @Select("select * from orders where orders_id = #{ordersId}")
    Order findOneByOrderId(String ordersId);
    //根据用户ID查询订单集合
    @Select("select * from orders where user_id = #{userId}")
    List<Order> findAllByUid(String userId);
    //根据商品ID查询订单集合
    @Select("select * from orders where goods_id = #{goodsId}")
    List<Order> findAllByGid(String goodsId);
    //根据订单状态查询订单集合
    @Select("select * from orders where orders_status = #{status}")
    List<Order> findAllByStatus(int status);
    //根据支付ID查询单个订单
    @Select("select * from orders where pay_id = #{payId}")
    Order findOneByPayId(String payId);
    //查询总数
    @Select("select count(*) from orders")
    int findCount();
}
