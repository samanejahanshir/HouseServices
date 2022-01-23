<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/23/2022
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container m-1">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr><th>Id</th><th>Name</th><th>Price</th><th>Description</th></tr>
        <c:forEach var="service" items="${listSubServices}" >
            <tr>
                <td>${service.id}</td>
                <td>${service.name}</td>
                <td>${service.basePrice}</td>
                <td>${service.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
