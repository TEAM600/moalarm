function getChannel(type) {
    return get(`/channels//info/${type}`)
}

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

function deleteChannel(type) {
    console.log("channel delete");
    return remove(`/channels/${type}`);
}