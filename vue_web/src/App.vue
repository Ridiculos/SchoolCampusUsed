<template>
  <div id="app">
    <router-view name="web-body"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  *{
    margin: 0;
    padding: 0;
  }
  a,a:visited,a:link{
    text-decoration: none;
  }
</style>
