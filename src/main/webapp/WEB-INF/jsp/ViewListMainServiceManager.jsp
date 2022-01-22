<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>MainService</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<div class="container m-1">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>FirstName</th><th>SubServices</th></tr>
    <c:forEach var="service" items="${listMainServices}" >
        <tr>
            <td>${service.groupName}</td>
            <td><c:if test="${role_user.equals('manager')}">
                <a href="/manager/viewListSubServices/${service.groupName}">view SubServices</a>
            </c:if>
                <c:if test="${role_user.equals('customer')}">
                    <a href="/customer/viewListSubServices/${service.groupName}">view SubServices</a>
                </c:if></td>
<%--<c:if test="${role_user.equals('manager')}">
    <td><a></a></td>
</c:if>--%>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
