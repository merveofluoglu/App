<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Posts</title>

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

<table id="MyOrders" class="display" width="100%">
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



<script>
    $(document).ready(function () {
        getCategories();
        getSubCategories();
        FillDatatable();
    });
    let categories;
    let subcategories;

    getCategories = () => {
        $.ajax({
            method: "GET",
            url: "${pageContext.request.contextPath}/category/getAll",
            success: function (response) {
                categories = JSON.parse(response).data;
            }
        })
    }

    getSubCategories = () => {
        $.ajax({
            method: "GET",
            url: "${pageContext.request.contextPath}/subcategory/getAll",
            success: function (response) {
                subcategories = JSON.parse(response).data;
            }
        })
    }

    getCategoryNameById = (id) => {
        return categories.filter(item => item.category_id == id)[0].category_name;
    }
    getSubCategoryNameById = (id) => {
        return subcategories.filter(item => item.subcategory_id == id)[0].subcategory_name;
    }

    var table;
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
            url: '${pageContext.request.contextPath}/post/myorders',
            method: "GET",
            success: function (data) {
                data = data.data;
                data.forEach(element => {

                    categoryName = getCategoryNameById(element.category_id);
                    subCategoryName = getSubCategoryNameById(element.subcategory_id);

                    element.category_id = categoryName;
                    element.subcategory_id = subCategoryName;
                })
                table = $('#MyOrders').DataTable({
                    data: data,
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
                        { title: "Category", data: "category_id" },
                        { title: "Sub Category", data: "subcategory_id" }
                    ],
                    select: true,
                }).off("select")
                    .on("select", function (e, dt, type, indexes) {
                        _selectedId = dt.data().post_id;
                        _selectedName = dt.data().name;
                        _selectedDescription = dt.data().description;
                        _selectedUserId = dt.data().user_id;
                        _selectedPrice = dt.data().price;
                        _selectedStatus = dt.data().status;
                    });
            },
            error: function(){
                toastr.error("There are no orders of this user!");
                return;
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
