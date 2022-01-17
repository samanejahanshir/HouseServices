<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<%--
<div class="container">
    <form:form method="post" action="/SignUp" modelAttribute="userDto">
    first name: <form:input type="text" path="firstName"/>
    <br/>
    last name: <form:input type="text" path="lastName"/>
    <br/>
    email: <form:input type="email" path="email"/>
    <br/>
    password: <input type="password" name="password"/>
    <br/>
    <c:if test="${role_user=='expert'}">
        image : <input type="file" name="image"/>
    </c:if>
    <br/>
    <c:if test="${role_user=='customer'}">
    Address :
    <br/>
    <form:form  method="post" action="/SignUp" modelAttribute="address">
        city: <form:input type="text" path="city"/>
        <br/>
        street: <form:input type="text" path="street"/>
        <br/>
        postal code: <form:input type="text" path="postalCode"/>
        <br/>
        tag: <form:input type="text" path="tag"/>
        <br/>
    </form:form>
</div>
</c:if>

<input type="submit" value="register"/>
</form:form>
</div>--%>
<form:form  method="post" action="/SignUpManager" modelAttribute="manager">
    Email: <form:input type="email" path="userName"/>
    <br/>
    Password: <form:input type="password" path="password"/>
    <br/>
   <input type="submit" value="register">
</form:form>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>

</body>
</html>
