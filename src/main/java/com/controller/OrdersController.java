package com.controller;

import com.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取所有用户订单信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getOrdersAllByUserId.do",method = {RequestMethod.GET})
    public @ResponseBody List<Order> getOrdersAllByUserId(@RequestParam String userId){
        return orderService.getOrdersAllByUserId(userId);
    }

    /**
     * 创建一个订单
     * @param order
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createOrder.do",method = {RequestMethod.POST})
    public void createOrder(@RequestBody Order order, HttpServletResponse response)throws IOException {
        int count = orderService.createOrder(order);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 创建用户购物车内的所有订单
     * @param order
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createOrders.do",method = {RequestMethod.POST})
    public void createOrders(@RequestBody Order order,HttpServletResponse response)throws IOException{
        int count = orderService.createOrders(order);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
