function smsRegistClick() {
    const $emailInput = document.getElementById("key");
    const $passwordInput = document.getElementById("secret");
    const $extraValue = document.getElementById("extraValue");
    createChannel("sms", $emailInput.value, $passwordInput.value, $extraValue.value)
    .then(()=>console.log("create channel"));
  }
  

function gmailRegistClick() {
  const $emailInput = document.getElementById("key");
  const $passwordInput = document.getElementById("secret");
    console.log("메일 정보를 등록합니다.");
    createChannel("mail", $emailInput.value, $passwordInput.value)
    .then(()=>console.log("create channel"));
}

function pushRegistClick() {
    console.log("푸쉬 정보를 등록합니다.");
}

onload = () => {
    console.log("main-page.js loaded")
    const $modalDialog = document.getElementById("modal-dialog");
    const mailButton = document.getElementById("mailButton");
    const smsButton = document.getElementById("smsButton");
    const pushButton = document.getElementById("pushButton");

    smsButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("SMS",[createTextInputWithIdAndLabel("key","API-Key"), createPasswordInputWithIdAndLabel("secret", "API-Secret"),
            createInputWithIdAndLabelAndTypeAndPlaceHolder("extraValue","Phone", "text", "(ex : 01012345678)")]);
        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', smsRegistClick);
    };

    mailButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("Mail",[createTextInputWithIdAndLabel("key", "Email"), createPasswordInputWithIdAndLabel("secret","Secret")]);
        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', gmailRegistClick);    
    }

    pushButton.onclick = () => {
        $modalDialog.innerHTML = createModalContent("PUSH", [createTextAreaWithIdAndLabel("service-key", "Service-Key")]);

        const $registBtn = document.getElementById("regist-btn");
        $registBtn.addEventListener('click', pushRegistClick);
    }

}
