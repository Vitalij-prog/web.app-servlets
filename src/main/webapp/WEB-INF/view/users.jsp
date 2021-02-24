<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 12.11.2020
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/homeAdmin">back</a>
<table border="1">
    <thead>
    <tr>
        <th>user id</th>
        <th>user name</th>
        <th>user role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.user_name}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
