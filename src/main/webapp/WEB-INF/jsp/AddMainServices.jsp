<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/17/2022
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<form:form modelAttribute="mainService" action="/manager/saveMainService">
    <form:input path="groupName" />
    <input type="submit" value="save">
</form:form>
</body>
</html>
