package com.domain.tablereflect;

import java.io.Serializable;

public class ShopCar implements Serializable {
    private String userId;
    private String goodsId;

    @Override
    public String toString() {
        return "ShopCarTable{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                '}';
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

    public ShopCar(String userId, String goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public ShopCar(){

    }
}
