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
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body style="background-color: #c3e7f8">
<c:set  var="email" value="${email}"/>
<div class="container p-1">
    <div class="card" style="width: 18rem;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/customer/allOrders">View All Orders</a></li>
            <li class="list-group-item"><a href="/customer/newOrders">View New Orders</a></li>
<%--
            <li class="list-group-item"><a href="/customer/addNewOrder/${email}">Register New Order</a></li>
--%>
            <li class="list-group-item"><a href="/customer/viewListServices">View List SubServices</a></li>
            <li class="list-group-item"><a href="/customer/incrementCredit">Increment Credit</a></li>
            <li class="list-group-item"><a href="/customer/changePass">Change Password</a></li>
            <li class="list-group-item"><a href="">Log Out</a></li>

        </ul>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>