import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history', // 去掉url中的#
  routes: [
    {
      path:'',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/pages/login")
    },
    {
      path: '/index',
      name: 'index',
      component: () => import("@/pages/main/index"),
      children: [
        {
          path: '/commoditylist',
          name: 'commoditylist',
          component: () => import("@/pages/main/commoditylist")
        },{
          path: '/bill',
          name: 'bill',
          component: () => import("@/pages/main/bill")
        }
      ]
    },
    {
      path: '/register',
      name: 'register',
      component: () => import("@/pages/register")
    },
    {
      path: '/pay',
      name: 'pay',
      component: () => import("@/pages/main/pay")
    }
  ]
})
