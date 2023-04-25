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

<div class="modal fade" id="addMessage" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Add Message</h4>
        <button type="button" class="btn-close" target="#addMessage" data-bs-dismiss="modal" aria-label="Close"></button>
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
          <label>Price:</label>
          <input type="number" name="MessageBody" id="MessageBody" class="form-control" />
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#addPost" data-bs-dismiss="modal">Close</button>
        <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addMessage()">Add</button>
      </div>

    </div>
  </div>
</div>

<script>
  const addMessage = () => {
    const _data = {
      creator_id: parseInt($("#addMessage [name='CreatorId']").val()),
      recipient_id: parseInt($("#addMessage [name='RecipientId']").val()),
      parent_message_id: parseInt($("#addMessage [name='ParentMessageId']").val()),
      customer_id: parseInt($("#addMessage [name='CustomerId']").val()),
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
</script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables.net-select/1.6.2/dataTables.select.min.js" integrity="sha512-jrKaCKk8AzkQYkQtPSx6e12ScvqooX4lJzVC+w2ewqmJFd/UAkpETT+2MP/sMoJoSAhv1HIVQBm8ku1QaS6jFw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables-buttons/2.3.6/js/dataTables.buttons.min.js" integrity="sha512-hPELv/uqaT+ZbHiKMWXHNV15N6SPTB80TXb9/idOejUqAJZmeLjITlt3Fts8RtCshL/v2kfw7mIKpZnFilDEnA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</body>
</html>
