<%--
  Created by IntelliJ IDEA.
  User: MitKnight
  Date: 1/19/2022
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Change pass</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body style="background-color: #c3e7f8">
<p>${message}</p>
<br>
<%--<c:if test="${role_user.equals('manager')}">
<form action="/manager/home">
    </c:if>--%>
<c:if test="${role_user.equals('expert')}">
<form action="/expert/home">
    </c:if>
    <c:if test="${role_user.equals('customer')}">
    <form action="/customer/home">
        </c:if>
        <button type="submit" id="dashboard" name="dashboard" class="btn btn-primary btn-group"
                style="margin: 2vh 2vw">Dashboard
        </button>
    </form>
    <div class="container col-12">
        <c:if test="${role_user.equals('expert')}">
        <form cssClass="p-1 my-5 mx-5" method="post" action="/expert/checkVerifyCode">
            </c:if>
            <c:if test="${role_user.equals('customer')}">
            <form cssClass="p-1 my-5 mx-5" method="post" action="/customer/checkVerifyCode">
                </c:if>
                <%-- <c:if test="${role_user.equals('manager')}">
                 <form cssClass="p-1 my-5 mx-5" method="post" action="/manager/saveNewPass">
                     </c:if>--%>
                <h2 style="text-justify: distribute-center-last">Change Password</h2>
                <table class="table table-bordered table-striped table-primary text-dark">
                    <tr>
                        <td>
                            verify code:
                        </td>
                        <td>
                            <input type="number" name="code">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="submit" value="check code">
                        </td>
                    </tr>
                </table>
            </form>
    </div>

    <c:if test="${verify==true}">
        <c:if test="${role_user.equals('expert')}">
        <form cssClass="p-1 my-5 mx-5" method="post" action="/expert/saveNewPass" onsubmit="return checkPassword();">
            </c:if>
            <c:if test="${role_user.equals('customer')}">
            <form cssClass="p-1 my-5 mx-5" method="post" action="/customer/saveNewPass" onsubmit="return checkPassword();">
                </c:if>
                <%-- <c:if test="${role_user.equals('manager')}">
                 <form cssClass="p-1 my-5 mx-5" method="post" action="/manager/saveNewPass">
                     </c:if>--%>
                <h2 style="text-justify: distribute-center-last">Change Password</h2>
                <table class="table table-bordered table-striped table-primary text-dark">
                    <tr>
                        <td>
                            password:
                        </td>
                        <td>
                            <input type="password" name="password" id="password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Repeat password:
                        </td>
                        <td>
                            <input type="password" name="re_password" id="repassword">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="submit" value="change password">
                        </td>
                    </tr>
                </table>
            </form>
        </c:if>
</body>

<script>

    var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");

    function checkInputs() {
        if (password.length < 8) {
            alert("postal code numbers should be 10");
            this.value = "";
            return false;
        }
        if (isNaN(form.postalCode.value)) {
            alert("type of postal code should be numbers");
            return false;
        }
    }
</script>
<script type="text/javascript">
    var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
    const password = document.getElementById("password").value;
    const repassword = document.getElementById("repassword").value;
    function checkPassword()
    {
        // at least one number, one lowercase and one uppercase letter
        // at least six characters
       if(!re.test(password)){
           return false;
       }
        if(password!==repassword){
            return false;
        }
    }

</script>
</html>
