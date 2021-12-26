import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// import './plugins/element.js'
import './assets/commond.css'
import infiniteScroll from "vue-infinite-scroll";
import SlideVerify from 'vue-monoplasty-slide-verify';
import axios from 'axios'
import VueAxios from 'vue-axios'
// import api from "./api"

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

Vue.use(VueAxios, axios)

// Vue.prototype.$api = api;
Vue.use(SlideVerify);
axios.defaults.baseURL = "/api"
Vue.config.productionTip = false

Vue.use(infiniteScroll);
//过滤器
router.beforeEach((to, from, next) => {
  //to 将要进入的路由
  // from 将要离开的路由
  // next 让路由继续执行
  let token = sessionStorage.getItem("token");
  if (to.path === '/mainL/login'||to.path === '/mainL/register') {
      next();
  }else if(!token){
    next({path:"/mainL/login"});
  }
    next();
})


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
