<template>
    <div class="login_container">
        <div class="login_box">
            <!-- 头像 -->
            <!-- <div class="avator_box">
              <img src="../assets/logo.png" alt />
            </div>
          -->
            <el-card>
                <!-- 输入表单 -->
                <el-form
                    method="POST"
                    class="login_form"
                    ref="form"
                    :rules="rules"
                    :model="form"
                >
                    <!-- name -->
                    <el-form-item label="用户名" prop="username" label-width="65px">
                        <el-input
                            v-model="form.username"
                            placeholder="请输入内容"
                        ></el-input>
                    </el-form-item>
                    <!-- password -->
                    <el-form-item label="密 码 " prop="password" label-width="65px">
                        <el-input
                            type="password"
                            v-model="form.password"
                            placeholder="请输入密码"
                        ></el-input>
                    </el-form-item>
                    <!-- 滑动验证 -->
                    <el-form-item>
                        <SlideVerify ref="slideblock" @success="success"></SlideVerify>
                    </el-form-item>
                    <!--button -->
                    <el-form-item class="btns">
                        <el-button type="primary" @click="onSubmit">登录</el-button>
                        <el-button type="primary">找回密码</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script>
import SlideVerify from "./SliderVerify.vue"; //引入

export default {
    beforeDestroy(){
        this.$router.go(0);
    },
    data() {
        return {
            slide:false,
            form: {
                username: "",
                password: "",
            },
            rules: {
                username: [
                    {required: true, message: "请输入用户名", trigger: "blur"},
                ],
                password: [{required: true, message: "请输入密码", trigger: "blur"}],
            },
        };
    },
    // inject: ['checkAdmin'],
    components: {
        SlideVerify, //注册组件
    },
    methods: {
        onSubmit() {
            if (this.slide){
                var data = new FormData();
                data.append('username', this.form.username);
                data.append('password', this.form.password);
                this.axios({
                    method: "post",
                    url: "login",
                    data: data,
                    Headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then((response) => {
                    console.log(response);
                    if (response.data.code==200){
                        let res = response.data;
                        sessionStorage.setItem("token", res.data.token);
                        res.data.roles.forEach(item=>{
                            if (item.authority=="ROLE_ADMIN"){
                                sessionStorage.setItem("role","ROLE_ADMIN");
                            }
                            // this.checkAdmin();
                        })
                        this.$message.success(response.data.msg);
                        this.$router.push({path:"/"});
                    }else{
                        this.$message.error(response.data.msg);
                    }
                });
            }else{
                this.$message.error('请先完成滑动验证！');
            }
        },
        success(val){
            this.slide=val;
        }
    },
};
</script>
<style>
.btns {
    display: flex;
    justify-content: center;
}
</style>