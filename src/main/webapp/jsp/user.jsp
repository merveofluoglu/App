<!DOCTYPE html>
<!--suppress ALL -->
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin Dashboard</title>

  <!-- ========== All CSS files linkup ========= -->
  <link rel="stylesheet" href="admin-dashboard/assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="admin-dashboard/assets/css/lineicons.css" />
  <link rel="stylesheet" href="admin-dashboard/assets/css/main.css" />
  <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">

  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!-- ======== sidebar-nav start =========== -->
<aside class="sidebar-nav-wrapper">
  <div class="navbar-text">Admin Dashboard

  </div>
  <nav class="sidebar-nav">
    <ul>
      <li class="nav-item">
        <a
                href="user.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">Users</span>
        </a>
      </li>

      <li class="nav-item">
        <a
                href="category.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">Categories</span>
        </a>
      </li>

      <li class="nav-item">
        <a
                href="sub_category.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">Sub Categories</span>
        </a>
      </li>

      <li class="nav-item">
        <a
                href="permission.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">Permissions</span>
        </a>
      </li>

      <li class="nav-item">
        <a
                href="system-log.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">System Logs</span>
        </a>
      </li>

      <li class="nav-item">
        <a
                href="user-log.jsp"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                          d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
          <span class="text">User Logs</span>
        </a>
      </li>
    </ul>
  </nav>
</aside>
<div class="overlay"></div>
<!-- ======== sidebar-nav end =========== -->

<!-- ======== main-wrapper start =========== -->
<main class="main-wrapper">
  <!-- ========== header start ========== -->
  <header class="header">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-5 col-md-5 col-6">
          <div class="header-left d-flex align-items-center">
            <div class="menu-toggle-btn mr-20">
              <button
                      id="menu-toggle"
                      class="main-btn primary-btn btn-hover"
              >
                <i class="lni lni-chevron-left me-2"></i> Menu
              </button>
            </div>
            <div class="col-lg-7 col-md-7 col-6">
              <div class="header-right">
                <!-- application -->
                <div class="ml-15 d-none d-md-flex">
                  <button
                          class="btn btn-light"
                          type="button"
                          id="to-application"
                          style="width: 180px; background: rgb(243 244 245)"
                  ><a href="main.jsp" style="color: black;">
                    <i class="lni lni-grid-alt"></i> To Application
                  </a>
                  </button>
                </div>
              </div>
            </div>
            <div class="col-lg-7 col-md-7 col-6">
              <div class="header-right">
                <!-- profile -->
                <div class="ml-15 d-none d-md-flex">
                  <button
                          class="btn btn-info"
                          type="button"
                          id="to-profile"
                          style="width: 160px; background: rgb(23 162 184)"
                  ><a href="profile.jsp" style="color: white;">
                    <i class="lni lni-user"></i> View Profile
                  </a>
                  </button>
                </div>
              </div>
            </div>
            <div class="col-lg-7 col-md-7 col-6">
              <div class="header-right">
                <!-- profile start -->
                <div class="profile-box ml-15">
                  <button
                          class="dropdown-toggle bg-transparent border-0"
                          type="button"
                          id="profile"
                          data-bs-toggle="dropdown"
                          aria-expanded="false"
                  >
                    <div class="profile-info">
                      <div class="info">
                        <h6>Admin</h6>
                      </div>
                    </div>

                    <i class="lni lni-chevron-down"></i>
                  </button>
                  <ul
                          class="dropdown-menu dropdown-menu-end"
                          aria-labelledby="profile"
                  >
                    <li>
                      <a onclick="logout()">
                        <i class="lni lni-exit"></i> Sign Out
                      </a>
                    </li>
                  </ul>
                </div>
                <!-- profile end -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <!-- ========== header end ========== -->

  <!-- ========== section start ========== -->
  <section class="section">
    <div class="container-fluid">
      <!---------DATATABLE--------->

      <table id="User" class="display" width="100%">
        <thead>
        <tr>
          <th id="UserId">User Id</th>
          <th id="Name">Name</th>
          <th id="Surname">Surname</th>
          <th id="Email">Email</th>
          <th id="CreationDate">Creation Date</th>
          <th id="UpdateDate">Update Date</th>
        </tr>
        </thead>
      </table>

      <!---------ADD USER--------->

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

      <!---------EDIT USER--------->

      <div class="modal fade" id="editUser" tabindex="-1">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">Edit User</h4>
              <button type="button" class="btn-close" target="#editUser" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="form-group" style="display:none">
                <label>ID:</label>
                <input type="number" name="UserId" id="UserId" class="form-control" readonly="readonly" />
              </div>
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
              <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#editUser" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateUser()">Edit</button>
              </div>

            </div>
          </div>
        </div>
      </div>

      <!---------DELETE USER--------->

      <div id="dialog" class="modal fade" role="dialog" style="display:none">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h3 class="modal-title">Delete User</h3>
              <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>Do you want to delete this user?</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-toolbar" target="#dialog" data-bs-dismiss="modal">Close</button>
              <button type="button" id="confirm" class="btn btn-danger">Confirm</button>
            </div>
          </div>
        </div>
      </div>

      <!---------GET BY ID USER MODAL--------->

      <div id="dialogId" class="modal fade" role="dialog" style="display:none">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h3 class="modal-title">User Get By Id</h3>
              <button type="button" class="btn-close" target="#dialogId" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>Do you want to retrieve this user?</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-toolbar" target="#dialogId" data-bs-dismiss="modal">Close</button>
              <button type="button" id="confirmId" class="btn btn-danger">Confirm</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end container -->
  </section>
  <!-- ========== section end ========== -->

  <!-- ========== footer start =========== -->
  <footer class="footer">
    <div class="container-fluid">

    </div>
    <!-- end container -->
  </footer>
  <!-- ========== footer end =========== -->
</main>
<!-- ======== main-wrapper end =========== -->

<script>
  $(document).ready(function () {
    FillDatatable();
  });

  var table;

  const addUser = () => {
    const _data = {
      name: $("#addUser [name='Name']").val(),
      surname: $("#addUser [name='Surname']").val(),
      email: $("#addUser [name='Email']").val(),
      password: $("#addUser [name='Password']").val(),
    };
    $.ajax({
              url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/user/register",
              method: "POST",
              data: _data,
              success: function (response) {
                $('#addUser').modal('hide');
                table.destroy();
                FillDatatable();
                toastr.success(response);
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const removeUser = (id) => {
    $.ajax({
              url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + '/user/delete/' + id,
              method: "POST",
              success: function (response) {
                table.destroy();
                FillDatatable();
                toastr.error("User deleted succesfully!");
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const updateUser = () => {

    const _data = {
      userId: parseInt($("#editUser [name='UserId']").val()),
      name: $("#editUser [name='Name']").val(),
      surname: $("#editUser [name='Surname']").val(),
      email: $("#editUser [name='Email']").val(),
      password: $("#editUser [name='Password']").val(),
      ppPath: $("#editUser [name='UserppPath']").val()
    };

    $.ajax({
              url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + '/user/update',
              method: "POST",
              data: _data,
              success: function (response) {
                $('#editUser').modal('hide');
                table.destroy();
                FillDatatable();
                toastr.info("User updated succesfully!");
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const FillDatatable = () => {

    let _selectedId = 0;
    let _selectedName;
    let _selectedSurname;
    let _selectedEmail;
    let _selectedPassword;
    let _selectedRoleId = 1;
    let _selectedSCreationDate;
    let _selectedUpdateDate;
    let _selectedProfilePath;
    let _selectedIsDeleted = false;

    $.ajax({
      url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + '/user/getAll',
      method: "GET",
      success: function (data) {

        table = $('#User').DataTable({
          data: JSON.parse(data).data,
          bDestroy: true,
          dom: "Bfrtip",
          columns: [
            { title: "User Id", data: "userId" },
            { title: "Name", data: "name" },
            { title: "Surname", data: "surname" },
            { title: "Email", data: "email" },
            { title: "Creation Date", data: "creationDate" },
            { title: "Update Date", data: "updateDate" },
          ],
          select: true,
          buttons: [{
            text: "Delete",
            className: 'btn btn-danger',
            atr: {
              id: 'delete'
            },
            action: function () {
              if (_selectedId == 0)
                alert("Please select a row!");
              else {
                $("#dialog").modal('show');

                $("#confirm").off('click').click(function () {
                  $('#dialog').modal('hide');
                  removeUser(_selectedId);
                });
              }
            }
          },
            {
              text: "Edit",
              className: 'btn btn-secondary',
              atr: {
                id: 'edit'
              },
              action: function () {
                if (_selectedId == 0)
                  alert("Please select a row!");
                else {
                  $("#editUser [name='UserId']").val(_selectedId);
                  $("#editUser [name='Name']").val(_selectedName);
                  $("#editUser [name='Surname']").val(_selectedSurname);
                  $("#editUser [name='Email']").val(_selectedEmail);
                  $("#editUser [name='Password']").val(_selectedPassword);
                  $("#editUser [name='UserppPath']").val(_selectedProfilePath);
                  $("#editUser").modal('show');
                }
              }
            },
            {
              text: "Register",
              className: 'btn btn-primary',
              atr: {
                id: 'register'
              },
              action: function () {
                $("#addUser [name='Name']").val("");
                $("#addUser [name='Surname']").val("");
                $("#addUser [name='Email']").val("");
                $("#addUser [name='Password']").val("");
                $("#addUser").modal('show');
              }
            }
          ]
        }).off("select")
                .on("select", function (e, dt, type, indexes) {
                  _selectedId = dt.data().userId;
                  _selectedName = dt.data().name;
                  _selectedSurname = dt.data().surname;
                  _selectedEmail = dt.data().email;
                  _selectedPassword = dt.data().password;
                  _selectedRoleId = dt.data().roleId;
                  _selectedSCreationDate = dt.data().creationDate;
                  _selectedUpdateDate = dt.data().updateDate;
                  _selectedProfilePath = dt.data().ppPath;
                  _selectedIsDeleted = dt.data().isDeleted;

                });
      }
    });

  }

  const logout = () => {
    $.ajax({
              url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/user/logout",
              method: "GET",
              success: function (response) {
                toastr.success("Successfully logged out!");
                window.location.href = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/jsp/login.jsp"; // redirect
              },
              error: function () {
                alert("error");
              }
            }
    );
  }
</script>

<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables.net-select/1.6.2/dataTables.select.min.js" integrity="sha512-jrKaCKk8AzkQYkQtPSx6e12ScvqooX4lJzVC+w2ewqmJFd/UAkpETT+2MP/sMoJoSAhv1HIVQBm8ku1QaS6jFw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables-buttons/2.3.6/js/dataTables.buttons.min.js" integrity="sha512-hPELv/uqaT+ZbHiKMWXHNV15N6SPTB80TXb9/idOejUqAJZmeLjITlt3Fts8RtCshL/v2kfw7mIKpZnFilDEnA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!-- ========= All Javascript files linkup ======== -->
<script src="admin-dashboard/assets/js/bootstrap.bundle.min.js"></script>
<script src="admin-dashboard/assets/js/main.js"></script>
</body>
</html>