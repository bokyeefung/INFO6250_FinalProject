<template>
    <div id="source-home-container" class="source-home-container">
        <el-card style="margin-top: 20px;" v-loading="loading">
            <div slot="header">
                <span>Overview</span>
                <el-button style="float: right;" size="small" type="primary" @click="toSourceAdd">New Product</el-button>
            </div>
            <div class="content-list-function">
                <el-button type="text" size="mini" :underline="false" @click="getAllListImpl"
                           style="font-size: 13px; margin-right: 10px;">
                    Refresh<i class="el-icon-refresh el-icon--right"></i>
                </el-button>
                <el-pagination :current-page.sync="page.current" :page-size="page.size" :total="page.total"
                               layout="total, prev, pager, next, jumper" style="float: right;">
                </el-pagination>
            </div>
            <el-table :data="tableData" :resizable="false" ref="multipleTable" @selection-change="handleSelectionChange"
                      border highlight-current-row class="content-list-table">
                <el-table-column type="selection" width="36"></el-table-column>
                <el-table-column prop="name" label="Product Name" min-width="180" fixed sortable></el-table-column>
                <el-table-column prop="moneyCost" label="Unit Price (USD)" min-width="160" sortable>
                    <template slot-scope="scope">
                        {{formatMoney(scope.row.moneyCost)}}
                    </template>
                </el-table-column>
                <el-table-column prop="timeCost" label="Production Cycle (Days)" min-width="220" sortable></el-table-column>
                <el-table-column prop="number" label="Stock" min-width="120" sortable></el-table-column>
                <el-table-column label="Options" width="180" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="updateSourceImpl(scope.row)">Update</el-button>
                        <el-button type="text" size="small" @click="deleteSourceImpl(scope.row)">Delete</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="content-list-function">
                <el-button type="text" size="mini" :underline="false" @click="getAllListImpl"
                           style="font-size: 13px; margin-right: 10px;">
                    Refresh<i class="el-icon-refresh el-icon--right"></i>
                </el-button>
                <el-pagination :current-page.sync="page.current" :page-size="page.size" :total="page.total"
                               layout="total, prev, pager, next, jumper" style="float: right;">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
    import sourceManagement from "../../assets/js/api/sourceManagement/sourceManagement";
    import {formatDate, formatMoney} from "../../assets/js/common/format";

    export default {
        name: 'sourceHome',
        mixins: [sourceManagement],
        data() {
            return {
                loading: false,
                multipleSelection: [],
                page: {
                    current: 1,
                    size: 20,
                    total: 20
                },
                scope: "",
                tableData: []
            }
        },
        mounted() {
            this.getAllListImpl();
        },
        methods: {
            formatDate,
            formatMoney,
            getAllListImpl() {
                let that = this;
                that.loading = true;
                let data = {};
                that.getAllList(data, result => {
                    that.tableData = result;
                    that.page.total = result.length;
                    that.loading = false;
                }, () => { that.loading = false; });
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            toSourceAdd() {
                this.$router.push('/source/add');
            },
            updateSourceImpl(scopeRow) {
                this.$router.push(`/source/change/${scopeRow.uuid}`);
            },
            deleteSourceImpl(scopeRow) {
                let that = this;
                this.$confirm(`Delete product "${scopeRow.name}"？`, 'Warning', {
                    confirmButtonText: 'Confirm',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    that.deleteSource(scopeRow.uuid, function () {
                        that.successMessage('Delete product succeed.');
                        that.getAllListImpl();
                    });
                }).catch(() => {});
            },
            toSourceChange(scopeRow) {
                let id = scopeRow.id;
                this.$router.push('/source/change/' + id);
            }
        }
    }
</script>

<style lang="scss" scoped>

    .source-home-container {
        margin: 20px;

        .content-list-function {
            height: 32px;
        }

        .content-list-table {
            width: 100%;
            margin: 10px 0;
        }
    }

</style>

