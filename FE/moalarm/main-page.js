function sendRegistChannel(channelType) {
    const $key = document.getElementById("key");
    const $secret = document.getElementById("secret");
    const $extraValue = document.getElementById("extraValue");

    let key = $key === null ? null : $key.value;
    let secret = $secret === null ? null : $secret.value;
    let extraValue = $extraValue === null ? null : $extraValue.value;

    console.log("channelType : ", channelType);
    console.log("key : ", key);
    console.log("secret : ", secret);
    console.log("extra : ", extraValue);
    
    createChannel(channelType, key, secret, extraValue)
        .then(()=>console.log("create channel"));
}

function setApiKeyContent(moalarmKey) {
    const $apiKey = document.getElementById("api-key");
    $apiKey.innerText = moalarmKey;
}

onload = () => {
    const $modalDialog = document.getElementById("modal-dialog");
    const mailButton = document.getElementById("mailButton");
    const smsButton = document.getElementById("smsButton");
    const pushButton = document.getElementById("pushButton");

    smsButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("SMS",
            [createTextInputWithIdAndLabel("key","API-Key"), createPasswordInputWithIdAndLabel("secret", "API-Secret"),
                createInputWithIdAndLabelAndTypeAndPlaceHolder("extraValue","Phone", "text", "(ex : 01012345678)")]);
        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', () => sendRegistChannel("sms"));
    };

    mailButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("MAIL",
            [createTextInputWithIdAndLabel("key", "Email"), createPasswordInputWithIdAndLabel("secret","Secret")]);
        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', () => sendRegistChannel("mail"));    
    }

    pushButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("PUSH", 
            [createTextAreaWithIdAndLabel("service-key", "Service-Key")]);
        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', () => sendRegistChannel("push"));
    }


    const $refreshButton = document.getElementById("refresh-api-key");

    getKey()
        .then(response => {
            setApiKeyContent(response.moalarmKey);
        });

    $refreshButton.onclick = () => {
        refreshKey().then(response=>setApiKeyContent(response.moalarmKey));
    }

}
