package com.controller;

import com.dao.GoodsurlDao;
import com.domain.tablereflect.Goodsurl;
import com.service.GoodsUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UpFileController {
    @Autowired
    private GoodsUrlService goodsUrlService;

    @RequestMapping(value = "/uploadFile.do",method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile[] files) throws Exception {
        String goodsId = map.get("goodsId");
        //存储图片的物理路径
        String pic_path = "H:/imgUpload/";
        File f = new File(pic_path);
        if(!(f.exists() && f.isDirectory())) {
            boolean mkdir = f.mkdir();
            if(!mkdir){
                throw new Exception("图片上传文件夹创建失败");
            }
        }
        for(MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            //新的图片名称
            assert originalFilename != null;
            String newFileName =  goodsId + UUID.randomUUID().toString() +originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片
            File newFile = new File(pic_path + newFileName);
            // 将上传的文件移动到指定文件夹
            try {
                file.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            String fileUrl = pic_path + newFileName;
            //System.out.println(fileUrl);


            //此处代码在大数据情况下有逻辑错误，后期应该解决维护
            int count = goodsUrlService.saveGoodsUrl(new Goodsurl(goodsId, fileUrl, UUID.randomUUID().toString()));
            if (count == 0)
                throw new Exception("图片保存失败");
        }
    }
}
