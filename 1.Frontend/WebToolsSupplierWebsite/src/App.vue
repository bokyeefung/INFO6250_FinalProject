<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    let that = this;
    that.$store.state.windowSize.width = document.body.clientWidth;
    that.$store.state.windowSize.height = document.body.clientHeight;
    window.onresize = () => {
      that.$store.state.windowSize.width = document.body.clientWidth;
      that.$store.state.windowSize.height = document.body.clientHeight;
    }
  }
}
</script>

<style>
#app {
  width: 100vw;
  min-width: 300px;
  height: 100vh;
  overflow: hidden;
  background-color: #FAFAFA;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
