<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Categories</title>

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

<table id="Category" class="display" width="100%">
    <thead>
    <tr>
        <th id="CategoryId">Category Id</th>
        <th id="CategoryName">Category Name</th>
    </tr>
    </thead>
</table>

<!---------Add Category--------->

<div class="modal fade" id="addCategory" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Add Category</h4>
                <button type="button" class="btn-close" target="#addCategory" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Category Name:</label>
                    <input type="text" name="CategoryName" id="CategoryName" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#addCategory" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addCategory()">Add</button>
            </div>

        </div>
    </div>
</div>

<!---------Edit Category--------->

<div class="modal fade" id="editCategory" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Edit Category</h4>
                <button type="button" class="btn-close" target="#editCategory" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" >
                <div class="form-group" style="display:none">
                    <label>Category Id: </label>
                    <input type="text" name="CategoryId" id="CategoryId" class="form-control" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Category Name:</label>
                    <input type="text" name="CategoryName" id="CategoryName" class="form-control" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#editCategory" data-bs-dismiss="modal">Close</button>
                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateCategory()">Edit</button>
            </div>

        </div>
    </div>
</div>

<!---------Delete Category--------->

<div id="dialog" class="modal fade" role="dialog" style="display:none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Delete Category</h3>
                <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Do you want to delete this category?</p>
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
    const addCategory = () => {
        const _data = {
            categoryName: $("#addCategory [name='CategoryName']").val(),
        };

        if(!checkValidity(_data)) {
            return;
        }

        $.ajax({
                url: "${pageContext.request.contextPath}/category/add",
                method: "POST",
                data: _data,
                success: function (response) {
                    $('#addCategory').modal('hide');
                    table.destroy();
                    FillDatatable();
                    toastr.success("Category Creation Successfully!");
                },
                error: function (response) {
                    toastr.error(response.statusText);
                }
            }
        );
    }

    const removeCategory = (id) => {
        $.ajax({
                url: '${pageContext.request.contextPath}/category/delete/' + id,
                method: "POST",
                success: function (response) {
                    table.destroy();
                    FillDatatable();
                    toastr.error("Category Deleting Successfully");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    const updateCategory = () => {

        const _data = {
            categoryId: $("#editCategory [name='CategoryId']").val(),
            categoryName: $("#editCategory [name='CategoryName']").val(),
        };

        if(!checkValidity(_data)) {
            return;
        }

        $.ajax({
                url: '${pageContext.request.contextPath}/category/update',
                method: "POST",
                data: _data,
                success: function (response) {
                    $('#editCategory').modal('hide');
                    table.destroy();
                    FillDatatable();
                    toastr.info("Category Updating Successfully");
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    checkValidity = (data) => {
        if(data.categoryName == "" || data.categoryName == null || data.categoryName == undefined) {
            toastr.error("Please fill all sections!");
            return false;
        }
        return true;
    }

    const FillDatatable = () => {

        let _selectedId = 0;
        let _selectedCategoryName;

        $.ajax({
            url: '${pageContext.request.contextPath}/category/getAll',
            method: "GET",
            success: function (data) {

                table = $('#Category').DataTable({
                    data: JSON.parse(data).data,
                    bDestroy: true,
                    dom: "Bfrtip",
                    columns: [
                        { title: "Id", data: "categoryId" },
                        { title: "Category Name", data: "categoryName" },
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
                                    removeCategory(_selectedId);
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
                                    $("#editCategory [name='CategoryId']").val(_selectedId);
                                    $("#editCategory [name='CategoryName']").val(_selectedCategoryName);
                                    $("#editCategory").modal('show');
                                }
                            }
                        },
                        {
                            text: "Add Category",
                            atr: {
                                id: 'add'
                            },
                            action: function () {
                                $("#addCategory [name='CategoryName']").val("");
                                $("#addCategory").modal('show');
                            }
                        }
                    ]
                }).off("select")
                    .on("select", function (e, dt, type, indexes) {
                        _selectedId = dt.data().categoryId;
                        _selectedCategoryName = dt.data().categoryName;
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
