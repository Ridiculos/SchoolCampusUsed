package com.domain;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private String userId;
    private String payId;
    private double price = 0;
    private int status = 0;
    private String payTime = new Date().toString();
    private String goodsId;
    private int payStatus = 0;

    @Override
    public String toString() {
        return "Payment{" +
                "userId='" + userId + '\'' +
                ", payId='" + payId + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", payTime='" + payTime + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", payStatus=" + payStatus +
                '}';
    }

    public Payment() {
    }

    public Payment(String userId, String payId, double price, int status, String payTime, String goodsId, int payStatus) {
        this.userId = userId;
        this.payId = payId;
        this.price = price;
        this.status = status;
        this.payTime = payTime;
        this.goodsId = goodsId;
        this.payStatus = payStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
}
