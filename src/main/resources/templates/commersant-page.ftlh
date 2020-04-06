<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../static/css/commersant-page-style.css">
    <title>Document</title>
</head>

<body>
    <footer class="footer">
        <div class="row flex-row space-between">
            <div class="logo">
                <img src="../static/merchant_page/design/logo.png" width="220" height="70" alt="">
            </div>
            <div class="menu">
                <div> <a href="">Главная</a></div>
                <div> <a href="">Операции</a></div>
                <div> <a href="">Счета</a></div>
                <div> <a href="">Возмещения</a></div>
                <div> <a href="">Магазины</a></div>
                <div> <a href="">Помощь</a></div>
            </div>
            <div class="icon fa fa-bars w3-large">   </div>
        </div>
    </footer>

    <div class="container">
        <div class="search-title">
            <div class="flex-row space-around">
                <a href="">Поиск</a>
                <a href="">Шаблоны</a>
                <a href="">Выгрузки</a>
            </div>
        </div>
        <div class="result-title">
            <div class="flex flex-wrap" id="itemList">
                <#list transactions as listTransaction>
                    <div class="flex flex-column box flex-v-center">
                        <a href="/transactions/${listTransaction.id}">
                            <div class="flex flex-column flex-v-center box-128">
                                ${listTransaction.name}
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</body>

</html>