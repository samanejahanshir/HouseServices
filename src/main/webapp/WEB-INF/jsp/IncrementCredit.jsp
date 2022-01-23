<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set  var="email" value="${email}"/>
<form method="post" action="/customer/saveCredit">
    <h2 style="text-justify: distribute-center-last">increment credit</h2>
    <table class="table table-bordered table-striped table-primary text-dark">
        <tr>
            <td>
                amount :
            </td>
            <td>
                <input type="number" name="amount" />
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="save">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
