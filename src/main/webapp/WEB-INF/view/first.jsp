<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 09.11.2020
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    <a href="${home}">Home</a>
    <a href="showProducts">Show products</a>
    <a href="myOrders">My orders</a>
    <a href="orderProduct">Order product</a>
    <a href="exit">Exit</a>
    <%--<p>Username: <%=request.getParameter("username")%> </p>
    <p>Password: <%=request.getParameter("password")%> </p>--%>
    <p>Username: ${sessionScope.username}</p>    <!-- same with username-->
    <p>Password: ${sessionScope.password}</p>
    <%--  <p>HttpScope: ${sessionScope.username}</p>
    <p>ServletContext: ${applicationScope.username}</p>--%>
</body>
</html>
