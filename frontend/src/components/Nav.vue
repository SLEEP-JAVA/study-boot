<template>
  <el-menu :default-active=activeIndex mode="horizontal" @select="handleSelect">
    <el-menu-item index="1">회원가입 </el-menu-item>
    <el-submenu index="2">
      <template slot="title">스터디 보기  </template>
      <el-menu-item index="2-1">지금 핫한 스터디 🔥</el-menu-item>
      <el-menu-item index="2-2">마감된 스터디 😢</el-menu-item>
      <el-menu-item index="2-3">종료된 스터디 ✅</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
  export default {
    name: 'Nav',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>

</style>
