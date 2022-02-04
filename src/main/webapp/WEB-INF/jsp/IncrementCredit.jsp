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
    <title>Increment credit</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body onload="timer=setTimeout('auto_reload()',600000)">
<P>${message}</P>
<div class="container col-12">
    <p id="timertag"></p>
    <form:form cssClass="p-1 my-5 mx-5" method="post" action="/customer/saveCredit"
               modelAttribute="cart">
        <h2 style="text-justify: distribute-center-last">Add Order</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
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
                    <input type="number" name="amount" />
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
<footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2" >
    <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
</footer>
<script>

    var timer = null;

    function auto_reload() {
        window.location = '/customer/home';
    }
    /*function myTimer() {

        var time=new Date();
        document.getElementById("timer").innerText=(d.getTime()-time.getTime()).toString();
    }
    setInterval(myTimer,600000);*/

</script>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
</body>
</html>
