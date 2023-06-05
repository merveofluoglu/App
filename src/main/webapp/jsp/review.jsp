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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="author" content="damacanan">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/jsp/admin-dashboard/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="../jsp/admin-dashboard/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../jsp/admin-dashboard/assets/css/lineicons.css" />
    <link rel="stylesheet" href="admin-dashboard/assets/css/main.css" />
    <link rel="stylesheet" href="../resources/static/css/mainpage.css">
    <link rel="stylesheet" href="../resources/static/css/owl.css">
    <link rel="stylesheet" href="../resources/static/css/postDetails.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />

</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------DATATABLE--------->
<!-- * Header Area Start * -->
<header class="header-area header-sticky" style="background-color: darkred; position: inherit !important;">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- * Logo Start * -->
                    <a href="${pageContext.request.contextPath}/jsp/profile.jsp" class="logo">
                        DAMACANAN
                    </a>
                    <!-- * Logo End * -->
                    <!-- * Menu Start * -->
                    <ul class="nav">
                        <li class="scroll-to-section-button">
                            <div class="main-button-red-login">
                                <div class="scroll-to-section-button"><a onclick="logout()">Log out</a></div>
                            </div>
                        </li>
                        <li class="scroll-to-section-button">
                            <div class="main-button-red-login">
                                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/profile.jsp">Back to Profile</a></div>
                            </div>
                        </li>
                    </ul>
                    <!-- * Menu End * -->
                </nav>
            </div>
        </div>
    </div>
</header>
<table id="Review" class="display" width="100%">
    <thead>
    <tr>
        <th id="ReviewerName">Reviewer Name</th>
        <th id="ReviewerSurname">Reviewer Surname</th>
        <th id="ReviewerEmail">Reviewer Email</th>
        <th id="PostId">Post Id</th>
        <th id="Description">Description</th>
        <th id="PointScale">Point Scale</th>
    </tr>
    </thead>
</table>

<script>
    $(document).ready(function () {
        getAllUsers();
    });

    var table;
    let user;

    const getAllUsers = () => {
        $.ajax({
            url: '${pageContext.request.contextPath}/user/getAll',
            method: "GET",
            success: function (data) {
                user = JSON.parse(data).data;
                FillDatatable();
            }
        });
    }

    const getUserDataByUserId = (id) => {
        return user.filter(item => item.userId == id)[0];
    }

    const FillDatatable = () => {

        let _selectedId = 0;
        let _selectedUserId;
        let _selectedSellerId;
        let _selectedPostId;
        let _selectedDescription;
        let _selectedPointScale;
        let userName = "";
        let userSurname = "";
        let userEmail = "";

        $.ajax({
            url: '${pageContext.request.contextPath}/review/getAll/${user.userId}',
            method: "GET",
            success: function (data) {
                let _data = data.data;
                _data.forEach(element => {

                    let _user = getUserDataByUserId(element.userId);

                    element.reviewId = _user.name;
                    element.userId = _user.surname;
                    element.sellerId = _user.email;
                })

                table = $('#Review').DataTable({
                    data: _data,
                    bDestroy: true,
                    dom: "Bfrtip",
                    columns: [
                        { title: "Reviewer Name", data: "reviewId"},
                        { title: "Reviewer Surname", data: "userId"},
                        { title: "Reviewer Email", data: "sellerId"},
                        { title: "Post Id", data: "postId" },
                        { title: "Description", data: "description" },
                        { title: "Point Scale", data: "pointScale" }
                    ],
                })
            }
        });

    }
    const logout = () => {
        $.ajax({
                url: window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/user/logout",
                method: "GET",
                success: function (response) {
                    toastr.success("Successfully logged out!");
                    window.location.href = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/jsp/login.jsp"; // redirect
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
