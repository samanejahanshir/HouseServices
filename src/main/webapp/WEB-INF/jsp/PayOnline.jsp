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
<body onload="timer=setTimeout('auto_reload()',600000)">
<P>${message}</P>
<div class="container col-12">
    <p id="timertag"></p>
    <form:form cssClass="p-1 my-5 mx-5" method="post" action="/order/paymentOnline"
               modelAttribute="cart">
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
                    year: <form:input type="number" name="year" path="year" id="year"/> month: <form:input type="number"
                                                                                                           path="month"
                                                                                                           name="month"
                                                                                                           id="month"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="year"/> <form:errors path="month"/>


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

    var timer = null;

    function auto_reload() {
        window.location = '/order/allOrders';
    }
    /*function myTimer() {

        var time=new Date();
        document.getElementById("timer").innerText=(d.getTime()-time.getTime()).toString();
    }
    setInterval(myTimer,600000);*/

</script>
</body>
</html>
