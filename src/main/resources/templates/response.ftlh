<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/order-page-style.css">
    <title>Response page</title>
</head>
<body>
<h1 class="header">Status page</h1>
<div style = "padding-left: 20%">
    <#if rcCode??>
        <p>Transaction approved</p>
        <p id = "rcCode"> Response code: ${rcCode}</p>
        <p>Transaction reference number: ${REF}</p>
        <p>Internal Transaction number: ${ITR}</p>
        <p>Bank's approval code: ${apprCode}</p>
    </#if>
</div>


</body>
</html>