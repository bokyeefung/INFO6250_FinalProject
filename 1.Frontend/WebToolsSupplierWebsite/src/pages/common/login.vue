<template>
    <div class="login-container">
        <h1 style="font-size: 50px; color: #409eff; text-align: center; margin-bottom: 20px;">SUPPLIER</h1>
        <el-card>
            <el-form ref="userLoginForm" :model="form.userLogin" status-icon label-width="100px" :rules="rules.userRule">
                <el-form-item label="USERNAME" prop="username" :error="form.errorCode.userLoginError">
                    <el-input v-model="form.userLogin.username" placeholder="USERNAME"
                              @keyup.enter.native="loginFunction"></el-input>
                </el-form-item>
                <el-form-item label="PASSWORD" prop="password" :error="form.errorCode.userLoginError">
                    <el-input v-model="form.userLogin.password" placeholder="PASSWORD" show-password
                              @keyup.enter.native="loginFunction"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="loginFunction">SIGN IN</el-button>
                    <el-button type="primary" disabled>SIGN UP</el-button>
                    <el-button @click="goBack">RETURN</el-button>
                    <el-link type="primary" :underline="false" style="float: right;">Forgot your password?</el-link>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";

export default {
    name: "login",
    mixins: [loginAndLogout],
    data() {
        return {
            form: {
                image: '',
                userLogin: {
                    username: 'manager',
                    password: 'Changeme_123',
                    verification: ''
                },
                errorCode: {
                    userLoginError: ''
                }
            },
            rules: {
                userRule: {
                    username: {required: true, message: '用户名/邮箱不能为空', trigger: 'blur'},
                    password: {required: true, message: '密码不能为空', trigger: 'blur'},
                    // verification: {required: true, validator: imageVerificationRule, trigger: 'blur'}
                }
            }
        }
    },
    methods: {
        loginFunction() {
            const that = this;
            let data = {
                name: that.form.userLogin.username,
                passwd: that.form.userLogin.password
            };
            that.form.errorCode.userLoginError = '';
            that.$refs['userLoginForm'].validate((valid) => {
                if (valid) {
                    that.login(data, function () {
                        that.goBack();
                    }, function (msg) {
                        that.form.errorCode.userLoginError = msg;
                        return false;
                    });
                } else {
                    return false;
                }
            });
        },
        goBack() {
            let that = this;
            let from = that.$route.query.from;
            if (!!from) {
                that.$router.push({path: from});
            } else {
                that.$router.push({path: '/'});
            }
        },
    }
}
</script>

<style lang="scss" scoped>

.login-container {
    width: 530px;
    max-width: 96%;
    margin: 50px auto;
}
</style>
