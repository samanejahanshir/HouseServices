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
    <title>Customer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<p>${message}</p>
<div class="container m-1">
<table class="table table-bordered table-striped table-primary text-dark">
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
</div>
</body>
</html>
