<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/manager/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>
<div class="container col-12">
<h1>Users List</h1>

    <form:form cssClass="text-center"  modelAttribute="conditionSearch" action="/manager/search"  method="post">
        <table class="table table-striped table-primary text-dark table-hover">
            <tr>
                <td>
                    <form:input path="firstName" name="lastName" placeHolder="first  name"/>
                </td>
                <td>
                    <form:input path="lastName" name="firstName" placeHolder="last name"/>
                </td>
                <td>
                    <form:input path="email" name="email" placeHolder="email"/>
                </td>
                <td>
                    <form:input path="role" name="role" placeHolder="role"/>
                </td>
                <td>
                    <form:input path="maxScore" name="maxScore" placeHolder="max score"/>
                </td>
                <td>
                    <form:input path="minScore" name="minScore" placeHolder="min score"/>
                </td>
                <td>
                    <form:input path="subServiceName" name="subServiceName" placeHolder="subServiceName"/>
                </td>
                <td>
                    <form:button name="search">Search</form:button>
                </td>

            </tr>
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Register Date</th>

            </tr>
            <c:forEach items="${listUserDto}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.registerDate}</td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>

</body>
</html>
