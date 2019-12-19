package com.domain.tablereflect;

import java.io.Serializable;

public class Goodsurl implements Serializable {
    private String goodsId;
    private String imgUrl;
    private String imgId;

    @Override
    public String toString() {
        return "Goodsurl{" +
                "goodsId='" + goodsId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgId='" + imgId + '\'' +
                '}';
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public Goodsurl(String goodsId, String imgUrl, String imgId) {
        this.goodsId = goodsId;
        this.imgUrl = imgUrl;
        this.imgId = imgId;
    }

    public Goodsurl() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
