<template>
    <div id="source-home-container" class="source-home-container">

        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto;"
                            @back="goBack" content="Modify Raw Material Order"></el-page-header>
        </div>


        <el-card style="margin: 20px;">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px" span="11">
                <el-form-item label="Product Name" prop="relationId">
                    <el-input v-model="ruleForm.productName" disabled></el-input>
                </el-form-item>
                <el-form-item label="Ordered Quantity" prop="number">
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
import config from "../../assets/js/common/config";
import materialManagement from "../../assets/js/api/orderManagement/materialManagement";
import relationManagement from "../../assets/js/api/orderManagement/relationManagement";

export default {
    mixins: [materialManagement, relationManagement, config],
    data() {
        return {
            numberValidateForm: {
                age: ''
            },
            ruleForm: {
                relationId: "",
                productName: "",
                number: 0
            },
            rules: {
                name: [
                    {required: true, message: 'Relation cannot be empty', trigger: 'blur'}
                ],
                number: [
                    {required: true, trigger: 'blur'}
                ]
            }
        }
    },
    mounted() {
        const that = this;
        this.selectHostOrder(this.$route.params.uuid, (data) => {
            that.ruleForm = data;
            that.ruleForm.productName = `${data.srcArticle.name} => ${data.dstArticle.name}`;
        });
    },
    methods: {
        submitForm() {
            const that = this;
            this.$refs["ruleForm"].validate((valid) => {
                if (valid) {
                    let user = that.getUserInfo();
                    let data = { ...that.ruleForm };
                    data.groupId = user.groupId;
                    this.updateHostOrder(data, () => {
                        that.successMessage("Change order succeed.");
                        setTimeout(() => that.goBack(), 200);
                    });
                } else {
                    return false;
                }
            });
        },
        resetForm() {
            let name = this.ruleForm.name;
            this.$refs["ruleForm"].resetFields();
            this.ruleForm.name = name;
        },
        goBack() {
            let that = this;
            that.$router.push('/order/material');
        }
    }
}
</script>

<style lang="scss" scoped>

.source-home-container {
}

</style>

