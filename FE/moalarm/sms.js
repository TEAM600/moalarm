const formgetSMS = document.getElementById("modalFormSection");
const smsButton = document.getElementById("smsButton");

const smsContent = `
<div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="smsModalLabel">SMS</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              ${createApiKeyInputWithId("key")}
              ${createApiSecretInputWithId("secret")}
              ${createPhoneInputWithId("extraValue")}
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <div id="smsPost" class="btn btn-primary">Regist</button>
          </div>
        </div>
        `;

smsButton.addEventListener('click', smsModal);

function createApiKeyInputWithId(id) {
  return `
    <div class="mb-3">
      <label for="recipient-name" class="col-form-label">API-Key:</label>
      <input type="text" class="form-control" id="${id}">
    </div>`;
}

function createApiSecretInputWithId(id) {
  return `
    <div class="mb-3">
      <label for="recipient-name" class="col-form-label">API-Secret:</label>
      <input type="password" class="form-control" id="${id}">
    </div>`;
}

function createPhoneInputWithId(id) {
  return `
    <div class="mb-3">
      <label for="recipient-name" class="col-form-label">Phone:</label>
      <input type="text" class="form-control" id="${id}" placeholder="(ex : 01012345678)">
    </div>`;
}

function smsModal() {
    formgetSMS.innerHTML = smsContent;
    const smsPost = document.getElementById("smsPost");
    smsPost.addEventListener('click', smsRegistClick);
}

function smsRegistClick() {
  const $emailInput = document.getElementById("key");
  const $passwordInput = document.getElementById("secret");
  const $extraValue = document.getElementById("extraValue");
  createChannel("sms", $emailInput.value, $passwordInput.value, $extraValue.value)
  .then(()=>console.log("create channel"));
}
