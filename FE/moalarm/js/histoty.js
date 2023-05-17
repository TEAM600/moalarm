function getHistory(requestId) {
    return get(`/history/${requestId}`);
}

function getHistory() {
    return get(`/history`);
}  

function getHistoryChart() {
    return get(`/history/chart?period=7`);
}
