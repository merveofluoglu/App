<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: cemtabarmacbook
  Date: 20.04.2023
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Operations</title>

  <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->

<table id="User" class="display" width="100%">
  <thead>
  <tr>
    <th id="UserId">User Id</th>
    <th id="Name">Name</th>
    <th id="Surname">Surname</th>
    <th id="Email">Email</th>
    <th id="Password">Password</th>
    <th id="RoleId">Role Id</th>
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
        <div class="form-group">
          <label>Profile Path: </label>
          <input type="text" name="ProfilePath" id="ProfilePath" class="form-control" />
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
        <div class="form-group">
          <label>Profile Path:</label>
          <input type="text" name="ProfilePath" id="ProfilePath" class="form-control"/>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#editUser" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateUser()">Edit</button>
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

<!---------LOGIN--------->
<div class="modal fade" id="login" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Login</h4>
        <button type="button" class="btn-close" target="#loginEUser" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Email: </label>
          <input type="text" name="Email" id="Email" class="form-control" />
        </div>
        <div class="form-group">
          <label>Password: </label>
          <input type="text" name="Password" id="Password" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#login" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-success" id="login" data-dismiss="modal" onclick="login()">Login</button>
      </div>

    </div>
  </div>
</div>

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
        pp_path: $("#addUser [name='ProfilePath']").val()
      };
      $.ajax({
                url: "${pageContext.request.contextPath}/user/register",
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

    const login = () => {
      const _data = {
        email: $("#login [name='Email']").val(),
        password: $("#login [name='Password']").val(),
      };
      $.ajax({
                url: "${pageContext.request.contextPath}/user/login",
                method: "POST",
                data: _data,
                success: function (response) {
                  $('#login').modal('hide');
                  table.destroy();
                  FillDatatable();
                  toastr.success("Logged in succesfully!");
                },
                error: function () {
                  alert("error");
                }
              }
      );
    }

    const removeUser = (id) => {
      $.ajax({
                url: '${pageContext.request.contextPath}/user/delete/' + id,
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
        user_id: parseInt($("#editUser [name='UserId']").val()),
        name: $("#editUser [name='Name']").val(),
        surname: $("#editUser [name='Surname']").val(),
        email: $("#editUser [name='Email']").val(),
        password: $("#editUser [name='Password']").val(),
        pp_path: $("#editUser [name='UserppPath']").val()
      };

      $.ajax({
                url: '${pageContext.request.contextPath}/user/update',
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
        url: '${pageContext.request.contextPath}/user/getAll',
        method: "GET",
        success: function (data) {

          table = $('#User').DataTable({
            data: JSON.parse(data).data,
            bDestroy: true,
            dom: "Bfrtip",
            columns: [
              { title: "User Id", data: "userID" },
              { title: "Name", data: "name" },
              { title: "Surname", data: "surname" },
              { title: "Email", data: "email" },
              { title: "Password", data: "password" },
              { title: "Role Id", data: "role_id" },
              { title: "Creation Date", data: "creation_date" },
              { title: "Update Date", data: "update_date" },
            ],
            select: true,
            buttons: [{
              text: "Delete",
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
                    $("#editUser [name='RoleId']").val(1);
                    $("#editUser [name='CreationDate']").val(_selectedSCreationDate);
                    $("#editUser [name='UserUpdateDate']").val(_selectedUpdateDate);
                    $("#editUser [name='UserUpdateDate']").val(_selectedUpdateDate);
                    $("#editUser [name='UserppPath']").val(_selectedProfilePath);
                    $("#editUser").modal('show');
                  }
                }
              },
              {
                text: "Add User",
                atr: {
                  id: 'register'
                },
                action: function () {
                  $("#addUser [name='Name']").val("");
                  $("#addUser [name='Surname']").val("");
                  $("#addUser [name='Email']").val("");
                  $("#addUser [name='Password']").val("");
                  $("#addUser [name='ProfilePath']").val("");
                  $("#addUser").modal('show');
                }
              },
              {
                text: "Login",
                atr: {
                  id: 'login'
                },
                action: function () {
                  $("#login [name='Email']").val("");
                  $("#login [name='Password']").val("");
                  $("#login").modal('show');
                }
              },


            ]
          }).off("select")
                  .on("select", function (e, dt, type, indexes) {
                    _selectedId = dt.data().userID;
                    _selectedName = dt.data().name;
                    _selectedSurname = dt.data().surname;
                    _selectedEmail = dt.data().email;
                    _selectedPassword = dt.data().password;
                    _selectedRoleId = dt.data().role_id;
                    _selectedSCreationDate = dt.data().creation_date;
                    _selectedUpdateDate = dt.data().update_date;
                    _selectedProfilePath = dt.data().pp_path;
                    _selectedIsDeleted = dt.data().is_deleted;

                  });
        }
      });

    }



  </script>

<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables.net-select/1.6.2/dataTables.select.min.js" integrity="sha512-jrKaCKk8AzkQYkQtPSx6e12ScvqooX4lJzVC+w2ewqmJFd/UAkpETT+2MP/sMoJoSAhv1HIVQBm8ku1QaS6jFw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables-buttons/2.3.6/js/dataTables.buttons.min.js" integrity="sha512-hPELv/uqaT+ZbHiKMWXHNV15N6SPTB80TXb9/idOejUqAJZmeLjITlt3Fts8RtCshL/v2kfw7mIKpZnFilDEnA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
