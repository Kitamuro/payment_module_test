
ListOrders.addEventListener('click', async function (e) {
    let markupTransaction = e.target.parentElement;
    infoBlock.hidden = false;
    searchBlock.hidden = true;
    let orderId = markupTransaction.getElementsByClassName('order-id')[0].innerText;
    let responseData = await fetch(`${ORDER_URL}/${orderId}`);
    let promise = await responseData.json();
    orderDTO = JSON.parse(JSON.stringify(promise));

    printOrder(orderDTO);

    let classList = ListOrders.children;
    for (let i = 0; i < classList.length; i++) {
        classList[i].classList.remove('active')
    }
    markupTransaction.classList.add("active");
});