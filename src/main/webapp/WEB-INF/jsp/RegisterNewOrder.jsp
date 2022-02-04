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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>New Order</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<form action="/customer/home">
    <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
            style="margin: 2vh 2vw">Dashboard
    </button>
</form>

    <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
            onclick="history.back()" style="margin: 2vh 2vw">back
    </button>

<%--
<c:set  var="email" value="${email}"/>
--%>

<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5" method="post" action="/order/saveOrder"
               modelAttribute="orderDto" onsubmit="return checkPostalCode(this);">
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
                    <input type="date" name="orderDate" id="date" placeHolder="yyyy-mm-dd"/>
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
                </td>
                <td>
                    <form:errors path="orderDoingTime" cssClass="text-danger"/>
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
                    <form:input type="text" id="postalCode" path="address.postalCode" placeHolder="postalCode"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="address.postalCode" cssClass="text-danger"/>
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
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2" >
    <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
</footer>
<script>
    //  const postalCode = document.getElementById("postalCode");

    function checkPostalCode(form) {
        if (form.postalCode.value.length < 10) {
            alert("postal code numbers should be 10");
            this.value = "";
            return false;
        }
        if (isNaN(form.postalCode.value)) {
            alert("type of postal code should be numbers");
            return false;
        }
    }
</script>
<%--<script>
    //  const postalCode = document.getElementById("postalCode");

    function checkDate(form) {
        const date=document.getElementById("date").innerText;
        if (date==="" || date==null) {
            alert("date can not empty");
            this.value = "";
            return false;
        }
    }
</script>--%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
</body>
</html>
