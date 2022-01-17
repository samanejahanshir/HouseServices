<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p></p>
<div class="container">
<form:form method="post" action="/info" modelAttribute="expertDto">
    first name: <form:input type="text" path="firstName"/>
    <br/>
    last name: <form:input type="text" path="lastName"/>
    <br/>
    email: <form:input type="email" path="email"/>
    <br/>
    password: <input type="password" name="password"/>
    <br/>
        image : <form:input type="file" path="image"/>

    <br/>
    <input type="submit" value="register"/>

</form:form>
</body>
</html>
