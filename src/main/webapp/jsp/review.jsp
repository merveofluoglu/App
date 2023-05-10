<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: gorkemyilmaz
  Date: 21/04/23
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Reviews</title>

    <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->

<table id="Review" class="display" width="100%">
    <thead>
    <tr>
        <th id="ReviewId">Review Id</th>
        <th id="UserId">User Id</th>
        <th id="SellerId">Seller Id</th>
        <th id="PostId">Post Id</th>
        <th id="Description">Description</th>
        <th id="PointScale">Point Scale</th>
    </tr>
    </thead>
</table>

<!---------ADD REVIEW--------->

<div class="modal fade" id="addReview" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="reviewModalLabel">Add Review</h4>
                <button type="button" class="btn-close" target="#addReview" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>User Id:</label>
                    <input type="number" name="UserId" id="UserId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Seller Id:</label>
                    <input type="number" name="SellerId" id="SellerId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Post Id:</label>
                    <input type="number" name="PostId" id="PostId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <input type="text" name="Description" id="Description" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Point Scale:</label>
                    <input type="number" name="PointScale" id="PointScale" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#addReview" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addReview()">Add</button>
            </div>

        </div>
    </div>
</div>
<!---------EDIT REVIEW--------->

<div class="modal fade" id="editReview" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="reviewModalLabel">Edit Review</h4>
                <button type="button" class="btn-close" target="#editReview" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group" style="display:none">
                    <label>Id:</label>
                    <input type="text" name="ReviewId" id="ReviewId" class="form-control" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label>User Id:</label>
                    <input type="number" name="UserId" id="UserId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Seller Id:</label>
                    <input type="number" name="SellerId" id="SellerId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Post Id:</label>
                    <input type="number" name="PostId" id="PostId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <input type="text" name="Description" id="Description" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Point Scale:</label>
                    <input type="number" name="PointScale" id="PointScale" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#editReview" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateReview()">Edit</button>
            </div>

        </div>
    </div>
</div>
<!---------DELETE REVIEW--------->

<div id="dialog" class="modal fade" role="dialog" style="display:none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Delete Review</h3>
                <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Do you want to delete this review?</p>
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

    const addReview = () => {
        const _data = {
            userId: $("#addReview [name='UserId']").val(),
            sellerId: $("#addReview [name='SellerId']").val(),
            postId: $("#addReview [name='PostId']").val(),
            description: $("#addReview [name='Description']").val(),
            pointScale: $("#addReview [name='PointScale']").val()
        };
        $.ajax({
                url: "${pageContext.request.contextPath}/review/add",
                method: "POST",
                data: _data,
                success: function (response) {
                    $('#addReview').modal('hide');
                    table.destroy();
                    FillDatatable();
                    toastr.success("Review added succesfully!");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    const removeReview = (id) => {
        $.ajax({
                url: '${pageContext.request.contextPath}/review/delete/' + id,
                method: "POST",
                success: function (response) {
                    table.destroy();
                    FillDatatable();
                    toastr.error("Review deleted succesfully!");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    const updateReview = () => {

        const _data = {
            reviewId: parseInt($("#editReview [name='ReviewId']").val()),
            userId: $("#editReview [name='UserId']").val(),
            sellerId: $("#editReview [name='SellerId']").val(),
            postId: $("#editReview [name='PostId']").val(),
            description: $("#editReview [name='Description']").val(),
            pointScale: $("#editReview [name='PointScale']").val()
        };

        $.ajax({
                url: '${pageContext.request.contextPath}/review/update',
                method: "POST",
                data: _data,
                success: function (response) {
                    $('#editReview').modal('hide');
                    table.destroy();
                    FillDatatable();
                    toastr.info("Review updated succesfully!");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    const FillDatatable = () => {

        let _selectedId = 0;
        let _selectedUserId;
        let _selectedSellerId;
        let _selectedPostId;
        let _selectedDescription;
        let _selectedPointScale;

        $.ajax({
            url: '${pageContext.request.contextPath}/review/getAll',
            method: "GET",
            success: function (data) {

                table = $('#Review').DataTable({
                    data: JSON.parse(data).data,
                    bDestroy: true,
                    dom: "Bfrtip",
                    columns: [
                        { title: "Id", data: "reviewId" },
                        { title: "User Id", data: "userId" },
                        { title: "Seller Id", data: "sellerId" },
                        { title: "Post Id", data: "postId" },
                        { title: "Description", data: "description" },
                        { title: "Point Scale", data: "pointScale" }
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
                                    removeReview(_selectedId);
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
                                    $("#editReview [name='ReviewId']").val(_selectedId);
                                    $("#editReview [name='UserId']").val(_selectedUserId);
                                    $("#editReview [name='SellerId']").val(_selectedSellerId);
                                    $("#editReview [name='PostId']").val(_selectedPostId);
                                    $("#editReview [name='Description']").val(_selectedDescription);
                                    $("#editReview [name='PointScale']").val(_selectedPointScale);
                                    $("#editReview").modal('show');
                                }
                            }
                        },
                        {
                            text: "Add Review",
                            atr: {
                                id: 'add'
                            },
                            action: function () {
                                $("#addPost [name='UserId']").val(null);
                                $("#addPost [name='SellerId']").val(null);
                                $("#addPost [name='PostId']").val(null);
                                $("#addPost [name='Description']").val("");
                                $("#addPost [name='Point Scale']").val("");
                                $("#addPost").modal('show');
                            }
                        }
                    ]
                }).off("select")
                    .on("select", function (e, dt, type, indexes) {
                        _selectedId = dt.data().reviewId;
                        _selectedUserId = dt.data().userId;
                        _selectedSellerId = dt.data().sellerId;
                        _selectedPostId = dt.data().postId;
                        _selectedDescription = dt.data().description;
                        _selectedPointScale = dt.data().pointScale;
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
