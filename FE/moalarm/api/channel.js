function getChannels() {
    return get(`/channels`);
}

function createChannel(type, key, secret, json) {
    return post(`/channels/${type}`, {
        "key": key,
        "secret": secret,
        "json": json
    });
}