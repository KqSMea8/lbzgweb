// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import iView from 'iview'
import '../src/assets/theme/default/index.less';
//import 'iview/dist/styles/iview.css'
import '@/assets/css/html.css'
import '@/assets/css/page.css'

import lodash from 'lodash'

import 'babel-polyfill'
//滚动条
import Vuebar from 'vuebar'
//import Calendar from 'vue-calendar-component';

//axios 配置
import axios from 'axios'
axios.defaults.baseURL = '/corp'
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.method = 'post'
axios.defaults.withCredentials = true

//请求拦截器
axios.interceptors.request.use(function(config){
  return config;
},function(err){ 
  err.toString = ()=>{
    return '请求出错！';
  }
  return Promise.reject(err);
});

//响应拦截器
axios.interceptors.response.use(function(res){
  if(res.data && typeof(res.data) == "object"){
  	// 登录失效
  	if(res.data.code == 401){
  		window.location.replace('/corp/login');
  		return res;
  	}
  }
  return res;
},function(err){ 
  err.toString = ()=>{
    return '响应错误！错误码：' + err.response.status+'，'+err.response.statusText;
  } 
  return Promise.reject(err);
});

Vue.use(iView)
Vue.use(Vuebar)
//Vue.use(Calendar)
Vue.config.productionTip = false
Vue.prototype.$http = axios

window.Vue = Vue

window.onmessage = function(event) {
  if (event.data === "reload") {
    location.reload();
  }
};
/*bus event bus*/
let bus = new Vue({});
/*router*/
router.beforeEach(function (to, from, next){
  bus.$emit('on-router-before-each', {to,from});
  next();
});


router.afterEach(function (to, from){
  bus.$emit('on-router-after-each', {to,from});
  if(from && from.matched.length && from.matched[0].instances && from.matched[0].instances.default){
    var vm = from.matched[0].instances.default;
    if(typeof(to.query.forward) == 'undefined'){
      // https://segmentfault.com/a/1190000015845117
      if(vm.$vnode.parent && vm.$vnode.parent.componentInstance && vm.$vnode.parent.componentInstance.cache)
      {
        if (vm.$vnode.componentOptions)
        {
            var key = vm.$vnode.key == null
                ? vm.$vnode.componentOptions.Ctor.cid + (vm.$vnode.componentOptions.tag ? `::${this.$vnode.componentOptions.tag}` : '')
                : vm.$vnode.key;
            var cache = vm.$vnode.parent.componentInstance.cache;
            var keys  = vm.$vnode.parent.componentInstance.keys;
            if (cache[key])
            {
                if (keys.length) {
                    var index = keys.indexOf(key);
                    if (index > -1) {
                        keys.splice(index, 1);
                    }
                }
                delete cache[key];
            }
        }
      }
      vm.$destroy(false);
    } 
  } 
});

Vue.prototype.$bus = bus;

/*user*/
let user = {};
user.adminId = 0;
user.userId = ''; // 员工编号、微信 UserId
user.avatar='';
user.loginName = '';
user.isLogin = false;
user.isSuper = false;
user.powerKeys = [];
user.hasPower = function(key){
  if(key == '' || key == null || this.isSuper){
    return true;
  }
  for(var i = 0;i<user.powerKeys.length;i++){
    if(user.powerKeys[i] == key){
      return true;
    }
  }
  return false;
}
user.config = {};
user.config.default = {
  storageId:''
};
user.config.data = {};
user.config.load = function(data){
  user.config.data = Object.assign({},user.config.default,data);
}
user.config.save = function(){
  // 请替换此方法
}

/*args*/
let args = {};
args.argMap = null;
args.getArgText = function(group,code){
  if(group == '' || group == null || code == '' || code == null || args.argMap == null){
    return '';
  }
  let resText ='';
  for(var key in args.argMap){
    if(key == group){
      let groupArgs = args.argMap[key];
      if(groupArgs !=null){
        for(var i=0;i< groupArgs.length;i++){
          let e = groupArgs[i]
          if(e.argCode == code){
            resText = e.argText;
            break;
          }
        }
      }
    }
  }
  return resText;
}
args.getArgGroup = function(group){
  return args.argMap[group] || [];
}

Vue.prototype.$user = user;
Vue.prototype.$args = args;
Vue.prototype.$imgViewUrl = '/corp/api/file/view?fileId=';


/*权限控制*/
Vue.directive('power', {
  bind: function (el, binding, vnode) {
    if(!user.hasPower(binding.value)){
      el.title = '暂无权限';
      el.style.opacity = 0.3
      if(el.tagName=='BUTTON'){
        el.disabled = true;
        el.click = null; 
      }
    }
  }
});


/* eslint-disable no-new */
var app = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {
    App
  }
})

window.$app = app;
