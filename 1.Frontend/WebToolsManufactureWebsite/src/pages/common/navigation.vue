<template>
    <div id="main-container" class="main-container">
        <el-menu :default-active="this.$route.path" @select="selectMenu"
                 :unique-opened="true" :router="true" :collapse="isCollapse" :collapse-transition="true"
                 :class="['el-menu-vertical-demo', 'left-box', {'screen-collapse': isCollapse}]"
                 background-color="#1e2b32" text-color="#fff" active-text-color="#2f89fc">
            <div class="button-box" @click="collapse">
                <div class="tip-box">
                    <div class="collapse-tip">{{(isCollapse ? '展开' : '收起') + '导航栏'}}</div>
                </div>
                <div class="collapse-button">
                    <div class="dash-bar"></div><div class="dash-bar"></div><div class="dash-bar"></div>
                </div>
            </div>
            <recursion-submenu :navigation="navigation"></recursion-submenu>
        </el-menu>
        <div class="right-box">
            <div class="right-header-box">
                <div class="user-function-box">
                    <el-link v-if="!isLogin" type="primary" :underline="false" @click="loginFunction"
                             style="height: 36px; line-height: 36px;">Login </el-link>
                    <div v-else style="height: 100%; display: flex; flex-direction: row;">
                        <el-dropdown style="display: inline-block; margin-left: 10px; height: 36px;" @command="handleCommand">
                            <span class="el-dropdown-link">{{ user.name }}<i class="el-icon-arrow-down el-icon--right"></i></span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="logoutImpl">Exit</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </div>
            </div>
            <div class="view-content-box"><router-view/></div>
        </div>
        <div :class="['aside-wrapper', {'screen-collapse': isCollapse}]" @click="collapse"></div>
    </div>
</template>

<script>
    import recursionSubmenu from '../../components/recursion-submenu/recursion-submenu';
    import config from "../../assets/js/common/config";
    import loginAndLogout from "../../assets/js/api/userManagement/loginAndLogout";
    import message from "../../assets/js/common/message";

    export default {
        name: "navigation",
        components: {
            recursionSubmenu
        },
        mixins: [config, loginAndLogout, message],
        data() {
            return {
                navigation: [],
                isCollapse: false,
            }
        },
        mounted() {
            this.getNavigation();
        },
        methods: {
            getNavigation() {
                const baseNavigation = [
                    { name: 'Home', icon: 'el-icon-s-home', href: '/'},
                    { name: 'User Management', icon: 'el-icon-s-order', href: '/user', role: [1] },
                    { name: 'Supply Chain Management', icon: 'el-icon-s-order', href: '/supply', role: [2] },
                    { name: 'Raw Material Management', icon: 'el-icon-s-order', href: '/source', role: [3] }
                ];
                const currentNavigation = [];
                baseNavigation.forEach(item => {
                    if (item.role === undefined || item.role.length === 0 || item.role.includes(this.user.role)) {
                        currentNavigation.push(item);
                    }
                })
                this.navigation = currentNavigation;
            },
            collapse() {
                this.isCollapse = !this.isCollapse;
            },
            selectMenu() {
                if (document.body.clientWidth <= 991) {
                    this.collapse();
                }
            },
            loginFunction() {
                this.$router.push('/login?from=' + this.$route.path);
            },
            handleCommand(command) {
                if (command === "logoutImpl") {
                    this.logoutImpl();
                }
            },
            logoutImpl() {
                let that = this;
                that.logout(() => {}, that.errorMessage);
            }
        }
    }
</script>

<style lang="scss" scoped>

.main-container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;

    .left-box {
        flex-shrink: 0;
        border-right: none;
        background-color: #2b3440;

        .button-box {
            display: flex;
            flex-direction: row;
            width: 100%;
            height: 56px;
            cursor: pointer;
            transition: background-color .3s;

            .tip-box {
                width: 100%;
                overflow: hidden;

                .collapse-tip {
                    float: right;
                    padding: 0 10px;
                    height: 56px;
                    text-align: right;
                    font-size: 14px;
                    line-height: 56px;
                    color: #909399;
                    transform: translateX(100%);
                    transition: color .3s, transform .3s;
                }
            }

            .collapse-button {
                position: relative;
                width: 64px;
                flex-shrink: 0;

                .dash-bar {
                    position: absolute;
                    left: 25px;
                    width: 14px;
                    height: 4px;
                    background-color: #909399;
                    border-radius: 4px;
                    transition: width .2s, opacity .2s, left .2s, transform .3s, color .3s;

                    &:nth-child(1) { top: 21px; }
                    &:nth-child(2) { top: 26px; }
                    &:nth-child(3) { top: 31px; }
                }
            }

            &:hover {
                background-color: #182228;

                .tip-box {
                    .collapse-tip {
                        color: #2f89fc;
                        transform: translateX(0);
                    }
                }

                .dash-bar {
                    background-color: #2f89fc;

                    &:nth-child(1) { transform: translate3d(1px, 2px, 0) rotate(30deg); }
                    &:nth-child(2) { width: 0; opacity: 0; }
                    &:nth-child(3) { transform: translate3d(1px, -2px, 0) rotate(-30deg); }
                }
            }
        }

        &:not(.el-menu--collapse) {
            width: 300px;

            .button-box:hover {
                .dash-bar:nth-child(1) { transform: translate3d(-1px, 2px, 0) rotate(-30deg) !important; }
                .dash-bar:nth-child(2) { width: 0 !important; opacity: 0 !important; left: 39px; }
                .dash-bar:nth-child(3) { transform: translate3d(-1px, -2px, 0) rotate(30deg) !important; }
            }
        }
    }

    .right-box {
        width: 100%;
        height: 100%;
        overflow: hidden;
        display: flex;
        flex-direction: column;

        .right-header-box {
            height: 56px;
            flex-shrink: 0;
            font-size: 14px;
            color: #fff;
            background-color: #1e2b32;

            .user-function-box {
                float: right;
                height: 36px;
                padding: 10px 20px;

                .el-dropdown-link {
                    cursor: pointer;
                    color: #fff;
                    line-height: 36px;
                }

                .el-icon-arrow-down {
                    font-size: 12px;
                }

                /deep/ .el-link--primary {
                    color: #fff;

                    &:hover {
                        color: #409EFF;
                    }
                }
            }
        }

        .view-content-box {
            height: 100%;
            overflow-y: auto;
        }
    }

    @media screen and (max-width: 991px) {
        display: block;

        .left-box {
            position: fixed;
            left: -300px;
            width: 300px;
            height: 100vh;
            z-index: 1001;
            transition: transform .3s;

            .button-box {
                position: absolute;
                width: 30px;
                height: 30px;
                top: 10px;
                left: 310px;
                border-radius: 56px;
                overflow: hidden;

                .tip-box {
                    display: none;
                }

                .collapse-button {
                    width: 56px;
                    background-color: rgba(30, 43, 50, 0.6);

                    .dash-bar {
                        left: 8px;

                        &:nth-child(1) { top: 8px; }
                        &:nth-child(2) { top: 13px; }
                        &:nth-child(3) { top: 18px; }
                    }
                }
            }

            /deep/ .el-menu-item {
                i {
                    margin-right: 5px !important;
                }

                span {
                    width: inherit;
                    height: inherit;
                    visibility: inherit;
                }
            }

            &:not([class*=screen-collapse]) {
                transform: translateX(100%);
            }
        }

        .right-box {
            display: block;
            overflow-y: auto;

            .view-content-box {
                height: fit-content;
                overflow-y: hidden;
            }
        }

        .aside-wrapper {
            position: fixed;
            left: 0;
            top: 0;
            width: 100vw;
            height: 100vh;
            opacity: 0;
            visibility: hidden;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
            transition: opacity .3s;

            &:not([class*=screen-collapse]) {
                opacity: 1;
                visibility: visible;
            }
        }
    }
}

</style>
