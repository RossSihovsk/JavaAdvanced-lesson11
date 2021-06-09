<%--
  Created by IntelliJ IDEA.
  User: Ross
  Date: 6/2/2021
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Реєстрація</title>
</head>

<body>
<h1>Реєстрація</h1>
<form action="registering" method="post">
    <input name="firstName" type="text" placeholder="Імя"><br><br>
    <input name="lastName" type="text" placeholder="Прізвище"><br><br>
    <input name="email" type="text" placeholder="Логін"><br><br>
    <input name="password" type="password" placeholder="Пароль"><br><br>
    <input name="accessLevel" type="radio" id="user" value="user" checked>
    <label for="user">Користувач</label><br>
    <input name="accessLevel" type="radio" id="admin" value="admin">
    <label for="admin">Адміністратор</label><br><br>
    <input type="submit" value="Відправити">
</form>
</body>
</html>
