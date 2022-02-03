<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body style="background-color: #c3e7f8">
<br>
<p>${message}</p>
<br>
<div class="container col-12">
    <c:if test="${role_user.equals('customer')}">
    <form method="post" action="<c:url value="/customer/doLogin"/>">
        </c:if>
        <c:if test="${role_user.equals('expert')}">
        <form method="post" action="<c:url value="/expert/doLogin"/>">
            </c:if>
            <c:if test="${role_user.equals('manager')}">
            <form method="post" action="<c:url value="/manager/doLogin"/>">
                </c:if>
                <h2>Sign in ${role_user}</h2>
                <table class="table table-bordered table-striped table-primary text-dark">
                    <tr>
                        <td>
                            <label>Email: :</label>
                        </td>
                        <td>
                            <input type="email" name="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label> Password:</label>
                        </td>
                        <td>
                            <input type="password" name="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="submit" value="Login">
                        </td>
                    </tr>

                </table>
            </form>
</div>
<footer>
<div class="border border-primary w-99 m-2" style="background-color: #adc4fc;height: 48px;"></div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>

</body>
</html>
