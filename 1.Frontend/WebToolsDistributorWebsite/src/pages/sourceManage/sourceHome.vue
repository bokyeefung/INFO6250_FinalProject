<template>
    <div id="source-home-container" class="source-home-container">
        <el-card>
            <el-row style="display: flex; flex-direction: row;">
                <el-col style="width: 100%; padding-right: 10px;">
                    <el-input class="header-input" placeholder="请输入关键词进行查找">
                        <el-button slot="append" icon="el-icon-search"></el-button>
                    </el-input>
                </el-col>
                <el-col style="width: 120px; flex-shrink: 0;">
                    <el-button class="header-add-btn" type="primary" icon="el-icon-edit" @click="toSourceAdd">添加货源</el-button>
                </el-col>
            </el-row>
        </el-card>
        <el-card style="margin-top: 20px;" v-loading="loading">
            <div class="content-list-function">
                <el-button type="text" size="mini" :underline="false" @click="getAllListImpl"
                           style="font-size: 13px; margin-right: 10px;">
                    刷新<i class="el-icon-refresh el-icon--right"></i>
                </el-button>
                <el-dropdown trigger="click" style="padding: 2px; line-height: 28px;">
                    <el-button type="text" size="mini" :disabled="multipleSelection.length === 0" style="font-size: 13px;">
                        批量操作<i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>批量上架</el-dropdown-item>
                        <el-dropdown-item>批量下架</el-dropdown-item>
                        <el-dropdown-item>批量删除</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <el-pagination :current-page.sync="page.current" :page-size="page.size" :total="page.total"
                               layout="total, prev, pager, next, jumper" style="float: right;">
                </el-pagination>
            </div>
            <el-table :data="tableData" :resizable="false" ref="multipleTable" @selection-change="handleSelectionChange"
                      border highlight-current-row class="content-list-table">
                <el-table-column type="selection" width="36"></el-table-column>
                <el-table-column prop="sName" label="名称" min-width="150" fixed sortable></el-table-column>
                <el-table-column prop="selledAmount" label="已售数量 (件)" width="130" sortable></el-table-column>
                <el-table-column prop="stockAmount" label="库存数量 (件)" width="130" sortable></el-table-column>
                <el-table-column prop="sellPrice" label="销售价格 (元/件)" width="150" sortable>
                    <template slot-scope="scope">
                        {{formatMoney(scope.row.sellPrice)}}
                    </template>
                </el-table-column>
                <el-table-column prop="minimumQuantity" label="最低购买数量 (件)" width="160" sortable></el-table-column>
                <el-table-column prop="expirationTime" label="过期时间" min-width="110" sortable>
                    <template slot-scope="scope">
                        {{formatDate(scope.row.expirationTime)}}
                    </template>
                </el-table-column>
                <el-table-column prop="postage" label="邮费 (元/件)" width="120" sortable>
                    <template slot-scope="scope">
                        {{formatMoney(scope.row.postage)}}
                    </template>
                </el-table-column>
                <el-table-column label="支持发货种类" min-width="120">
                    <template slot-scope="scope">
                        <el-link v-if="!!scope.row.cityAgent" type="primary" :underline="false">同城</el-link>
                        <span v-if="!!scope.row.cityAgent && !!scope.row.allAgent">、</span>
                        <el-link v-if="!!scope.row.allAgent" type="primary" :underline="false">全国</el-link>
                        <el-link v-if="!scope.row.cityAgent && !scope.row.allAgent" type="danger" :underline="false">无</el-link>
                    </template>
                </el-table-column>
                <el-table-column prop="supplyStatus" label="货源状态" min-width="80">
                    <template slot-scope="scope">
                        <el-link v-if="scope.row.supplyStatus === 1" type="success" :underline="false">正常</el-link>
                        <el-link v-else-if="scope.row.supplyStatus === 2" type="danger" :underline="false">缺货</el-link>
                        <el-link v-else-if="scope.row.supplyStatus === 3" type="warning" :underline="false">暂停</el-link>
                        <el-link v-else type="info" :underline="false">错误</el-link>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="150" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="showDetail(scope.row)">详情</el-button>
                        <el-button type="text" size="small" @click="toSourceChange(scope.row)">编辑</el-button>
                        <el-dropdown trigger="click" style="display: inline-block; margin-left: 10px;">
                            <el-button type="text" size="mini" style="font-size: 12px;">
                                更多<i class="el-icon-arrow-down el-icon--right"></i>
                            </el-button>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>查看物料图片</el-dropdown-item>
                                <el-dropdown-item>查看证书图片</el-dropdown-item>
                                <el-dropdown-item>删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </template>
                </el-table-column>
            </el-table>
            <div class="content-list-function">
                <el-button type="text" size="mini" :underline="false" @click="getAllListImpl"
                           style="font-size: 13px; margin-right: 10px;">
                    刷新<i class="el-icon-refresh el-icon--right"></i>
                </el-button>
                <el-dropdown trigger="click" style="padding: 2px; line-height: 28px;">
                    <el-button type="text" size="mini" :disabled="multipleSelection.length === 0" style="font-size: 13px;">
                        批量操作<i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>批量上架</el-dropdown-item>
                        <el-dropdown-item>批量下架</el-dropdown-item>
                        <el-dropdown-item>批量删除</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
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
                    that.tableData = result.list;
                    that.loading = false;
                }, () => { that.loading = false; });
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            toSourceAdd() {
                this.$router.push('/source/add');
            },
            showDetail(scopeRow) {
                // TODO: 添加并跳转到详情展示页面
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

