<template>

  <div id="app">
    <Nav></Nav>
    <router-view />
  </div>
</template>

<script>
  import Nav from './components/Nav'

  export default {
    head: {
      link: [
        {rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Source+Sans+Pro'},
      ],
    },
    name: 'App',
    components: {
      Nav
    }
  }
</script>

<style>
  #app {
    font-family: 'Source Sans Pro', 'APPLE SD Gothic Neo', HelveticaNeue, Helvetica, 'NanumGothic', '맑은 고딕';
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #030A15;
  }
</style>
