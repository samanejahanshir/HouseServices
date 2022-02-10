<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Expert Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body style="background-color: #c3e7f8">
<div class="w-100 " style="height: 20px">
    <p style="margin-left: 10px; margin-top: 5px;">${message}</p>
</div>
<c:set  var="email" value="${email}"/>
<div class="container col-12">
    <div class="card" style="width: 18rem;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/expert/addServiceToList">Add a subService to my list</a></li>
            <li class="list-group-item"><a href="/expert/changePass">Change password</a></li>
            <li class="list-group-item"><a href="/expert/viewInformation">view information</a></li>
            <li class="list-group-item"><a href="/offer/viewListOfferExpert">view List Offers</a></li>
            <li class="list-group-item"><a href="/expert/order/allOrdersExpert">view all orders</a></li>
            <li class="list-group-item"><a href="/expert/order/historyWorks">view history works</a></li>
            <li class="list-group-item"><a href="/expert/order/listWorks">List works</a></li>
            <li class="list-group-item"><a href="/expert/logout">Log Out</a></li>

        </ul>
    </div>

</div>
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2" >
    <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>
