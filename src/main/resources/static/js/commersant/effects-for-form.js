
const FORM = window['form'];
//
// FORM.addEventListener("submit", async function (e) {
//     e.preventDefault();
//     e.stopPropagation();
//
//     let formData = new FormData(FORM);
//     let registrationData = Object.fromEntries(formData);
//
//     let response = await fetch(REG_URL, {
//         method: "post",
//         headers: {"Content-Type": "application/json;charset=utf-8",
//             'X-CSRF-token': token, cookie: 'crumb=' + token},
//         body: JSON.stringify(registrationData)
//     });
//
//     let responseMessage = await response.text();
//
//     if (responseMessage === VALIDATION_SUCCESS) {
//         validationSuccess()
//     } else  {
//         validationUnsuccess(responseMessage)
//     }
// });

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
    label.style.top = '4px';
    label.style.transition = '0.2s';
    label.style.fontSize = '13px';
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
    label.style.top = '4px';
    input.style.background = 'white';
    label.style.fontSize = '13px';
    input.style.boxShadow = 'inset 0 0 0 1px #616871';
}

function notValue(input, label) {
    label.style.top = '12px';
    input.style.background = '#ecf1f7';
    label.style.fontSize = '15px';
    input.style.boxShadow = null;

}