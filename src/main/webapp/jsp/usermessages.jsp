<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Messages</title>

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

<table id="MyMessages" class="display" width="100%">
    <thead>
    <tr>
        <th id="MessageId">Message Id</th>
        <th id="Subject">Subject</th>
        <th id="Body">Message Body</th>
        <th id="CreationDate">Created Time</th>
        <th id="ParentMessageId">Message Flow Id</th>
        <th id="ExpirationDate">Until</th>
        <th id="CreatorId">My Id</th>
        <th id="RecipientId">Sent Who?</th>
        <th id="isRead">Is Read?</th>
    </tr>
    </thead>
</table>



<script>
    $(document).ready(function () {
        FillDatatable();
    });




    var table;
    const FillDatatable = () => {
        $.ajax({
            url: '${pageContext.request.contextPath}/message/usermessages',
            method: "GET",
            success: function (data) {

                data = data.data;
                table = $('#MyMessages').DataTable({
                    data: data,
                    bDestroy: true,
                    dom: "Bfrtip",
                    columns: [
                        { title: "Message Id", data: "message_id" },
                        { title: "Subject", data: "subject" },
                        { title: "Message Body", data: "meassage_body" },
                        { title: "Creation Date", data: "creation_date" },
                        { title: "Message Flow Id", data: "parent_message_id" },
                        { title: "Until", data: "expiration_date" },
                        { title: "Sent Who?", data: "recipient_id" },
                        { title: "My Id", data: "creator_id" },
                        { title: "Is Read?", data: "is_read" }
                    ],
                })
            },
            error: function(){
                toastr.error("There are no messages for this user!");
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
