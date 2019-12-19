<template>
  <div class="index-body">
    <el-row :gutter="20" style="margin-top: 30px;">
<!--      <el-col :span="4" style="padding-top: 100px;">-->
<!--        <img src="../../assets/logo2.png" alt="吉大二手交易平台——让吉大没有难卖的二手货">-->
<!--      </el-col>-->
      <el-col :span="24">
        <el-row style="margin-top: 30px;">
          <el-carousel direction="horizontal" :autoplay="true" height="370px">
            <el-carousel-item v-for="(v,k) in items" :key="k">
              <img :src="v" alt="请检查网络连接" width="100%">
            </el-carousel-item>
          </el-carousel>
        </el-row>
        <el-row style="position: relative;top:73px;">
          <el-col :span="5" :offset="16">
            <el-input
              placeholder="输入商品名、商品ID"
              v-model="search"
              clearable>
            </el-input>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" icon="el-icon-search" @click="clickSearch" v-loading.fullscreen.lock="fullscreenLoading">搜索</el-button>
          </el-col>
        </el-row>
        <p class="index-body-p-1">热门精品</p>
        <hr />
        <el-row style="margin-top: 50px;" v-for="(obj1, index1) in getRowNums(data1)" :key="getRandomString(20)">
          <el-col :span="4" v-for="(obj2, index2) in mySlice(data1,index1*4,(index1+1)*4)" :key="getRandomString(21)" :offset="index2 > 0 ? 2 : 1">
            <el-card :body-style="{ padding: '0px', textAlign: 'center'}">
              <img :src="obj2.picture" class="image" style="width: 100%" alt="请检查网络连接">
              <div style="padding: 10px;">
                <h4>{{ obj2.name }}</h4>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="clickGoodsInfoButton(obj2.goodsId)">查看详情</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>

<!--        分页组件-->
        <el-row style="text-align: center;margin-top: 80px;" v-show="paginationShow">
          <el-col :span="24">
<!--            限定每页 12 条数据-->
            <el-pagination
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[12]"
              :page-size="pageSize"
              layout="prev, pager, next"
              :total="dataSearch.length">
            </el-pagination>
          </el-col>
        </el-row>


        <p class="index-body-p-1" style="margin-top: 60px;">低价好物</p>
        <hr />
        <el-row style="margin-top: 50px;" v-for="(obj1, index1) in getRowNums(data2)" :key="getRandomString(20)">
          <el-col :span="4" v-for="(obj2, index2) in mySlice(data2,index1*4,(index1+1)*4)" :key="getRandomString(21)" :offset="index2 > 0 ? 2 : 1">
            <el-card :body-style="{ padding: '0px', textAlign: 'center'}">
              <img :src="obj2.picture" class="image" style="width: 100%" alt="请检查网络连接">
              <div style="padding: 10px;">
                <h4>{{ obj2.name }}</h4>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="clickGoodsInfoButton(obj2.goodsId)">查看详情</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <p class="index-body-p-1" style="margin-top: 60px;">9 新以上</p>
        <hr />
        <el-row style="margin-top: 50px;" v-for="(obj1, index1) in getRowNums(data3)" :key="getRandomString(20)">
          <el-col :span="4" v-for="(obj2, index2) in mySlice(data3,index1*4,(index1+1)*4)" :key="getRandomString(21)" :offset="index2 > 0 ? 2 : 1">
            <el-card :body-style="{ padding: '0px', textAlign: 'center'}">
              <img :src="obj2.picture" class="image" style="width: 100%" alt="请检查网络连接">
              <div style="padding: 10px;">
                <h4>{{ obj2.name }}</h4>
                <div class="bottom clearfix">
                  <el-button type="text" class="button" @click="clickGoodsInfoButton(obj2.goodsId)">查看详情</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>

      </el-col>
    </el-row>


    <!--      商品详情弹出框-->
    <el-dialog
      :title="goodsInfoName"
      :visible.sync="centerDialogVisible"
      width="418px"
      center>
      <div style="text-align: center">
        <el-carousel direction="horizontal" :autoplay="true">
<!--          商品图片源于淘宝，淘宝采用418 * 418 的商品预览图-->
          <el-carousel-item v-for="(v,k) in goodsInfoImg" :key="k" style="width: 365px;height: 300px;">
            <img :src="v" alt="请检查网络连接" width="93%">
          </el-carousel-item>
        </el-carousel>
        <h2><img src="https://i.loli.net/2019/12/17/NhTJjayS6ZCsHWP.png" alt="￥" width="30px">：{{ goodsInfoPrice }}</h2>
        <p>{{ goodsInfoDscrip }}</p>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addToShopCar" style="margin-top: 30px;">加入购物车</el-button>
        </span>
      </div>
    </el-dialog>

    <!--      加入购物车结果提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible2"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
          <el-row style="margin-top: 30px;">
            <el-col :span="6" :offset="5">
              <el-button type="primary" @click="clickButton" style="width: 100%">确 定</el-button>
            </el-col>
            <el-col :span="6" :offset="2">
              <el-button type="primary" @click="clickGoToShopCar" :disabled="goToShopCar">前往购物车</el-button>
            </el-col>
          </el-row>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "index-body",
      data(){
        return{
          items: {
            // img1: require("../../assets/school1.jpg"),
            img2: require("../../assets/school2.jpg"),
            img3: require("../../assets/school3.jpg"),
            img4: require("../../assets/school4.jpg"),
            img5: require("../../assets/school5.jpg")
          },
          rules: {
            search: [
              {validator: checkString, trigger: 'blur'}
            ]
          },
          search: "",
          data1:[],//热门精品
          data2:[],//低价好物
          data3:[],//九新以上
          dataSearch:[],//用来保存搜索的全部结果
          goodsInfoId:"",
          goodsInfoImg:{},
          goodsInfoName:"",
          goodsInfoDscrip:"",
          goodsInfoPrice: 0,
          centerDialogVisible:false,
          dialogValue: "",
          centerDialogVisible2:false,
          goToShopCar:true,
          currentPage:1, //初始页
          pageSize:12, //每页的数据
          paginationShow: false,//默认不显示分页
          searchFlag:false,//用来避免频繁向服务器发送数据
          fullscreenLoading: false,//模拟加载
        };
        let checkString = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('请填写内容'));
          } else {
            callback();
          }
        };
      },
      mounted() {
        //加载数据

        //加载热门精品数据，服务端返回最多 12条 数据
        let self = this;

        //延迟 0.3 S 绑定数据
        self.fullscreenLoading = true;
        setTimeout(() => {
          self.fullscreenLoading = false;
        }, 300);

        $.get("http://localhost:8083/goods/getIndexGoods.do/1",function (data) {
          self.data1 = data;
          $(self.data1).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            //为每个表格元素加载图片数据，主图
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.data1,index,element);
            },"json");
          });
        },"json");

        //加载低价好物数据，服务端返回最多 12条 数据
        $.get("http://localhost:8083/goods/getIndexGoods.do/2",function (data) {
          self.data2 = data;
          $(self.data2).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            //为每个表格元素加载图片数据，主图
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.data2,index,element);
            },"json");
          });
        },"json");

        //加载 9 新以上数据，服务端返回最多 12条 数据
        $.get("http://localhost:8083/goods/getIndexGoods.do/3",function (data) {
          self.data3 = data;
          $(self.data3).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            //为每个表格元素加载图片数据，主图
            element.picture = "";
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.data3,index,element);
            },"json");
          });
        },"json");
      },
      updated() {
        //当搜索框的值被清空
        //加载热门精品数据，服务端返回 12条 数据
        let self = this;
        //当搜索框无内容且已经搜索过，那么就是清空了搜索框，这时重新加载初始数据
        if(this.search === "" && this.searchFlag){
          //加载热门精品数据，服务端返回最多 12条 数据
          $.get("http://localhost:8083/goods/getIndexGoods.do/1",function (data) {
            //延迟 0.3 S 绑定数据
            self.fullscreenLoading = true;
            setTimeout(() => {
              self.fullscreenLoading = false;
            }, 300);
            self.data1 = data;
            self.dataSearch = [];//重置搜索结果内容为空数组
            self.searchFlag = false;
            $(self.data1).each(function (index,element) {
              let jsonObj = {};
              jsonObj.goodsId = element.goodsId;
              //为每个表格元素加载图片数据，主图
              $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
                //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
                element.picture = "http://localhost:9090/" + data.imgUrl;
                //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
                self.$set(self.data1,index,element);
              },"json");
            });
          },"json");
          //隐藏分页组件
          this.paginationShow = false;
        }
      },
      methods:{
        //点击查看详情
        clickGoodsInfoButton(goodsId){
          this.centerDialogVisible = true;
          this.goodsInfoId = goodsId;
          let self = this;
          let jsonObj = {};
          jsonObj.goodsId = goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          $.get("http://localhost:8083/goods/getGoodsById.do",jsonObj,function (data) {
            self.goodsInfoName = data.name;
            self.goodsInfoPrice = data.price;
            self.goodsInfoDscrip = data.dscrip;
            $.get("http://localhost:8083/goods/getGoodsImgMap.do",jsonObj,function (data) {
              self.goodsInfoImg = data;
            },"json");
          },"json");
        },
        //点击加入购物车按钮
        addToShopCar(){
          let jsonObj = {};
          jsonObj.userId = window.sessionStorage.getItem("userId");
          jsonObj.goodsId = this.goodsInfoId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/shopCar/addOneToShopCar.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "加入购物车成功";
              self.goToShopCar = false;
            }else{
              self.dialogValue = "加入购物车失败，错误码：" + data.code;
              self.goToShopCar = true;
            }
          },"json");
          this.centerDialogVisible2 = true;
        },
        // 获取长度为len的随机字符串
        getRandomString(len) {
          len = len || 32;
          let $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
          let maxPos = $chars.length;
          let pwd = '';
          for (let i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
          }
          return pwd;
         },
        clickButton(){
          this.centerDialogVisible = false;
          this.centerDialogVisible2 = false;
        },
        //点击跳转购物车按钮
        clickGoToShopCar(){
          this.clickButton();
          this.$router.push('shopCar');
        },
        // 初始页currentPage、初始每页数据数pageSize和数据data
        // 点击了分页组件换页按钮
        handleCurrentChange(currentPage){
          this.currentPage = currentPage;
          // console.log(this.currentPage)  //点击第几页
          //切割 dataSearch 赋值给 data1
          // this.data1 = this.dataSearch.slice((this.currentPage-1)*12,this.currentPage*12);
          this.data1 = this.mySlice(this.dataSearch,(this.currentPage-1)*12,this.currentPage*12);
        },
        //点击搜索按钮
        clickSearch(){
          if(this.search !== "") {
            let self = this;

            //延迟 0.3 S 绑定数据
            self.fullscreenLoading = true;
            setTimeout(() => {
              self.fullscreenLoading = false;
            }, 300);

            let jsonObj = {};
            jsonObj.text = this.search;
            $.get("http://localhost:8083/goods/getSearchGoods.do", jsonObj, function (data) {
              //保存搜索内容
              self.dataSearch = data;
              $(self.dataSearch).each(function (index,element) {
                let jsonObj = {};
                jsonObj.goodsId = element.goodsId;
                //为每个表格元素加载图片数据，主图
                $.get("http://localhost:8083/goods/getGoodsMainImg.do", jsonObj, function (data) {
                  //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
                  element.picture = "http://localhost:9090/" + data.imgUrl;
                  //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
                  self.$set(self.dataSearch, index, element);
                }, "json");
              });
              //设置搜索过，用于清空搜索框时还原初始数据
              self.searchFlag = true;

              //延迟 0.8 S 绑定数据
              self.fullscreenLoading = true;
              setTimeout(() => {
                self.fullscreenLoading = false;
              }, 800);

              //当 dataSearch 的长度大于 12，显示分页组件，否则隐藏
              if(self.dataSearch.length > 12){
                self.paginationShow = true;
              }else{
                self.paginationShow = false;
              }

              //如果分页组件被展示，切割 data1
              if(self.paginationShow === true){
                // this.data1 = this.dataSearch.slice((this.currentPage-1)*12,this.currentPage*12);
                self.data1 = self.mySlice(self.dataSearch,(self.currentPage-1)*12,self.currentPage*12);
                // console.log(123);
              }else{
                //如果没有展示分页组件，并且搜索了数据
                self.data1 = self.dataSearch;
                // console.log(this.dataSearch);
              }
            }, "json");
          }
        },
        //计算打印的行数
        getRowNums(data){
          let len = parseInt(data.length/4);
          if(len === 0){
            return 1;
          }else{
            if(data.length - len*4 > 0){
              return len + 1;
            }else{
              return len;
            }
          }
        },
        //数组切割
        mySlice(data,indexStart,indexEnd){
          if(indexStart < 0)
            indexStart = 0;
          if(data.length >= indexEnd){
            return data.slice(indexStart,indexEnd);
          }else{
            if(indexStart < data.length){
              return data.slice(indexStart,data.length);
            }else{
              return [];
            }
          }
        }
      }
    }
</script>

<style scoped>
  .index-body{
    width: 80%;
    margin: 0 auto;
  }
  .index-body-p-1{
    font-weight: bold;
    font-size: 30px;
    color: black;
    margin-top: 30px;
  }
</style>
