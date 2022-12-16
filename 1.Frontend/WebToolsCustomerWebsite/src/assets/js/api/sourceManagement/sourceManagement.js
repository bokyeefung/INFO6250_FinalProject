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
            that.get("/demoproject/test/getAllList", data).then(result => {
                    successFunc(result);
            }).catch(err => {
                failedFunc(err);
            });
        }
    }
}
