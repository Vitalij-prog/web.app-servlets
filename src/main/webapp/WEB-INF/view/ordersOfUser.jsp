<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 11.11.2020
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My orders</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/homeUser">back</a>
<table border="1">
    <thead>
    <tr>
        <th>order id</th>
        <th>product name</th>
        <th>product amount</th>
        <th>order price</th>
        <th>order date</th>
        <th>order time</th>
        <th>order status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.prod_name}</td>
            <td>${order.amount}</td>
            <td>${order.price}</td>
            <td>${order.date}</td>
            <td>${order.time}</td>
            <td>${order.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
