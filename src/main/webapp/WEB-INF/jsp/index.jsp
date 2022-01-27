<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body style="background-color: #c3e7f8">
<br>
<p>${message}</p>
<br>
<div class="container align-items-center border border-primary " style="background-color: #adc4fc">
    <ul class="nav nav-pills align-items-center ">
        <li class="nav-item">
            <a href="#">Home</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">Sign
                Up</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<c:url value="/expert/Signup"/>">Sign Up expert</a>
                <a class="dropdown-item" href="<c:url value="/customer/Signup"/>">Sign Up customer</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">Sign
                in</a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<c:url value="/expert/Signin"/>">Sign in expert</a>
                <a class="dropdown-item" href="<c:url value="/customer/Signin"/>">Sign in customer</a>
                <a class="dropdown-item" href="<c:url value="/manager/Signin"/>">Sign in manager</a>

            </div>
        </li>
    </ul>
</div>

<div class="container" style="margin-top: 30px">
    <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/R.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src=images/RE.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
                <img src="images/th.jpg" class="d-block w-100">
            </div>
        </div>
    </div>
</div>
<script src=" https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
                        crossorigin="anonymous"></script>

</body>
</html>
