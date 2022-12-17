<template>
    <div class="login-container">
        <h1 style="font-size: 50px; color: #409eff; text-align: center; margin-bottom: 20px;">CUSTOMER</h1>
        <el-card>
            <el-form ref="ruleForm" :model="form.userLogin" status-icon label-width="100px" :rules="rules">
                <el-form-item label="Username" prop="name">
                    <el-input v-model="form.userLogin.name"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="passwd">
                    <el-input v-model="form.userLogin.passwd" type="password"></el-input>
                </el-form-item>
                <el-form-item label="E-mail" prop="email">
                    <el-input v-model="form.userLogin.email"></el-input>
                </el-form-item>
                <el-form-item label="Phone" prop="phone">
                    <el-input v-model="form.userLogin.phone"></el-input>
                </el-form-item>
                <el-form-item label="Address" prop="address">
                    <el-input v-model="form.userLogin.address"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm">SUBMIT</el-button>
                    <el-button @click="resetForm">RESET</el-button>
                    <el-button @click="goBack">CANCEL</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import userManagement from "../../assets/js/api/userManagement/userManagement";

export default {
    name: "signup",
    mixins: [userManagement],
    data() {
        return {
            form: {
                image: '',
                userLogin: {
                    name: "plant manager2",
                    passwd: "Changeme_123",
                    email: null,
                    phone: null,
                    address: "null",
                    role: 2
                },
                errorCode: {
                    userLoginError: ''
                }
            },
            rules: {
                name: [
                    {required: true, message: 'Please input username', trigger: 'blur'}
                ],
                passwd: [
                    {required: true, message: 'Please input password', trigger: 'blur'}
                ],
                email: [
                    {required: false}
                ],
                phone: [
                    {required: false}
                ],
                address: [
                    {required: true, message: 'Please input address', trigger: 'blur'}
                ],
                role: [
                    {required: true, message: 'Please select role', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        submitForm() {
            const that = this;
            this.$refs["ruleForm"].validate((valid) => {
                if (valid) {
                    this.addUser(this.form.userLogin, () => {
                        that.successMessage("Add user succeed.");
                        setTimeout(() => that.goBack(), 200);
                    });
                } else {
                    return false;
                }
            });
        },
        resetForm() {
            this.$refs["ruleForm"].resetFields();
        },
        goBack() {
            this.$router.push({path: '/login'});
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
