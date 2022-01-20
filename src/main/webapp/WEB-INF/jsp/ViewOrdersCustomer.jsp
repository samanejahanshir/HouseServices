<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2" bgcolor="#f8f8ff">
    <tr><th>Id</th><th>DoingDate</th><th>DoingTime</th><th>RegisterDate</th><th>Price</th><th>state</th><th>sub service</th></tr>
    <c:forEach var="order" items="${listOrdersDto}" >
        <tr>
            <td>${order.id}</td>
            <td>${order.orderDoingDate}</td>
            <td>${order.orderDoingTime}</td>
            <td>${order.orderRegisterDate}</td>
            <td>${order.proposedPrice}</td>
            <td>${order.state}</td>
            <td>${order.subServiceDto.name}</td>
            <td><a href="/customer/viewListOffers/${order.id}">view Offers</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
