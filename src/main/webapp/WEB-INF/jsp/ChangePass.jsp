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
    <title>Change pass</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
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
        <div class="container col-12">
            <c:if test="${role_user.equals('expert')}">
            <form cssClass="p-1 my-5 mx-5" method="post" action="/expert/saveNewPass">
                </c:if>
                <c:if test="${role_user.equals('customer')}">
                <form cssClass="p-1 my-5 mx-5" method="post" action="/customer/saveNewPass">
                    </c:if>
                    <c:if test="${role_user.equals('manager')}">
                    <form cssClass="p-1 my-5 mx-5" method="post" action="/manager/saveNewPass">
                        </c:if>
                        <h2 style="text-justify: distribute-center-last">Register Customer</h2>
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
                                   New Password:
                                </td>
                                <td>
                                    <input type="password" name="password">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="submit" value="change password">
                                </td>
                            </tr>
                        </table>
                    </form>
        </div>
</body>
</html>
