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
    <title>Title</title>
</head>
<body style="background-color: #c3e7f8">
<div class="container" >
    <form:form method="post" action="/SignUpExpert" modelAttribute="expertDto">
    first name: <form:input type="text" path="firstName"/>
        <form:errors path="firstName" />
    <br/>
    last name: <form:input type="text" path="lastName"/>
        <form:errors path="lastName"/>

        <br/>
    email: <form:input type="email" path="email"/>
        <form:errors path="email"/>

        <br/>
    password: <input type="password" name="password" id="password"/>
        <br/>
    image : <form:input type="file" path="image"/>
        <form:errors path="image" />

    <br/>
    <input type="submit" value="register"/>

    </form:form>
</div>
</body>
</html>
