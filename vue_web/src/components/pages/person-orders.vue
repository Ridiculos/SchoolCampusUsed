<template>
    <div class="ordersDiv" id="ordersDivId">
      <h2>我的订单：</h2>
      <!--数据列表-->
      <!--根据商品名搜索-->
      <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
        style="width: 100%">
<!--        <el-table-column-->
<!--          label="订单ID"-->
<!--          prop="ordersId">-->
<!--        </el-table-column>-->
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
          label="购买时间"
          prop="time">
        </el-table-column>
        <el-table-column
          label="订单状态"
          prop="ordersStatusText">
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
              @click="handleEdit(scope.$index, scope.row)">反馈</el-button>
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

      <!--      反馈弹出框-->
      <el-dialog
        title="反馈"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
        <div style="text-align: center">
          <el-form :model="feedbackForm">
            <el-form-item label="订单ID" label-position="top">
              <el-input v-model="feedbackForm.ordersId" autocomplete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="反馈类型" label-position="top">
              <el-select v-model="feedbackForm.feedbacksStatus" placeholder="请选择反馈类型" style="width: 100%;">
                <el-option label="投诉" :value=1></el-option>
                <el-option label="建议" :value=0></el-option>
                <el-option label="其他" :value=-1></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="反馈内容" label-position="top">
              <el-input
                type="textarea"
                placeholder="请输入反馈内容"
                v-model="feedbackForm.value"
                maxlength="250"
                show-word-limit
              >
              </el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFeedback" style="margin-top: 30px;">提交反馈</el-button>
          </span>
        </div>
      </el-dialog>
      <!--      反馈结果提示框-->
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
      name: "person-orders",
      data() {
        return {
          tableData: [],
          search: '',
          centerDialogVisible: false,
          centerDialogVisible2: false,
          feedbackForm: {
            ordersId: "",
            feedbacksStatus: -1,
            value: "",
            userId:"",
            goodsId:"",
            payId:"",
            feedbacksTime:"",
            solveStatus:0,
            solveValue:"",
            name:""
          },
          dialogValue: '',
          currentPage:1, //初始页
          pageSize:5, //每页的数据
        }
      },
      mounted() {
        //加载数据
        let jsonObj = {};
        jsonObj.userId = window.sessionStorage.getItem("userId");
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        //加载文字数据
        $.get("http://localhost:8083/orders/getOrdersAllByUserId.do",jsonObj,function (data) {
          self.tableData = data;
          //更改前端显示文字
          $(self.tableData).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            if(element.ordersStatus === 1){
              element.ordersStatusText = "已完成";
            }else if(element.ordersStatus === 0){
              element.ordersStatusText = "未完成";
            }else{
              element.ordersStatusText = "有异议";
            }

            // if (element.adminStatus === 1){
            //   element.adminStatusText = "已处理";
            // }else{
            //   element.adminStatusText = "未处理或无反馈";
            // }
            //为每个表格元素加载图片数据，主图
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口，部署到远程服务器需要修改这里，服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.tableData,index,element);
            },"json");
            //取得商品对应信息
            $.get("http://localhost:8083/goods/getGoodsById.do",jsonObj,function (data) {
              element.name = data.name;
              //引起 Vue 重新渲染
              self.$set(self.tableData,index,element);
            },"json");
          });
        },"json");
      },
      methods: {
        handleEdit(index, row) {
          //点击了反馈按钮
          // console.log(index, row);
          this.feedbackForm.ordersId = row.ordersId;
          this.centerDialogVisible = true;
          this.feedbackForm.feedbacksStatus = -1;
          this.feedbackForm.value = "";
          this.feedbackForm.userId = window.sessionStorage.getItem("userId");
          this.feedbackForm.name = row.name;
          this.feedbackForm.feedbacksTime = new Date().toLocaleString();
          //模拟支付
          this.feedbackForm.payId = row.goodsId;
          this.feedbackForm.goodsId = row.goodsId;
        },
        submitFeedback(){
          // console.log("点击了提交反馈");
          let self = this;
          let jsonMsg = JSON.stringify(this.feedbackForm);
          $.post("http://localhost:8083/feedback/submitFeedback.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "提交成功";
            }else{
              self.dialogValue = "提交失败，错误码" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
        },
        clickButton(){
          this.centerDialogVisible2 = false;
          if(this.dialogValue === "提交成功"){
            this.centerDialogVisible = false;
          }
        },
        // 初始页currentPage、初始每页数据数pageSize和数据data
        handleSizeChange: function (size) {
          this.pageSize = size;
          // console.log(this.pageSize)  //每页下拉显示数据
        },
        handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
          // console.log(this.currentPage)  //点击第几页
        },
      }
    }
</script>

<style scoped>
  .ordersDiv{
    width: 80%;
    margin: 0 auto;
    padding-top: 50px;
  }
</style>
