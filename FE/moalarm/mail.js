const formget = document.getElementById("modalFormSection");
const mailButton = document.getElementById("mailButton");

const mailContent = () => `
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="MailModalLabel">Mail</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              ${createEmailInputWithId("key")}
              ${createPasswordInputWithId("secret")}
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <div id="mailPost" class="btn btn-primary">Regist</button>
          </div>
        </div>
        `;

function createEmailInputWithId(id) {
  return `
    <div class="mb-3">
      <label for="recipient-name" class="col-form-label">Email:</label>
      <input type="text" class="form-control" id="${id}">
    </div>`;
}

function createPasswordInputWithId(id) {
  return `
    <div class="mb-3">
      <label for="recipient-name" class="col-form-label">Secret:</label>
      <input type="password" class="form-control" id="${id}">
    </div>`;
}

mailButton.addEventListener('click', mailModal);

function mailModal() {
    formget.innerHTML = mailContent();
    const mailPost = document.getElementById("mailPost");
    mailPost.addEventListener('click', gmailRegistClick);
}

function gmailRegistClick() {
  const $emailInput = document.getElementById("key");
  const $passwordInput = document.getElementById("secret");
    console.log("메일 정보를 등록합니다.");
    createChannel("mail", $emailInput.value, $passwordInput.value)
    .then(()=>console.log("create channel"));
}
