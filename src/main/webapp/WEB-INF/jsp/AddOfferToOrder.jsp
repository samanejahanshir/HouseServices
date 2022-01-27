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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body>
<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5"  method="post" action="/expert/saveOffer/${idOrder}"
               modelAttribute="offerDto">
        <h2 style="text-justify: distribute-center-last">Add Offer</h2>
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
                    <form:button name="add">add offer</form:button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
