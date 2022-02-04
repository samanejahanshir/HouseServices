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
        <button type="submit" id="back" name="back" class="btn btn-primary btn-group"
                onclick="history.back()" style="margin: 2vh 2vw">back
        </button>
        <c:if test="${verify==null || verify==false}">
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
        </c:if>
    <c:if test="${verify==true}">
        <c:if test="${role_user=='expert'}">
        <form cssClass="p-1 my-5 mx-5" method="post" action="/expert/saveNewPass" onsubmit="return checkPassword();">
            </c:if>
            <c:if test="${role_user=='customer'}">
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
<%--
                /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/
--%><footer class=" w-100 footer-no-nav navbar-fixed-bottom border border-primary text-center text-lg-start text-primary mt-2" >
                <div class="w-100" style="background-color: #adc4fc;height: 50px;">Home Services</div>
            </footer>
                <script type="text/javascript">

                    var re = /(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}/ ;
                    const password = document.getElementById("password").value;
                    const repassword = document.getElementById("repassword").value;
                    function checkPassword()
                    {
                        // at least one number, one lowercase and one uppercase letter
                        // at least six characters
                        if(password.match(re)){
                            alert("password format should be contain 0-9 and a-z and A-Z")
                            return false;
                        }
                        if(password!==repassword){
                            alert("password and repeat password is different")
                            return false;
                        }
                    }

                </script>
                <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                        crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
                        crossorigin="anonymous"></script>
</body>


</html>
