import ajax from "../common/ajax";
import config from "../../common/config";
import message from "../../common/message";

export default {
    name: 'loginAndLogout',
    mixins: [config, message, ajax],
    methods: {
        login(data, success, failed) {
            let that = this;
            this.$axios.post('/api/manufacture/security/login', data).then(result => {
                that.isLogin = true;
                that.user = result.data;
                that.flushUserInfo();
                that.successMessage('Login Success.');
                success(result.data);
            }).catch(err => {
                if ('function' === typeof failed) {
                    failed(err.response.data.message);
                }
                that.errorMessage('Login failed.');
            });
        },
        logout(success, failed) {
            let that = this;
            this.$axios.post('/api/manufacture/security/logout', {}).then(result => {
                that.clearUserInfo();
                that.successMessage('Logout Success.');
                success(result.data.msg);
            }).catch(err => {
                if ('function' === typeof failed) {
                    failed(err.response.data.message);
                }
                that.errorMessage('Logout failed.');
            });
        },
      checkLogin(uuid, failed) {
        let that = this;
        this.$axios.post('/api/manufacture/security/check/' + uuid).then(() => {
        }).catch(err => {
          if ('function' === typeof failed) {
            failed(err.response.data.message);
          }
          that.clearUserInfo();
          that.warningMessage('Please login again.');
        });
      }
    }
}
