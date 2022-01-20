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
    <title>Title</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2" bgcolor="#f8f8ff">
    <tr><th>FirstName</th><th>SubServices</th></tr>
    <c:forEach var="service" items="${listMainServices}" >
        <tr>
            <td>${service.groupName}</td>
            <td><a href="/manager/viewListSubServices/${service.groupName}">view SubServices</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
