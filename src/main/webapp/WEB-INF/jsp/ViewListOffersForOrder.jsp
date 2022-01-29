<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/20/2022
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Offers</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/customer/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>
<div class="container col-12">
    <h1>Users List</h1>

    <form:form cssClass="text-center"  modelAttribute="offerFilter" action="/offer/searchOffers/${orderId}"  method="post">
        <table class="table table-striped table-primary text-dark table-hover">
            <tr>
                <td>
                   byPrice: <form:checkbox  path="filter"  value="byPrice"/>
                </td>
                <td>
                  byScore:  <form:checkbox path="filter" value="byScore" />
                </td>

                <td>
                    <form:button name="search">Search</form:button>
                </td>

            </tr>

            <tr>
                <th>Id</th>
                <th>createDate</th>
                <th>price</th>
                <th>Duration time</th>
                <th>Start time</th>
                <th>expert</th>
                <th>state</th>
                <th>select offer</th>
            </tr>

            <c:forEach var="offer" items="${listOffers}" >
                <tr>
                    <td>${offer.id}</td>
                    <td>${offer.offerCreateDate}</td>
                    <td>${offer.offerPrice}</td>
                    <td>${offer.durationTime}</td>
                    <td>${offer.startTime}</td>
                    <td>${offer.expertDto.firstName} ${offer.expertDto.lastName}</td>
                    <td>${offer.state}</td>
                    <c:if test="${offer.orderDto.state=='WAIT_SELECT_EXPERT'}">
                    <td><a href="/offer/selectOffer/${offer.id}">select</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</div>
</body>
</html>
