<template>
    <div id="source-home-container" class="source-home-container">
        <el-card style="margin-top: 20px;" v-loading="loading">
            <div slot="header">
                <span>Overview</span>
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
                <el-table-column label="Product Name" min-width="180" fixed sortable>
                    <template slot-scope="scope">
                        {{scope.row.srcArticle.name}}
                    </template>
                </el-table-column>
                <el-table-column label="Stock" min-width="120" sortable>
                    <template slot-scope="scope">
                        {{scope.row.srcArticle.number}}
                    </template>
                </el-table-column>
                <el-table-column prop="number" label="Ordered Quantity" min-width="180" sortable>
                    <template slot-scope="scope">
                        <font :color="(scope.row.isConfirmed === 0 && scope.row.number > scope.row.srcArticle.number) ? 'red' : 'black'">{{scope.row.number}}</font>
                    </template>
                </el-table-column>
                <el-table-column prop="isConfirmed" label="Status" min-width="120" sortable>
                    <template slot-scope="scope">
                        <el-link :underline="false" :type="scope.row.isConfirmed === 0 ? 'primary' : 'success'">{{ scope.row.isConfirmed === 0 ? "OPENING" : "CLOSED" }}</el-link>
                    </template>
                </el-table-column>
                <el-table-column label="Options" width="100" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="confirmSourceImpl(scope.row)" :disabled="scope.row.isConfirmed !== 0">Confirm</el-button>
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
    import {formatDate, formatMoney} from "../../assets/js/common/format";
    import orderManagement from "../../assets/js/api/orderManagement/orderManagement";

    export default {
        name: 'orderHome',
        mixins: [orderManagement],
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
            confirmSourceImpl(scopeRow) {
                let that = this;
                this.$confirm(`Confirm order "${scopeRow.srcArticle.name}"？`, 'Warning', {
                    confirmButtonText: 'Confirm',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    that.confirmOrder(scopeRow.uuid, function () {
                        that.successMessage('Order confirmed.');
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

