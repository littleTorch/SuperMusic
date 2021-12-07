import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/commond.css'

import SlideVerify from 'vue-monoplasty-slide-verify';

// import api from "./api"

import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)

// Vue.prototype.$api = api;
Vue.use(SlideVerify);
axios.defaults.baseURL = "/api"
Vue.config.productionTip = false

axios.interceptors.request.use(config => {
  let token = sessionStorage.getItem("token");
  config.headers.common['token'] = token;
  config.headers['Content-Type'] = 'application/json'
  return config;
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
