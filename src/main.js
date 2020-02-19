// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/index.js'

Vue.use(Element)
Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}
Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}


//  路由前置
router.beforeEach((to, from, next) => {
  const type=to.meta.type;
  if(type==="/login"){
    if(window.sessionStorage.getItem("user")){
      next()
    }else{
      next("/login")
    }
  }else{
    next()
  }
})


Vue.config.productionTip = false
/* eslint-disable no-new */
const rootVueObj = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

export default rootVueObj;
