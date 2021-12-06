<template>
  <div class="register">

<div class="login_box">
      <!-- 头像 -->
      <!-- <div class="avator_box">
        <img src="../assets/logo.png" alt />
      </div>
    -->
  <el-card>
       <!-- 输入表单 -->
      <el-form method="POST" 
      class="login_form" 
      ref="form" 
      :rules="rules"
      :model="form" 
     
      >
        <!-- name -->
        <el-form-item label="用户名" prop="username" label-width="65px">
         <el-input v-model="form.username" placeholder="请输入内容"></el-input>
        </el-form-item>
        <!-- password -->
        <el-form-item label="密 码 " prop="password" label-width="65px">
          <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
      <!-- 邮箱 -->
      <el-form-item label="邮 箱" prop="email" label-width="65px">
      
        <el-input type="email" v-model="form.password" placeholder="请输入邮箱"></el-input>
      </el-form-item>
        <!-- n验证码 -->
        <el-form-item label="验证码" prop="verify" label-width="65px">
         <el-input v-model="form.verify" placeholder="请输入验证码"></el-input>
        </el-form-item>
        <!--button -->
        <el-form-item class="btns">
          <el-button type="primary" @click="onSubmit" class="btn">注册</el-button>
        </el-form-item>

      </el-form>
  </el-card>

    </div>


    
  </div>
</template>
 
<script>
import SlideVerify from "./SliderVerify.vue";//引入


export default {
data(){
  return{
     form: {
        username: "",
        password: "",
      },
      rules: {
        username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        verify: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      }
  };
},

methods:{
  onSubmit(){
   
    this.$api.getlogin2({
      username:this.form.username,
      password:this.form.password

    }).then(() =>{
    //   console.log(res.data);
       console.log(this.form);
    })

  }
}

}
</script>
<style>
.btns{
  display: flex;
  justify-content: center;
}
.btn{
}
</style>