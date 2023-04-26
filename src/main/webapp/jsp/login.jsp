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

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!-- Register User -->

<div class="modal fade" id="addUser" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Add User</h4>
        <button type="button" class="btn-close" target="#addEUser" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Name: </label>
          <input type="text" name="Name" id="Name" class="form-control" />
        </div>
        <div class="form-group">
          <label>Surname:</label>
          <input type="text" name="Surname" id="Surname" class="form-control" />
        </div>
        <div class="form-group">
          <label>Email:</label>
          <input type="email" name="Email" id="Email" class="form-control" />
        </div>
        <div class="form-group">
          <label>Password:</label>
          <input type="password" name="Password" id="Password" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#addUser" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-success" id="register" data-dismiss="modal" onclick="addUser()">Add</button>
      </div>

    </div>
  </div>
</div>

<form method="POST" action="${pageContext.request.contextPath}/user/login">
  <h3>Login Here</h3>
  <label for="email">email:</label>
  <input id="email" name="email" type="text"/><br/><br/>
  <label for="password">password:</label>
  <input id="password" name="password" type="password"/><br/><br/>
  <button class="button" type="submit">Log In</button><br/>

</form>
<div style="margin-top: 15px; margin-left: 3px;  font-size: 9px;"> Do not have an account?
  <button id="registerUser" >Register</button>
</div>

<c:choose>
  <c:when test="${message.error}">
    <p><c:out value="${message.message}"/></p>
  </c:when>
  <c:otherwise></c:otherwise>
</c:choose>

<script>

  $( "#registerUser" ).on( "click", function() {
    $("#addUser [name='Name']").val("");
    $("#addUser [name='Surname']").val("");
    $("#addUser [name='Email']").val("");
    $("#addUser [name='Password']").val("");
    $("#addUser").modal('show');
  });

  const addUser = () => {
    const _data = {
      name: $("#addUser [name='Name']").val(),
      surname: $("#addUser [name='Surname']").val(),
      email: $("#addUser [name='Email']").val(),
      password: $("#addUser [name='Password']").val(),
    };
    $.ajax({
              url: "${pageContext.request.contextPath}/user/register",
              method: "POST",
              data: _data,
              success: function (response) {
                $('#addUser').modal('hide');
                toastr.success(response);
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
