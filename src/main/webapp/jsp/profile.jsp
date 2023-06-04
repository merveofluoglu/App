<!DOCTYPE html>
<html lang="en">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Page</title>

  <!-- Custom Css -->
  <link rel="stylesheet" href="../resources/static/css/profile.css">
  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

  <!-- Font Awesome -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

  <!-- Additional CSS Files -->
  <link rel="stylesheet" href="admin-dashboard/assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="admin-dashboard/assets/css/lineicons.css" />
  <link rel="stylesheet" href="admin-dashboard/assets/css/main.css" />
  <link rel="stylesheet" href="../resources/static/css/mainpage.css">
  <link rel="stylesheet" href="../resources/static/css/owl.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
</head>
<body>
<script src='img[src="data:image/jpg;data.base64,' + ${user.base64} integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>


<!-- Navbar top -->
<div class="navbar-top">
  <!-- Navbar -->
  <ul>
    <li>
      <a href="${pageContext.request.contextPath}/jsp/main-page/mainpage.jsp"><i class='fa fa-arrow-left fa-2x' style='color: white;scale: inherit'></i></a>
    </li>
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
    <img src="data:image/jpeg;base64,${user.base64}" alt="pp_png" border="0" width="50" height="50" id="profilePic"/>
    <div class="name">
      ${user.name} ${user.surname}
    </div>
  </div>
  <div class="sidenav-url">
    <div class="url">
      <a onclick="openModal()" class="active">Update Profile Photo</a>
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
<!------------------    ADD FILE   ----------------------------------->

<form method="post" class="form-group" enctype= "multipart/form-data" action="${pageContext.request.contextPath}/user/upload">
  <div class="modal fade" id="updatePP" tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="addModalFile">Upload Photo</h4>
          <button type="button" class="btn-close" target="#updatePP" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" id="addFileModal">
          <div class="form-group">
            <label for="file">File:</label>
            <input id="file" type="file" name="ppPath" />
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-toolbar" target="#updatePP" data-bs-dismiss="modal">Close</button>
            <input type="submit" value="Save" class="btn btn-success">
          </div>
        </div>
      </div>
    </div>
  </div>
</form>
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
/*
  get_images = () => {
    $.ajax({
      method: "GET",
      url: "${pageContext.request.contextPath}/user/getProfile",
      success: function (response) {

        let data = JSON.parse(response).data;
        data.forEach(elem => {
          let img = document.createElement('img');

          img.src = 'data:image/jpeg;base64,' + elem.base64;
          document.body.appendChild(img);

        });

      }
    })
  }*/
  const openModal = () =>{
    $("#updatePP [name='file']").val("");
    $("#updatePP").modal('show');
  }
  $('#updatePP').submit(function (e) {
    let frm = $('#updatePP');
    e.preventDefault();
    $.ajax({
      type: frm.attr('method'),
      data: frm.serialize(),
      url: "${pageContext.request.contextPath}/user/upload",
      success: function (response) {
        $("#updatePP").modal('hide');
        let data = JSON.parse(response).data;
        let img = document.getElementById("profilePic");
        img.src = 'data:image/jpeg;base64,' + data;
      },
      error: function (response){
        console.log(response);
      }
    });
  });

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
  <script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <script src="admin-dashboard/assets/js/bootstrap.bundle.min.js"></script>
  <script src="admin-dashboard/assets/js/main.js"></script>
</body>
</html>