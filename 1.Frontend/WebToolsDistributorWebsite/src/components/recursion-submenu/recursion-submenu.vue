<template>
    <fragment>
        <fragment v-for="(submenu, indexSubmenu) in navigation" :key="indexSubmenu">
            <el-submenu v-if="submenu.children" :index="getIndex(indexSubmenu + 1)" :disabled="!!submenu.disabled">
                <template slot="title">
                    <i v-if="!!submenu.icon" :class="submenu.icon"></i>
                    <span>{{submenu.name}}</span>
                </template>
                <!-- 递归引用当前组件，生成下一层导航菜单，并将前缀设置为当前节点的唯一编号 -->
                <recursion-submenu :navigation="submenu.children" :prefix="getIndex(indexSubmenu + 1)"></recursion-submenu>
            </el-submenu>
            <el-menu-item v-else :index="submenu.href || '/'" :disabled="!!submenu.disabled">
                <i v-if="!!submenu.icon" :class="submenu.icon"></i>
                <span>{{submenu.name}}</span>
            </el-menu-item>
        </fragment>
    </fragment>
</template>

<script>
    /**
     * 关于 navigation 对象的说明：
     *   navigation对象用于本页面中二次封装的子导航栏组件，支持任意多层导航节点。
     *   按钮节点的写法：
     *     { name: '', icon: '', href: '', disabled: false }
     *     其中，name是导航按钮的名称，icon是导航按钮的图标，href是导航按钮的目标跳转地址, disabled表示是否禁用该节点
     *   下拉列表节点的写法：
     *     {
     *       name: '',
     *       icon: '',
     *       disabled: false，
     *       children: [
     *         { name: '', icon: '', href: '', disabled: false }
     *       ]
     *     }
     *     其中，children是父级导航节点的子导航节点列表，子导航节点也可以是按钮节点或下拉列表节点
     *
     * 关于 prefix 对象的说明：
     *   prefix对象用于为下拉列表节点进行编号，使用prefix属性为每个节点生成全局唯一编号，防止编号重复造成错误
     */
    export default {
        name: "recursion-submenu",
        props: {
            navigation: {
                type: Array,
                required: true
            },
            prefix: {
                type: [String, Number],
                required: false,
                default: ''
            }
        },
        methods: {
            /**
             * 根据传入组件的index前缀和当前节点的序号生成对应的全剧唯一index
             * @param currentIndex 当前节点的序号
             * @returns {string} 生成的全局唯一index
             */
            getIndex(currentIndex){
                let that = this;
                let currentPrefix = !!that.prefix ? that.prefix + '-' : '';
                return currentPrefix + currentIndex;
            }
        }
    }
</script>

<style scoped>

</style>
