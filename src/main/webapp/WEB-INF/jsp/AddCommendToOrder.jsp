<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/24/2022
  Time: 8:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<div class="container m-1">
    <form:form modelAttribute="commend" action="/manager/saveMainService">
        <h2 style="text-justify: distribute-center-last">Add MainService</h2>
        <table class="table table-bordered table-striped table-primary text-dark">
            <tr>
                <td>
                    <form:label path="commend">Commend :</form:label>
                </td>
                <td>
                    <form:textarea path="commend" />        </td>
            </tr>
            <tr>
                <td>
                    <form:label path="score">Commend :</form:label>
                </td>
                <td>
                    <ul>
                        <form:select path="score">
                            <form:option type="number" value="1">1</form:option>
                            <form:option type="number" value="2">2</form:option>
                            <form:option type="number" value="3">3</form:option>
                            <form:option type="number" value="4">4</form:option>
                            <form:option type="number" value="5">4</form:option>

                        </form:select>
                    </ul>
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
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>


</body>
</html>
