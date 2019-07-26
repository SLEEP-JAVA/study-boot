<template>
  <el-menu :default-active=activeIndex mode="horizontal" :router=true>
    <el-menu-item index="1"  :route="{name:'Home'}">메인 </el-menu-item>
    <el-menu-item index="2"  :route="{name:'Signup'}">회원가입 </el-menu-item>
    <el-submenu index="3">
      <template slot="title">스터디 보기  </template>
      <el-menu-item active index="3-1" :route="{name:'Study'}">지금 핫한 스터디 🔥</el-menu-item>
      <el-menu-item index="3-2">마감된 스터디 😢</el-menu-item>
      <el-menu-item index="3-3">종료된 스터디 ✅</el-menu-item>
    </el-submenu>
    <el-menu-item index="4" :route="{name: 'StudyForm'}"> <el-button type="primary" icon="el-icon-edit" round >스터디 만들기</el-button></el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: 'Nav',
    data() {
      return {
        activeIndex: '1',
      };
    },
    methods: {
    }
  }
</script>

<style>

</style>
