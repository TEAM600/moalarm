const formget = document.getElementById("modalFormSection");
const mailButton = document.getElementById("mailButton");

mailButton.addEventListener('click', mailModal);

function mailModal() {
    formget.innerHTML = createModalContent("Mail",[createInputWithIdAndLabel("key", "Email"), createInputWithIdAndLabel("secret","Secret")]);
  
    const $registBtn = document.getElementById("regist-btn");

    $registBtn.addEventListener('click', gmailRegistClick);
}

function gmailRegistClick() {
  const $emailInput = document.getElementById("key");
  const $passwordInput = document.getElementById("secret");
    console.log("메일 정보를 등록합니다.");
    createChannel("mail", $emailInput.value, $passwordInput.value)
    .then(()=>console.log("create channel"));
}
