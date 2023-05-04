function createModalContent(label ,doms) {
    const modalTop = 
            `<div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${label}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form> `;

    let modalMid = ``;
    for (let dom of doms) {
        modalMid += dom;
    }

    const modalBot =
            `</form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <div id="regist-btn" class="btn btn-primary">Regist</button>
                </div>
            </div>`;
    return modalTop + modalMid + modalBot;
}

function createInputWithIdAndLabel(id, label) {
    return `
      <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <input type="text" class="form-control" id="${id}">
      </div>`;
  }