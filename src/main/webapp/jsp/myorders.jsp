<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>My Orders</title>

    <link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
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
                                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/profile.jsp">Back to Profile</a></div>
                            </div>
                        </li>
                        <li class="scroll-to-section-button">
                            <div class="main-button-red-login">
                                <div class="scroll-to-section-button"><a onclick="logout()">Log out</a></div>
                            </div>
                        </li>
                    </ul>
                    <!-- * Menu End * -->
                </nav>
            </div>
        </div>
    </div>
</header>

<!---------DATATABLE--------->
<div class="container-fluid pt-5 pb-3" style="padding-left: 15px; background-color: #f5f5f5">
    <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span>My Orders</span></h2>
    <div class="row px-xl-5" id="order-section">
    </div>
</div>

<div id="postDetails" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2 style="color:white">Details</h2>
        </div>
        <div class="modal-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <img src="" alt="img" id="orderImage" width="500" height="500">
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                <b>Name:</b>
                            </div>
                            <div class="col" id="orderName">
                                name
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Category:</b>
                            </div>
                            <div class="col" id="orderCategoryName">
                                cat
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Subcategory:</b>
                            </div>
                            <div class="col" id="orderSubCategoryName">
                                subcat
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Order Date:</b>
                            </div>
                            <div class="col" id="orderDate">
                                subcat
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Status:</b>
                            </div>
                            <div class="col" id="orderStatus">
                                status
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <b>Description:</b>
                    <p id="orderDescription">....</p>
                </div>
            </div>
        </div>
        <div class="modal-footer">
        </div>
    </div>
</div>

<!---------EDIT POST--------->
<div id="postReview" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="postReviewClose">&times;</span>
            <h2 style="color: white">Rate the Seller</h2>
        </div>
        <div class="modal-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="row" style="display: none">
                            <div class="col">
                                <label>Post Id:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postReviewId" id="postReviewId" class="form-control" readonly="readonly" />
                            </div>
                        </div>
                        <div class="row" style="display: none">
                            <div class="col">
                                <label>User Id:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postReviewUserId" id="postReviewUserId" class="form-control" readonly="readonly" />
                            </div>
                        </div>

                        <div class="row" style="display: none">
                            <div class="col">
                                <label>Seller Id:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postReviewSellerId" id="postReviewSellerId" class="form-control" readonly="readonly" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Comment:</label>
                            </div>
                            <div class="col">
                                <input type="text-area" name="postReviewDescription" id="postReviewDescription" class="form-control" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label>Scale:</label>
                            </div>
                            <div class="col" >
                                <input type="range" name="postReviewEditScale" id="postReviewEditScale" min="0" max="100" value="90" step="10" class="form-control"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="giveReview()">Edit</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>

    let sectionFirst = document.getElementById("order-section").innerHTML;
    $(document).ready(function () {
        getCategories();
        getSubCategories();
        getMyOrders();

    });

    getCategories = () => {
        $.ajax({
            method: "GET",
            url: "${pageContext.request.contextPath}/category/getAll",
            async:false,
            success: function (response) {
                categories = JSON.parse(response).data;
            }
        })
    }

    getSubCategories = () => {
        $.ajax({
            method: "GET",
            url: "${pageContext.request.contextPath}/subcategory/getAllSubCategories",
            success: function (response) {
                subcategories = JSON.parse(response).data;
            }
        })
    }

    getCategoryNameById = (id) => {
        return categories.filter(item => item.categoryId == id)[0].categoryName;
    }
    getSubCategoryNameById = (id) => {
        return subcategories.filter(item => item.subcategoryId == id)[0].subcategoryName;
    }

    var modal = document.getElementById("postDetails");
    var secondmodal = document.getElementById("postReview");
    var span = document.getElementsByClassName("close");

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
        else if(event.target == secondmodal){
            secondmodal.style.display = "none";
        }
    }
    span[0].onclick = function() {
        modal.style.display = "none";
    }
    span[1].onclick = function() {
        secondmodal.style.display = "none";
    }

    const getMyOrders = () => {
        $.ajax({
                url: '${pageContext.request.contextPath}/post/myorders',
                method: "GET",
                success: function (response) {
                    let data = response.data;
                    if(data.length === 0) {
                        window.location.href = '${pageContext.request.contextPath}/jsp/main-page/oops-page.jsp';
                    }
                    let section = document.getElementById("order-section");
                    section.innerHTML = sectionFirst;
                    data.forEach( element => {
                        let lolo = 'data:image/jpeg;base64,'+ element.base64;
                        if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
                            lolo = "images/img.jpg";
                        }
                        //const content = fillContent(element);
                        //section.innerHTML += content;
                        //document.getElementById(element.postId).onclick = function() { openPostDetails(element.postId) }

                        //Main Paren Div
                        const divParent = document.createElement("div");
                        divParent.className = "col-lg-3 col-md-4 col-sm-6 pb-1 img__wrapper";


                        //First Div Child - includes image and icons
                        const divFirstChild = document.createElement("div");
                        divFirstChild.className = "product-item mb-4";
                        divFirstChild.setAttribute("style","border-style: solid; border-color: orange;");

                        //Second Div Child includes image
                        const divSecondChild = document.createElement("div");
                        divSecondChild.className = "product-img position-relative overflow-hidden";
                        const image = document.createElement("img");
                        image.className = "img-fluid w-100";
                        image.setAttribute("src",lolo);
                        image.setAttribute("style","width:300px; height:300px");
                        image.setAttribute("alt","pp.png");


                        //Third Div Child includes icons - Show/Edit/Delete
                        const divThirdChild = document.createElement("div");
                        divThirdChild.className = "product-action";

                        //Link for Show Details
                        const aFirst = document.createElement("a");
                        aFirst.className = "btn btn-outline-dark btn-square";
                        aFirst.setAttribute("style","alignment: absolute");
                        const i = document.createElement("i");
                        i.className = "fa fa-info-circle";
                        i.onclick = function() { openPostDetails(element); };
                        aFirst.append(i);

                        const sSecond = document.createElement("a");
                        sSecond.className = "btn btn-outline-dark btn-square";
                        sSecond.setAttribute("style","alignment: absolute");
                        const iSecond = document.createElement("i");
                        iSecond.className = "fa fa-star";
                        iSecond.onclick = function() { openReview(element); };
                        sSecond.append(iSecond);

                        //Fourth Div Child includes name and price of the post
                        const divFourthChild = document.createElement("div");
                        divFourthChild.className = "text-center py-4";
                        const aSecond = document.createElement("a");

                        //Name of the post
                        aSecond.className = "h6 text-decoration-none text-truncate";
                        aSecond.setAttribute("href","");
                        aSecond.setAttribute("id","`+element.postId+`");
                        aSecond.text = element.name;

                        //Fifth Div Child includes price of the post
                        const divFifthChild = document.createElement("div");
                        divFifthChild.className = "d-flex align-items-center justify-content-center mt-2";
                        //Price of the post
                        const aThird = document.createElement("a");
                        aThird.className = "h6 text-decoration-none text-truncate";
                        aThird.setAttribute("href","");
                        aThird.text = element.price;

                        //Put the price tag into fifth
                        divFifthChild.append(aThird);

                        //Put the name tag into fourth
                        divFourthChild.append(aSecond);

                        //Put the fifth into fourth
                        divFourthChild.append(divFifthChild);

                        //Put icons to the third
                        divThirdChild.append(aFirst);
                        divThirdChild.append(sSecond);

                        //put image to the second
                        divSecondChild.append(image);

                        //put icons to the second
                        divSecondChild.append(divThirdChild);

                        //put second to the first
                        divFirstChild.append(divSecondChild);

                        //put fifth to the first
                        divFirstChild.append(divFourthChild);

                        //put first to the parent
                        divParent.append(divFirstChild);

                        //put parent to the section
                        section.append(divParent);

                    });
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }

    const openPostDetails = (element) => {
        let lolo = 'data:image/jpeg;base64,'+ element.base64;
        if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
            lolo = "images/img.jpg";
        }
        document.getElementById("orderDescription").innerText = element.description;
        document.getElementById("orderName").innerText= element.name;
        document.getElementById("orderCategoryName").innerText = getCategoryNameById(element.categoryId);
        document.getElementById("orderSubCategoryName").innerText = getSubCategoryNameById(element.subcategoryId);
        document.getElementById("orderImage").src = lolo;
        let soldRegex = "Yes";
        if(element.sold == true){
            soldRegex = "Yes";
        }
        else{
            soldRegex = "No";
        }
        document.getElementById("orderStatus").innerText = element.status;
        document.getElementById("orderDate").innerText = element.soldDate;
        modal.style.display = "block";
    }

    const openReview = (element) => {
        document.getElementById("postReviewId").value = element.postId;
        document.getElementById("postReviewUserId").value = element.userId;
        document.getElementById("postReviewSellerId").value = element.customerId;
        secondmodal.style.display = "block";
    }

    const giveReview = () => {
        // Edit the post
        const _data = {
            postId: parseInt($("#postReview [name='postReviewId']").val()),
            userId: parseInt($("#postReview [name='postReviewUserId']").val()),
            sellerId: parseInt($("#postReview [name='postReviewSellerId']").val()),
            description: $("#postReview [name='postReviewDescription']").val(),
            pointScale : parseFloat($("#postReview [name='postReviewEditScale']").val()),
        };

        $.ajax({
                url: '${pageContext.request.contextPath}/review/add',
                method: "POST",
                data: _data,
                success: function (response) {
                    secondmodal.style.display = "none";
                    toastr.info("Review information added sucessfully!");
                    setTimeout(() => {
                        window.location.href = '${pageContext.request.contextPath}/jsp/myorders.jsp';
                    }, 5000);
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