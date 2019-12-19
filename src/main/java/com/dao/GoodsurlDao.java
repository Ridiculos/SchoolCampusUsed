package com.dao;

import com.domain.tablereflect.Goodsurl;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsurlDao {
    /*添加功能*/
    //添加商品图片信息
    @Insert("insert into goodsurl values(#{goodsId},#{imgUrl},#{imgId})")
    int createGoodsurl(Goodsurl goodsurl);

    /*修改功能*/
    //根据imgId修改商品图片URL地址信息
    @Update("update goodsurl set img_url = #{imgUrl} where img_id = #{imgId}")
    int changeUrlByUrlId(@Param("imgId") String imgId, @Param("imgUrl") String imgUrl);

    /*删除功能*/
    //根据商品ID删除所有图片信息
    @Delete("delete from goodsurl where goods_id = #{goodsId}")
    int deleteAllByGoodsId(String goodsId);
    //根据imgId删除商品单个图片信息
    @Delete("delete from goodsurl where img_id = #{imgId}")
    int deleteOneByUrlId(String imgId);

    /*查询功能*/
    //根据商品ID查询所有图片信息
    @Select("select * from goodsurl where goods_id = #{goodsId}")
    List<Goodsurl> findAllByGoodsId(String goodsId);
    //根据imgId查询单个图片信息
    @Select("select * from goodsurl where img_id = #{imgId}")
    Goodsurl findOneByUrlId(String imgId);
}
