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

    <form action="http://localhost:8080/paymentPage" method="post">
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

        <button class="button-submit">
            Оформить заказ
        </button>
    </form>

</div>

</body>
</html>