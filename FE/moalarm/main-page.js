function setApiKeyContent(moalarmKey) {
    const $apiKey = document.getElementById("api-key");
    $apiKey.innerText = moalarmKey;
}

const createSMSModal = () => {
    return createModalContent("SMS",
        [
            createTextInputWithIdAndLabelAndValue("key","API-Key",""), 
            createPasswordInputWithIdAndLabelAndValue("secret", "API-Secret",""),
            createInputWithIdAndLabelAndTypeAndPlaceHolderAndValue("extraValue","Phone", "text", "(ex : 01012345678)","")
        ]);
};

const createMailModal = () => {
    return createModalContent("MAIL",
        [
            createTextInputWithIdAndLabelAndValue("key", "Email",""), 
            createPasswordInputWithIdAndLabelAndValue("secret","Secret",""), 
            createTextInputWithIdAndLabelAndValue("extraValue","Sender","")
        ]);
};

const createPushModal = () => {
    return createModalContent("PUSH",
        [
            createTextAreaWithIdAndLabelAndValue("service-key", "Service-Key","")

        ]);
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

function changeToDeleteButton(button) {
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
        regist: () => {
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createSMSModal();
            const $registBtn = document.getElementById("regist-btn");
            $registBtn.addEventListener('click', () => sendRegistChannel("sms"));
        },
        view: async () => {
            console.log("view click");
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createModalContent("SMS",
                [
                    createTextInputWithIdAndLabelAndValue("key","API-Key","example"), 
                    createPasswordInputWithIdAndLabelAndValue("secret", "API-Secret","example"),
                    createInputWithIdAndLabelAndTypeAndPlaceHolderAndValue("extraValue","Phone", "text", "(ex : 01012345678)","example")
                ]);
                const $registBtn = document.getElementById("regist-btn");
                changeToDeleteButton($registBtn);
                $registBtn.addEventListener('click', () => {
                    console.log("delete sms");
                    deleteChannel("sms");
                });
        }
    };
    channelMap["mail"] = {
        button: document.getElementById("mailButton"),
        regist: () => {
            console.log("regist modal")
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createMailModal();
            const $registBtn = document.getElementById("regist-btn");
            $registBtn.addEventListener('click', () => sendRegistChannel("mail"));
        },
        view: async () => {
            console.log("view click");
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createModalContent("MAIL",
                [
                    createTextInputWithIdAndLabelAndValue("key", "Email","example"), 
                    createPasswordInputWithIdAndLabelAndValue("secret","Secret","example"), 
                    createTextInputWithIdAndLabelAndValue("extraValue","Sender","example")
                ]);
                const $registBtn = document.getElementById("regist-btn");
                changeToDeleteButton($registBtn);
                $registBtn.addEventListener('click', () => {
                    console.log("delete mail");
                    deleteChannel("mail");
                });
        }
    };
    channelMap["push"] = {
        button: document.getElementById("pushButton"),
        regist: () => {
            console.log("regist modal")
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createPushModal();
            const $registBtn = document.getElementById("regist-btn");
            $registBtn.addEventListener('click', () => sendRegistChannel("push"));
        },
        view: async () => {
            console.log("view click");
            const $modalDialog = document.getElementById("modal-dialog");
            $modalDialog.innerHTML = createModalContent("PUSH",
                [
                    createTextAreaWithIdAndLabelAndValue("service-key", "Service-Key","example")
                ]);
            const $registBtn = document.getElementById("regist-btn");
            changeToDeleteButton($registBtn);
            $registBtn.addEventListener('click', () => {
                console.log("delete push");
                deleteChannel("push");
            });
        }
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
                    currentChannel.button.onclick = currentChannel.regist;
                } else {
                    currentChannel.button.onclick = currentChannel.view;
                    changeToViewButton(currentChannel.button);
                }
            }
        });

}
