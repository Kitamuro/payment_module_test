
ListOrders.addEventListener('click', async function (e) {
    let markupTransaction = e.target.parentElement;
    infoBlock.hidden = false;
    searchBlock.hidden = true;
    let orderElem = markupTransaction.getElementsByClassName('order-obj')[0].innerHTML;
    let order = JSON.parse(orderElem);
    let responseData = await fetch(`${ORDER_URL}/${order.id}`);
    let promise = await responseData.json();
    orderDTO = JSON.parse(JSON.stringify(promise));

    print(orderDTO);

    let classList = ListOrders.children;
    for (let i = 0; i < classList.length; i++) {
        classList[i].classList.remove('active')
    }
    markupTransaction.classList.add("active");
});


