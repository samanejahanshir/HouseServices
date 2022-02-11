<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Change password</title>
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
            <c:if test="${role_user.equals('expert')}">
            <form action="/expert/home">
                </c:if>
                <c:if test="${role_user.equals('customer')}">
                <form action="/customer/home">
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
    <c:if test="${role_user.equals('expert')}">
    <form cssClass="p-1 my-5 mx-5" method="post" action="/expert/sendEmail">
        </c:if>
        <c:if test="${role_user.equals('customer')}">
        <form cssClass="p-1 my-5 mx-5" method="post" action="/customer/sendEmail">
            </c:if>
            <c:if test="${role_user.equals('manager')}">
            <form cssClass="p-1 my-5 mx-5" method="post" action="/manager/sendEmail">
                </c:if>
                <h2 style="text-justify: distribute-center-last">Change Password</h2>
                <table class="table table-bordered table-striped table-primary text-dark">
                    <tr>
                        <td>
                            Email:
                        </td>
                        <td>
                            <input type="email" name="email">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="submit" value="send code to email for change password" class="btn btn-primary btn-group"
                                  >
                        </td>
                    </tr>
                </table>
            </form>
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
