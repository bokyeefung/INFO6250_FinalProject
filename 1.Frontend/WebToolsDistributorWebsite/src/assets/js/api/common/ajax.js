import message from "../../common/message";

export default {
    name: 'ajax',
    mixins: [message],
    data() {
        return {
            prefix: "/api",
            successCode: 200,
            successMsg: "请求成功",
            unLoginCode: -1,
            unLoginMsg: "请先登录",
            loginUrl: "/login",
            errorMsg: "请求失败"
        }
    },
    methods: {
        get(path, data) {
            let that = this;
            return new Promise((resolve, reject) => {
                that.$axios.get(that.prefix + path, {params: data}).then(result => {
                    if (result.data.status === that.unLoginCode) {
                        that.warningMessage(that.unLoginMsg);
                        that.router.replace({path: that.loginUrl});
                    } else if (result.data.status === that.successCode) {
                        that.successMessage(that.successMsg);
                        resolve(result.data.data);
                    } else {
                        that.warningMessage(result.data.msg || that.errorMsg);
                        reject(result.data);
                    }
                }).catch(err => {
                    that.errorMessage(that.errorMsg);
                    reject(err);
                })
            });
        },
        post(path, data) {
            let that = this;
            return new Promise((resolve, reject) => {
                that.$axios.post(that.prefix + path, data).then(result => {
                    if (result.data.status === that.unLoginCode) {
                        that.warningMessage(that.unLoginMsg);
                        that.router.replace({path: that.loginUrl});
                    } else if (result.data.status === that.successCode) {
                        that.successMessage(that.successMsg);
                        resolve(result.data.data);
                    } else {
                        that.warningMessage(result.data.msg || that.errorMsg);
                        reject(result.data);
                    }
                }).catch(err => {
                    that.errorMessage(that.errorMsg);
                    reject(err);
                })
            });
        },
        put(path, data) {
            let that = this;
            return new Promise((resolve, reject) => {
                that.$axios.put(that.prefix + path, data).then(result => {
                    if (result.data.status === that.unLoginCode) {
                        that.warningMessage(that.unLoginMsg);
                        that.router.replace({path: that.loginUrl});
                    } else if (result.data.status === that.successCode) {
                        that.successMessage(that.successMsg);
                        resolve(result.data.data);
                    } else {
                        that.warningMessage(result.data.msg || that.errorMsg);
                        reject(result.data);
                    }
                }).catch(err => {
                    that.errorMessage(that.errorMsg);
                    reject(err);
                })
            });
        },
        delete(path, data) {
            let that = this;
            return new Promise((resolve, reject) => {
                that.$axios.delete(that.prefix + path, data).then(result => {
                    if (result.data.status === that.unLoginCode) {
                        that.warningMessage(that.unLoginMsg);
                        that.router.replace({path: that.loginUrl});
                    } else if (result.data.status === that.successCode) {
                        that.successMessage(that.successMsg);
                        resolve(result.data.data);
                    } else {
                        that.warningMessage(result.data.msg || that.errorMsg);
                        reject(result.data);
                    }
                }).catch(err => {
                    that.errorMessage(that.errorMsg);
                    reject(err);
                })
            });
        }
    }
}
