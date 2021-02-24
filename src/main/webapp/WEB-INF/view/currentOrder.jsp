<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 12.11.2020
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your order</title>
</head>
<body>
    <p>Product name: ${order.prod_name}</p>
    <p>Amount: ${order.amount}</p>
    <p>Order price: ${order.price}</p>
    <p>Date: ${order.date}</p>
    <p>Time: ${order.time}</p>
    <p>Status: ${order.status}</p>
    <a href="homeUser">home</a>
</body>
</html>
