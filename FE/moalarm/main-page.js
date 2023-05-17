function setApiKeyContent(moalarmKey) {
    const $apiKey = document.getElementById("api-key");
    $apiKey.innerText = moalarmKey;
}

const createSMSModal = () => {
    const $modalDialog = document.getElementById("modal-dialog");
    $modalDialog.innerHTML = createModalContent("SMS",
        [createTextInputWithIdAndLabel("key","API-Key"), createPasswordInputWithIdAndLabel("secret", "API-Secret"),
            createInputWithIdAndLabelAndTypeAndPlaceHolder("extraValue","Phone", "text", "(ex : 01012345678)")]);
    const $registBtn = document.getElementById("regist-btn");
    $registBtn.addEventListener('click', () => sendRegistChannel("sms"));
};

const createMailModal = () => {
    const $modalDialog = document.getElementById("modal-dialog");
    $modalDialog.innerHTML = createModalContent("MAIL",
        [createTextInputWithIdAndLabel("key", "Email"), createPasswordInputWithIdAndLabel("secret","Secret"), 
        createTextInputWithIdAndLabel("extraValue","Sender")]);
    const $registBtn = document.getElementById("regist-btn");
    $registBtn.addEventListener('click', () => sendRegistChannel("mail"));    
};

const createPushModal = () => {
    const $modalDialog = document.getElementById("modal-dialog");
    $modalDialog.innerHTML = createModalContent("PUSH", 
        [createTextAreaWithIdAndLabel("service-key", "Service-Key")]);
    const $registBtn = document.getElementById("regist-btn");
    $registBtn.addEventListener('click', () => sendRegistChannel("push"));
};

function sendRegistChannel(channelType) {
    const $key = document.getElementById("key");
    const $secret = document.getElementById("secret");
    const $extraValue = document.getElementById("extraValue");

    let key = $key === null ? null : $key.value;
    let secret = $secret === null ? null : $secret.value;
    let extraValue = $extraValue === null ? null : $extraValue.value;
    
    createChannel(channelType, key, secret, extraValue)
        .then(()=>console.log("create channel"))
        .then(()=>{
            const $modalComponent = bootstrap.Modal.getInstance(document.getElementById("modalComponent"));
            $modalComponent.hide();
            alert("생성되었습니다.");
        })
        .catch(console.log);
}

function changeToViewButton(button) {
    button.innerText = "VIEW";
    button.style.backgroundColor = "BLUE";
    button.style.borderColor = "BLUE";
}

function changeToViewButton(button) {
    button.innerText = "DELETE";
    button.style.backgroundColor = "RED";
    button.style.borderColor = "RED";
    button.removeAttribute("data-bs-toggle");
    button.removeAttribute("data-bs-target");            
}

onload = () => {
    const $refreshButton = document.getElementById("refresh-api-key");
    
    const channelMap = {};
    channelMap["sms"] = {
        button: document.getElementById("smsButton"),
        modal: createSMSModal,
        view: () => {
            console.log("view click");
        },
        remove: () => deleteChannel("sms")
    };
    channelMap["mail"] = {
        button: document.getElementById("mailButton"),
        modal: createMailModal,
        remove: () => deleteChannel("mail")
    };
    channelMap["push"] = {
        button: document.getElementById("pushButton"),
        modal: createPushModal,
        remove: () => deleteChannel("push")
    };

    getKey()
        .then(response => {
            setApiKeyContent(response.moalarmKey);
        });

    $refreshButton.onclick = () => {
        refreshKey().then(response=>setApiKeyContent(response.moalarmKey));
    }

    getChannels()
        .then((response)=> {
            for (let channel of response) {
                console.log(channel);
                const currentChannel = channelMap[channel.type];
                if (channel.registration === false) {
                    currentChannel.button.onclick = currentChannel.modal;
                } else {
                    currentChannel.button.onclick = currentChannel.view;
                    changeToViewButton(currentChannel.button);
                    
                }
            }
            
        });

}
