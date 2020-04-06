<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/order-page-style.css">
    <title>3D страница</title>
</head>
<body>
<h1 class="header">3D Secure Page</h1>
<div class="form">
    <#if url??>
<form action="${url}" method="post" class="order-form">
    <div>
     <h4>PaReg</h4>
    <input id = "PaReg" type="text" name="PaReg" value="" placeholder="a message passed to the Issuing Bank" required>
    </div>
    <div>
    <h4>TermUrl</h4>
    <input id = "TermUrl" type="text" name="TermUrl" value="" placeholder="URL configured to receive the consumer returning from
    completing authentication" required>
    </div>
    <div>
    <h4>MD</h4>
    <input id = "MD" type="text" name="MD" value="" placeholder="secret key between the Issuer, acquirer and the merchant" required>
    </div>
    <button id="3D-button" class="button-submit" type="submit">Отправить</button>
</form>
</#if>
</div>
</body>
</html>