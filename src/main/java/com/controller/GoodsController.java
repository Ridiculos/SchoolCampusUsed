package com.controller;

import com.domain.Goods;
import com.domain.tablereflect.Goodsurl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.GoodsService;
import com.service.GoodsUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsUrlService goodsUrlService;


    /**
     * 获取随机不重复的商品ID
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getRandomGoodsId.do",method = {RequestMethod.GET})
    public void getRandomGoodsId(HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodsId",goodsService.getRandomGoodsId());
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 创建新商品（闲置回收）
     * @param goods
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createGoods.do",method = {RequestMethod.POST})
    public void createGoods(@RequestBody Goods goods,HttpServletResponse response) throws IOException {
        int count = goodsService.createGoods(goods);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("code",count);
        response.getWriter().write(new ObjectMapper().writeValueAsString(hashMap));
    }

    /**
     * 获取用户所有闲置
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getMyGoods.do",method = {RequestMethod.GET})
    public @ResponseBody List<Goods> getMyGoods(@RequestParam String userId){
        return goodsService.getMyGoods(userId);
    }

    /**
     * 获取商品主图
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoodsMainImg.do",method = {RequestMethod.GET})
    public @ResponseBody Goodsurl getGoodsMainImg(@RequestParam String goodsId){
        return goodsUrlService.getGoodsMainImg(goodsId);
    }

    /**
     * 获取商品全部图片
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoodsImgs.do",method = {RequestMethod.GET})
    public @ResponseBody List<Goodsurl> getGoodsImgs(@RequestParam String goodsId){
        return goodsUrlService.getGoodsImgs(goodsId);
    }

    /**
     * 获取商品全部图片 Map
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoodsImgMap.do",method = {RequestMethod.GET})
    public @ResponseBody Map<String,String> getGoodsImgMap(@RequestParam String goodsId){
        return goodsUrlService.getGoodsImgMap(goodsId);
    }

    /**
     * 获取商品介绍
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoodsDscrip.do",method = {RequestMethod.GET})
    public @ResponseBody Goods getGoodsDscrip(@RequestParam String goodsId){
        return goodsService.getGoodsDscrip(goodsId);
    }

    /**
     * 保存商品介绍信息
     * @param goods
     * @param response
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/saveGoodsDscrip.do",method = {RequestMethod.POST})
    public void saveGoodsDscrip(@RequestBody Goods goods,HttpServletResponse response) throws IOException {
        int code = goodsService.saveGoodsDscrip(goods);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("code",code);
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 取消出售
     * @param goods
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/deleteMyGoodsAndUrls.do",method = {RequestMethod.POST})
    public void deleteMyGoodsAndUrls(@RequestBody Goods goods,HttpServletResponse response) throws Exception {
        int i = goodsService.deleteMyGoods(goods.getGoodsId());
        int j = goodsUrlService.deleteAllGoodsImgsById(goods.getGoodsId());
        HashMap<String, Integer> map = new HashMap<>();
        if(i == 1 && j != 0){
            map.put("code",1);
        }else if(i != 1){
            map.put("code",2);
        }else{
            map.put("code",3);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 取得商品实体全部信息
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getGoodsById.do",method = {RequestMethod.GET})
    public @ResponseBody Goods getGoodsById(@RequestParam String goodsId){
        return goodsService.getGoodsById(goodsId);
    }

    /**
     * 获取热门精品数据、低价好物数据、9新以上数据
     * @param index
     * @return
     */
    @RequestMapping(value = "/getIndexGoods.do/{index}",method = {RequestMethod.GET})
    public @ResponseBody List<Goods> getIndexGoods(@PathVariable int index){
        return goodsService.getIndexGoods(index);
    }

    /**
     * 获取搜索的商品数据
     * @param text
     * @return
     */
    @RequestMapping(value = "/getSearchGoods.do",method = {RequestMethod.GET})
    public @ResponseBody List<Goods> getSearchGoods(@RequestParam String text){
        return goodsService.getSearchGoods(text);
    }
}
