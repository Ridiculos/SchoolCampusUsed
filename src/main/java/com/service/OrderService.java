package com.service;

import com.domain.Order;

import java.util.List;

public interface OrderService {
    /**
     * 获取用户所有订单信息
     * @param userId
     * @return
     */
    List<Order> getOrdersAllByUserId(String userId);

    /**
     * 创建订单
     * @param order
     * @return
     */
    int createOrder(Order order);

    /**
     * 根据用户ID创建购物车内的所有订单，购物车全部购买时使用
     * @param order
     * @return
     */
    int createOrders(Order order);
}
