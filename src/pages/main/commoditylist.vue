<template>
  <div >
    <el-form @submit.native.prevent :inline="true">
      <el-form-item label="商品名称" prop="name" label-width="68px">
        <el-input v-model="queryParam.proName"
                  placeholder="请输入商品名称"
                  size="small" style="width: 240px"
                  clearable @keyup.enter.native="getList">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="getList"
        >搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="productList" style="width:100%"
              row-key="id"
              @row-click="rowClick">
      <el-table-column prop="picUrl" label="商品主图" width="180" align="center">
        <template slot-scope="scope">
          <el-image :src="host+scope.row.picUrl">
            <div slot="error">
             暂无此图
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="proName" label="商品名称" width="180" align="center"/>
      <el-table-column prop="price" label="商品价格" align="center"/>
      <el-table-column prop="repertory" label="商品库存" align="center"/>
    </el-table>
    <el-pagination
      background
      :hide-on-single-page="true"
      :page-sizes="[2,5, 10, 15, 20]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      :page-size.sync="queryParam.pageSize"
      :current-page.sync="queryParam.pageNum"
      @current-change="getList"
      @size-change="getList"
    />

    <el-dialog title="商品详情" :visible.sync="open" width="550px">
      <div v-html="product.proDetails"></div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="buy">加入订单</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {list,productById} from '@/api/commoditylist'
  import {add} from '@/api/bill'

  export default {
  name: "index",
  data() {
    return {
      total: 0,
      host: 'http://localhost:82/images/',
      open: false,
      queryParam:{
        proName: '',
        pageSize: 2,
        pageNum: 1
      },
      productList: [],
      product: ''
    }
  },
  methods: {
    getList(){
      list(this.queryParam).then(response => {
        this.productList=response.data;
        this.total = response.total;
      }).catch(error => {
        this.msgError(error.message)
      })
    },
    reset() {
      this.product = {
        proName: undefined,
        repertory: undefined,
        proDetails: undefined,
        price: undefined,
        picUrl: undefined,
        number: 1
      }
      if (this.$refs['productform']!=undefined) {
        this.$refs['productform'].resetFields();
      }
    },
    rowClick(row){
      this.reset();
      productById(row.id).then(response => {
        this.product = response.data;
      }).catch(error => {
        this.msgError("异常："+error.message)
      })
      this.open = true
    },
    cancel(){
      this.reset();
      this.open = false
    },
    buy(){
      add(this.product).then(response => {
        if(response.error == undefined){
          if(response.resCode=="1000"){
            this.msgSuccess(response.info)
            this.$router.push({
              path: response.page,
              query: {         //参数携带方式
                pid: this.product.id,
                bid: response.code
              }
            });
          }else{
            this.msgError(response.info)
          }
        }
      })
    }
  },
  created() {
    this.getList()
  }
};
</script>
