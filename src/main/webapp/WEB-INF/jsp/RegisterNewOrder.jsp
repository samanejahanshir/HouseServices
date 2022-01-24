<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>New Order</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<%--
<c:set  var="email" value="${email}"/>
--%>

<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5"  method="post" action="/order/saveOrder"
               modelAttribute="orderDto">
        <h2 style="text-justify: distribute-center-last">Add Order</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
            <tr>
                <td>
                    description:
                </td>
                <td>
                    <form:input type="text" path="description" placeHolder="description"/>
                </td>
            </tr>
            <tr>
                <td>
                    orderDoingDate:
                </td>
                <td>
                    <input type="date" name="orderDate" placeHolder="yyyy-mm-dd"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="orderDoingTime">orderDoingTime :</form:label>
                </td>
                <td>
                    <form:input type="number" path="orderDoingTime" placeHolder="orderDoingTime"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="address.city">city:</form:label>
                </td>
                <td>
                    <form:input type="text" path="address.city" placeHolder="city"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address.street">street:</form:label>
                </td>
                <td>
                    <form:input type="text" path="address.street" placeHolder="street"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address.postalCode">postal code:</form:label>
                </td>
                <td>
                    <form:input type="text" path="address.postalCode" placeHolder="postalCode"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="address.tag">tag:</form:label>
                </td>
                <td>
                    <form:input type="text" path="address.tag" placeHolder="tag"/>
                </td>
            </tr>
           <tr>
                <td>
                </td>
                <td>
                    <form:button name="add">add order</form:button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
