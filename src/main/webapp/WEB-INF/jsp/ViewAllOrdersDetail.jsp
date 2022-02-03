<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/30/2022
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Orders</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/manager/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>

    <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
            onclick="history.back()" style="margin: 2vh 2vw">back
    </button>

<div class="container col-12">
    <h1>Orders List</h1>

    <form:form cssClass="text-center" modelAttribute="orderSearch" action="/manager/searchOrders" method="post">
        <table class="table table-striped table-primary text-dark table-hover">
            <tr>
                <td>MainService:
                    <form:select path="mainServiceName" cssClass="dropdown form-control"
                                 cssStyle="width: 12vw">
                        <option value="">--</option>
                        <c:forEach items="${mainServices}" var="service">
                            <option value="${service.groupName}">${service.groupName}</option>
                        </c:forEach>
                    </form:select>
                </td>
                <td>SubService:
                    <form:select path="subServiceName" cssClass="dropdown form-control"
                                 cssStyle="width: 12vw">
                        <option value="">--</option>
                        <c:forEach items="${subServices}" var="service">
                            <option value="${service.name}">${service.name}</option>
                        </c:forEach>
                    </form:select>
                </td>
                <td>OrderState:
                    <form:select path="state" cssClass="dropdown form-control"
                                 cssStyle="width: 12vw">
                        <option value="">--</option>
                        <option value="WAIT_OFFER_EXPERTS">WAIT_OFFER_EXPERTS</option>
                        <option value="WAIT_SELECT_EXPERT">WAIT_SELECT_EXPERT</option>
                        <option value="WAIT_EXPERT_COME">WAIT_EXPERT_COME</option>
                        <option value="STARTED">STARTED</option>
                        <option value="DONE">DONE</option>
                        <option value="PAID">PAID</option>

                    </form:select>
                </td>
                <td>
                </td>
                <td>
                    from:<br/><input type="date"  path="startDate" name="startDate"  placeHolder="start date"/>
                </td>
                <td>
                    to:<br/><input type="date" path="endDate" name="endDate"  placeHolder="end date"/>
                </td>

                <td>
                    <br/>
                    <form:button name="search">Search</form:button>
                </td>

            </tr>
            <tr><th>Id</th>
                <th>DoingDate</th>
                <th>DoingTime</th>
                <th>RegisterDate</th>
                <th>Price</th>
                <th>state</th>
                <th>sub service</th>
            </tr>
            <c:forEach var="order" items="${listOrders}" >
                <tr>
                    <td>${order.id}</td>
                    <td>${order.orderDoingDate}</td>
                    <td>${order.orderDoingTime}</td>
                    <td>${order.orderRegisterDate}</td>
                    <td>${order.proposedPrice}</td>
                    <td>${order.state}</td>
                    <td>${order.subServiceDto.name}</td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
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
