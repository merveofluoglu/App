<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: mehmetsanisoglu
  Date: 13.04.2023
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Message</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->

<table id="Message" class="display" width="100%">
  <thead>
  <tr>
    <th id="MessageId">Message Id</th>
    <th id="CreatorId">Creator Id</th>
    <th id="RecipientId">Recipient Id</th>
    <th id="ParentMessageId">Parent MessageId</th>
    <th id="Subject">Subject</th>
    <th id="MessageBody">Message Body</th>
    <th id="IsRead">Is Read</th>
  </tr>
  </thead>
</table>

<!---------ADD MESSAGE--------->
<div class="modal fade" id="addMessage" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Add Message</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Creator Id: </label>
          <input type="number" name="CreatorId" id="CreatorId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Recipient Id:</label>
          <input type="number" name="RecipientId" id="RecipientId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Parent Message Id:</label>
          <input type="number" name="ParentMessageId" id="ParentMessageId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Subject:</label>
          <input type="text" name="Subject" id="Subject" class="form-control" />
        </div>
        <div class="form-group">
          <label>Message Body:</label>
          <input type="text" name="MessageBody" id="MessageBody" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addMessage()">Add</button>
      </div>

    </div>
  </div>
</div>
<!---------EDIT MESSAGE--------->

<div class="modal fade" id="editMessage" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Edit Post</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Id:</label>
          <input type="text" name="MessageId" id="MessageId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Creator Id: </label>
          <input type="number" name="CreatorId" id="CreatorId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Recipient Id:</label>
          <input type="number" name="RecipientId" id="RecipientId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Parent Message Id:</label>
          <input type="number" name="ParentMessageId" id="ParentMessageId" class="form-control" />
        </div>
        <div class="form-group">
          <label>Subject:</label>
          <input type="text" name="Subject" id="Subject" class="form-control" />
        </div>
        <div class="form-group">
          <label>Message Body:</label>
          <input type="text" name="MessageBody" id="MessageBody" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="updateMessage()">Edit</button>
      </div>
    </div>
  </div>
  </div>
</div>

<!---------DELETE MESSAGE--------->
<div class="modal fade" id="deleteMessage" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Delete Post</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label>Id:</label>
          <input type="text" name="MessageId" id="MessageId" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" id="delete" data-dismiss="modal" onclick="deleteMessage()">Delete</button>
      </div>
    </div>
  </div>
</div>
</div>

<script>
      $(document).ready(function () {
        FillDatatable();
      });

      const addMessage = () => {
        const _data = {
          creator_id: parseInt($("#addMessage [name='CreatorId']").val()),
          recipient_id: parseInt($("#addMessage [name='RecipientId']").val()),
          parent_message_id: parseInt($("#addMessage [name='ParentMessageId']").val()),
          subject: $("#addMessage [name='Subject']").val(),
          message_body: $("#addMessage [name='MessageBody']").val(),
        };
        console.log(_data)
        $.ajax({
                  url: "${pageContext.request.contextPath}/message/add",
                  method: "POST",
                  data: _data,
                  success: function (response) {
                    $('#addMessage').modal('hide');
                    toastr.success("Post added succesfully!");
                  },
                  error: function () {
                    alert("error");
                  }
                }
        );
      }

      const updateMessage = () => {

        const _data = {
          message_id: parseInt($("#editMessage [name='MessageId']").val()),
          creator_id: parseInt($("#editMessage [name='CreatorId']").val()),
          recipient_id: parseInt($("#editMessage [name='RecipientId']").val()),
          parent_message_id: parseInt($("#editMessage [name='ParentMessageId']").val()),
          subject: $("#editMessage [name='Subject']").val(),
          message_body: $("#editMessage [name='MessageBody']").val(),
        };
        console.log("Update data")
        console.log(_data)
        $.ajax({
                  url: '${pageContext.request.contextPath}/message/update',
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

      const deleteMessage = () => {

        const _data = {
          message_id: parseInt($("#deleteMessage [name='MessageId']").val()),
        };
        $.ajax({
                  url: '${pageContext.request.contextPath}/message/delete',
                  method: "POST",
                  data: _data,
                  success: function (response) {
                    $('#editPost').modal('hide');
                    table.destroy();
                    FillDatatable();
                    toastr.info("Post deleted succesfully!");
                  },
                  error: function () {
                    alert("error");
                  }
                }
        );
      }

      const FillDatatable = () => {

        let _selectedId = 0;
        let _selectedCreatorId;
        let _selectedRecipientId;
        let _selectedParentMessageId;
        let _selectedSubject;
        let _selectedMessageBody;
        let _selectedIsRead;

        $.ajax({
          url: '${pageContext.request.contextPath}/message/getAll',
          method: "GET",
          success: function (data) {
            console.log(JSON.parse(data).data)
            table = $('#Message').DataTable({
              data: JSON.parse(data).data,
              bDestroy: true,
              dom: "Bfrtip",
              columns: [
                { title: "Id", data: "message_id" },
                { title: "CreatorId", data: "creator_id" },
                { title: "RecipientId", data: "recipient_id" },
                { title: "ParentMessageId", data: "parent_message_id" },
                { title: "Subject", data: "subject" },
                { title: "MessageBody", data: "message_body" },
                { title: "IsRead", data: "isRead" },

              ],
              select: true,
              buttons: [
                {
                  text: "Add Message",
                  atr: {
                    id: 'add'
                  },
                  action: function () {
                    $("#addMessage [name='CratorId']").val("");
                    $("#addMessage [name='RecipientId']").val("");
                    $("#addMessage [name='ParentMessageId']").val(null);
                    $("#addMessage [name='Subject']").val(null);
                    $("#addMessage [name='MessageBody']").val(null);
                    $("#addMessage").modal('show');
                  }
                }
              ]
            }).off("select")
                    .on("select", function (e, dt, type, indexes) {
                      _selectedId = dt.data().message_id;
                      _selectedCreatorId = dt.data().creator_id;
                      _selectedRecipientId = dt.data().recipient_id;
                      _selectedParentMessageId = dt.data().parent_message_id;
                      _selectedSubject = dt.data().subject;
                      _selectedMessageBody = dt.data().message_body;
                      _selectedIsRead = dt.data().isRead;
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
