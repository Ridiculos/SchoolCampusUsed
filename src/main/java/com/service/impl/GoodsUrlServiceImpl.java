package com.service.impl;

import com.dao.GoodsurlDao;
import com.domain.tablereflect.Goodsurl;
import com.service.GoodsUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsUrlServiceImpl implements GoodsUrlService {
    @Autowired
    private GoodsurlDao goodsurlDao;

    @Override
    public int saveGoodsUrl(Goodsurl goodsurl) {
        return goodsurlDao.createGoodsurl(goodsurl);
    }

    @Override
    public Goodsurl getGoodsMainImg(String goodsId) {
        List<Goodsurl> goodsurls = goodsurlDao.findAllByGoodsId(goodsId);
        //默认第一张图作为主图
        Goodsurl goodsurl = goodsurls.get(0);
        String imgUrl = goodsurl.getImgUrl();
        String[] strings = imgUrl.split("/");
        goodsurl.setImgUrl(strings[strings.length - 1]);
        return goodsurls.get(0);
    }

    @Override
    public List<Goodsurl> getGoodsImgs(String goodsId) {
        List<Goodsurl> goodsurls = goodsurlDao.findAllByGoodsId(goodsId);
        for(Goodsurl goodsurl : goodsurls){
            String imgUrl = goodsurl.getImgUrl();
            String[] strings = imgUrl.split("/");
            goodsurl.setImgUrl(strings[strings.length - 1]);
        }
        return goodsurls;
    }

    @Override
    public int deleteAllGoodsImgsById(String goodsId) throws Exception {
        List<Goodsurl> goodsurls = goodsurlDao.findAllByGoodsId(goodsId);
        for(Goodsurl goodsurl : goodsurls){
            File file = new File(goodsurl.getImgUrl());
            if(file.exists())
                if(!file.delete())
                    throw new Exception("文件删除异常");
        }
        return goodsurlDao.deleteAllByGoodsId(goodsId);
    }

    @Override
    public Map<String, String> getGoodsImgMap(String goodsId) {
        List<Goodsurl> list = goodsurlDao.findAllByGoodsId(goodsId);
        HashMap<String, String> map = new HashMap<>();
        int i = 1;
        for(Goodsurl goodsurl : list){
            String[] strings = goodsurl.getImgUrl().split("/");
            //这里...你最好确定你的图片服务器就是这个端口
            map.put("img"+i,"http://localhost:9090/"+strings[strings.length - 1]);
            i++;
        }
        return map;
    }
}
