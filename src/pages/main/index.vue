<template>
 <div>
   <el-container style="height: 600px; border: 1px solid #eee">
     <el-aside width="230px" style="background-color: #9daea4">
       <h3 class="center">商品购物系统</h3>
       <div :class="{'display':user,'center':!user}">
         <img src="static/image/timg.jpg" height="50" width="50" style="border-radius:50%;"/>
       </div>
       <div  class="display" v-if="user!=undefined">
         <div>
           用户名：{{user.username}}
         </div>
         <div>
           余额：{{user.balance}} ￥
         </div>
       </div>
       <div class="center" v-else>
         <router-link to="/" class="btn">
          登 录
         </router-link>
       </div>
       <router-link to="/commoditylist" class="list-group-item sello">商品列表</router-link>
       <router-link to="/bill" class="list-group-item sello">我的订单</router-link>
       <div class="list-group-item sello" @click="logOut">退出系统</div>
     </el-aside>
     <el-container>
       <el-header>
         商品购物系统
       </el-header>
       <el-main>
         <router-view/>
       </el-main>
     </el-container>
   </el-container>
 </div>
</template>

<script>
  import {loginOut} from "@/api/login";
  import {userinfo} from "@/api/user";
export default {
  name: 'index',
  data () {
    return {
      user: undefined
    }
  },
  methods: {
    logOut(){
      loginOut().then(response => {
        this.$router.push({path: response})
      }).catch(error => {
        alert("异常："+error.message)
      })
    }
  },
  created() {
    userinfo().then(response => {
      this.user = response.data
    }).catch(error => {
      this.msgError('异常：'+error.msg)
    })
    this.$router.push({path: '/commoditylist'})
  }
};
</script>

<style>
  .list-group-item{
    top: 5%;
    text-decoration:none;
    color: #303133;
    position: relative;
    display: block;
    padding: 10px 15px;
    margin-bottom: -1px;
    border: 1px solid #ddd;
  }
  .sello:hover{
    background: aqua;
  }
  .el-header{
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .center{
    text-align: center;
  }
  .display{
    vertical-align: middle;
    display: inline-block;
  }
  .btn{
    height: 25px;
    padding: 0 15px;
    background: #ffffff;
    border: 1px #e2e2e2 solid;
    border-radius: 3px;
    color: #000000;
    display: inline-block;
    text-decoration: none;
    outline: none;
  }
</style>
