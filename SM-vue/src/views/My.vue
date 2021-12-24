<template>
    <el-main>
        <el-row :gutter="20">
            <el-col :span="6">
                <el-card shadow="always" :body-style="{ padding: '10px' }">
                    <span>个人信息</span>
                    <el-divider></el-divider>
                    <div style="text-align: center">
                        <userAvatar v-if="user.id" :user="user"/>
                    </div>
                    <el-divider></el-divider>
                    <div class="myMessage">
            <span>
              <i style="font-size: 15px" class="el-icon-user-solid"/>账号
            </span>
                        <Span>{{ user.username }}</Span>
                    </div>
                    <el-divider></el-divider>
                    <div class="myMessage">
            <span>
              <i style="font-size: 15px" class="el-icon-s-custom"/>用户昵称
            </span>
                        <Span>{{ user.nickname }}</Span>
                    </div>
                    <el-divider></el-divider>
                    <div class="myMessage">
            <span>
              <i style="font-size: 15px" :class="user.sex==1?'el-icon-male':'el-icon-female'"/>性别
            </span>
                        <Span>{{ user.sex == 0 ? '男' : '女' }}</Span>
                    </div>
                    <el-divider></el-divider>
                    <div class="myMessage">
            <span>
              <i style="font-size: 15px" class="el-icon-mobile-phone"/>手机号
            </span>
                        <Span>{{ user.phone }}</Span>
                    </div>
                    <el-divider></el-divider>
                    <div style="height: 20px"></div>
                </el-card>
            </el-col>
            <el-col :span="15">
                <el-card shadow="always">
                    <span>基本资料</span>
                    <el-divider></el-divider>
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="用户资料" name="用户资料">
                            <el-form :model="msgForm" label-position="right" label-width="80">
                                <el-form-item label="用户昵称">
                                    <el-input v-model="msgForm.nickname" placeholder></el-input>
                                </el-form-item>
                                <el-form-item label="手机号">
                                    <el-input v-model="msgForm.phone" placeholder></el-input>
                                </el-form-item>
                                <el-form-item label="性别">
                                    <el-radio-group v-model="msgForm.sex">
                                        <el-radio :label="0">男</el-radio>
                                        <el-radio :label="1">女</el-radio>
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item label="邮箱">
                                    <el-input v-model="msgForm.email" placeholder></el-input>
                                </el-form-item>
                            </el-form>
                            <div class="myButton">
                                <el-button @click="updata" type="primary" round size="mini">保存修改</el-button>
                                <el-button @click="reset" type="success" round size="mini">重置信息</el-button>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="修改密码" name="修改密码">
                            <el-form :model="passwordForm" label-position="right" label-width="80">
                                <el-form-item label="旧密码">
                                    <el-input v-model="passwordForm.oldPassword"></el-input>
                                </el-form-item>
                                <el-form-item label="新密码">
                                    <el-input show-password v-model="passwordForm.newPassword" placeholder></el-input>
                                </el-form-item>
                                <el-form-item label="确认密码">
                                    <el-input show-password v-model="passwordForm.renewlyPassword"
                                              placeholder></el-input>
                                </el-form-item>
                            </el-form>
                            <div class="myButton">
                                <el-button @click="updataPas" type="primary" round size="mini">保存修改</el-button>
                            </div>
                        </el-tab-pane>
                    </el-tabs>
                </el-card>
            </el-col>
        </el-row>
    </el-main>
</template>

<script>
import userAvatar from "./userAvatar";

export default {
    methods: {
        updataPas() {
            if (this.passwordForm.oldPassword) {
                if (this.passwordForm.newPassword) {
                    if (this.passwordForm.renewlyPassword) {
                        if (this.passwordForm.newPassword == this.passwordForm.renewlyPassword) {
                            this.axios.put("/user/updataPassword",JSON.stringify({
                                arg:this.user.id,
                                arg2: this.passwordForm.newPassword
                            })).then((result) => {
                                if (result.data.code==200){
                                    this.passwordForm = {
                                        oldPassword: '',
                                        newPassword: '',
                                        renewlyPassword: ''
                                    }
                                    this.$message.success(result.data.msg)
                                }else{
                                    this.$message({
                                        message: result.data.msg,
                                        type: 'warning'
                                    });
                                }
                            })
                        } else {
                            this.$message.error('再次密码与新密码不一致！');
                        }
                    } else {
                        this.$message.error('再次输入新密码不能为空！');
                    }
                } else {
                    this.$message.error('新密码不能为空！');
                }
            } else {
                this.$message.error('旧密码不能为空！');
            }
        },
        reset() {
            this.msgForm.nickame = this.user.user.nickame;
            this.msgForm.phone = this.user.user.phone;
            this.msgForm.sex = this.user.user.sex;
            this.msgForm.email = this.user.user.email;
        },
        updata() {
            this.axios.put("/user/updata",JSON.stringify(this.msgForm)).then((result) => {
                if (result.data.code == 200)  {
                    this.sel();
                    this.$message({
                        type: "success",
                        message: result.data.msg
                    })
                } else {
                    this.$message({
                        type: "error",
                        message: result.data.msg
                    })
                }
            })
        },
        sel() {
            this.axios({
                method: "get",
                url: "user",
                params: {
                    token: sessionStorage.getItem('token')
                }
            }).then((result) => {
                console.log(result.data)
                this.user = result.data.data;
                this.msgForm.id=result.data.data.id;
                this.msgForm.nickname = result.data.data.nickname;
                this.msgForm.phone = result.data.data.phone;
                this.msgForm.sex = result.data.data.sex;
                this.msgForm.email = result.data.data.email;
            })
        },
    },
    created() {
        this.sel();
    },
    components: {
        userAvatar
    },
    data() {
        return {
            user: {},
            msgForm: {
                id: '',
                nickname: "",
                phone: "",
                sex: 0,
                email: ''
            },
            passwordForm: {
                oldPassword: "",
                newPassword: "",
                renewlyPassword: "",
            },
            activeName: "用户资料",
        };
    },
};
</script>

<style scoped="scoped">
.el-divider--horizontal {
    margin: 5px 0;
}

.myMessage {
    display: flex;
    justify-content: space-between;
    margin: 10px 5px;
    font-size: 13px;
}

.el-form-item {
    margin-bottom: 0px;
}

.myButton {
    margin: 20px 20px;
}
.el-main{
    margin-left: 20vh;
}
</style>