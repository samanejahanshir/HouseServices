<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/24/2022
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<p>${message}</p>
<form:form modelAttribute="orderDto">
<table class="table table-bordered table-striped table-primary text-dark">
    <tr><th>Id</th><th>DoingDate</th><th>DoingTime</th><th>RegisterDate</th>
    <th>Price</th><th>state</th><th>sub service</th>
    <th>customer</th>
    <th>address</th></tr>
        <tr>
            <td>${orderDto.id}</td>
            <td>${orderDto.orderDoingDate}</td>
            <td>${orderDto.orderDoingTime}</td>
            <td>${orderDto.orderRegisterDate}</td>
            <td>${orderDto.proposedPrice}</td>
            <td>${orderDto.state}</td>
            <td>${orderDto.subServiceDto.name}</td>
            <td>${orderDto.customerDto.firstName}  ${orderDto.customerDto.lastName}</td>
            <td>${orderDto.address.city}-${orderDto.address.street}-${orderDto.address.postalCode}-${orderDto.address.tag}</td>
            <td><a href="/order/startWork/${orderDto.id}">start</a> </td>
            <td><a href="/order/endWork/${orderDto.id}">end</a> </td>
            <td><a href="/order/paymentCustomer/${orderDto.id}">Payment customer</a> </td>
        </tr>

</table>
</form:form>
</body>
</html>
