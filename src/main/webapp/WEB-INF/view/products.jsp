<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 11.11.2020
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <a href="${home}">back</a>
    <table border="1">
        <thead>
            <tr>
                <th>id</th>
                <th>product name</th>
                <th>price</th>
                <th>amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.amount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
