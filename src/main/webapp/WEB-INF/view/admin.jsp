<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 10.11.2020
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/homeAdmin">Home</a>
    <a href="showProducts">Show products</a>
    <a href="orders">Show orders</a>
    <a href="users">Show users</a>
    <a href="ordersProcessing"> Orders processing</a>
    <a href="exit">Exit</a>
    <%--<p>Username: <%=request.getParameter("username")%> </p>
    <p>Password: <%=request.getParameter("password")%> </p>--%>
    <p>Username: ${sessionScope.username}</p>    <!-- same with username-->
    <p>Password: ${sessionScope.password}</p>
</body>
</html>
