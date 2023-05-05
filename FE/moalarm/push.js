const formgetPost = document.getElementById("modalFormSection");
const pushButton = document.getElementById("pushButton");

pushButton.addEventListener('click', pushModal);

function pushModal() {
    formgetPost.innerHTML = createModalContent("PUSH", [createTextAreaWithIdAndLabel("service-key", "Service-Key")]);

    const $registBtn = document.getElementById("regist-btn");
    $registBtn.addEventListener('click', pushRegistClick);
}

function pushRegistClick() {
    console.log("푸쉬 정보를 등록합니다.");
}
