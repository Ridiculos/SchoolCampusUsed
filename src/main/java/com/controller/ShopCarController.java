package com.controller;

import com.domain.tablereflect.ShopCar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopCarService;

    /**
     * 查询用户购物车
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findAllByUid.do",method = {RequestMethod.GET})
    public @ResponseBody List<ShopCar> findAllByUid(@RequestParam String userId){
        return shopCarService.findAllByUid(userId);
    }

    /**
     * 删除一个
     * @param shopCar
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteOneByUidAndGid.do",method = {RequestMethod.POST})
    public void deleteOneByUidAndGid(@RequestBody ShopCar shopCar, HttpServletResponse response)throws IOException {
        int count = shopCarService.deleteOneByUidAndGid(shopCar);
        HashMap<String,Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 删除所有
     * @param shopCar
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/deleteAllByUid.do",method = {RequestMethod.POST})
    public void deleteAllByUid(@RequestBody ShopCar shopCar,HttpServletResponse response)throws IOException {
        int count = shopCarService.deleteAllByUid(shopCar.getUserId());
        HashMap<String,Integer> map = new HashMap<>();
        if(count >= 0){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 加入一个商品到购物车
     * @param shopCar
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/addOneToShopCar.do",method = {RequestMethod.POST})
    public void addOneToShopCar(@RequestBody ShopCar shopCar,HttpServletResponse response)throws IOException{
        int count = shopCarService.addOneToShopCar(shopCar);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
