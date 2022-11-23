<!DOCTYPE html>
<html>
<head>
    <title>OurApp</title>
</head>
<body>
<h1>Список акції</h1>
<form action="/login" method="post">
    User name: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Ok">
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

<#--покишо зробив, аби було, прийти переробить!-->
</body>
</html>