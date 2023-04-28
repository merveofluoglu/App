<%--suppress ALL --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Permission Operations</title>

    <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->

<table id="permission" class="display" width="100%">
    <thead>
    <tr>
        <th id="PermissionId">Permission Id</th>
        <th id="PermissionName">Name</th>

    </tr>
    </thead>
</table>

<!---------ADD POST--------->

<div class="modal fade" id="addPermission" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Add Permission</h4>
                <button type="button" class="btn-close" target="#addPermission" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                     <label>PermissionName: </label>
                     <input type="text" name="PermissionName" id="PermissionName" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#addPermission" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addPermission()">Add</button>
            </div>

        </div>
    </div>
</div>

<!---------EDIT POST--------->

<div class="modal fade" id="editPermission" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Edit Permission</h4>
                <button type="button" class="btn-close" target="#editPermission" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display:none">
                    <label>PermissionId:</label>
                    <input type="text" name="PermissionId" id="PermissionId" class="form-control" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label>PermissionName: </label>
                    <input type="text" name="PermissionName" id="PermissionName" class="form-control" />
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#editPermission" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updatePermission()">Edit</button>
            </div>

        </div>
    </div>
</div>

<!---------DELETE POST--------->

<div id="dialog" class="modal fade" role="dialog" style="display:none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Delete Permission</h3>
                <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Do you want to delete this permission?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#dialog" data-bs-dismiss="modal">Close</button>
                <button type="button" id="confirm" class="btn btn-danger">Confirm</button>
            </div>
        </div>
    </div>
</div>

    <script>
        $(document).ready(function () {
            FillDatatable();
        });

        var table;

        const addPermission = () => {
            const _data = {
                name: $("#addPermission [name='PermissionName']").val()


            };
            $.ajax({
                    url: '${pageContext.request.contextPath}/permission/add',
                    method: "POST",
                    data: _data,
                    success: function (response) {
                        $('#addPermission').modal('hide');
                        table.destroy();
                        FillDatatable();
                        toastr.success("Permission added succesfully!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

        const removePermission = (id) => {
            $.ajax({
                    url: '${pageContext.request.contextPath}/permission/delete/' + id,
                    method: "POST",
                    success: function (response) {
                        table.destroy();
                        FillDatatable();
                        toastr.error("Permission deleted succesfully!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

        const updatePermission = () => {

            const _data = {

                permission_id: $("#editPermission [name='PermissionId']").val(),
                name: $("#editPermission [name='PermissionName']").val()
            };

            $.ajax({
                    url: '${pageContext.request.contextPath}/permission/update',
                    method: "POST",
                    data: _data,
                    success: function (response) {
                        $('#editPermission').modal('hide');

                        toastr.info("Permission updated succesfully!");
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


            $.ajax({
                url: '${pageContext.request.contextPath}/permission/allpermissions',
                method: "GET",
                success: function (data) {

                    table = $('#permission').DataTable({
                        data: JSON.parse(data).data,
                        bDestroy: true,
                        dom: "Bfrtip",
                        columns: [
                            { title: "PermissionId", data: "permission_id" },
                            { title: "PermissionName", data: "name" },
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
                                        removePermission(_selectedId);
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
                                        $("#editPermission [name='PermissionId']").val(_selectedId);
                                        $("#editPermission [name='PermissionName']").val(_selectedName);
                                        $("#editPermission").modal('show');
                                    }
                                }
                            },
                            {
                                text: "Add Permission",
                                atr: {
                                    id: 'add'
                                },
                                action: function () {
                                    $("#addPermission [name='PermissionId']").val("");
                                    $("#addPermission [name='PermissionName']").val("");
                                    $("#addPermission").modal('show');
                                }
                            }
                        ]
                    }).off("select")
                        .on("select", function (e, dt, type, indexes) {
                            _selectedId = dt.data().permission_id;
                            _selectedName = dt.data().name;

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
