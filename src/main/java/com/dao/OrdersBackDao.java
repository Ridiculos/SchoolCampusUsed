package com.dao;

import com.domain.OrdersBack;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersBackDao {
    /**
     * 查询
     */
    //根据订单ID查询所有关联
    @Select("select * from ordersback where orders_id = #{ordersId}")
    List<OrdersBack> findAllByOrdersId(String ordersId);
    /**
     * 添加
     */
    //添加关联
    @Insert("insert into ordersback values(#{ordersId},#{feedbacksId})")
    int createByOrdersId(OrdersBack ordersBack);
    /**
     * 修改
     */
    //本表修改不允许用户和管理员修改
    /**
     * 删除
     */
    //根据订单ID删除所有关联
    @Delete("delete from ordersback where orders_id = #{ordersId}")
    int deleteAllByOrdersId(String ordersId);
    //根据订单ID和投诉、建议、反馈ID删除单个关联
    @Delete("delete from ordersback where orders_id = #{ordersId} and feedbacks_id = #{feedbacksId}")
    int deleteAllByOrdersIdAndFeedbacksId(@Param("ordersId") String ordersId, @Param("feedbacksId") String feedbacksId);
}
