<template>
  <div class="personOldDiv" id="personOldId">
    <h2>我的闲置：</h2>
    <!--数据列表-->
    <!--根据商品名搜索-->
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
<!--      <el-table-column-->
<!--        label="商品ID"-->
<!--        prop="goodsId">-->
<!--      </el-table-column>-->
      <el-table-column
        label="商品图">
        <template slot-scope="scope">
          <img :src="scope.row.picture" alt="加载中..." width="80" height="80">
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
        label="新旧程度"
        prop="goodsLevel">
      </el-table-column>
      <el-table-column
        label="热度"
        prop="hot">
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
            @click="handleEdit(scope.$index, scope.row)">修改介绍</el-button>
          <el-button
            size="mini"
            @click="handleEdit2(scope.$index, scope.row)">取消出售</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>


    <!--      商品介绍弹出框-->
    <el-dialog
      title="商品介绍"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <div style="text-align: center">
        <el-form :model="goodsForm">
          <el-form-item label="商品ID" label-position="top">
            <el-input v-model="goodsForm.goodsId" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品名称" label-position="top">
            <el-input v-model="goodsForm.name" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品介绍" label-position="top">
            <el-input
              type="textarea"
              placeholder="请输入商品介绍"
              v-model="goodsForm.dscrip"
              maxlength="250"
              show-word-limit
            >
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitDscrip" style="margin-top: 30px;">保存</el-button>
        </span>
      </div>
    </el-dialog>

    <!--      弹出提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible2"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="clickButton" style="margin-top: 30px;">确 定</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
      name: "person-old",
      data(){
        return{
          tableData: [],
          search: '',
          currentPage:1, //初始页
          pageSize:5, //每页的数据
          goodsForm:{
            goodsId:"",
            name:"",
            dscrip:"",
          },
          centerDialogVisible: false,
          centerDialogVisible2: false,
          dialogValue:"",
        }
      },
      mounted() {
        //加载数据
        let jsonObj = {};
        jsonObj.userId = window.sessionStorage.getItem("userId");
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        //加载文字数据
        $.get("http://localhost:8083/goods/getMyGoods.do",jsonObj,function (data) {
          self.tableData = data;
          //为每个表格元素加载图片数据，主图
          $(self.tableData).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.tableData,index,element);
            },"json");
          });
        },"json");
      },
      methods:{
        // 初始页currentPage、初始每页数据数pageSize和数据data
        handleSizeChange: function (size) {
          this.pageSize = size;
          // console.log(this.pageSize)  //每页下拉显示数据
        },
        handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
          // console.log(this.currentPage)  //点击第几页
        },
        handleEdit(index, row) {
          //点击了商品介绍按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.get("http://localhost:8083/goods/getGoodsDscrip.do",jsonObj,function (data) {
            if(data !== "" || data !== null) {
              self.goodsForm.goodsId = data.goodsId;
              self.goodsForm.name = data.name;
              self.goodsForm.dscrip = data.dscrip;
              self.centerDialogVisible = true;
            }
          },"json");
        },
        handleEdit2(index, row) {
          //点击了取消出售按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/goods/deleteMyGoodsAndUrls.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "删除成功";
            }else{
              self.dialogValue = "删除失败，错误码：" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
          window.location.reload();
        },
        submitDscrip(){
          //点击了保存按钮
          let jsonObj = {};
          jsonObj.goodsId = this.goodsForm.goodsId;
          jsonObj.dscrip = this.goodsForm.dscrip;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/goods/saveGoodsDscrip.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "保存成功";
            }else{
              self.dialogValue = "保存失败，错误码：" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
          window.location.reload();
        },
        clickButton(){
          this.centerDialogVisible2 = false;
          this.centerDialogVisible = false;
        }
      }
    }
</script>

<style scoped>
  .personOldDiv{
    width: 80%;
    margin: 0 auto;
    padding-top: 50px;
  }
</style>
