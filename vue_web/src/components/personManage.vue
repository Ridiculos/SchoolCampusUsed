<template>
    <div class="personManageDiv">
      <!--      首页条-->
      <index-header></index-header>
      <!--      个人信息-->
      <person-info></person-info>
      <!--      订单模块-->
      <person-orders></person-orders>
      <!--      我的闲置-->
      <person-old></person-old>
      <!--      首页尾-->
      <index-footer></index-footer>
    </div>
</template>

<script>
    import IndexHeader from './pages/index-header'
    import IndexFooter from './pages/index-footer'
    import PersonOrders from './pages/person-orders'
    import PersonInfo from './pages/person-info'
    import PersonOld from './pages/person-old'
    export default {
      name: "personManage",
      components:{
        IndexHeader,PersonOrders,IndexFooter,PersonInfo,PersonOld
      }
    }
</script>

<style scoped>
  .personManageDiv{

  }
  h2{
    font-weight: 400;
    color: #1f2f3d;
    font-size: 28px;
  }
</style>
