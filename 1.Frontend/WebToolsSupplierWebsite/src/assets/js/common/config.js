export default {
    data() {
        return {
            isLogin: false,
            user: null
        }
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        flushUserInfo() {
            this.isLogin = this.user !== null;
            localStorage.setItem('userInfo', JSON.stringify(this.user));
        },
        getUserInfo() {
            if (this.user === null) {
                this.user = JSON.parse(localStorage.getItem('userInfo'));

                if (this.user === null && this.$route.path !== "/login") {
                    this.$router.push('/login?from=' + this.$route.path);
                }
            }
            this.isLogin = this.user !== null;
            return this.user;
        },
        clearUserInfo() {
            this.user = null;
            this.flushUserInfo();
            this.$router.push({path: '/login?from=' + this.$route.path});
        }
    }
}
