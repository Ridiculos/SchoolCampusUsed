package com.service;

import com.domain.tablereflect.ShopCar;

import java.util.List;

public interface ShopCarService {
    /**
     * 查询用户购物车
     * @param userId
     * @return
     */
    List<ShopCar> findAllByUid(String userId);

    /**
     * 删除一个
     * @param shopCar
     * @return
     */
    int deleteOneByUidAndGid(ShopCar shopCar);

    /**
     * 删除所有
     * @param userId
     * @return
     */
    int deleteAllByUid(String userId);

    /**
     * 加入购物车
     * @param shopCar
     * @return
     */
    int addOneToShopCar(ShopCar shopCar);
}
