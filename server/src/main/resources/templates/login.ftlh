<!DOCTYPE html>
<html>
<head>
    <title>OurApp</title>
</head>
<body>
<h1>Авторизація</h1>
<form action="/login" method="post">
    Email: <input type="email" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="OK">
</form>
<a href="/registration">Зареєструватися</a>
</body>
</html>