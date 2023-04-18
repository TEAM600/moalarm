const formgetPost = document.getElementById("formget");
const PushButton = document.getElementById("PushButton");

const pushContent = `
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title" id="SMSModalLabel">SMS</h5>
    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
  </div>
  <div class="modal-body">
    <form>
      <div class="mb-3">
        <label for="recipient-name" class="col-form-label">API-Key:</label>
        <input type="text" class="form-control" id="api-Key">
      </div>
      <div class="mb-3">
        <label for="recipient-name" class="col-form-label">API_SECRET:</label>
        <input type="password" class="form-control" id="api_secret">
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    <div id="pushPost" class="btn btn-primary">Regist</button>
  </div>
</div>
        `;

PushButton.addEventListener('click', pushModal);

function pushModal() {
    formgetPost.innerHTML = pushContent;
    const pushPost = document.getElementById("pushPost");
    pushPost.addEventListener('click', pushRegistClick);
}

function pushRegistClick() {
    console.log("푸쉬 정보를 등록합니다.");
}