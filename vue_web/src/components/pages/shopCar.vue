<template>
    <div>
      <!--      首页条-->
      <index-header></index-header>

      <!--网页主体-->
      <div class="shopCarDiv">
        <h2><i class="el-icon-goods" style="color: rgb(92,182,255)"></i>&nbsp;我的购物车：</h2>
        <el-table
          :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
          style="width: 100%">
          <el-table-column
            label="商品图">
            <template slot-scope="scope">
              <img :src="scope.row.picture" alt="无商品图" width="80" height="80">
            </template>
          </el-table-column>
          <el-table-column
            label="商品名"
            prop="name">
          </el-table-column>
          <el-table-column
            label="价格"
            prop="price">
          </el-table-column>
          <el-table-column
            label="商品ID"
            prop="goodsId">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="输入商品名搜索"/>
            </template>
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit2(scope.$index, scope.row)">单独购买</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleEdit(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="18" :offset="1">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[5, 10, 20, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableData.length">
            </el-pagination>
          </el-col>
          <el-col :span="5">
            <el-button type="primary" @click="buyAll">全部购买</el-button>
          </el-col>
        </el-row>
      </div>

      <!--      删除结果提示框-->
      <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
        <div style="text-align: center">
          <h4>{{ dialogValue }}</h4>
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="clickButton" style="margin-top: 30px;">确 定</el-button>
          </span>
        </div>
      </el-dialog>

      <!--      支付框提示框-->
      <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible2"
        width="30%"
        center>
        <div style="text-align: center">
          <h4>{{ dialogValue2 }}</h4>
          <img src="https://i.loli.net/2019/12/17/3rPjiINl9uS5Jdz.jpg" alt="请检查网络连接" width="240" height="240">
          <br/>
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="centerDialogVisible2 = false" style="margin-top: 30px;">取消支付</el-button>
          </span>
        </div>
      </el-dialog>

      <!--      首页尾-->
      <index-footer></index-footer>
    </div>
</template>

<script>
    import IndexHeader from './index-header'
    import IndexFooter from './index-footer'
    export default {
      name: "shopCar",
      data(){
          return{
            currentPage:1, //初始页
            pageSize:5, //每页的数据
            tableData: [],
            search: '',
            dialogValue: '',
            centerDialogVisible: false,
            centerDialogVisible2: false,
            dialogValue2: '',
          }
      },
      components: {
        IndexHeader,IndexFooter
      },
      mounted() {
        //加载数据
        let jsonObj = {};
        jsonObj.userId = window.sessionStorage.getItem("userId");
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        //加载文字数据
        $.get("http://localhost:8083/shopCar/findAllByUid.do",jsonObj,function (data) {
          self.tableData = data;
          //加载商品主图
          $(self.tableData).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.tableData,index,element);
            },"json");
            //取得商品其他信息
            $.get("http://localhost:8083/goods/getGoodsById.do",jsonObj,function (data) {
              element.name = data.name;
              element.price = data.price;
              //引起 Vue 重新渲染
              self.$set(self.tableData,index,element);
            },"json");
          });
        },"json");
      },
      methods: {
        // 初始页currentPage、初始每页数据数pageSize和数据data
        handleSizeChange: function (size) {
          this.pageSize = size;
          // console.log(this.pageSize)  //每页下拉显示数据
        },
        handleCurrentChange: function (currentPage) {
          this.currentPage = currentPage;
          // console.log(this.currentPage)  //点击第几页
        },
        handleEdit(index, row) {
          //点击了删除按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          jsonObj.userId = window.sessionStorage.getItem("userId");
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/shopCar/deleteOneByUidAndGid.do", jsonMsg, function (data) {
            if (data.code === 1) {
              self.dialogValue = "删除成功";
              self.initShopCar();
            } else {
              self.dialogValue = "删除失败";
            }
            self.centerDialogVisible = true;
          }, "json");
        },
        handleEdit2(index, row) {
          //点击了单独购买按钮
          // console.log(index, row);
          this.dialogValue2 = "总价为：" + row.price + "\r\n请扫码支付";
          this.centerDialogVisible2 = true;

          let self = this;

          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          jsonObj.userId = window.sessionStorage.getItem("userId");
          let jsonMsg = JSON.stringify(jsonObj);

          $.post("http://localhost:8083/shopCar/deleteOneByUidAndGid.do",jsonMsg,function (data) {
            //这里的代码因为只是模拟，实际上存在逻辑错误
            if(data.code === 1){
              //创建订单
              $.post("http://localhost:8083/orders/createOrder.do",jsonMsg,function (data) {
                if(data.code === 1){
                  //模拟支付
                  //15秒后自动支付成功
                  setTimeout(function () {
                    self.centerDialogVisible2 = false;
                    self.dialogValue = "支付成功";
                    self.centerDialogVisible = true;
                    self.initShopCar();
                  }, 10000);
                }else{
                  //模拟支付
                  //15秒后自动支付成功
                  setTimeout(function () {
                    self.centerDialogVisible2 = false;
                    self.dialogValue = "支付失败";
                    self.centerDialogVisible = true;
                    self.initShopCar();
                  }, 10000);
                }
              },"json");
            }
          },"json");
        },
        clickButton() {
          this.centerDialogVisible = false;
        },
        buyAll() {
          let price = 0;
          for (let i = 0; i < this.tableData.length; i++) {
            price += this.tableData[i].price;
          }
          this.dialogValue2 = "总价为：" + price + "\r\n请扫码支付";
          this.centerDialogVisible2 = true;

          let self = this;
          let jsonObj = {};
          jsonObj.userId = window.sessionStorage.getItem("userId");
          let jsonMsg = JSON.stringify(jsonObj);
          //创建订单
          $.post("http://localhost:8083/orders/createOrders.do",jsonMsg,function (data) {
            if(data.code === 1){
              //订单创建成功，删除购物车内商品
              $.post("http://localhost:8083/shopCar/deleteAllByUid.do",jsonMsg,function (data) {
                //这里的代码因为只是模拟，实际上存在逻辑错误，这里应该全部交给后端
                if(data.code === 1){
                  //模拟支付
                  //15秒后自动支付成功
                  setTimeout(function () {
                    self.centerDialogVisible2 = false;
                    self.dialogValue = "支付成功";
                    self.centerDialogVisible = true;
                    self.initShopCar();
                  }, 10000);
                }else{
                  //模拟支付
                  //15秒后自动支付成功
                  setTimeout(function () {
                    self.centerDialogVisible2 = false;
                    self.dialogValue = "支付失败";
                    self.centerDialogVisible = true;
                    self.initShopCar();
                  }, 10000);
                }
              },"json");
            }
          },"json");
        },
        initShopCar() {
          //重载数据
          let jsonObj = {};
          jsonObj.userId = window.sessionStorage.getItem("userId");
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          //加载文字数据
          $.get("http://localhost:8083/shopCar/findAllByUid.do",jsonObj,function (data) {
            self.tableData = data;
            //加载商品主图
            $(self.tableData).each(function (index,element) {
              let jsonObj = {};
              jsonObj.goodsId = element.goodsId;
              $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
                //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
                element.picture = "http://localhost:9090/" + data.imgUrl;
                //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
                self.$set(self.tableData,index,element);
              },"json");
              //取得商品其他信息
              $.get("http://localhost:8083/goods/getGoodsById.do",jsonObj,function (data) {
                element.name = data.name;
                element.price = data.price;
                //引起 Vue 重新渲染
                self.$set(self.tableData,index,element);
              },"json");
            });
          },"json");
        },
      }
    }
</script>

<style scoped>
  .shopCarDiv{
    width: 80%;
    margin: 0 auto;
    padding-top: 80px;
  }
</style>
