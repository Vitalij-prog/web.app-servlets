<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 12.11.2020
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order Product</title>
</head>
<body>
<form action="orderProduct" method="POST">
    <p>Product name: </p>
    <select name="product_name">
        <c:forEach var="product" items="${products}">
            <option>${product.name}</option>
        </c:forEach>
    </select>
    <p>Amount: </p>
    <p><input type="number" min="1" max="100" value="1" name="amount"/></p>

    <span><input type="submit"  value="order"></span>
    <a href="homeUser">back</a>
</form>
</body>
</html>
