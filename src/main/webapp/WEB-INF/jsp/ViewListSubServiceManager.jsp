<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>SubService</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<div class="w-100 " style="height: 20px">
    <p style="margin-left: 10px; margin-top: 5px;">${message}</p>
</div>
<div class="container row">
    <div class="col-2">
        <c:if test="${role_user.equals('manager')}">
        <form action="/manager/home">
            </c:if>
            <c:if test="${role_user.equals('customer')}">
            <form action="/customer/home">
                </c:if>
                <c:if test="${role_user.equals('expert')}">
                <form action="/expert/home">
                    </c:if>
                    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
                            style="margin: 2vh 2vw">Dashboard
                    </button>
                </form>
    </div>
    <div class="col-1">
        <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
                onclick="history.back()" style="margin: 2vh 2vw">back
        </button>
    </div>
</div>
<div class="container col-12">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
        </tr>
        <c:forEach var="service" items="${listSubServices}">
            <tr>
                <td>${service.id}</td>
                <td>${service.name}</td>
                <td>${service.basePrice}</td>
                <td>${service.description}</td>
                <c:if test="${role_user.equals('customer')}">
                    <td><a href="/order/addNewOrder/${service.name}">New Order</a></td>
                </c:if>
                <c:if test="${role_user.equals('expert')}">
                    <td><a href="/expert/addSubServiceToList/${service.name}">add to my list subservice</a></td>
                </c:if>
                <c:if test="${role_user.equals('manager')}">
                    <td><a href="/manager/addExpertToServices/${service.name}">add expert to subservice</a></td>
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
