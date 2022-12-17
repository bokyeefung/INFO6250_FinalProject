import message from "../../common/message";

export default {
    name: 'orderManagement',
    mixins: [message],
    data() {
        return {}
    },
    methods: {
        selectRelation(uuid, successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/distributor/common/relation/' + uuid).then(result => {
                successFunc(result.data);
            }).catch(err => {
                if ('function' === typeof failedFunc) {
                    failedFunc(err.response.data.message);
                }
                that.errorMessage(err.response.data.message);
            });
        },
        selectAllRelation(successFunc, failedFunc) {
            let that = this;
            this.$axios.get('/api/distributor/common/relation/all').then(result => {
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
