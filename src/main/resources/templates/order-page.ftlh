<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Оформление заказа</title>
    <link rel="stylesheet" href="css/order-page-style.css">
</head>
<body>

<header class="header">
    <div class="shop-name">
        Example Shop
    </div>
    <div class="title-text">
        Страница для оформления заказа
    </div>
</header>


<div class="form">

    <form action="/" class="order-form">
        <input type="hidden" name="shopName" value="Example Shop">
        <div>
            <div> ФИО</div>
            <input name="userName" value="test" type="text" required>
        </div>
        <div>
            <div> Адрес электронной почты</div>
            <input name="email"  value="test@test.com" type="email" required>
        </div>
        <div>
            <div> Контактный телефон</div>
            <input name="phone" type="tel" minlength="5"  value="7-777-777-77-77" placeholder="7-777-777-77-77" maxlength="24"
                   pattern="[[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}" required>
        </div>
        <div>
            <div> Номер заказа </div>
            <input name="order_id" value="1000" type="number" required>
        </div>
        <div>
            <div> Сумма</div>
            <input name="amount" value="10000" type="number" required>
        </div>

        <button id="order-button" class="button-submit" type="submit">
            Оформить заказ
        </button>
    </form>


    <template id="purchase-form-template">
        <section class="right">
            <h1>Страница оплаты</h1>


            <#if order??>
                <div class="data">
                    <div class="order-text"> <p> Магазин: ${order.shopName} </p></div>
                    <div class="order-text"> <p> Покупатель: ${order.userName}  </p></div>
                    <div class="order-text"> <p> Email: ${order.email}  </p></div>
                    <div class="order-text"> <p> Телефон: ${order.phone}  </p></div>
                    <div class="order-text"> <p> Заказ: ${order.order_id}  </p></div>
                    <div class="order-text"> <p> Сумма :${order.amount}  </p></div>
                </div>
            </#if>
            <form action="#" id="purchase-form">
                <#if order??>
                    <input type="hidden" name="shopName" value=${order.shopName}>
                    <input type="hidden" name="userName" value=${order.userName}>
                    <input type="hidden" name="email" value=${order.email}>
                    <input type="hidden" name="phone" value=${order.phone}>
                    <input type="hidden" name="amount" value=${order.amount}>
                </#if>
                <div class="form-name form-field">
                    <label for="cc-number">Имя держателя карты</label>
                    <input name="cardHolderName" class="cc-name" type="text" placeholder="John John" required>
                </div>
                <div class="form-card form-field">
                    <label for="cc-number">Номер карты</label>
                    <input name="CARD" class="cc-number" value="1111111111111111" type="text" placeholder="1111 2222 3333 4444"
                           required minlength="15"
                           maxlength="16" size="16">
                </div>

                <div class="form-date form-field">
                    <label for="expiry-month">Срок действия</label>
                    <div class="date-val">
                        <select name="EXP" class="expiry-month" required>
                            <option class="trans-label_month" value="" default="default" selected="selected">Месяц</option>
                            <option value="1">01</option>
                            <option value="2">02</option>
                            <option value="3">03</option>
                            <option value="4">04</option>
                            <option value="5">05</option>
                            <option value="6">06</option>
                            <option value="7">07</option>
                            <option value="8">08</option>
                            <option value="9">09</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <select name="EXP_YEAR" class="expiry-year" required>
                            <option class="trans-label_year" value="" default="" selected="selected">Год</option>
                            <option value="2018">18</option>
                            <option value="2019">19</option>
                            <option value="2020">20</option>
                            <option value="2021">21</option>
                            <option value="2022">22</option>
                            <option value="2023">23</option>
                            <option value="2024">24</option>
                            <option value="2025">25</option>
                            <option value="2026">26</option>
                            <option value="2027">27</option>
                            <option value="2028">28</option>
                            <option value="2029">29</option>
                            <option value="2030">30</option>
                            <option value="2031">31</option>
                            <option value="2032">32</option>
                            <option value="2033">33</option>
                            <option value="2034">34</option>
                            <option value="2035">35</option>
                            <option value="2036">36</option>
                            <option value="2037">37</option>
                            <option value="2038">38</option>
                            <option value="2039">39</option>
                            <option value="2040">40</option>
                            <option value="2041">41</option>
                            <option value="2042">42</option>
                            <option value="2043">43</option>
                            <option value="2044">44</option>
                            <option value="2045">45</option>
                            <option value="2046">46</option>
                            <option value="2047">47</option>
                        </select>
                    </div>
                </div>

                <div class="form-sec-code form-field">
                    <label for="sec-code">CVC код</label>
                    <input name="CVC2" type="password" inputmode="numeric" minlength="2" maxlength="3" size="3"
                           placeholder="123" required>
                </div>

                <button type="submit" id="purchase-button"> Оплатить </button>

            </form>
        </section>
    </template>

</div>

<script>
    let orderButton = document.getElementById("order-button");
    orderButton.addEventListener("click", function (e) {
        e.preventDefault();
        console.log("sdasdsaasd");
        let template = document.getElementById("purchase-form-template").content;

        const formClone = template.cloneNode(true);

        let placeForPurchase = document.getElementsByTagName("body")[0].children[1];
        placeForPurchase.prepend(formClone);

        let inputOrder = document.getElementsByClassName("order-form")[0];
        inputOrder.hidden = true;

        let form = document.getElementById("purchase-form");
        let url = "http://localhost:8080/pay";

        form.addEventListener("submit", async function (e) {
            e.preventDefault();
            e.stopPropagation();

            let formData = new FormData(form);
            let transaction = Object.fromEntries(formData);
            console.log(transaction);
            let response = await fetch(url, {
                method: "post",
                headers: {"Content-Type": "application/json;charset=utf-8"},
                body: JSON.stringify(transaction)
            });
            console.log(response)
    })})
</script>

</body>
</html>