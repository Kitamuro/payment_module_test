const ListOrders = window['orders'];
const infoBlock = window['info-block'];
const searchBlock = window['search-block'];
let transactionBlock = window['transactions'];
let orderDTO;


const BASE_URL = 'http://localhost:8080/';
const SHOP_URL = `${BASE_URL}aboutShop`;
const ORDER_URL = `${BASE_URL}/orders/`;

const FORM = window['form'];

const REG_URL = BASE_URL + '/registration-api';
const VALIDATION_SUCCESS = 'Validation Successful';
const MESSAGE_SUCCESS = 'Регистрация прошла успешно';
// let token = document.getElementsByTagName('meta')['_csrf'].getAttribute('content');

