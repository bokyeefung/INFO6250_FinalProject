<template>
    <div id="source-home-container" class="source-home-container">

        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto;"
                            @back="goBack" content="Add User"></el-page-header>
        </div>

        <el-card style="margin: 20px;">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" span="11">
                <el-form-item label="Username" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="passwd">
                    <el-input v-model="ruleForm.passwd" type="password"></el-input>
                </el-form-item>
                <el-form-item label="E-mail" prop="email">
                    <el-input v-model="ruleForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Phone" prop="phone">
                    <el-input v-model="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="Address" prop="address">
                    <el-input v-model="ruleForm.address"></el-input>
                </el-form-item>
                <el-form-item label="Role" prop="role">
                    <el-select v-model="ruleForm.role" placeholder="Role">
                        <el-option label="Plant Manager" :value="2"></el-option>
                        <el-option label="Manager" :value="3"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm">Submit</el-button>
                    <el-button @click="resetForm">Reset</el-button>
                    <el-button @click="goBack">Cancel</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>


<script>
import userManagement from "../../assets/js/api/userManagement/userManagement";
import message from "../../assets/js/common/message";

export default {
    mixins: [userManagement, message],
    data() {
        return {
            numberValidateForm: {
                age: ''
            },
            ruleForm: {
                groupId: "3e6e119c-3469-4476-84ad-8e6ee8e7fc21",
                name: "",
                passwd: "",
                email: "",
                phone: "",
                address: "",
                role: 2
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
                    this.addUser(this.ruleForm, () => {
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
            let that = this;
            that.$router.push('/user');
        }
    }
}
</script>

<style lang="scss" scoped>

.source-home-container {
}

</style>

