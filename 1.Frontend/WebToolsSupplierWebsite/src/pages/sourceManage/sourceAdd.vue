<template>
    <div id="source-home-container" class="source-home-container">

        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto;"
                            @back="goBack" content="New Raw Material"></el-page-header>
        </div>


        <el-card style="margin: 20px;">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="200px" span="11">
                <el-form-item label="Raw Material Name" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="Unit Price (USD)" prop="moneyCost">
                    <el-input-number v-model="ruleForm.moneyCost" style="width: 300px;" :min="0" :precision="2"></el-input-number>
                </el-form-item>
                <el-form-item label="Production Cycle (Days)" prop="timeCost">
                    <el-input-number v-model="ruleForm.timeCost" style="width: 300px;" :min="0"></el-input-number>
                </el-form-item>
                <el-form-item label="Stock" prop="number">
                    <el-input-number v-model="ruleForm.number" style="width: 300px;" :min="0"></el-input-number>
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
import sourceManagement from "../../assets/js/api/sourceManagement/sourceManagement";
import config from "../../assets/js/common/config";

export default {
    mixins: [sourceManagement, config],
    data() {
        return {
            numberValidateForm: {
                age: ''
            },
            ruleForm: {
                name: "",
                moneyCost: 0,
                timeCost: 0,
                number: 0
            },
            rules: {
                name: [
                    {required: true, message: 'Raw material name cannot be empty', trigger: 'blur'}
                ],
                moneyCost: [
                    {required: true, trigger: 'blur'}
                ],
                timeCost: [
                    {required: true, trigger: 'blur'}
                ],
                number: [
                    {required: true, trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        submitForm() {
            const that = this;
            this.$refs["ruleForm"].validate((valid) => {
                if (valid) {
                    let user = that.getUserInfo();
                    let data = { ...that.ruleForm };
                    data.groupId = user.groupId;
                    this.addSource(data, () => {
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
            that.$router.push('/source');
        }
    }
}
</script>

<style lang="scss" scoped>

.source-home-container {
}

</style>

