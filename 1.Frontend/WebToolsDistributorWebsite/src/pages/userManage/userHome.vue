<template>
    <div id="source-home-container" class="source-home-container">
        <el-card style="margin-top: 20px;" v-loading="loading">
            <div slot="header">
                <span>Overview</span>
                <el-button style="float: right;" size="small" type="primary" @click="toUserAdd">Add User</el-button>
            </div>
            <div class="content-list-function">
                <el-button type="text" size="mini" :underline="false" @click="getAllListImpl"
                           style="font-size: 13px; margin-right: 10px;">
                    Refresh<i class="el-icon-refresh el-icon--right"></i>
                </el-button>
                <el-pagination :current-page.sync="page.current" :page-size="page.size" :total="page.total"
                               layout="total, prev, pager, next, jumper" style="float: right;">
                </el-pagination>
            </div class="content-list-function">
            <el-table :data="tableData" :resizable="false" ref="multipleTable" @selection-change="handleSelectionChange"
                      border highlight-current-row class="content-list-table">
                <el-table-column type="selection" width="36"></el-table-column>
                <el-table-column prop="name" label="Username" min-width="120" fixed sortable></el-table-column>
                <el-table-column prop="email" label="E-mail" min-width="120" sortable></el-table-column>
                <el-table-column prop="phone" label="Phone" min-width="120" sortable></el-table-column>
                <el-table-column prop="address" label="Address" min-width="300" sortable></el-table-column>
                <el-table-column prop="role" label="Role" min-width="120" sortable>
                    <template slot-scope="scope">
                        {{formatRole(scope.row.role)}}
                    </template>
                </el-table-column>
                <el-table-column label="Options" width="100" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="deleteUserImpl(scope.row)">Delete</el-button>
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
    import { formatDate, formatRole } from "../../assets/js/common/format";
    import userManagement from "../../assets/js/api/userManagement/userManagement";

    export default {
        name: 'userHome',
        mixins: [userManagement],
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
            formatRole,
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
            toUserAdd() {
                this.$router.push('/user/add');
            },
            deleteUserImpl(scopeRow) {
                let that = this;
                this.$confirm(`Delete user "${scopeRow.name}"？`, 'Warning', {
                    confirmButtonText: 'Confirm',
                    cancelButtonText: 'Cancel',
                    type: 'warning'
                }).then(() => {
                    that.deleteUser(scopeRow.uuid, function () {
                        that.successMessage('Delete user succeed.');
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

