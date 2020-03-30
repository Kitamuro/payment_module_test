<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/order-page-style.css">
    <title>Status page</title>
</head>
<body>
<h1 class="header">Status page</h1>
<div style = "padding-left: 20%">
<#if rcCode??>
<p>Sorry, due to technical fault your request cannot be processed</p>
<#if error??>
<p>${error}</p>
</#if>
<p id = "rcCode"> Response code: ${rcCode}</p>
<p>Please try once again or visit us later or choose another means of payment.</p>
</#if>
</div>
</body>
</html>