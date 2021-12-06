import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/commond.css'

import SlideVerify from 'vue-monoplasty-slide-verify';

import api from "./api"

Vue.prototype.$api = api;
Vue.use(SlideVerify);

Vue.config.productionTip = false

new Vue({
  router,
  store,

  render: h => h(App)
}).$mount('#app')
