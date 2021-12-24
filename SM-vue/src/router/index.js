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
    redirect: '/Admin/IndexSet',

    // component: () => import('../views/other/Administrator.vue'),
    component: () => import('../views/other/AdminPage.vue'),
    children: [
      {
        path: 'IndexSet',
        name: 'IndexSet',
        component: () => import('../views/admin/IndexSet.vue')
      }, {
        path: 'SongSet',
        name: 'SongSet',
        component: () => import('../views/admin/SongSet.vue')
      }, {
        path: 'SingSet',
        name: 'SingSet',
        component: () => import('../views/admin/SingSet.vue')
      }, {
        path: 'PlaylistSet',
        name: 'PlaylistSet',
        component: () => import('../views/admin/PlaylistSet.vue')
      }, {
        path: 'SonglistSet',
        name: 'SonglistSet',
        component: () => import('../views/admin/SonglistSet.vue')
      }, {
        path: 'UserSet',
        name: 'UserSet',
        component: () => import('../views/admin/UserSet.vue')
      }

    ]
  },
  {
    //页面找不到
    path: '*',
    name: 'NotFound',
    component: () => import('../views/NotFound'),
  },
  {
    path: '/my',
    name: 'my',

    component: () => import('../views/My.vue'),
  },
  {
    path: '/mySongSheet',
    name: 'mySongSheet',

    component: () => import('../views/other/mySongSheet.vue'),
  },
  {
    path: '/RankingList',
    name: 'RankingList',
    component: () => import('../views/other/RankingList.vue')

  },
  {
    path: '/Singer',
    name: 'Singer',

    component: () => import('../views/other/Singer.vue'),
  },
  {
    path: '/Singer_profile',
    name: 'Singer_profile',
    component: () => import('../views/other/Singer_profile.vue'),
  },
  {
    path: '/SongSheet',
    name: 'SongSheet',

    component: () => import('../views/other/SongSheet.vue'),
  },
  {
    path: '/SongSheet_profile/:id',
    name: 'SongSheet_profile',

    component: () => import('../views/other/SongSheet_profile.vue'),
  }
]

const router = new VueRouter({
  routes,
  mode: 'history',
  linkActiveClass: "active",
})

export default router
