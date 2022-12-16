<template>
    <div class="login-container">
        <h1 style="font-size: 50px; color: #409eff; text-align: center; margin-bottom: 20px;">MANUFACTURE</h1>
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
                    username: '',
                    password: '',
                    verification: ''
                },
                errorCode: {
                    userLoginError: ''
                }
            },
            rules: {
                userRule: {
                    username: {required: true, message: 'Username cannot be empty', trigger: 'blur'},
                    password: {required: true, message: 'Password cannot be empty', trigger: 'blur'}
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
                    that.login(data, () => {
                        setTimeout(() => that.goBack(), 200);
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
            this.$router.push({path: '/'});
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
