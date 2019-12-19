<template>
  <div class="index-header">
<!--    顶栏-->
    <nav class="navbar index-header-bar">
      <el-row>
        <el-col :span="3" :offset="3">
          <span>吉首大学校园二手交易平台</span>
        </el-col>
        <el-col :span="10" :offset="5">
          <el-row>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <a href="/">首页</a>
            </el-col>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <a href="#" data-toggle="dropdown">登录/注册</a>
              <ul class="dropdown-menu my-dropdown-menu-1">
                <li>
<!--                  <a href="#" class="my-dropdown-menu-li-1">登录</a>-->
                  <a class="my-dropdown-menu-li-1" @click="checkLogin1">登录</a>
                </li>
                <li>
<!--                  <a href="#" class="my-dropdown-menu-li-1">注册</a>-->
                  <a class="my-dropdown-menu-li-1" @click="checkLogin2">注册</a>
                </li>
              </ul>
            </el-col>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <router-link to="shopCar"><img src="../../assets/shopcar.png" alt="" style="width: 20px;"> 购物车</router-link>
            </el-col>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <router-link to="oldSail">闲置卖钱</router-link>
            </el-col>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <a href="#" data-toggle="dropdown">个人中心</a>
              <ul class="dropdown-menu my-dropdown-menu-1">
                <li>
                  <a class="my-dropdown-menu-li-1" @click="checkLogin4">个人信息</a>
                </li>
                <li>
                  <a class="my-dropdown-menu-li-1" @click="checkLogin3">已购订单</a>
                </li>
                <li>
                  <a class="my-dropdown-menu-li-1" @click="checkLogin5">个人闲置</a>
                </li>
              </ul>
            </el-col>
            <el-col :span="3" :offset="1" class="index-header-bar-span">
              <a href="#" data-toggle="dropdown">{{ name }}</a>
              <ul class="dropdown-menu my-dropdown-menu-1">
                <li>
                  <!--                  <a href="#" class="my-dropdown-menu-li-1">登录</a>-->
                  <a class="my-dropdown-menu-li-1" @click="logout">退出</a>
                </li>
              </ul>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </nav>
<!--    顶栏结束-->

<!--    导航条-->
<!--    <el-row>-->
<!--      <el-col :span="20" :offset="2">-->
<!--        <el-menu :default-active="activeIndex" class="el-menu-demo my-el-menu-1" mode="horizontal">-->
<!--          <el-menu-item index="1" class="my-el-menu-item-1">首页</el-menu-item>-->
<!--          <el-menu-item index="2" class="my-el-menu-item-1">数码设备区</el-menu-item>-->
<!--          <el-menu-item index="3" class="my-el-menu-item-1">日用品区</el-menu-item>-->
<!--          <el-menu-item index="4" class="my-el-menu-item-1">其他 / 杂项</el-menu-item>-->
<!--        </el-menu>-->
<!--      </el-col>-->
<!--    </el-row>-->
<!--    导航条结束-->

    <!--      弹出提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="centerDialogVisible = false" style="margin-top: 30px;">确 定</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    //import { mapGetters } from 'vuex'
    export default {
      name: "index-header",
      data(){
        return{
          activeIndex: '1',
          centerDialogVisible: false,
          dialogValue:"",
          name:window.sessionStorage.getItem("name")
        }
      },
      //计算属性
      // computed:{
      //   ...mapGetters(["getName","getIsLogin"])
      // },
      methods:{
        checkLogin1(){
          if(!window.sessionStorage.getItem("isLogin")){
            this.$router.push("/login");
          }else{
            this.dialogValue = "请先登出账户";
            this.centerDialogVisible = true;
          }
        },
        checkLogin2(){
          if(!window.sessionStorage.getItem("isLogin")){
            this.$router.push("/register");
          }else{
            this.dialogValue = "请先登出账户";
            this.centerDialogVisible = true;
          }
        },
        checkLogin3(){
          if(window.sessionStorage.getItem("isLogin")){
            this.$router.push("/person#ordersDivId");
          }else{
            this.dialogValue = "请先登录账户";
            this.centerDialogVisible = true;
          }
        },
        checkLogin4(){
          if(window.sessionStorage.getItem("isLogin")){
            this.$router.push("/person#personInfoId");
          }else{
            this.dialogValue = "请先登录账户";
            this.centerDialogVisible = true;
          }
        },
        checkLogin5(){
          if(window.sessionStorage.getItem("isLogin")){
            this.$router.push("/person#personOldId");
          }else{
            this.dialogValue = "请先登录账户";
            this.centerDialogVisible = true;
          }
        },
        logout(){
          window.sessionStorage.clear();
          window.location.reload();
        }
      }
    }
</script>

<style scoped>
  .index-header-bar{
    border-radius: 0;
    line-height: 36px;
    background-color: rgb(140,34,44);
    position:fixed;
    width: 100%;
    z-index: 999;
  }
  .navbar{
    height: 36px;
    min-height: 0;
  }
  .index-header-bar span{
    color: #e2c8ca;
    font-size: 12px;
    line-height: 34px;
  }
  .index-header-bar span:hover{
    color: white;
  }
  .index-header-bar-span{
    color: #e2c8ca;
    font-size: 12px;
    text-align: center;
    line-height: 34px;
    position: relative;
  }
  .index-header-bar-span:hover{
    background-color: rgb(123,17,27);
  }
  .index-header-bar a{
    color: #e2c8ca;
    font-size: 12px;
    line-height: 34px;
    text-decoration: none;
    border: 0;
  }
  .index-header-bar a:hover{
    color: white;
  }
  .my-dropdown-menu-1{
    background-color: rgb(140,34,44);
    text-align: center;
    min-width: 80px;
  }
  .my-dropdown-menu-li-1:hover{
    background-color: rgb(123,17,27);
  }
  .my-el-menu-1{
    border-bottom: 0;
  }
  .my-el-menu-1>.el-menu-item.is-active{
    border-bottom: 2px solid rgb(181,170,154);
  }
  .my-el-menu-item-1{
    margin-left: 5%;
    line-height: 50px;
    height: 50px;
  }
</style>
