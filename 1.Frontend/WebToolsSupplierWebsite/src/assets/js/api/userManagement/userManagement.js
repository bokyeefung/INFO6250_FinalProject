import message from "../../common/message";
import ajax from "../common/ajax";

export default {
    name: 'sourceManagement',
    mixins: [message],
    data() {
        return {}
    },
    methods: {
        getAllList(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/supplier/admin/user').then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        addUser(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.post('/api/supplier/admin/user', data).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        deleteUser(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.delete('/api/supplier/admin/user/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        }
    }
}
