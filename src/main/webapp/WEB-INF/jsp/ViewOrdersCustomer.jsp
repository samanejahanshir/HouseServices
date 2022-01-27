<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:22 PM
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
<form action="/customer/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>
<div class="container col-12">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>Id</th><th>DoingDate</th><th>DoingTime</th><th>RegisterDate</th><th>Price</th><th>state</th><th>sub service</th></tr>
    <c:forEach var="order" items="${listOrdersDto}" >
        <tr>
            <td>${order.id}</td>
            <td>${order.orderDoingDate}</td>
            <td>${order.orderDoingTime}</td>
            <td>${order.orderRegisterDate}</td>
            <td>${order.proposedPrice}</td>
            <td>${order.state}</td>
            <td>${order.subServiceDto.name}</td>
<%--
            <c:if test="${order.state.equals('WAIT_OFFER_EXPERTS') || order.state.equals('WAIT_SELECT_EXPERT')}">
--%><c:if test="${order.state=='PAID'}">
            <td><a href="/commend/addCommend/${order.id}">add a commend</a></td>

        </c:if>
            <c:if test="${(order.state=='WAIT_OFFER_EXPERTS') || (order.state=='WAIT_SELECT_EXPERT')}">

            <td><a href="/offer/viewListOffers/${order.id}">view Offers</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</div>

</body>
</html>
