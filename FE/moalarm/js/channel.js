function getChannels() {
    return get(`/channels`);
}

function createChannel(type, key, secret, extraValue) {
    return post(`/channels/${type}`, {
        "key": key,
        "secret": secret,
        "extraValue": extraValue
    });
}