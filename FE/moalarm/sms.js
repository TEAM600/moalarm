const formgetSMS = document.getElementById("modalFormSection");
const smsButton = document.getElementById("smsButton");

smsButton.addEventListener('click', smsModal);

function smsModal() {
  formgetSMS.innerHTML = createModalContent("SMS",[createTextInputWithIdAndLabel("key","API-Key"), createPasswordInputWithIdAndLabel("secret", "API-Secret"),
      createInputWithIdAndLabelAndTypeAndPlaceHolder("extraValue","Phone", "text", "(ex : 01012345678)")]);
  const $registBtn = document.getElementById("regist-btn");
  $registBtn.addEventListener('click', smsRegistClick);
}

function smsRegistClick() {
  const $emailInput = document.getElementById("key");
  const $passwordInput = document.getElementById("secret");
  const $extraValue = document.getElementById("extraValue");
  createChannel("sms", $emailInput.value, $passwordInput.value, $extraValue.value)
  .then(()=>console.log("create channel"));
}
