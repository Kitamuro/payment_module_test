
FORM.addEventListener("submit", async function (e) {
    e.preventDefault();
    e.stopPropagation();

    let formData = new FormData(FORM);
    let registrationData = Object.fromEntries(formData);

    let response = await fetch(REG_URL, {
        method: "post",
        headers: {"Content-Type": "application/json;charset=utf-8"},
        body: JSON.stringify(registrationData)
    });

    let responseMessage = await response.text();

    if (responseMessage === VALIDATION_SUCCESS) {
        validationSuccess()
    } else  {
        validationUnsuccess(responseMessage)
    }
});

FORM.addEventListener('click', function (e) {
    let input = e.target;

    if (input.nodeName === 'INPUT') {
        let label = input.nextElementSibling;
        inputFocus(input, label);
        input.addEventListener('blur', function () {
            checkValue(input, label)
        });
    }
});

function validationSuccess() {
    FORM.hidden = true;
    document.body.append(MESSAGE_SUCCESS);
}

function validationUnsuccess(responseMessage) {
    FORM.hidden = true;
    document.body.append(responseMessage);
}

function inputFocus(input, label) {
    input.style.background = 'white';
    label.style.top = '0';
    label.style.transition = '0.3s';
    input.style.boxShadow = 'inset 0 0 0 1px #616871';

}

function checkValue(input, label) {
    if (input.value !== ''|| null) {
        hasValue(input, label);
    } else {
        notValue(input, label)
    }
}

function hasValue(input, label) {
    label.style.top = '0';
    input.style.background = 'white';
    input.style.boxShadow = 'inset 0 0 0 1px #616871';
}

function notValue(input, label) {
    label.style.top = '10px';
    input.style.background = '#ecf1f7';
    input.style.boxShadow = null;
}