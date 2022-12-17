import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/',
      component: () => import('@/pages/common/navigation'),
      children: [
        {
          path: '/',
          component: () => import('@/pages/common/home')
        },
        {
          path: '/source',
          component: () => import('@/pages/sourceManage/sourceHome')
        },
        {
          path: '/source/add',
          component: () => import('@/pages/sourceManage/sourceAdd')
        },
        {
          path: '/source/change/:uuid',
          component: () => import('@/pages/sourceManage/sourceChange')
        },
        {
          path: '/order/product',
          component: () => import('@/pages/orderManage/productHome')
        },
        {
          path: '/order/product/add',
          component: () => import('@/pages/orderManage/productAdd')
        },
        {
          path: '/order/product/:uuid',
          component: () => import('@/pages/orderManage/productChange')
        },
        {
          path: '/order/commodity',
          component: () => import('@/pages/orderManage/commodityHome')
        },
        {
          path: '/user',
          component: () => import('@/pages/userManage/userHome')
        },
        {
          path: '/user/add',
          component: () => import('@/pages/userManage/userAdd')
        },
        {
          path: '/supply',
          component: () => import('@/pages/supplyChainManagement/supplyChainHome')
        },
      ]
    },
    {
      path: '/login',
      component: () => import('@/pages/common/login')
    }
  ]
})

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};
