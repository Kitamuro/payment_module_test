const FORM = window['form'];
const BASE_URL = 'http://localhost:8080';
const REG_URL = BASE_URL + '/registration-api';
const VALIDATION_SUCCESS = 'Validation Successful';
const MESSAGE_SUCCESS = 'Регистрация прошла успешно';
let token = document.getElementsByTagName('meta')['_csrf'].getAttribute('content');