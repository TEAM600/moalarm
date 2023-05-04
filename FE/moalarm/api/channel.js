function getChannels() {
    return get(`/channels`);
}

function createChannel(type, key, secret, json) {
    return post(`/channels/type/${type}`, {
        "key": key,
        "secret": secret,
        "exraValue": json
    });
}