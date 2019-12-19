<template>
  <!--    logo-->
  <el-row class="my-el-row-2">
    <el-col :span="16" :offset="4" class="my-el-col-2" style="margin-top: 40px;">
      <img src="../../assets/logo.png" alt="吉首大学二手交易平台">
    </el-col>
  </el-row>
  <!--    logo结束-->
</template>

<script>
    export default {
        name: "index-logo"
    }
</script>

<style scoped>
  .my-el-row-2{
    height: 260px;
    background-color: rgb(242,241,234);
    text-align: center;
  }
  .my-el-col-2{
    padding-top: 50px;
  }
</style>
