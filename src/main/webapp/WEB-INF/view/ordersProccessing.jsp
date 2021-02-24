<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 13.11.2020
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders Processing</title>
</head>
<body>
<form action="ordersProcessing" method="POST">
    <p>Order id: </p>
    <select name="order_id">
        <c:forEach var="order" items="${orders}">
            <option>${order.id}</option>
        </c:forEach>
    </select>
    <p>Option: </p>
    <select name="option_type">
        <c:forEach var="option" items="${options}">
            <option>${option}</option>
        </c:forEach>
    </select>

    <span><input type="submit"  value="ok"></span>
    <a href="homeAdmin">back</a>
    <p style="color: ${color}">${message}</p>
</form>
</body>
</html>
