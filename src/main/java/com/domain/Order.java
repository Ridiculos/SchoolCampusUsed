package com.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String userId;
    private String goodsId;
    private int ordersStatus = 0;
    private double price = 0;
    private String payId;
    private String ordersId;
    private int adminStatus = 0;
    private String time = new Date().toString();

    public int getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(int adminStatus) {
        this.adminStatus = adminStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", ordersStatus=" + ordersStatus +
                ", price=" + price +
                ", payId='" + payId + '\'' +
                ", ordersId='" + ordersId + '\'' +
                ", adminStatus=" + adminStatus +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Order(String userId, String goodsId, int ordersStatus, double price, String payId, String ordersId, int adminStatus, String time) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.ordersStatus = ordersStatus;
        this.price = price;
        this.payId = payId;
        this.ordersId = ordersId;
        this.adminStatus = adminStatus;
        this.time = time;
    }

    public Order() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(int ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }
}
