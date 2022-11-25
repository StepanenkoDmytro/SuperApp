<!DOCTYPE html>
<html lang="en">
<head>
    <title>OurApp</title>
</head>
<body>
<h1>Hello, brother.</h1><hr>
<h4>Products from NASDAQ</h4>
<h5>Here will be listProducts</h5>
<#--<#list stocks as stock>-->
<#--    <div>-->
<#--        <p><b>${stock.stock}</b> ${stock.price} | <a href="/product/${stock.id}">Детальніше...</a></p>-->
<#--    </div>-->
<#--    <#else>-->
<#--    <h3>Акції відсутні</h3>-->
<#--</#list>-->
<hr>
<h3>Додати нову акцію</h3>
<form action="/product/create" method="post">
    Назва компанії: <input type="text" name="stock"/><br><br>
    Опис компанії: <input type="text" name="stock_desc"/><br><br>
    Ціна компанії: <input type="number" name="price"/><br><br>
    Біржа: <input type="text" name="stock_exchange"/><br><br>
    <input type="submit" value="Додати акцію"/>
</form>
</body>
</html>