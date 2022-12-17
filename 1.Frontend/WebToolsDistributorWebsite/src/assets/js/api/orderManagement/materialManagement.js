import message from "../../common/message";

export default {
    name: 'orderManagement',
    mixins: [message],
    data() {
        return {}
    },
    methods: {
        getAllList(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/distributor/manager/orders/host').then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        addHostOrder(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.post('/api/distributor/manager/orders/host', data).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        deleteHostOrder(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.delete('/api/distributor/manager/orders/host/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        selectHostOrder(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/distributor/manager/orders/host/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        updateHostOrder(data, successFunc, failedFunc) {
            let that = this;
            this.$axios.put('/api/distributor/manager/orders/host', data).then(result => {
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
