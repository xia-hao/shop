<template>
  <div class="login-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="login-form">
      <h2 class="login-title" >商品购物系统登录</h2>
      <el-form-item label="账号" class="box" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" class="box" prop="password">
        <el-input v-model="form.password" type="password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="cancle" type="primary" @click="onSubmit">登录</el-button>
        <el-button class="cancle" @click="clean">重置</el-button>
      </el-form-item>
      <el-form-item style=" margin-left: 70px;">

      </el-form-item>
      <div>
        <span>
           <router-link to="/register" style="text-decoration: none;color: #3c8899;">
           还没有账号?点击注册
          </router-link></span>
        <span style="float: right;">
          <router-link to="/commoditylist" style="text-decoration: none;color: #a7a7a9;">
            游 客 登 录
          </router-link>
        </span>
      </div>
    </el-form>

  </div>
</template>

<script>
import {signIn} from '@/api/login'

export default {
  name: "Login",
  data() {
    return {
      form: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      },
    };
  },
  methods: {
    onSubmit() {
      //定位到表单，再进行校验
      this.$refs['form'].validate(valid => {
        console.log(valid)      //成功为true，失败为false
        if(valid){
          // 提交表单给后台进行验证是否正确
          signIn(this.form).then(response => {
            if(response.info){
              window.sessionStorage.setItem("user",JSON.stringify(this.form));
              this.msgSuccess(response.info)
              this.$router.push({path: response.data})
            }else{
              this.msgError(response.info)
            }
          }).catch(error => {
            this.msgError('异常：'+error.message)
          })
        }else{
          alert('文本框内容不能为空！')
          return false
        }
      })
    },
    clean(){
      this.$refs['form'].resetFields();
    }
  }
};
</script>



<style scoped>
  .login-form {
    width: 350px;
    /* 上下嫌隙 160px，左右自动居中 */
    margin: 160px auto;
    padding: 28px;
    border-radius: 20px;
    /* border: solid 1px black; */
    box-shadow: 0 0 30px 3px rgba(119,118,118,0.33);
  }

  .login-container {
    position: absolute;
    width: 100%;
    height: 100%;
  }

  .login-title {
    color: #303133;
    text-align: center;
  }
  .box {
    margin-left: 0px;
  }
  .cancle{
    margin-left: 25px;
    margin-right: 20px;
  }
</style>
