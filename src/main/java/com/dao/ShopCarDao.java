package com.dao;

import com.domain.tablereflect.ShopCar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCarDao {
    //增加
    @Insert("insert into shopcar values(#{userId},#{goodsId})")
    int createShopCar(ShopCar shopCar);

    //查询
    //根据用户ID查询购物车
    @Select("select * from shopcar where user_id = #{userId}")
    List<ShopCar> findAllByUid(String userId);

    //删除
    //根据用户ID和商品ID删除购物车
    @Delete("delete from shopcar where user_id = #{userId} and goods_id = #{goodsId}")
    int deleteOneByUidAndGid(@Param("userId") String userId,@Param("goodsId") String goodsId);
    //根据用户ID删除所有
    @Delete("delete from shopcar where user_id = #{userId}")
    int deleteAllByUid(String userId);

    //修改
    //这里不需要修改
}
