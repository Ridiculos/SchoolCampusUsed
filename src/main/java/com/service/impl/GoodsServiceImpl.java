package com.service.impl;

import com.dao.GoodsDao;
import com.domain.Goods;
import com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public String getRandomGoodsId(){
        synchronized (this){
            while (true){
                String goodsId = UUID.randomUUID().toString();
                //判断是否存在 ID 重复
                if(goodsDao.findOneById(goodsId)== null){
                    return goodsId;
                }
            }
        }
    }

    @Override
    public int createGoods(Goods goods) {
        //新商品默认热度为0，上架状态为1，可用状态为1
        return goodsDao.createGoods(goods);
    }

    @Override
    public List<Goods> getMyGoods(String userId) {
        List<Goods> goods = goodsDao.findAllByUserId(userId);
        return goods;
    }

    @Override
    public Goods getGoodsDscrip(String goodsId) {
        return goodsDao.findOneById(goodsId);
    }

    @Override
    public int saveGoodsDscrip(Goods goods) {
        return goodsDao.changeDescriById(goods.getGoodsId(),goods.getDscrip());
    }

    @Override
    public int deleteMyGoods(String goodsId) {
        return goodsDao.deleteGoodsById(goodsId);
    }

    @Override
    public Goods getGoodsById(String goodsId) {
        return goodsDao.findOneById(goodsId);
    }

    @Override
    public List<Goods> getIndexGoods(int index) {
        List<Goods> list = null;
        //获取热门精品
        if(index == 1){
            list = goodsDao.findAllSortListByHot();
        }else if(index == 2){
            //获取低价好物
            list = goodsDao.findAllSortListByPrice();
        }else{
            //获取 9 新以上
            list = goodsDao.findAllSortListByLevel(90);
        }
        return list;
    }

    @Override
    public List<Goods> getSearchGoods(String text) {
        return goodsDao.findAllByNameAlike("%" + text + "%");
    }
}
