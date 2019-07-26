import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Nav from '@/components/Nav'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'

Vue.use(Router)
Vue.use(ElementUI)
Vue.use(Nav)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/study/list',
      name: 'Study',
      component: () => import('@/components/StudyList.vue')
    },
    {
      path: '/signup',
      name: 'Signup',
      component: () => import('@/components/Signup.vue')
    },
    {
      path: '/study/create',
      name: 'StudyForm',
      component: () => import('@/components/StudyForm.vue')
    }
  ]
})
