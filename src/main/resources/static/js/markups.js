function addFormRefund(orderDTO) {
    const html = `
                    <div >
                    <form action="/sendRequest" method="post">
                    <input type="hidden" value="${orderDTO.id}" name="orderId">
                    <input type="hidden" value="REFUND" name="type">
                    <div class = "flex-row">
                    <input class="input-refund" type="number"  placeholder="введите сумму" name="amount" oninput="validateSum(value)" >
                    <button class = "input-refund returnButton" type="submit">вернуть</button>
                    </div>
                    </form>
                    </div>`;
    const div = document.createElement('div');
    div.innerHTML = html;
    return div;
}

function addFormAuth(orderDTO) {
    const html = `
                    <div>
                    <form action="/sendRequest" method="post">
                    <input type="hidden" value="${orderDTO.id}" name="orderId">
                    <input type="hidden" value="AUTH" name="type">
                    <input type="number"  placeholder="введите сумму" name="amount" oninput="validateSum(value)" >
                    <button id="returnButton" type="submit">подтвердить</button>
                    </form>
                    </div>`;
    const div = document.createElement('div');
    div.innerHTML = html;
    return div;
}

function print(orderDTO) {
    block.innerHTML = `<div id="close-icon" class="close"> x </div>
                           <div class="id"> ${orderDTO.id} </div>
                           <div> Остаток : ${orderDTO.residual} </div>
                           <div class="status">${orderDTO.status} </div>
                           <div class="amount">${orderDTO.amount}  TГ </div>
                           <div class="title"> Платежная информация </div>
                           <div class="row"> <div>Дата оплаты</div>  <div> ${dateFormat(orderDTO.date)} </div> </div>
                           <div class="row"> <div>Магазин</div>  <div> ${orderDTO.shopName} </div> </div>
                           <div class="order-title">Способ оплаты</div>
                           <div class="row"> <div>Карта</div>  <div> ${orderDTO.card} </div> </div>
                           <div class="row"> <div>Покупатель</div>  <div>${orderDTO.cardHolderName}</div></div>
                           <div class="order-title">Информация о покупке</div>
                           <div class="row"> <div>Телефон</div>  <div>${orderDTO.phone} </div> </div>
                           <div class="row"> <div>Почта</div>  <div> ${orderDTO.email} </div> </div>
                           <div>  История платежей </div>
                           <div>
                               ${orderDTO.transactions.map(transaction => `<div> ${dateFormat(transaction.date)} <span> ${transaction.amount} тг </span> ${transaction.type}  </div>`).join('')}
                           </div>`.trim();
    const amount = document.getElementsByClassName('amount')[0];
    if (orderDTO.status === 'APPROVED' || orderDTO.status === 'PARTIAL_REFUND') {
        const formDiv = addFormRefund(orderDTO);
        amount.after(formDiv)
    }
    if(orderDTO.status === 'RESERVED' ) {
        const formDivAuth = addFormAuth(orderDTO);
        amount.after(formDivAuth);
    }

    let closeIcon = window['close-icon'];
    closeIcon.addEventListener('click', function () {
        block.hidden = true;
        searchElement.hidden = false;
    })
}