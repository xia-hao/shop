<template>
  <div >
    <el-table :data="billList" style="width:100%"
              row-key="id">
      <el-table-column prop="uid" label="用户id" width="180" align="center"/>
      <el-table-column prop="pid" label="商品id" width="180" align="center"/>
      <el-table-column prop="money" label="订单金额" align="center"/>
      <el-table-column prop="billDate" label="订单时间" align="center"/>
      <el-table-column prop="billStatus" label="订单状态" align="center"/>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" v-show="scope.row.billStatus==0" @click="clearing(scope.row)">结 算</el-button>
          <div  v-show="scope.row.billStatus==1">
            已结算
          </div>
          <div v-show="scope.row.billStatus==2">
            已取消
          </div>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {list} from '@/api/bill'
export default {
  name: "bill",
  data() {
    return {
      host: 'http://localhost:82/images/',
      billList:{
        id: undefined,
        uid: undefined,
        pid: undefined,
        money: undefined,
        billDate: undefined,
        billStatus: undefined
      }
    }
  },
  methods: {
    clearing(row){
      this.$router.push(
        {
          path: '/pay',
          query: {
            pid: row.pid,
            bid: row.id
          }
      })
    }
  },
  created() {
    list().then(response => {
      this.billList = response.bill;
    })
  }
};
</script>
