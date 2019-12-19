package com.domain.tablereflect;

import java.io.Serializable;

public class Ordersback implements Serializable {
    private String ordersId;
    private String feedbacksId;

    public Ordersback(String ordersId, String feedbacksId) {
        this.ordersId = ordersId;
        this.feedbacksId = feedbacksId;
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

    @Override
    public String toString() {
        return "Ordersback{" +
                "ordersId='" + ordersId + '\'' +
                ", feedbacksId='" + feedbacksId + '\'' +
                '}';
    }
}
