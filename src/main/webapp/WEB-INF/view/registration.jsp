<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 10.11.2020
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="signUp" method="POST">
    <p>Login:    <input name="username"/></p>
    <p>Password: <input type="password" name="password1"/></p>
    <p>Password: <input type="password" name="password2"/></p>
    <p style="color: ${color}">${message}</p>
    <span><input type="submit" value="sign up"></span>
    <a href="${back}">back</a>
</form>
</body>
</html>
