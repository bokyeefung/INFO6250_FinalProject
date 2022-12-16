const formatDateObj = (currentDate) => {
    let date = new Date(currentDate);
    return {
        year: date.getFullYear(),
        month: (date.getMonth() + 1 < 10 ? '0' : '') + (date.getMonth() + 1),
        date: (date.getDate() < 10 ? '0' : '') + date.getDate(),
        hour: (date.getHours() < 10 ? '0' : '') + date.getHours(),
        minute: (date.getMinutes() < 10 ? '0' : '') + date.getMinutes(),
        second: (date.getSeconds() < 10 ? '0' : '') + date.getSeconds()
    };
};

const formatDate = (currentDate) => {
    let dateObj = formatDateObj(currentDate);
    return dateObj.year + '-' + dateObj.month + '-' + dateObj.date;
};

const formatTime = (currentDate) => {
    let dateObj = formatDateObj(currentDate);
    return dateObj.hour + ':' + dateObj.minute + ':' + dateObj.second;
};

const formatDatetime = (currentDate) => {
    return formatDate(currentDate) + ' ' + formatTime(currentDate);
};

const formatMoney = (currentMoney) => {
    return currentMoney.toFixed(2);
};

const formatRole = (roleNumber) => {
    switch (roleNumber) {
        case 1:
            return "Admin";
        case 2:
            return "Plant Manager";
        default:
            return "Manager";
    }
};

export {
    formatDateObj,
    formatDate,
    formatTime,
    formatDatetime,
    formatMoney,
    formatRole
}
