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

    <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
            onclick="window.history.back()" style="margin: 2vh 2vw">back
    </button>

<div class="container col-12">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>Id</th>
        <th>DoingDate</th>
        <th>DoingTime</th>
        <th>RegisterDate</th>
        <th>Price</th>
        <th>state</th>
        <th>sub service</th>
    </tr>
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
--%><c:if test="${order.state=='PAID' && order.commendDto==null}">
            <td><a href="/commend/addCommend/${order.id}">add a commend</a></td>

        </c:if>
            <c:if test="${order.state=='WAIT_SELECT_EXPERT'}">

            <td><a href="/offer/viewListOffers/${order.id}">view Offers</a></td>
            </c:if>
            <c:if test="${(order.state=='DONE')}">
                <td><a href="/order/payOnline/${order.id}">pay online</a></td>
                <td><a href="/order/payByCredit/${order.id}">pay by credit</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</div>
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2" >
    <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
</body>
</html>
