<%@ page import="java.util.Objects" %><%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>My Posts</title>
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
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.10/dist/sweetalert2.min.css" rel="stylesheet">

</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!---------EDIT POST--------->
<div id="editPost" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="editPostClose">&times;</span>
            <h2 style="color: white">Edit Post Details</h2>
        </div>
        <div class="modal-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <img src="" alt="img" id="postEditImage" width="500" height="500">
                    </div>
                    <div class="col">
                        <div class="row" style="display: none">
                            <div class="col">
                                <label>Post Id:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postEditId" id="postEditId" class="form-control" readonly="readonly" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Name:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postEditName" id="postEditName" class="form-control" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label>Description:</label>
                            </div>
                            <div class="col">
                                <input type="text" name="postEditDescription" id="postEditDescription" class="form-control"/>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label>Price:</label>
                            </div>
                            <div class="col" >
                                <input type="number" name="postEditPrice" id="postEditPrice" class="form-control"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <button class="btn btn-primary" id="edit" data-dismiss="modal" onclick="editPost()">Edit</button>
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
<!-- The Post Details Modal -->
<div id="postDetails" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2 style="color: white">Details</h2>
        </div>
        <div class="modal-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <img src="" alt="img" id="postImage" width="500" height="500">
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                <b>Name:</b>
                            </div>
                            <div class="col" id="postName">
                                name
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Category:</b>
                            </div>
                            <div class="col" id="postCategoryName">
                                cat
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Subcategory:</b>
                            </div>
                            <div class="col" id="postSubCategoryName">
                                subcat
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Price:</b>
                            </div>
                            <div class="col" id="postPrice">
                                name
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Status:</b>
                            </div>
                            <div class="col" id="postStatus">
                                status
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <b>Creation Date:</b>
                            </div>
                            <div class="col" id="postCreationDate">
                                create date
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col">
                    <b>Description: </b>
                    <p id="postDescription">Some other text...</p>
                </div>
            </div>

        </div>
        <div class="modal-footer">
        </div>
    </div>
</div>
<!-- ** Header Area Start ** -->
<header class="header-area header-sticky" style="background-color: darkred; position: inherit !important;">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ** Logo Start ** -->
                    <a href="${pageContext.request.contextPath}/jsp/main-page/mainpage.jsp" class="logo">
                        DAMACANAN
                    </a>
                    <!-- ** Logo End ** -->
                    <!-- ** Menu Start ** -->
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
                    <!-- ** Menu End ** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<div class="container-fluid pt-5 pb-3" style="padding-left: 15px; background-color: #f5f5f5">
    <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span>My Posts</span></h2>
    <div class="row px-xl-5" id="posts-section">
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
    let sectionFirst = document.getElementById("posts-section").innerHTML;
    $(document).ready(function () {
        getCategories();
        getSubCategories();
        getMyPosts();

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

    // Get the modal
    var modal = document.getElementById("postDetails");
    var secondmodal = document.getElementById("editPost");
    var span = document.getElementsByClassName("close");
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
        else if(event.target == secondmodal){
            secondmodal.style.display = "none";
        }
    }
    span[0].onclick = function() {
        secondmodal.style.display = "none";
    }
    span[1].onclick = function() {
        modal.style.display = "none";
    }


    const getMyPosts = () => {

        $.ajax({
                url: "${pageContext.request.contextPath}/post/myposts",
                method: "GET",
                success: function (response) {
                    let data = response.data;
                    data = data.reverse();
                    let section = document.getElementById("posts-section");
                    section.innerHTML = sectionFirst;
                    data.forEach( element=> {

                        if (element.status == "Requested"){
                            let lolo = 'data:image/jpeg;base64,'+ element.base64;
                            if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
                                lolo = "images/img.jpg";
                            }
                            //const content = fillContent(element);
                            //section.innerHTML += content;
                            //document.getElementById(element.postId).onclick = function() { openPostDetails(element.postId) }
                            /*
                            const divAnimatedFirst = document.createElement("div");
                            divAnimatedFirst.className = "relative group border border-secondary overflow-hidden";
                            const divAnimatedSecond = document.createElement("div");
                            divAnimatedSecond.className = "w-96 h-96 bg-top bg-cover transition duration-700 group-hover:-translate-y-full flex flex-col justify-end items-center card-bounce";
                            */

                            //Main Paren Div
                            const divParent = document.createElement("div");
                            divParent.className = "col-lg-3 col-md-4 col-sm-6 pb-1 group duration-75 group-hover:-translate-y-full flex flex-col justify-end items-center card-bounce img__wrapper";
                            const aDiagonal = document.createElement("a");
                            aDiagonal.className = "requested ";
                            aDiagonal.innerText = 'Requested';
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

                            //Link for Edit post
                            const aFourth = document.createElement("a");
                            aFourth.className = "btn btn-outline-dark btn-square";
                            aFourth.setAttribute("style","alignment: absolute");
                            const isecond = document.createElement("i");
                            isecond.className = "fa fa-edit";
                            isecond.onclick = function() { updatePost(element); };
                            aFourth.append(isecond);

                            //Link for delete post
                            const aFifth = document.createElement("a");
                            aFifth.className = "btn btn-outline-dark btn-square";
                            aFifth.setAttribute("style","alignment: absolute");
                            const ithird = document.createElement("i");
                            ithird.className = "fa fa-trash";
                            ithird.onclick = function() { openSwal(element.postId); };
                            aFifth.append(ithird);

                            const aSixth = document.createElement("a");
                            aSixth.className = "btn btn-outline-dark btn-square";
                            aSixth.setAttribute("style","alignment: absolute");
                            const ifourth = document.createElement("i");
                            ifourth.className = "fa fa-bell";
                            ifourth.onclick = function() { openSwalSecond(element.postId); };
                            aSixth.append(ifourth);

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
                            divThirdChild.append(aFourth);
                            divThirdChild.append(aFifth);
                            divThirdChild.append(aSixth);

                            //put image to the second
                            divSecondChild.append(image);

                            //put icons to the second
                            divSecondChild.append(divThirdChild);

                            //put second to the first
                            divFirstChild.append(divSecondChild);

                            //put fifth to the first
                            divFirstChild.append(divFourthChild);

                            divParent.append(aDiagonal);
                            //put first to the parent
                            divParent.append(divFirstChild);
                            /*
                            divAnimatedSecond.append(divParent);

                            divAnimatedFirst.append(divAnimatedSecond);
                            */
                            //put parent to the section
                            section.append(divParent);
                        }
                        else if(element.status == 'Sold'){
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
                            const aDiagonal = document.createElement("a");
                            aDiagonal.className = "sold_out ";
                            aDiagonal.innerText = 'Sold';

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

                            //put image to the second
                            divSecondChild.append(image);

                            //put icons to the second
                            divSecondChild.append(divThirdChild);

                            //put second to the first
                            divFirstChild.append(divSecondChild);

                            //put fifth to the first
                            divFirstChild.append(divFourthChild);

                            divParent.append(aDiagonal);
                            //put first to the parent
                            divParent.append(divFirstChild);

                            //put parent to the section
                            section.append(divParent);

                        }
                        else{
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

                            const aDiagonal = document.createElement("a");
                            aDiagonal.className = "available ";
                            aDiagonal.innerText = 'Available';

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

                            //Link for Edit post
                            const aFourth = document.createElement("a");
                            aFourth.className = "btn btn-outline-dark btn-square";
                            aFourth.setAttribute("style","alignment: absolute");
                            const isecond = document.createElement("i");
                            isecond.className = "fa fa-edit";
                            isecond.onclick = function() { updatePost(element); };
                            aFourth.append(isecond);

                            //Link for delete post
                            const aFifth = document.createElement("a");
                            aFifth.className = "btn btn-outline-dark btn-square";
                            aFifth.setAttribute("style","alignment: absolute");
                            const ithird = document.createElement("i");
                            ithird.className = "fa fa-trash";
                            ithird.onclick = function() { openSwal(element.postId); };
                            aFifth.append(ithird);

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
                            divThirdChild.append(aFourth);
                            divThirdChild.append(aFifth);

                            //put image to the second
                            divSecondChild.append(image);

                            //put icons to the second
                            divSecondChild.append(divThirdChild);

                            //put second to the first
                            divFirstChild.append(divSecondChild);

                            //put fifth to the first
                            divFirstChild.append(divFourthChild);

                            divParent.append(aDiagonal);
                            //put first to the parent
                            divParent.append(divFirstChild);

                            //put parent to the section
                            section.append(divParent);
                        }

                    });

                },
                error: function () {

                }
            }
        );
    }

    openSwal = (id) => {
        Swal.fire({
            title: 'Are you sure to delete this post?',
            text: "Please, select confirm or cancel",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Confirm'
        }).then((result) => {
            if (result.isConfirmed) {
                deletePost(id)
            }
        })
    }

    openSwalSecond = (id) => {
        Swal.fire({
            title: 'Do you want ot accept the purchase request?',
            text: "Please, select Accept or Reject",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Accept',
            cancelButtonText: 'Reject'
        }).then((result) => {
            if (result.isConfirmed) {
                acceptRequest(id)
            }
            else if(result.dismiss == "cancel"){
                rejectRequest(id)
            }
            else{
                secondmodal.style.display = "none";
            }
        })
    }
    const updatePost = (element) => {
        let lolo = 'data:image/jpeg;base64,'+ element.base64;
        if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
            lolo = "images/img.jpg";
        }
        document.getElementById("postEditImage").src = lolo;
        document.getElementById("postEditId").value = element.postId;
        document.getElementById("postEditName").value= element.name;
        document.getElementById("postEditPrice").value = element.price;
        document.getElementById("postEditDescription").value = element.description;
        secondmodal.style.display = "block";

    }

    const editPost = () => {
        // Edit the post
        const _data = {
            postId: parseInt($("#editPost [name='postEditId']").val()),
            name: $("#editPost [name='postEditName']").val(),
            description: $("#editPost [name='postEditDescription']").val(),
            price: $("#editPost [name='postEditPrice']").val(),

        };

        $.ajax({
                url: '${pageContext.request.contextPath}/post/update',
                method: "POST",
                data: _data,
                success: function (response) {
                    secondmodal.style.display = "none";
                    toastr.info("Post information updated sucessfully!");
                    setTimeout(() => {
                        window.location.href = '${pageContext.request.contextPath}/jsp/myposts.jsp';
                    }, 5000);
                },
                error: function () {
                    alert("error");
                }
            }
        );

    }

    const deletePost = (id) => {
        // Delete the post
        $.ajax({
                url: "${pageContext.request.contextPath}/post/delete/"+id,
                method: "POST",
                success: function (response) {
                    toastr.info("Post deleted succesfully!");
                    setTimeout(() => {
                        window.location.href = '${pageContext.request.contextPath}/jsp/myposts.jsp';
                    }, 2000);
                },
                error: function (data) {
                    toastr.error(JSON.parse(data.responseText).error.message);
                }
            }
        );
    }

    const acceptRequest = (id) => {
        // Delete the post
        $.ajax({
                url: "${pageContext.request.contextPath}/post/acceptRequest/"+id,
                method: "POST",
                success: function (response) {
                    toastr.info("Purchase request accepted sucessfully!");
                    setTimeout(() => {
                        window.location.href = '${pageContext.request.contextPath}/jsp/myposts.jsp';
                    }, 5000);
                },
                error: function (data) {
                    toastr.error(JSON.parse(data.responseText).error.message);
                }
            }
        );
    }

    const rejectRequest = (id) => {
        // Delete the post
        $.ajax({
                url: "${pageContext.request.contextPath}/post/rejectRequest/"+id,
                method: "POST",
                success: function (response) {
                    toastr.info("Purchase request rejected sucessfully!");
                    setTimeout(() => {
                        window.location.href = '${pageContext.request.contextPath}/jsp/myposts.jsp';
                    }, 5000);

                },
                error: function (data) {
                    toastr.error(JSON.parse(data.responseText).error.message);
                }
            }
        );
    }

    const openPostDetails = (element) => {
        let lolo = 'data:image/jpeg;base64,'+ element.base64;
        if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
            lolo = "images/img.jpg";
        }
        document.getElementById("postDescription").innerText = element.description;
        document.getElementById("postName").innerText= element.name;
        document.getElementById("postCategoryName").innerText = getCategoryNameById(element.categoryId);
        document.getElementById("postSubCategoryName").innerText = getSubCategoryNameById(element.subcategoryId);
        document.getElementById("postImage").src = lolo;
        let soldRegex = "Yes";
        if(element.sold == true){
            soldRegex = "Yes";
        }
        else{
            soldRegex = "No";
        }
        document.getElementById("postStatus").innerText = element.status;
        document.getElementById("postCreationDate").innerText = element.startDate;
        document.getElementById("postPrice").innerText = element.price;

        modal.style.display = "block";
    }

    const logout = () => {
        $.ajax({
                url: "${pageContext.request.contextPath}/user/logout",
                method: "GET",
                success: function (response) {
                    toastr.success("Successfully logged out!");
                    window.location.href = "${pageContext.request.contextPath}/jsp/login.jsp"; // redirect
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.10/dist/sweetalert2.all.min.js"></script>

</body>
</html>