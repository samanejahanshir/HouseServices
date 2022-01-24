<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/24/2022
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Info</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<%--<p>${message}</p>--%>
<div class="container col-12">
<form:form modelAttribute="expertDto">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr>
            <th>First Name</th>
            <th>LastName</th>
            <th>Email</th>
            <th>RegisterDate</th>
            <th>Score</th>
            <th>SubService</th>
        </tr>
        <tr>
            <td>${expertDto.firstName}</td>
            <td>${expertDto.lastName}</td>
            <td>${expertDto.email}</td>
            <td>${expertDto.registerDate}</td>
            <td>${expertDto.score}</td>
                <c:forEach var="subservice" items="${expertDto.subServiceDto}">
                    <td>${subservice.name}</td>
                </c:forEach>
        </tr>

    </table>
</form:form>
</div>
</body>
</html>
