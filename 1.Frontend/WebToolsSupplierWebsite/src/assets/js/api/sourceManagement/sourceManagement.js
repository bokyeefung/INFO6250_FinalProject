import message from "../../common/message";
import ajax from "../common/ajax";

export default {
    name: 'sourceManagement',
    mixins: [message, ajax],
    data() {
        return {}
    },
    methods: {
        getAllList(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/supplier/manager/article').then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        addSource(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.post('/api/supplier/manager/article', data).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        deleteSource(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.delete('/api/supplier/manager/article/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        selectSource(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/supplier/manager/article/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        updateSource(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.put('/api/supplier/manager/article', data).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
    }
}
