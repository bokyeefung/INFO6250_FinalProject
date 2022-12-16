export default {
    name: 'format',
    data() {
        return {}
    },
    methods: {
        formatDateObj(currentDate) {
            let date = new Date(currentDate);
            return {
                year: date.getFullYear(),
                month: (date.getMonth() + 1 < 10 ? '0' : '') + (date.getMonth() + 1),
                date: (date.getDate() < 10 ? '0' : '') + date.getDate(),
                hour: (date.getHours() < 10 ? '0' : '') + date.getHours(),
                minute: (date.getMinutes() < 10 ? '0' : '') + date.getMinutes(),
                second: (date.getSeconds() < 10 ? '0' : '') + date.getSeconds()
            };
        },
        formatDate(currentDate) {
            let dateObj = this.formatDateObj(currentDate);
            return dateObj.year + '-' + dateObj.month + '-' + dateObj.date;
        },
        formatTime(currentDate) {
            let dateObj = this.formatDateObj(currentDate);
            return dateObj.hour + ':' + dateObj.minute + ':' + dateObj.second;
        },
        formatDatetime(currentDate) {
            return this.formatDate(currentDate) + ' ' + this.formatTime(currentDate);
        },
        formatMoney(currentMoney) {
            return currentMoney.toFixed(2);
        }
    }
}
