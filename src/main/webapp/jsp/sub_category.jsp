<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: AnilOzfirat
  Date: 4/21/2023
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <title>Get SubCategory</title>

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

<table id="SubCategory" class="display" width="100%">
  <thead>
  <tr>
    <th id="SubCategoryId">Sub Category Id</th>
    <th id="SubCategoryName">Sub Category Name</th>
    <th id="CategoryId">Category Id</th>
  </tr>
  </thead>
</table>


<!---------ADD SubCategory--------->

<div class="modal fade" id="addSubCategory" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Add SubCategory</h4>
        <button type="button" class="btn-close" target="#addESubCategory" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Sub Category Name:</label>
          <input type="text" name="SubCategoryName" id="SubCategoryName" class="form-control" />
        </div>
          <div class="form-group">
            <label>Category Id:</label>
            <input type="number" name="CategoryId" id="CategoryId" class="form-control" />
          </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#addSubCategory" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addSubCategory()">Add</button>
      </div>

    </div>
  </div>
</div>

<!---------EDIT SubCategory--------->

<div class="modal fade" id="editSubCategory" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Edit SubCategory</h4>
        <button type="button" class="btn-close" target="#editSubCategory" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="form-group" style="display:none">
          <label>Id:</label>
          <input type="number" name="SubCategoryId" id="SubCategoryId" class="form-control" readonly="readonly" />
        </div>
        <div class="form-group" style="display: none">
          <label>Id:</label>
          <input type="number" name="CategoryId" id="CategoryId" class="form-control"  />
        </div>
        <div class="form-group">
          <label>Name: </label>
          <input type="text" name="SubCategoryName" id="SubCategoryName" class="form-control" />
        </div>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#editSubCategory" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateSubCategory()">Edit</button>
      </div>

    </div>
  </div>
</div>


<!---------DELETE SubCategory--------->

<div id="dialog" class="modal fade" role="dialog" style="display:none">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title">Delete SubCategory</h3>
        <button type="button" class="btn-close" target="#dialog" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Do you want to delete this subcategory?</p>
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

  const addSubCategory = () => {
    const _data = {
      categoryId: $("#addSubCategory [name='CategoryId']").val(),
      subcategoryName: $("#addSubCategory [name='SubCategoryName']").val()
    };
    $.ajax({
              url: "${pageContext.request.contextPath}/subcategory/add",
              method: "POST",
              data: _data,
              success: function (response) {
                $('#addSubCategory').modal('hide');
                table.destroy();
                FillDatatable();
                toastr.success("Subcategory added succesfully!");
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const removeSubCategory = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/subcategory/delete/' + id,
              method: "POST",
              success: function (response) {
                table.destroy();
                FillDatatable();
                toastr.error("SubCategory deleted succesfully!");
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const updateSubCategory = () => {

    const _data = {
      subcategoryId: $("#editSubCategory [name='SubCategoryId']").val(),
      categoryId: $("#editCategory [name='CategoryId']").val(),
      subcategoryName: $("#editSubCategory [name='SubCategoryName']").val()

    };

    $.ajax({
              url: '${pageContext.request.contextPath}/subcategory/update',
              method: "POST",
              data: _data,
              success: function (response) {
                $('#editSubCategory').modal('hide');
                table.destroy();
                FillDatatable();
                toastr.info("SubCategory updated succesfully!");
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const FillDatatable = () => {

    let _selectedId = 0;
    let _selectedCategoryId;
    let _selectedSubCategoryName;

    $.ajax({
      url: '${pageContext.request.contextPath}/subcategory/getAll',
      method: "GET",
      success: function (data) {

        table = $('#SubCategory').DataTable({
          data: JSON.parse(data).data,
          bDestroy: true,
          dom: "Bfrtip",
          columns: [
            { title: "Sub Category Name", data: "subcategoryName" },
            { title: "Sub Category Id", data: "subcategoryId" },
            { title: "Category Id", data: "categoryId" }
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
                  removeSubCategory(_selectedId);
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
                  $("#editSubCategory [name='SubCategoryName']").val(_selectedSubCategoryName);
                  $("#editSubCategory [name='SubCategoryId']").val(_selectedId);
                  $("#editCategory [name='CategoryId']").val(_selectedCategoryId);
                  $("#editSubCategory").modal('show');
                }
              }
            },
            {
              text: "Add Sub Category",
              atr: {
                id: 'add'
              },
              action: function () {
                $("#addSubCategory [name='SubCategoryName']").val(null);
                $("#addCategory [name='CategoryId']").val(null);
                $("#addSubCategory").modal('show');
              }
            }
          ]
        }).off("select")
                .on("select", function (e, dt, type, indexes) {
                  _selectedId = dt.data().subcategoryId;
                  _selectedCategoryId = dt.data().categoryId;
                  _selectedSubCategoryName = dt.data().subcategoryName;
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


