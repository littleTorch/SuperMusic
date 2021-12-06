import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [

  {
    path: '/',
    // redirect: '/mainL',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/mainL',
    name: 'mainL',
    redirect: '/mainL/login',
    component: () => import('../views/login.vue'),
    children: [
      {
        path: 'login',
        name: 'login',

        component: () => import('../views/login/loginmain.vue'),
      }, {
        path: 'register',
        name: 'register',
        component: () => import('../views/login/register.vue')
      }
    ],

  }, {
    path: '/Admin',
    name: 'Admin',
    redirect: '/Admin/first',

    component: () => import('../views/other/Administrator.vue'),
    children: [
      {
        path: 'first',
        name: 'first',
        component: () => import('../components/admin/first.vue')
      }, {
        path: 'singer',
        name: 'singer',
        component: () => import('../components/admin/singerA.vue')
      }

    ]
  },
  {
    path: '/mySongSheet',
    name: 'mySongSheet',

    component: () => import('../views/other/mySongSheet.vue'),
  },
  {
    path: '/RankingList',
    name: 'RankingList',

    component: () => import('../views/other/RankingList.vue'),
  },
  {
    path: '/Singer',
    name: 'Singer',

    component: () => import('../views/other/Singer.vue'),
  },
  {
    path: '/SongSheet',
    name: 'SongSheet',

    component: () => import('../views/other/SongSheet.vue'),
  },
]

const router = new VueRouter({
  routes,
  mode: 'history',
  linkActiveClass: "active",
})

export default router