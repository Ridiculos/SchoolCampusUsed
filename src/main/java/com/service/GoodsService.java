package com.service;

import com.domain.Goods;
import java.util.List;

public interface GoodsService {
    /**
     * 获取随机且不重复的商品ID
     * @return
     */
    String getRandomGoodsId();

    /**
     * 保存新商品
     * @param goods
     * @return
     */
    int createGoods(Goods goods);

    /**
     * 查找用户所有的闲置
     * @param userId
     * @return
     */
    List<Goods> getMyGoods(String userId);

    /**
     * 获取商品介绍信息
     * @param goodsId
     * @return
     */
    Goods getGoodsDscrip(String goodsId);

    /**
     * 保存商品介绍
     * @param goods
     * @return
     */
    int saveGoodsDscrip(Goods goods);

    /**
     * 取消出售
     * @param goodsId
     * @return
     */
    int deleteMyGoods(String goodsId);

    /**
     * 取得商品实体全部属性
     * @param goodsId
     * @return
     */
    Goods getGoodsById(String goodsId);

    /**
     * 获取热门精品数据、低价好物数据、9新以上数据
     * @param index
     * @return
     */
    List<Goods> getIndexGoods(int index);

    /**
     * 获取搜索商品数据
     * @param text
     * @return
     */
    List<Goods> getSearchGoods(String text);
}
