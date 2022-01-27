<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>SubService</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/manager/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>
<div class="container col-12">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>Id</th><th>Name</th><th>Price</th><th>Description</th></tr>
    <c:forEach var="service" items="${listSubServices}" >
        <tr>
            <td>${service.id}</td>
            <td>${service.name}</td>
            <td>${service.basePrice}</td>
            <td>${service.description}</td>
            <c:if test="${role_user.equals('customer')}">
                <td><a href="/order/addNewOrder/${service.name}">New Order</a> </td>
            </c:if>
            <c:if test="${role_user.equals('expert')}">
                <td><a href="/expert/addSubServiceToList/${service.name}">add to my list subservice</a> </td>
            </c:if>
            <c:if test="${role_user.equals('manager')}">
                <td><a href="/manager/addExpertToServices/${service.name}">add expert to subservice</a> </td>
            </c:if>

        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
