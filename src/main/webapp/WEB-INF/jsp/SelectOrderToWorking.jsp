<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/24/2022
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<div class="w-100 " style="height: 20px">
    <p style="margin-left: 10px; margin-top: 5px;">${message}</p>
</div>
<div class="container row">
    <div class="col-2">
        <form action="/expert/home">
            <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
                    style="margin: 2vh 2vw">Dashboard
            </button>
        </form>
    </div>
    <div class="col-1">
        <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
                onclick="history.back()" style="margin: 2vh 2vw">back
        </button>
    </div>
</div>
<div class="container col-12">
    <form:form modelAttribute="orderDto">
        <table class="table table-bordered table-striped table-primary text-dark">
            <tr>
                <th>Id</th>
                <th>DoingDate</th>
                <th>DoingTime</th>
                <th>RegisterDate</th>
                <th>PriceSubService</th>
                <th>state</th>
                <th>sub service</th>
                <th>customer</th>
                <th>address</th>
            </tr>
            <tr>
                <td>${orderDto.id}</td>
                <td>${orderDto.orderDoingDate}</td>
                <td>${orderDto.orderDoingTime}</td>
                <td>${orderDto.orderRegisterDate}</td>
                <td>${orderDto.subServiceDto.basePrice}</td>
                <td>${orderDto.state}</td>
                <td>${orderDto.subServiceDto.name}</td>
                <td>${orderDto.customerDto.firstName} ${orderDto.customerDto.lastName}</td>
                <td>${orderDto.address.city}-${orderDto.address.street}-${orderDto.address.postalCode}-${orderDto.address.tag}</td>
                <c:if test="${orderDto.state=='WAIT_EXPERT_COME'}">
                    <td><a href="/order/startWork/${orderDto.id}">start</a></td>
                </c:if>
                <c:if test="${orderDto.state=='STARTED'}">
                    <td><a href="/order/endWork/${orderDto.id}">end</a></td>
                </c:if>
                    <%--
                                <td><a href="/order/paymentCustomer/${orderDto.id}">Payment customer</a> </td>
                    --%>
            </tr>

        </table>
    </form:form>
</div>
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2">
    <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
</body>
</html>
