<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/20/2022
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Offers</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<P>${message}</P>
<div class="container m-1">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>Id</th><th>createDate</th><th>price</th><th>Duration time</th><th>Start time</th><th>expert</th><th>state</th><th>select offer</th></tr>
    <c:forEach var="offer" items="${listOffers}" >
        <tr>
            <td>${offer.id}</td>
            <td>${offer.offerCreateDate}</td>
            <td>${offer.offerPrice}</td>
            <td>${offer.durationTime}</td>
            <td>${offer.startTime}</td>
            <td>${offer.expertDto.firstName} ${offer.expertDto.lastName}</td>
            <td>${offer.state}</td>
            <td><a href="/offer/selectOffer/${offer.id}">select</a></td>

        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
