<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 10.11.2020
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
      <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            <p>Login:    <input name="username"/></p>
            <p>Password: <input type="password" name="password"/></p>
            <p style="color: red">${message}</p>
            <span><input type="submit" value="login"></span>
            <a href="${back}">back</a>
        </form>
  </body>
</html>
