<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/manager/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>

    <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
            onclick="history.back()" style="margin: 2vh 2vw">back
    </button>

<div class="container col-12">
    <h1>Users List</h1>

    <form:form cssClass="text-center" modelAttribute="conditionSearch" action="/manager/search" method="post">
        <table class="table table-striped table-primary text-dark table-hover">
            <tr>
                <td>
                   firstName: <br/><form:input path="firstName" name="firstName" placeHolder="first  name"/>
                </td>
                <td>
                   lastName: <br/><form:input path="lastName" name="lastName" placeHolder="last name"/>
                </td>
                <td>
                   email: <br/><form:input path="email" name="email" placeHolder="email"/>
                </td>
                <td>
                    Role:<br/>
                    <form:select path="role" cssClass="dropdown form-control"
                                 cssStyle="width: 12vw">
                        <option value="">--</option>
                        <option value="EXPERT">EXPERT</option>
                        <option value="CUSTOMER">CUSTOMER</option>
                    </form:select>                </td>
                <td>
                   minScore:<br/> <form:input path="minScore" name="minScore" placeHolder="min score"/>
                </td>
                <td>
                   maxScore:<br/> <form:input path="maxScore" name="maxScore" placeHolder="max score"/>
                </td>
            </tr>
            <tr>
                <td>
                    from:<br/><input type="date"   name="startDate" />
                </td>
                <td>
                   to:<br/><input type="date"  name="endDate" />
                </td>
                <td>
<%--
                    subService:<br/><form:input path="subServiceName" name="subServiceName" placeHolder="subServiceName"/>
--%>                SubService:
                    <form:select path="subServiceName" cssClass="dropdown form-control"
                                 cssStyle="width: 12vw">
                        <option value="">--</option>
                        <c:forEach items="${allSubService}" var="service">
                            <option value="${service.name}">${service.name}</option>
                        </c:forEach>
                    </form:select>

                </td>
                <td>
                    <br/>
                    count add order(customer):<form:radiobutton path="orderUser" value="customer"/>
                </td>
                <td>
                    <br/>
                    count done order(expert):<form:radiobutton path="orderUser" value="expert"/>

                </td>
                <td>
                    <br/>
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
                <th>Orders Detail</th>
            </tr>
            <c:forEach items="${listUserDto}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.registerDate}</td>
                    <td><a href="/manager/viewOrders/${user.id}">order detail</a></td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
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
