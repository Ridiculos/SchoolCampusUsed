package com.domain;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
   private String userId;
   private String goodsId;
   private String payId;
   private String value;
   private String feedbacksTime = new Date().toString();
   private int feedbacksStatus = -1;
   private String ordersId;
   private String feedbacksId;
   private int solveStatus = 0;
   private String solveValue;
   private String name;


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

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeedbacksTime() {
        return feedbacksTime;
    }

    public void setFeedbacksTime(String feedbacksTime) {
        this.feedbacksTime = feedbacksTime;
    }

    public int getFeedbacksStatus() {
        return feedbacksStatus;
    }

    public void setFeedbacksStatus(int feedbacksStatus) {
        this.feedbacksStatus = feedbacksStatus;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getFeedbacksId() {
        return feedbacksId;
    }

    public void setFeedbacksId(String feedbacksId) {
        this.feedbacksId = feedbacksId;
    }

    public int getSolveStatus() {
        return solveStatus;
    }

    public void setSolveStatus(int solveStatus) {
        this.solveStatus = solveStatus;
    }

    public String getSolveValue() {
        return solveValue;
    }

    public void setSolveValue(String solveValue) {
        this.solveValue = solveValue;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", payId='" + payId + '\'' +
                ", value='" + value + '\'' +
                ", feedbacksTime='" + feedbacksTime + '\'' +
                ", feedbacksStatus=" + feedbacksStatus +
                ", ordersId='" + ordersId + '\'' +
                ", feedbacksId='" + feedbacksId + '\'' +
                ", solveStatus=" + solveStatus +
                ", solveValue='" + solveValue + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Feedback(String userId, String goodsId, String payId, String value, String feedbacksTime, int feedbacksStatus, String ordersId, String feedbacksId, int solveStatus, String solveValue, String name) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.payId = payId;
        this.value = value;
        this.feedbacksTime = feedbacksTime;
        this.feedbacksStatus = feedbacksStatus;
        this.ordersId = ordersId;
        this.feedbacksId = feedbacksId;
        this.solveStatus = solveStatus;
        this.solveValue = solveValue;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Feedback() {
    }
}
