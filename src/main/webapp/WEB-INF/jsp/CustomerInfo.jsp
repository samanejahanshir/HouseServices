<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/24/2022
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<%--<p>${message}</p>--%>
<form:form modelAttribute="customerDto">
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr>
            <th>First Name</th>
            <th>LastName</th>
            <th>Email</th>
            <th>RegisterDate</th>
            <th>Credit</th>
        </tr>
        <tr>
            <td>${customerDto.firstName}</td>
            <td>${customerDto.lastName}</td>
            <td>${customerDto.email}</td>
            <td>${customerDto.registerDate}</td>
            <td>${customerDto.credit}</td>
        </tr>

    </table>
</form:form>
</body>
</html>
