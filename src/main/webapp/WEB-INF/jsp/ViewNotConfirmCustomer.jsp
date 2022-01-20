<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/20/2022
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>${message}</p>
<table border="2" width="70%" cellpadding="2" bgcolor="#f8f8ff">
    <tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Email</th><th>Role</th><th>RegisterDate</th></tr>
    <c:forEach var="user" items="${listCustomer}" >
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.registerDate}</td>
            <td><a href="/manager/confirmCustomer/${user.id}">Confirm</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
