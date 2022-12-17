<template>
    <div id="source-home-container" class="source-home-container">

        <div style="background-color: white; border-bottom: 1px solid #eee;">
            <el-page-header style="padding: 24px; margin: 0 auto;"
                            @back="goBack" content="Order Raw Material"></el-page-header>
        </div>


        <el-card style="margin: 20px;">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="Product Name" prop="relationId">
                    <div style="display: flex; flex-direction: row;">
                        <el-input v-model="ruleForm.productName" placeholder="Click the query button on the right to select raw materials" disabled></el-input>
                        <el-button type="primary" style="margin-left: 10px;" @click="selectRelationImpl">···</el-button>
                    </div>
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

        <el-dialog title="Raw Material" :visible.sync="dialog.dialogTableVisible" width="70%">
            <el-table :data="dialog.gridData"max-height="400">
                <el-table-column label="Raw Material Name" min-width="180" fixed sortable>
                    <template slot-scope="scope">
                        {{scope.row.srcArticle.name}}
                    </template>
                </el-table-column>
                <el-table-column prop="number" label="Raw Material Stock" min-width="180" sortable>
                    <template slot-scope="scope">
                        {{scope.row.srcArticle.number}}
                    </template>
                </el-table-column>
                <el-table-column label="Product Name" min-width="180" sortable>
                    <template slot-scope="scope">
                        {{scope.row.dstArticle.name}}
                    </template>
                </el-table-column>
                <el-table-column label="Product Stock" min-width="150" sortable>
                    <template slot-scope="scope">
                        {{scope.row.dstArticle.number}}
                    </template>
                </el-table-column>
                <el-table-column label="Consumption" min-width="150" sortable>
                    <template slot-scope="scope">
                        {{scope.row.number}}
                    </template>
                </el-table-column>
                <el-table-column label="Options" width="100" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="selectCurrentRelation(scope.row)">Select</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
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
                relationId: [
                    {required: true, message: 'Relation cannot be empty', trigger: 'blur'}
                ],
                number: [
                    {required: true, trigger: 'blur'}
                ]
            },
            dialog: {
                dialogTableVisible: false,
                gridData: []
            }
        }
    },
    methods: {
        selectRelationImpl() {
            let that = this;
            this.selectAllRelation((data) => {
                that.dialog.dialogTableVisible = true;
                that.dialog.gridData = data;
            })
        },
        selectCurrentRelation(scopeRow) {
            this.ruleForm.relationId = scopeRow.uuid;
            this.ruleForm.productName = `${scopeRow.srcArticle.name} => ${scopeRow.dstArticle.name}`;
            this.dialog.dialogTableVisible = false;
        },
        submitForm() {
            const that = this;
            this.$refs["ruleForm"].validate((valid) => {
                if (valid) {
                    let user = that.getUserInfo();
                    let data = { ...that.ruleForm };
                    data.groupId = user.groupId;
                    this.addHostOrder(data, () => {
                        that.successMessage("Add order succeed.");
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
            that.$router.push('/order/material');
        }
    }
}
</script>

<style lang="scss" scoped>

.source-home-container {
}

</style>

