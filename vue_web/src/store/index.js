import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);

export default new Vuex.Store({//store对象

  //全局状态
  state: {
    isLogin: false,
    userId: "",
    name: "未登录"
  },

  //读状态（包装）
  getters:{
    getIsLogin(state){
      return state.isLogin;
    },
    getUserId(state){
      return state.userId;
    },
    getName(state){
      return state.name;
    }
  },

  //改状态
  mutations:{
    isLogin(state){
      state.isLogin = true;
    },
    isOut(state){
      state.isLogin = false;
    },
    setUserId(state,id){
      state.userId = id;
    },
    setName(state,name){
      state.name = name;
    }
  },

  //业务逻辑
  actions:{

  }
});
