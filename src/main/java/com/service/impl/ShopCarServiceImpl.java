package com.service.impl;

import com.dao.ShopCarDao;
import com.domain.tablereflect.ShopCar;
import com.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    private ShopCarDao shopCarDao;

    @Override
    public List<ShopCar> findAllByUid(String userId) {
        return shopCarDao.findAllByUid(userId);
    }

    @Override
    public int deleteOneByUidAndGid(ShopCar shopCar) {
        return shopCarDao.deleteOneByUidAndGid(shopCar.getUserId(),shopCar.getGoodsId());
    }

    @Override
    public int deleteAllByUid(String userId) {
        return shopCarDao.deleteAllByUid(userId);
    }

    @Override
    public int addOneToShopCar(ShopCar shopCar) {
        return shopCarDao.createShopCar(shopCar);
    }
}
