<template>
  <div class="index">
    <!--首页头-->
    <index-header></index-header>
    <!--首页 Logo-->
    <index-logo></index-logo>
    <!--首页主体-->
    <index-body></index-body>
    <!--首页尾-->
    <index-footer></index-footer>
  </div>
</template>

<script>
    import IndexHeader from './pages/index-header.vue';
    import IndexBody from './pages/index-body';
    import IndexFooter from './pages/index-footer';
    import IndexLogo from './pages/index-logo'
    export default {
      name: "index",
      components:{
          IndexHeader,IndexBody,IndexFooter,IndexLogo
      }
    }
</script>

<style scoped>

</style>
