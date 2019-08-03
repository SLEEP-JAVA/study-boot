// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import moment from 'moment'
import VueMomentJs from 'vue-momentjs'

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(VueAxios, axios);
Vue.use(VueMomentJs, moment);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

