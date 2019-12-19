<template>
  <div class="index-footer">
    <el-row>
      <el-col :span="21" :offset="1">
<!--        &lt;!&ndash;    友情链接&ndash;&gt;-->
<!--        <el-row>-->
<!--          <ul class="nav nav-tabs">-->
<!--            <li role="presentation" class="active" @mouseenter="indexFooterMyElRowChange1" id="index-footer-my-li-1"><a href="javascript:;">校内网站</a></li>-->
<!--            <li role="presentation" @mouseenter="indexFooterMyElRowChange2" id="index-footer-my-li-2"><a href="javascript:;">校外网站</a></li>-->
<!--            <li role="presentation" @mouseenter="indexFooterMyElRowChange3" id="index-footer-my-li-3"><a href="javascript:;">友情网站</a></li>-->
<!--          </ul>-->
<!--          <el-row v-show="indexFooterMyElRow1" style="margin-top: 20px">-->
<!--            <el-col :span="5" :offset="1"><a href="https://lib.jsu.edu.cn/">吉首大学图书馆</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://jwc.jsu.edu.cn/">吉首大学教务处</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://jwxt.jsu.edu.cn/jsxsd/">吉首大学强智系统</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://szjy.jsu.edu.cn/">吉首大学素质教育中心</a></el-col>-->
<!--          </el-row>-->
<!--          <el-row v-show="indexFooterMyElRow2" style="margin-top: 20px">-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.chsi.com.cn/">学信网</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.baidu.com/">百度</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="http://www.tencent.com/">腾讯</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.aliyun.com/">阿里云</a></el-col>-->
<!--          </el-row>-->
<!--          <el-row v-show="indexFooterMyElRow3" style="margin-top: 20px">-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.taobao.com/">淘宝</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.tmall.com/">天猫</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.jd.com/">京东</a></el-col>-->
<!--            <el-col :span="5" :offset="1"><a href="https://www.suning.com/">苏宁易购</a></el-col>-->
<!--          </el-row>-->
<!--        </el-row>-->
<!--        &lt;!&ndash;    友情链接结束&ndash;&gt;-->
        <el-row style="margin-top: 5px;text-align: center;">
          <hr />
          <p style="color: #999;font-size: 12px;">
            © 2019 吉首大学二手交易系统 版权所有
          </p>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "index-footer",
      data(){
          return{
            indexFooterMyElRow1:true,
            indexFooterMyElRow2:false,
            indexFooterMyElRow3:false,
          }
      },
      methods:{
        indexFooterMyElRowChange1(){
          this.indexFooterMyElRow1 = true;
          this.indexFooterMyElRow2 = false;
          this.indexFooterMyElRow3 = false;
          this.indexFooterMyElRowChangeActive("index-footer-my-li-1");
        },
        indexFooterMyElRowChange2(){
          this.indexFooterMyElRow1 = false;
          this.indexFooterMyElRow2 = true;
          this.indexFooterMyElRow3 = false;
          this.indexFooterMyElRowChangeActive("index-footer-my-li-2");
        },
        indexFooterMyElRowChange3(){
          this.indexFooterMyElRow1 = false;
          this.indexFooterMyElRow2 = false;
          this.indexFooterMyElRow3 = true;
          this.indexFooterMyElRowChangeActive("index-footer-my-li-3");
        },
        indexFooterMyElRowChangeActive(id){
          $("#index-footer-my-li-1").removeClass("active");
          $("#index-footer-my-li-2").removeClass("active");
          $("#index-footer-my-li-3").removeClass("active");
          $("#"+id).addClass("active");
        }
      }
    }
</script>

<style scoped>
  .index-footer a{
    text-decoration: none;
    font-size: 14px;
    color: #999;
  }
  .index-footer ul>li>a{
    font-weight: bold;
  }
  .index-footer{
    margin-top: 30px;
  }
</style>
