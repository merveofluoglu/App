<%--suppress ALL --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Posts</title>

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
        <th id="CategoryId">Category</th>
        <th id="SubCategoryId">Sub Category</th>
    </tr>
    </thead>
</table>

<!---------ADD POST--------->

<div class="modal fade" id="addPost" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Add Post</h4>
                <button type="button" class="btn-close" target="#addPost" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <label>Price:</label>
                    <input type="number" name="Price" id="Price" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Status:</label>
                    <input type="text" name="Status" id="Status" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Category:</label>
                    <select id="batchSelect" onchange="displaySubCategories()" class="form-control" name="CategoryId" id="CategoryId"></select>
                </div>
                <div class="form-group">
                    <label>Sub Category:</label>
                    <select id="batchSubCategory" class="form-control" name="SubCategoryId" id="SubCategoryId"></select>
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
                <div class="form-group" style="display:none">
                    <label>User Id:</label>
                    <input type="number" name="UserId" id="UserId" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Price:</label>
                    <input type="number" name="Price" id="Price" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Status:</label>
                    <input type="text" name="Status" id="Status" class="form-control" />
                </div>
                <!--
                <div class="form-group">
                    <label>Category:</label>
                    <select id="batchSelect" onchange="displaySubCategories()" class="form-control" name="CategoryId" id="CategoryId"></select>
                </div>
                <div class="form-group">
                    <label>Sub Category:</label>
                    <select id="batchSubCategory" class="form-control" name="SubCategoryId" id="SubCategoryId"></select>
                </div>
                -->
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

<!---------BUY POST--------->

<div id="buyPost" class="modal fade" role="dialog" style="display:none">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Buy Post</h3>
                <button type="button" class="btn-close" target="#buyPost" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Do you want to buy this post?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-toolbar" target="#buyPost" data-bs-dismiss="modal">Close</button>
                <button type="button" id="confirmBuy" class="btn btn-danger">Confirm</button>
            </div>
        </div>
    </div>
</div>


    <script>
        $(document).ready(function () {
            getCategories();
            getSubCategories();
        });

        var table;

        let categories;
        let subcategories;

        getCategories = () => {
            $.ajax({
                method: "GET",
                url: "${pageContext.request.contextPath}/category/getAll",
                success: function (response) {
                    categories = JSON.parse(response).data;
                    displayCategories();
                }
            })
        }

        getSubCategories = () => {
            $.ajax({
                method: "GET",
                url: "${pageContext.request.contextPath}/subcategory/getAllSubCategories",
                success: function (response) {
                    subcategories = JSON.parse(response).data;
                    FillDatatable();
                }
            })
        }

        getCategoryNameById = (id) => {
            return categories.filter(item => item.categoryId == id)[0].categoryName;
        }
        getSubCategoryNameById = (id) => {
            return subcategories.filter(item => item.subcategoryId == id)[0].subcategoryName;
        }

        const addPost = () => {
            const _data = {
                name: $("#addPost [name='Name']").val(),
                description: $("#addPost [name='Description']").val(),
                price: $("#addPost [name='Price']").val(),
                status: $("#addPost [name='Status']").val(),
                categoryId: $("#addPost [name='CategoryId']").val(),
                subcategoryId: $("#addPost [name='SubCategoryId']").val()
            };

            if(!checkValidity(_data)) {
                return;
            }

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
                        toastr.success("Post deleted succesfully!");
                    },
                    error: function (response) {
                        toastr.error("You cannot delete someone else's post!");
                        return;
                    }
                }
            );
        }

        const buyPost = (id) => {
            $.ajax({
                    url: '${pageContext.request.contextPath}/post/buy/' + id,
                    method: "POST",
                    success: function (response) {
                        table.destroy();
                        FillDatatable();
                        toastr.success("Post bought succesfully!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }

        const addFavourite = (id) => {
            $.ajax({
                    url: '${pageContext.request.contextPath}/favourite/add/' + id,
                    method: "POST",
                    success: function (response) {
                        toastr.success("Added to favourites!");
                    },
                    error: function () {
                        alert("error");
                    }
                }
            );
        }


        const updatePost = () => {

            let user_id = '<%= session.getAttribute( "userId" ) %>';

            if(user_id !== $("#editPost [name='UserId']").val()) {
                $('#editPost').modal('hide');
                toastr.error("You cannot update someone else's post!")
                return;
            }

            const _data = {
                postId: parseInt($("#editPost [name='PostId']").val()),
                name: $("#editPost [name='Name']").val(),
                description: $("#editPost [name='Description']").val(),
                userId: $("#editPost [name='UserId']").val(),
                price: $("#editPost [name='Price']").val(),
                status: $("#editPost [name='Status']").val(),
                //categoryId: $("#editPost [name='CategoryId']").val(),
                //subcategoryId: $("#editPost [name='SubCategoryId']").val()
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


        checkValidity = (data) => {

            if(data.name == "" || data.name == null || data.name == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            if(data.description == "" || data.description == null || data.description == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            if(data.price == "" || data.price == null || data.price == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            if(data.status == "" || data.status == null || data.status == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            if(data.categoryId == "" || data.categoryId == null || data.categoryId == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            if(data.subcategoryId == "" || data.subcategoryId == null || data.subcategoryId == undefined) {
                toastr.error("Please fill all sections!");
                return false;
            }

            return true;

        }

        const displayCategories = () => {
            const batchTrack = document.getElementById("batchSelect");

            categories.forEach(option => {
                const newOption = document.createElement("option");
                console.log(option);
                newOption.value = option.categoryId;
                newOption.text = option.categoryName;
                newOption.id = "CategoryIdOpt"
                batchTrack.appendChild(newOption);
            });
        };

        const displaySubCategories = () => {

            removeOptions();

            let categoryId = document.getElementById("batchSelect").value;
            subCat = subcategories.filter(item => item.categoryId == categoryId);

            const batchTrack = document.getElementById("batchSubCategory");

            subCat.forEach(option => {
                const newOption = document.createElement("option");
                console.log(option);
                newOption.value = option.subcategoryId;
                newOption.text = option.subcategoryName;
                newOption.id = "SubCategoryIdOpt"
                batchTrack.appendChild(newOption);
            });
        };

        const removeOptions = () => {
            $("#batchSubCategory").empty();
        }

        const FillDatatable = () => {

            let _selectedId = 0;
            let _selectedName;
            let _selectedDescription;
            let _selectedUserId;
            let _selectedPrice;
            let _selectedStatus;
           // let _selectedCategoryId;
           // let _selectedSubCategoryId;

            $.ajax({
                url: '${pageContext.request.contextPath}/post/getAll',
                method: "GET",
                success: function (data) {
                    data = JSON.parse(data).data;
                    data.forEach(element => {

                        categoryName = getCategoryNameById(element.categoryId);
                        subCategoryName = getSubCategoryNameById(element.subcategoryId);

                        element.categoryId = categoryName;
                        element.subcategoryId = subCategoryName;
                    })
                    table = $('#Post').DataTable({
                        data: data,
                        bDestroy: true,
                        dom: "Bfrtip",
                        columns: [
                            { title: "Id", data: "postId" },
                            { title: "Name", data: "name" },
                            { title: "Description", data: "description" },
                            { title: "User Id", data: "userId" },
                            { title: "Customer Id", data: "customerId" },
                            { title: "Price", data: "price" },
                            { title: "Status", data: "status" },
                            { title: "Category", data: "categoryId" },
                            { title: "Sub Category", data: "subcategoryId" }
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
                                        $("#editPost [name='Price']").val(_selectedPrice);
                                        $("#editPost [name='Status']").val(_selectedStatus);
                                       // $("#editPost [name='CategoryId']").val(null);
                                       // $("#editPost [name='SubCategoryId']").val(null);
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
                                    $("#addPost [name='Price']").val(null);
                                    $("#addPost [name='Status']").val("");
                                    $("#addPost [name='CategoryId']").val(null);
                                    $("#addPost [name='SubCategoryId']").val(null);
                                    $("#addPost").modal('show');
                                }
                            },
                            {
                                text: "Buy",
                                atr: {
                                    id: 'buy'
                                },
                                action: function () {

                                    if (_selectedId == 0)
                                        alert("Please select a row!");
                                    else {
                                        $("#buyPost").modal('show');

                                        $("#confirmBuy").off('click').click(function () {
                                            $('#buyPost').modal('hide');
                                            buyPost(_selectedId);
                                        });
                                    }
                                }
                            },
                            {
                                text: "Add to Favourites",
                                atr: {
                                    id: 'addFav'
                                },
                                action: function () {

                                    if (_selectedId == 0)
                                        alert("Please select a row!");
                                    else {
                                        addFavourite(_selectedId);
                                    }
                                }
                            }
                        ]
                    }).off("select")
                        .on("select", function (e, dt, type, indexes) {
                            _selectedId = dt.data().postId;
                            _selectedName = dt.data().name;
                            _selectedDescription = dt.data().description;
                            _selectedUserId = dt.data().userId;
                            _selectedPrice = dt.data().price;
                            _selectedStatus = dt.data().status;
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
