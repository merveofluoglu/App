<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 26.04.2023
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <!-- Custom Css -->
    <link rel="stylesheet" href="/jsp/profile.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
    <link href="${pageContext.request.contextPath}/jsp/admin-dashboard/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/signup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/fontawesome.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<div class="navbar-top">
    <!-- Navbar -->
    <ul>
    </ul>
    <!-- End -->
</div>
<!-- Main -->
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form id="reguser" method="POST" action="${pageContext.request.contextPath}/user/register">
    <h3>Register</h3>
    <label for="name">Name</label>
    <input type="text" placeholder="Name" name="name" id="name">

    <label for="surname">Surname</label>
    <input type="text" placeholder="Surname" name="surname" id="surname">

    <label for="email">Email</label>
    <input type="email" placeholder="Email" name="email" id="email">

    <label for="password">Password</label>
    <input type="password" placeholder="Password" name="password" id="password">

    <button type="submit">Sign Up</button>
    <div class="social">
        <div class="go"><i class="fab fa-google fa-spin fa-sm" style="color: #d10000;"></i>Google</div>
    </div>
</form>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>

<script>

    $('#reguser').submit(function (e) {

        let frm = $('#reguser');

        e.preventDefault();

        const _data = {
            name: $("#reguser [name='name']").val(),
            surname: $("#reguser [name='surname']").val(),
            email: $("#reguser [name='email']").val(),
            password: $("#reguser [name='password']").val(),
        };

        if(!checkValidity(_data)) {
            return;
        }

        $.ajax({
                url: "${pageContext.request.contextPath}/user/register",
                method: "POST",
                data: _data,
                success: function (response) {
                    toastr.success("User created successfully");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    });

    const reguser = () => {
        const _data = {
            name: $("#reguser [name='name']").val(),
            surname: $("#reguser [name='surname']").val(),
            email: $("#reguser [name='email']").val(),
            password: $("#reguser [name='password']").val(),
        };

        if(!checkValidity(_data)) {
            return;
        }

        $.ajax({
                url: "${pageContext.request.contextPath}/user/register",
                method: "POST",
                data: _data,
                success: function (response) {
                    toastr.success("User created successfully");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    checkValidity = (data) => {

        if(data.name == "" || data.name == null || data.name == undefined) {
            toastr.error("Please fill all sections!");
            return false;
        }

        if(data.surname == "" || data.surname == null || data.surname == undefined) {
            toastr.error("Please fill all sections!");
            return false;
        }

        if(data.email == "" || data.email == null || data.email == undefined) {
            toastr.error("Please fill all sections!");
            return false;
        }

        if(data.password == "" || data.password == null || data.password == undefined) {
            toastr.error("Please fill all sections!");
            return false;
        }

        return true;

    }

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
