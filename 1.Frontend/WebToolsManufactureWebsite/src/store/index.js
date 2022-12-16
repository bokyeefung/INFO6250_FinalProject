import Vue from 'vue'
import vuex from 'vuex'

Vue.use(vuex);

//创建VueX对象
const store = new vuex.Store({
  state: {
    windowSize: {
      width: 0,
      height: 0
    }
  }
});

export default store
