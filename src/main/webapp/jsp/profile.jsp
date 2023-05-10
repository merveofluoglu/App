<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Page</title>

  <!-- Custom Css -->
  <link rel="stylesheet" href="profile.css">

  <!-- FontAwesome 5 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>


<!-- Navbar top -->
<div class="navbar-top">
  <!-- Navbar -->
  <ul>
    <li>
      <i class="fa fa-sign-out-alt fa-2x" style="color: #ffffff" onclick="logout()"></i>
    </li>
  </ul>
  <!-- End -->
</div>
<!-- End -->

<!-- Sidenav -->
<div class="sidenav">
  <div class="profile">
    <button type="submit">
      <img src="https://i.ibb.co/CW5Wvry/buttonpng.png" alt="buttonpng" border="0" width="100" height="100"/>
    </button>

    <div class="name">
      ${user.name} ${user.surname}
    </div>
  </div>
  <div class="sidenav-url">
    <div class="url">
      <a href="${pageContext.request.contextPath}/jsp/uploadphoto.jsp" class="active">Update Profile Photo</a>
      <hr align="center">
    </div>
  </div>
  <div class="sidenav-url">
    <div class="url">
      <a href="${pageContext.request.contextPath}/jsp/changePassword.jsp" class="active">Change My Password</a>
      <hr align="center">
    </div>
  </div>
    <div class="sidenav-url">
      <div class="url">
        <a href="${pageContext.request.contextPath}/jsp/updateprofile.jsp" class="active">Edit Profile Information</a>
        <hr align="center">
      </div>
    </div>
      <div class="sidenav-url">
        <div class="url">
          <a href="${pageContext.request.contextPath}/jsp/get_favourites.jsp" class="active">Favorites</a>
          <hr align="center">
        </div>
      </div>
  <div class="sidenav-url">
    <div class="url">
      <a href="${pageContext.request.contextPath}/jsp/myposts.jsp" class="active">My Posts</a>
      <hr align="center">
    </div>
  </div>


  <div class="sidenav-url">
    <div class="url">
      <a href="${pageContext.request.contextPath}/jsp/myorders.jsp" class="active">My Orders</a>
      <hr align="center">
    </div>
  </div>

  <div class="sidenav-url">
    <div class="url">
      <a href="${pageContext.request.contextPath}/jsp/usermessages.jsp" class="active">My Messages</a>
      <hr align="center">
    </div>
  </div>

  </div>


</div>
<!-- End -->

<!-- Main -->
<div class="main">
  <h2>Profile Information</h2>
  <div class="card">
    <div class="card-body">
      <table>
        <tbody>
        <tr>
          <td>Name</td>
          <td>:</td>
          <td>${user.name}</td>
        </tr>
        <tr>
          <td>Surname</td>
          <td>:</td>
          <td>${user.surname}</td>
        </tr>
        <tr>
          <td>Email</td>
          <td>:</td>
          <td>${user.email}</td>
        </tr>
        <tr>
          <td>Member since</td>
          <td>:</td>
          <td>${user.creationDate}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>


<!-- End -->

<script>

  const logout = () => {
    $.ajax({
              url: "${pageContext.request.contextPath}/user/logout",
              method: "GET",
              success: function (response) {
                toastr.success("Successfully logged out!");
                window.location.href = "${pageContext.request.contextPath}/jsp/login.jsp"; // redirect
              },
              error: function () {
                alert("error");
              }
            }
    );
  }


  const updateProfile = () => {
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>