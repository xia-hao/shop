<template>
  <div class="container">
    <table class="el-table" style="width: 500px;margin: 160px auto;background-color:#dddddd">
      支付页面
      <tr>
        <td rowspan="4"><img :src="host+product.picUrl" style="width: 200px;height: 150px;"/></td>
      </tr>
      <tr> <td>商品名称：{{product.proName}}</td></tr>
      <tr> <td>商品价格：{{product.price}}￥</td></tr>
      <tr>
        <td>
        商品数量：
          <el-input-number v-model="product.number" :min="1" :max="product.repertory" controls-position="right" placeholder="请输入商品数量"/>
        </td>
      </tr>
     <tr>
       <td colspan="1" style="text-align: center">
         <div style="color: red">
           结算金额： {{product.number*product.price}} ￥
         </div>
       </td>
       <td colspan="2" style="text-align: right">
         <div slot="footer" class="dialog-footer">
           <el-button type="primary" @click="buy">购 买</el-button>
           <el-button @click="back">返回</el-button>
         </div>
       </td>
     </tr>
    </table>
  </div>
</template>

<script>
  import {productById} from '@/api/commoditylist'
  import {paybill} from '@/api/pay'
export default {
  name: "bill",
  data() {
    return {
      host: 'http://localhost:82/images/',
      pid: this.$route.query.pid,
      bid: this.$route.query.bid,
      product: ''
    }
  },
  methods: {
    buy(){
      paybill(this.product,this.bid).then(response => {
        if(response.error == undefined){
          if(response.resCode=='1000'){
            this.msgSuccess(response.info)
          }else{
            this.msgError(response.info)
          }
          this.$router.push({path: response.data})
        }
      })
    },
    back(){
      this.$router.go(-1)
    }
  },
    created() {
      productById(this.pid).then(response => {
        this.product = response.data
      })
    }
  };
</script>
<style>
  .container {
      margin: 160px auto;
  }
</style>
