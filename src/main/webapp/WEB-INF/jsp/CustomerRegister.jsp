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
    <title>Title</title>
</head>
<body style="background-color: #c3e7f8">
<div class="container">
    <form:form method="post" action="/SignUpCustomer" modelAttribute="customerDto">
        first name: <form:input type="text" path="firstName"/>
        <br/>
        last name: <form:input type="text" path="lastName"/>
        <br/>
        email: <form:input type="email" path="email"/>
        <br/>
        password: <input type="password" name="password"/>
        <br/>
        <input type="submit" value="register"/>
    </form:form>
</div>
</body>
</html>
