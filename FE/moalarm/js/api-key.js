function getKey() {
    return get(`/key`);
}

function refreshKey() {
    return post(`/key`)
}

function setApiKeyContent(moalarmKey) {
    const $apiKey = document.getElementById("api-key");
    $apiKey.innerText = moalarmKey;
}

onload = () => {
    const $refreshButton = document.getElementById("refresh-api-key");

    
    getKey()
    .then(response => {
        console.log(response);
        setApiKeyContent(response.moalarmKey);
    })

    $refreshButton.onclick = () => {
        refreshKey().then(response=>setApiKeyContent(response.moalarmKey));
    }

}