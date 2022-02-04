<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/20/2022
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Offers</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<div class="w-100 " style="height: 20px">
    <p style="margin-left: 10px; margin-top: 5px;">${message}</p>
</div>
<form action="/customer/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>

<button type="submit" id="back" name="back" class="btn btn-primary btn-group"
        onclick="history.back()" style="margin: 2vh 2vw">back
</button>

<div class="container col-12">
    <h1>Users List</h1>

    <table class="table table-striped table-primary text-dark table-hover">
        <tr>
            <th>Id</th>
            <th>createDate</th>
            <th>price</th>
            <th>Duration time</th>
            <th>Start time</th>
            <th>state</th>
            <th>subservice</th>
            <th>customer</th>

        </tr>

        <c:forEach var="offer" items="${offers}">
            <tr>
                <td>${offer.id}</td>
                <td>${offer.offerCreateDate}</td>
                <td>${offer.offerPrice}</td>
                <td>${offer.durationTime}</td>
                <td>${offer.startTime}</td>
                <td>${offer.state}</td>
                <td>${offer.orderDto.subServiceDto.name}</td>
                <td>${offer.orderDto.customerDto.firstName} ${offer.orderDto.customerDto.lastName} </td>
                <c:if test="${offer.state!= 'ACCEPT'}">
                    <td><a href="/offer/delete/${offer.id}">delete</a></td>
                </c:if>
                <c:if test="${offer.state == 'NEW'}">
                    <td><a href="/offer/edit/${offer.id}">edit</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2">
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