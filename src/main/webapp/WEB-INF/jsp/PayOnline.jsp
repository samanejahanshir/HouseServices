<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/30/2022
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body>
<P>${message}</P>
<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5" method="post" action="/order/paymentOnline"
               modelAttribute="cart" >
        <h2 style="text-justify: distribute-center-last">Add Order</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
            <form:input type="hidden" path="idOrder" value="${orderDto.id}"/>
            <tr>
                <td>
                    cart number:
                </td>
                <td>
                    <form:input type="text" path="number" name="cart" id="cart" placeHolder="0000000000000000"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="number"/>
<%--
                    <label id="cart_error"></label>
--%>
                </td>
            </tr>
            <tr>
                <td>
                    cvv2:
                </td>
                <td>
                    <form:input type="text" path="cvv2" name="cvv2" id="cvv2"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="cvv2"/>

<%--
                    <label id="cvv2_error"></label>
--%>
                </td>
            </tr>
            <tr>
                <td>
                    date:
                </td>
                <td>
                   year: <form:input type="number" name="year" path="year" id="year"/>   month: <form:input type="number" path="month" name="month" id="month"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="year"/>      <form:errors path="month"/>


<%--
                    <label id="year_error"></label> <label id="month_error"></label>
--%>

                </td>
            </tr>
            <tr>
                <td>
                    password:
                </td>
                <td>
                    <form:input type="text" path="password" name="password" id="password"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="password"/>

<%--
                    <label id="pass_error"></label>
--%>
                </td>
            </tr>

            <tr>
                <td>
                    amount :
                </td>
                <td>
                    <label> ${orderDto.proposedPrice}</label>
                </td>
            </tr>

            <tr>
                <td>
                </td>
                <td>
                    <form:button name="payment">payment</form:button>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<script>
    //  const postalCode = document.getElementById("postalCode");
    const cart = document.getElementById("cart").value;
    const cvv2 = document.getElementById("cvv2").value;
    const month =document.getElementById("month").value;
    const year = document.getElementById("year").value;
    const pass = document.getElementById("password").value;

    function check() {
        if (cart.length!==16) {
            document.getElementById("cart_error").innerText="cart length should be 16";
            return false;
        }
        if (isNaN(cart)) {
            document.getElementById("cart_error").innerText="cart number should be number";
            return false;
        }
        if (cvv2.toString().length < 3) {
            document.getElementById("cvv2_error").innerText="length of cvv2 > = 3";
            return false;
        }
        if (isNaN(cvv2)) {
            document.getElementById("cvv2_error").innerText="cvv2 should be number";
            return false;
        }
        if (year.toString().length !==2) {
            document.getElementById("year_error").innerText="length of year =2";
            return false;
        }
        if (isNaN(year)) {
            document.getElementById("year_error").innerText="year should be number";
            return false;
        }
        if (month.toString().length !==2) {
            document.getElementById("month_error").innerText="length of month=2";
            return false;
        }
        if (isNaN(month)) {
            document.getElementById("month_error").innerText="month should be number";
            return false;
        }
        if (pass.toString().length <5) {
            document.getElementById("pass_error").innerText="length of password should be >5 ";
            return false;
        }
        if (isNaN(pass)) {
            document.getElementById("pass_error").innerText="password should be number";
            return false;
        }
    }
</script>
</body>
</html>
