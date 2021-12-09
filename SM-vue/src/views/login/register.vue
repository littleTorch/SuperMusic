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
                         :model="form">
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

                        <el-input type="email" v-model="form.email" placeholder="请输入邮箱"></el-input>
                    </el-form-item>
                    <!-- n验证码 -->
                    <el-form-item label="验证码" prop="code" label-width="65px">
                        <el-input v-model="form.code" placeholder="请输入验证码"></el-input>
                    </el-form-item>
                    <!--button -->
                    <el-form-item class="btns">
                        <el-button type="primary" @click="getCode" class="btn">获取验证码</el-button>
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
    data() {
        return {
            form: {
                username: "",
                password: "",
                email: "",
                code:""
            },
            rules: {
                username: [{required: true, message: "请输入用户名", trigger: "blur"}],
                password: [{required: true, message: "请输入密码", trigger: "blur"}],
                email: [{required: true, message: "请输入邮箱", trigger: "blur"},
                    {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}],
                code: [{required: true, message: "请输入验证码", trigger: "blur"}],
            }
        };
    },

    methods: {
        onSubmit() {
            this.$refs["form"].validate((valid) => {
                if (valid) {
                    this.axios.post("user/register", JSON.stringify(this.form)).then(res => {
                        console.log(res)
                        this.$message({
                            type: "success",
                            duration: 1000,
                            message: res.data.msg
                        })
                        if (res.data.msg=="注册成功"){
                            this.$router.push('/mainL');
                        }
                    });
                }
            });
        },
        getCode() {
            let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
            let email = this.form.email //要验证的对象
            if (email === "") { //输入不能为空
                this.$message.error("邮箱输入不能为空!");
            } else if (!reg.test(email)) { //正则验证不通过，格式不对
                this.$message.error("邮箱验证不通过!");
            } else {
                this.axios.get("user/getcode", {
                    params: { mail:this.form.email}
                }).then(res => {
                    this.$message({
                        type: "success",
                        duration: 1000,
                        message: res.data.msg
                    })
                });
            }
        }
    }

}
</script>
<style>
.btns {
    display: flex;
    justify-content: center;
}

.btn {
}
</style>