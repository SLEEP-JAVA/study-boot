<template>
  <el-menu :default-active=activeIndex mode="horizontal" :router=true>
    <el-menu-item index="1" :route="{name:'Home'}">메인</el-menu-item>
    <el-submenu index="2">
      <template slot="title">스터디 보기</template>
      <el-menu-item active index="2-1" :route="{name:'Study'}">지금 핫한 스터디 🔥</el-menu-item>
      <el-menu-item index="2-2">마감된 스터디 😢</el-menu-item>
      <el-menu-item index="2-3">종료된 스터디 ✅</el-menu-item>
    </el-submenu>
    <el-menu-item index="3" :route="{name: 'StudyForm'}">
      <el-button type="primary" icon="el-icon-edit" round>스터디 만들기</el-button>
    </el-menu-item>
    <el-menu-item style="float: right">
      <!-- TODO v-if로 로그인 시 로그아웃 버튼 만들기 -->
      <el-button round @click="login">가입 및 로그인</el-button>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'Nav',
  data () {
    return {
      activeIndex: '1'
    }
  },
  methods: {
    login () {
      this.$http.get('/api/oauth2/authorization/github')
        .then((res) => {
          window.location.href = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    logout () {
      this.$http.post('/api/logout')
        .then((res) => {
          window.location.href = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>

<style>

</style>
