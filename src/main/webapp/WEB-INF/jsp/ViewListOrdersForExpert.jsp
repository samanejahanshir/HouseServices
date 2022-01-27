<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/23/2022
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/expert/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>
<div class="container col-12">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr>
            <th>Id</th>
            <th>DoingDate</th>
            <th>DoingTime</th>
            <th>RegisterDate</th>
            <th>Price</th>
            <th>state</th>
            <th>sub service</th>
            <th>customer</th>
        </tr>
        <c:forEach var="order" items="${listOrder}">
            <tr>
                <td>${order.id}</td>
                <td>${order.orderDoingDate}</td>
                <td>${order.orderDoingTime}</td>
                <td>${order.orderRegisterDate}</td>
                <td>${order.proposedPrice}</td>
                <td>${order.state}</td>
                <td>${order.subServiceDto.name}</td>
                <td>${order.customerDto.firstName} ${order.customerDto.lastName}</td>
                <c:if test="${typeList=='allOrders'}">
                    <td><a href="/expert/addOffer/${order.id}">add offer</a></td>
                </c:if>
                <c:if test="${typeList=='workList'}">
                    <td><a href="/order/select/${order.id}">select</a></td>
                </c:if>
                <c:if test="${typeList=='historyList'}">
                    <td><a href="/order/showScore/${order.id}">show score</a></td>
                    <td><p>${score}</p></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
