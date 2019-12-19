<template>
    <div class="loginDivClass">
      <el-card shadow="always" style="top:50px;margin: 0 auto;width: 29%;min-height: 650px;position: relative;">
        <div style="text-align:center;margin-top: 30px;">
          <img src="../../assets/jishou.jpg" alt="logoImg" style="max-width: 90px;">
          <h3 style="font-weight: 400;margin-top: 20px;">登录</h3>
        </div>
        <div style="text-align: center;margin-left: 20px;margin-top: 50px;">
          <el-input placeholder="请输入用户名" v-model="inputUsername" clearable maxlength="10"
                    show-word-limit>
            <template slot="prepend">用户名：</template>
          </el-input>
          <el-input placeholder="请输入密码" style="margin-top: 50px;" v-model="inputPassword" show-password maxlength="30"
                    show-word-limit>
            <template slot="prepend"><span style="padding-left: 7.2px;padding-right: 7.2px;">密码：</span></template>
          </el-input>
        </div>
        <div style="text-align: center;margin-left: 10px;margin-top: 110px;">
          <el-button type="primary" style="min-width: 80%;" @click="login()" :disabled="loginButton">登录</el-button>
        </div>
        <el-row type="flex" class="row-bg" justify="center" style="top: 60px;">
          <el-col :span="24">
            <h6 style="text-align: center;color: #757575;">吉首大学二手交易系统 版权所有</h6>
            <h6 style="text-align: center;color: #757575;">Copyright © 2019</h6>
          </el-col>
        </el-row>
      </el-card>

<!--      弹出提示框-->
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
    </div>
</template>

<script>
    //调用state的语法
    // import { mapMutations } from 'vuex'
    export default {
        name: "login",
      data(){
          return{
            "inputUsername":"",
            "inputPassword":"",
            "dialogValue":"登陆失败",
            "centerDialogVisible":false,
            "loginButton":true
          }
      },
      //数据更新完的生命周期钩子
      updated() {
          //更改登录按钮是否可点击的状态
          if(this.check()){
            this.loginButton = false;
          }else{
            this.loginButton = true;
          }
      },
      //计算属性
      // computed:{
      //     //解构绑定
      //   // ...mapGetters(["getIsLogin"])
      // },
      methods:{
          // ...mapMutations(["isLogin","setUserId"]),
          login() {
            let jsonObj = {};
            jsonObj.username = this.inputUsername;
            jsonObj.password = this.inputPassword;
            let jsonMsg = JSON.stringify(jsonObj);
            let self = this;
            // $.post("http://localhost:8083/user/login.do", jsonMsg, function (data) {
            //   // alert(data.userId);
            //   if (data != null && data.userId != null && data.userId !== "") {
            //     //登录成功
            //     // self.$store.commit("isLogin");
            //     // self.$store.commit("setUserId",data.userId);
            //     // self.$store.commit("setName",data.name);
            //     window.sessionStorage.setItem("isLogin",true);
            //     window.sessionStorage.setItem("userId",data.userId);
            //     window.sessionStorage.setItem("name",data.name);
            //     // window.location.href = "/"; 这句话会导致BUG
            //     self.dialogValue = "登录成功";
            //     self.centerDialogVisible = true;
            //   } else {
            //     self.centerDialogVisible = true;
            //   }
            // }, "json");
            $.ajax({
              type:"POST",
              url:"http://localhost:8083/user/login.do",
              dataType:"json",
              contentType:"application/json",
              data:JSON.stringify(jsonObj),
              success:function(data){
                if (data != null && data.userId != null && data.userId !== "") {
                  //登录成功
                  // self.$store.commit("isLogin");
                  // self.$store.commit("setUserId",data.userId);
                  // self.$store.commit("setName",data.name);
                  window.sessionStorage.setItem("isLogin",true);
                  window.sessionStorage.setItem("userId",data.userId);
                  window.sessionStorage.setItem("name",data.name);
                  // window.location.href = "/"; 这句话会导致BUG
                  self.dialogValue = "登录成功";
                  self.centerDialogVisible = true;
                } else {
                  self.centerDialogVisible = true;
                }
              }
            });
          },
          check(){
            //与注册完全吻合
            //纯字母4-10位
            let usernameRex = /^[a-zA-Z]{4,10}$/;
            //6到30位数字、字母、特殊符号
            let passwordRex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,30}$/;
            if(usernameRex.test(this.inputUsername) && passwordRex.test(this.inputPassword))
              return true;
            else
              return false;
          },
        clickButton(){
          if(this.dialogValue === "登录成功"){
            this.$router.push("/");
          }else{
            this.centerDialogVisible = false;
          }
        }
      }
    }
</script>

<style scoped>
  .loginDivClass{
    background: url("https://i.loli.net/2019/12/15/A2XS7tNrHCM9f4R.png");
    height: 792px;
  }
</style>
