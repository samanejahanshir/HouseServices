<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Register Expert</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<div class="container col-12">
    <form:form cssClass="p-1 my-5 mx-5"  method="post" action="/expert/register"
               modelAttribute="expertDto">
        <h2 style="text-justify: distribute-center-last">Register Expert</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
            <tr>
                <td>
                    <form:label path="firstName">firstName :</form:label>
                </td>
                <td>
                    <form:input type="text" path="firstName" placeHolder="first name"/>
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
                    <form:input type="text" path="lastName" placeHolder="last name"/>
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
                <td></td>
                <td>
                    <form:errors path="password"/>

                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="image">image:</form:label>
                </td>
                <td>
                    <form:input type="file" path="image" id="image" placeHolder="profile image"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="image"/>
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
<script>
    const imageFile = document.getElementById("image");

    imageFile.onchange = function () {
        const maxAllowedSize = 300 * 1024;
        if (this.files[0].size > maxAllowedSize) {
            alert("Image File is too big! should be <300kb");
            this.value = "";
        }
    }
</script>
</body>
</html>
