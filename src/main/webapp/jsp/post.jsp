<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11.04.2023
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Posts</title>

    <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->

<table id="Post" class="display" width="100%">
    <thead>
    <tr>
        <th id="PostId">Id</th>
        <th id="Name">Name</th>
        <th id="Description">Description</th>
        <th id="UserId">User Id</th>
        <th id="CustomerId">Customer Id</th>
        <th id="Price">Price</th>
        <th id="Status">Status</th>
        <th id="CategoryId">Category Id</th>
        <th id="SubCategoryId">Sub Category Id</th>
    </tr>
    </thead>
</table>

<!---------ADD POST--------->

<div class="modal fade" id="addPost" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Add Post</h4>
                <button type="button" class="btn-close" target="#addEPost" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Name: </label>
                    <input type="text" name="Name" id="Name" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <input type="text" name="Description" id="Description" class="form-control" />
                </div>
                <div class="form-group">
                    <label>User Id:</label>
                    <input type="number" name="UserId" id="UserId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Customer Id:</label>
                    <input type="number" name="CustomerId" id="CustomerId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" name="Price" id="Price" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Status:</label>
                    <input type="text" name="Status" id="Status" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Category Id:</label>
                    <input type="number" name="CategoryId" id="CategoryId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Sub Category Id:</label>
                    <input type="number" name="SubCategoryId" id="SubCategoryId" class="form-control" />
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#addPost" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addPost()">Add</button>
            </div>

        </div>
    </div>
</div>

<!---------EDIT POST--------->

<div class="modal fade" id="editPost" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Edit Post</h4>
                <button type="button" class="btn-close" target="#editPost" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display:none">
                    <label>Id:</label>
                    <input type="text" name="PostId" id="PostId" class="form-control" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label>Name: </label>
                    <input type="text" name="Name" id="Name" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <input type="text" name="Description" id="Description" class="form-control" />
                </div>
                <div class="form-group">
                    <label>User Id:</label>
                    <input type="number" name="UserId" id="UserId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Customer Id:</label>
                    <input type="number" name="CustomerId" id="CustomerId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" name="Price" id="Price" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Status:</label>
                    <input type="text" name="Status" id="Status" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Category Id:</label>
                    <input type="number" name="CategoryId" id="CategoryId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Sub Category Id:</label>
                    <input type="number" name="SubCategoryId" id="SubCategoryId" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#editPost" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updatePost()">Edit</button>
            </div>

        </div>
    </div>
</div>

<!---------DELETE POST--------->

<div id="dialog" class="modal fade" role="dialog" style="display:none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Delete Post</h3>
                <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Do you want to delete this post?</p>
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

        const addPost = () => {
            const _data = {
                name: $("#addPost [name='Name']").val(),
                description: $("#addPost [name='Description']").val(),
                user_id: $("#addPost [name='UserId']").val(),
                customer_id: $("#addPost [name='CustomerId']").val(),
                price: $("#addPost [name='Price']").val(),
                status: $("#addPost [name='Status']").val(),
                category_id: $("#addPost [name='CategoryId']").val(),
                subcategory_id: $("#addPost [name='SubCategoryId']").val()
            };
            $.ajax({
                    url: "${pageContext.request.contextPath}/post/add",
                    method: "POST",
                    data: _data,
                    success: function (response) {
                        $('#addPost').modal('hide');
                        table.destroy();
                        FillDatatable();
                        toastr.success("Post added succesfully!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

        const removePost = (id) => {
            $.ajax({
                    url: '${pageContext.request.contextPath}/post/delete/' + id,
                    method: "POST",
                    success: function (response) {
                        table.destroy();
                        FillDatatable();
                        toastr.error("Post deleted succesfully!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

        const updatePost = () => {

            const _data = {
                post_id: parseInt($("#editPost [name='PostId']").val()),
                name: $("#editPost [name='Name']").val(),
                description: $("#editPost [name='Description']").val(),
                user_id: $("#editPost [name='UserId']").val(),
                customer_id: $("#editPost [name='CustomerId']").val(),
                price: $("#editPost [name='Price']").val(),
                status: $("#editPost [name='Status']").val(),
                category_id: $("#editPost [name='CategoryId']").val(),
                subcategory_id: $("#editPost [name='SubCategoryId']").val()
            };

            $.ajax({
                    url: '${pageContext.request.contextPath}/post/update',
                    method: "POST",
                    data: _data,
                    success: function (response) {
                        $('#editPost').modal('hide');
                        table.destroy();
                        FillDatatable();
                        toastr.info("Post updated succesfully!");
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
            let _selectedDescription;
            let _selectedUserId;
            let _selecteCustomerId;
            let _selectedPrice;
            let _selectedStatus;
            let _selectedCategoryId;
            let _selectedSubCategoryId;

            $.ajax({
                url: '${pageContext.request.contextPath}/post/getAll',
                method: "GET",
                success: function (data) {

                    table = $('#Post').DataTable({
                        data: JSON.parse(data).data,
                        bDestroy: true,
                        dom: "Bfrtip",
                        columns: [
                            { title: "Id", data: "post_id" },
                            { title: "Name", data: "name" },
                            { title: "Description", data: "description" },
                            { title: "User Id", data: "user_id" },
                            { title: "Customer Id", data: "customer_id" },
                            { title: "Price", data: "price" },
                            { title: "Status", data: "status" },
                            { title: "Category Id", data: "category_id" },
                            { title: "Sub Category Id", data: "subcategory_id" }
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
                                        removePost(_selectedId);
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
                                        $("#editPost [name='PostId']").val(_selectedId);
                                        $("#editPost [name='Name']").val(_selectedName);
                                        $("#editPost [name='Description']").val(_selectedDescription);
                                        $("#editPost [name='UserId']").val(_selectedUserId);
                                        $("#editPost [name='CustomerId']").val(_selecteCustomerId);
                                        $("#editPost [name='Price']").val(_selectedPrice);
                                        $("#editPost [name='Status']").val(_selectedStatus);
                                        $("#editPost [name='CategoryId']").val(_selectedCategoryId);
                                        $("#editPost [name='SubCategoryId']").val(_selectedSubCategoryId);
                                        $("#editPost").modal('show');
                                    }
                                }
                            },
                            {
                                text: "Add Post",
                                atr: {
                                    id: 'add'
                                },
                                action: function () {
                                    $("#addPost [name='Name']").val("");
                                    $("#addPost [name='Description']").val("");
                                    $("#addPost [name='UserId']").val(null);
                                    $("#addPost [name='CustomerId']").val(null);
                                    $("#addPost [name='Price']").val(null);
                                    $("#addPost [name='Status']").val("");
                                    $("#addPost [name='CategoryId']").val(null);
                                    $("#addPost [name='SubCategoryId']").val(null);
                                    $("#addPost").modal('show');
                                }
                            }
                        ]
                    }).off("select")
                        .on("select", function (e, dt, type, indexes) {
                            _selectedId = dt.data().post_id;
                            _selectedName = dt.data().name;
                            _selectedDescription = dt.data().description;
                            _selectedUserId = dt.data().user_id;
                            _selecteCustomerId = dt.data().customer_id;
                            _selectedPrice = dt.data().price;
                            _selectedStatus = dt.data().status;
                            _selectedCategoryId = dt.data().category_id;
                            _selectedSubCategoryId = dt.data().subcategory_id;
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
