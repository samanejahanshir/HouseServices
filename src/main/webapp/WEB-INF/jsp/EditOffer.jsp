<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/23/2022
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add Offer</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body>
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
    <form:form cssClass="p-1 my-5 mx-5" method="post" action="/offer/updateOffer"
               modelAttribute="offerDto">
        <h2 style="text-justify: distribute-center-last">Add Offer</h2>
        <form:hidden path="id"/>
        <%-- <form:hidden path="state"/>
         <form:hidden path="orderDto"/>
         <form:hidden path="expertDto"/>
         <form:hidden path="offerCreateDate"/>--%>
        <table class="table table-bordered table-striped table-primary text-dark">

            <tr>
                <td>
                    <form:label path="startTime">start time :</form:label>
                </td>
                <td>
                    <form:input type="number" path="startTime" placeHolder="start time"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="durationTime">durationTime :</form:label>
                </td>
                <td>
                    <form:input type="number" path="durationTime" placeHolder="durationTime"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="offerPrice">offerPrice :</form:label>
                </td>
                <td>
                    <form:input type="number" path="offerPrice" placeHolder="offerPrice"/>
                </td>
            </tr>

            <tr>
                <td>
                </td>
                <td>
                    <form:button name="update">update</form:button>
                </td>
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
