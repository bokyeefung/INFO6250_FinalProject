// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

Vue.prototype.store = store;

// 引入Ajax和跨域相关组件
import Axios from 'axios';
Vue.prototype.$axios = Axios;
Axios.defaults.headers.post['Content-Type'] = 'application/json';

// 引入cookies相关组件
import VueCookies from 'vue-cookies';
Vue.use(VueCookies);

// 引入vue-fragment
import Fragment from 'vue-fragment'
Vue.use(Fragment.Plugin);

// 使用ElementUI和颜色主题
import ElementUI from 'element-ui';
import { Message } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
Vue.prototype.$message = Message;

// 引入全局CSS样式
import './assets/style/common-style.css'

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
