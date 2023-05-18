function getKey() {
    return get(`/key`);
}

function refreshKey() {
    return post(`/key`)
}
