package com.domain;

import java.io.Serializable;

public class OrdersBack implements Serializable {
    private String ordersId;
    private String feedbacksId;

    public OrdersBack(String ordersId, String feedbacksId) {
        this.ordersId = ordersId;
        this.feedbacksId = feedbacksId;
    }

    public OrdersBack() {
    }

    @Override
    public String toString() {
        return "OrdersBack{" +
                "ordersId='" + ordersId + '\'' +
                ", feedbacksId='" + feedbacksId + '\'' +
                '}';
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
}
