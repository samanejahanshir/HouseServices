<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<div class="container col-12">
    <br>
    <p>${message}</p>
    <br>
    <div class="card" style="width: 18rem;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><a href="/manager/listUsers">List Users</a></li>
            <li class="list-group-item"><a href="/manager/addMainService">Add New MainService</a></li>
            <li class="list-group-item"><a href="/manager/addSubService">Add New SubService</a></li>
            <li class="list-group-item"><a href="/manager/viewListMainServices">View List MainServices</a></li>
            <li class="list-group-item"><a href="/manager/viewListNotConfirmUser">View List NotConfirm Users</a></li>
            <li class="list-group-item"><a href="/manager/viewListAllOrders">View List Orders</a></li>
            <li class="list-group-item"><a href="/manager/changePass">change password</a></li>
            <li class="list-group-item"><a href="/manager/logout">Log Out</a></li>

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
