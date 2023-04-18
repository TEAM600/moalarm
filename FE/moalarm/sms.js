const formgetSMS = document.getElementById("formget");
const smsButton = document.getElementById("SMSButton");

const smsContent = `
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
            <div id="SMSPost" class="btn btn-primary">Regist</button>
          </div>
        </div>
        `;

smsButton.addEventListener('click', smsModal);

function smsModal() {
    formgetSMS.innerHTML = smsContent;
    const smsPost = document.getElementById("SMSPost");
    smsPost.addEventListener('click', smsRegistClick);
}

function smsRegistClick() {
    console.log("sms 정보를 등록합니다.");
}
