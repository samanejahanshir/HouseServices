<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/21/2022
  Time: 7:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
////TODOOOOOOO delete jsp
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container m-1">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr><th>FirstName</th><th>SubServices</th></tr>
        <c:forEach var="service" items="${listMainService}" >
            <tr>
                <td>${service.groupName}</td>
                <td><a href="/manager/viewListSubServices/${service.groupName}">view SubServices</a></td>
                <c:if test="${role_user.equals('customer')}">
                    <td><a></a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
