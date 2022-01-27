<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Register Customer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">

<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5"  method="post" action="/customer/register"
               modelAttribute="customerDto">
        <h2 style="text-justify: distribute-center-last">Register Customer</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
            <tr>
                <td>
                    <form:label path="firstName">firstName :</form:label>
                </td>
                <td>
                    <form:input path="firstName" placeHolder="first name"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="firstName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastName">lastName :</form:label>
                </td>
                <td>
                    <form:input  path="lastName" placeHolder="last name"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="lastName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="email">email :</form:label>
                </td>
                <td>
                    <form:input type="email" path="email" placeHolder="email"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>password :</label>
                </td>
                <td>
                    <form:input type="password" path="password" placeHolder="password"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:button name="register">Register</form:button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
