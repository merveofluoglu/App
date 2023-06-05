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

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
  <link href="${pageContext.request.contextPath}/jsp/admin-dashboard/assets/css/bootstrap.min.css" rel="stylesheet">
  <!-- Additional CSS Files -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/login.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/fontawesome.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <script>
    function preventBack() {
      window.history.forward();
    }

    setTimeout("preventBack()", 0);
    window.onunload = function() {
      null
    };
  </script>

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
<form method="POST" action="${pageContext.request.contextPath}/user/login" id="loginForm">
  <h3>Login</h3>
  <label for="email">Email</label>
  <input type="text" placeholder="Email or Phone" id="email" name="email" required>

  <label for="password">Password</label>
  <input type="password" placeholder="Password" id="password" name="password" required>

  <button type="submit" target="#loginForm">Log In</button>
  <a href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=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&redirect_uri=http://localhost:8080/GoogleSignInVerifier&client_id=34703303946-snsg5p13jmullq38d0vfc24jnao8rtgm.apps.googleusercontent.com">
  <div class="social">
    <div class="go"><i class="fab fa-google fa-spin fa-sm" style="color: #d10000;"></i>  Google</div>
  </div>
  </a>
</form>

<form-small>
  <div style="margin-left: 55px;  font-size: 12px; margin-bottom: 10px; margin-right: 5px"> Do not have an account?
    <button-register><a href="${pageContext.request.contextPath}/jsp/register.jsp"; style="color: #1a1d20; text-decoration:none;">Register</a></button-register>
  </div>
</form-small>
<script>

  $('#loginForm').submit(function (e) {
    let frm = $('#loginForm');
    const _data = {
      email: $("#loginForm [name='email']").val(),
      password: $("#loginForm [name='password']").val(),

    };
    e.preventDefault();
    $.ajax({
      type: frm.attr('method'),
      url: "${pageContext.request.contextPath}/user/login",
      data: _data,
      success: function (data) {
        window.location.href = '${pageContext.request.contextPath}/jsp/main-page/mainpage.jsp';
        console.log('Submission was successful.');
        console.log(data);
      },
      error: function (data) {
        console.log('An error occurred.');
        toastr.error(JSON.parse(data.responseText).error.message);

        setTimeout(() => {
          window.location.href = '${pageContext.request.contextPath}/jsp/login.jsp';
        }, 5000);
      },
    });
  });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
