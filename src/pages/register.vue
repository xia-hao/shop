<template>
  <div>
    <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" class="register-form">
      <h2 style="text-align: center">注册</h2>
      <el-form-item label="账户名称" prop="username">
        <el-input type="text" v-model="form.username" aria-placeholder="请输入手机号" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button @click="back">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {register} from '@/api/register'
  export default {
    name: 'register',
    data() {
      var phone = (rule, value, callback) => {
        if (!(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(value))) {
          callback(new Error('请输入正确手机号'));
        } else {
          callback();
        }
      };
      return {
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { validator: phone, trigger: 'blur' }
          ],
          password: [
            { required: true,message: '请输入密码', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            register(this.form).then(response => {
              this.msgSuccess(response.info)
              this.$router.push({path: response.data})
            }).catch(error => {
              this.msgError('异常：'+error.message)
            })
          } else {
            alert("注册内容不能为空！")
            return false;
          }
        });
      },
      resetForm() {
        this.$refs['form'].resetFields();
      },
      back(){
        this.$router.go(-1);
      }
    }
  }
</script>

<style>
  .register-form{
    width: 350px;
    /* 上下嫌隙 160px，左右自动居中 */
    margin: 160px auto;
    padding: 28px;
    border-radius: 20px;
    /* border: solid 1px black; */
    box-shadow: 0 0 30px 3px rgba(119,118,118,0.33);
  }
</style>
