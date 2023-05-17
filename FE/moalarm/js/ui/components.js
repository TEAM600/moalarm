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

function createTextInputWithIdAndLabel(id, label) {
    return createInputWithIdAndLabelAndType(id, label, "text");
}

function createPasswordInputWithIdAndLabel(id, label) {
    return createInputWithIdAndLabelAndType(id, label, "password");
}

function createInputWithIdAndLabelAndType(id, label, type) {
    return `
        <div class="mb-3">
            <label for="recipient-name" class="col-form-label">${label}</label>
            <input type="${type}" class="form-control" id="${id}">
        </div>`;
}

function createInputWithIdAndLabelAndTypeAndPlaceHolder(id, label, type, placeholder) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <input type="${type}" class="form-control" id="${id}" placeholder="${placeholder}">
    </div>`;
}

function createTextAreaWithIdAndLabel(id, label) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <textarea rows="15" class="form-control" id="${id}"></textarea>
    </div>`;
}

function createTextAreaWithIdAndLabelAndValue(id, label, value) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <textarea rows="15" class="form-control" id="${id}" vaule="${value}"></textarea>
    </div>`;
}

function createTextInputWithIdAndLabelAndValue(id, label, value) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <input type="text" class="form-control" id="${id}" value="${value}">
    </div>`;
}

function createPasswordInputWithIdAndLabelAndValue(id, label, value) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <input type="text" class="form-control" id="${id}" value="${value}">
    </div>`;
}

function createInputWithIdAndLabelAndTypeAndPlaceHolderAndValue(id, label, type, placeholder,value) {
    return `
    <div class="mb-3">
        <label for="recipient-name" class="col-form-label">${label}</label>
        <input type="${type}" class="form-control" id="${id}" placeholder="${placeholder}" value="${value}">
    </div>`;
}

