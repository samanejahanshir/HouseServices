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
    <title>Title</title>
</head>
<body>
<c:if test="${role_user.equals('expert')}">
<form cssClass="p-1 my-5 mx-5"  method="post" action="/expert/saveNewPass">
    </c:if>
        <c:if test="${role_user.equals('customer')}">
        <form cssClass="p-1 my-5 mx-5"  method="post" action="/customer/saveNewPass">
            </c:if>
    <h2 style="text-justify: distribute-center-last">Register Customer</h2>
    <table class="table table-bordered table-striped table-primary text-dark">
    <tr>
    <td>
        Password:
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
</body>
</html>
