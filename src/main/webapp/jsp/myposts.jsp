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
    <link rel="stylesheet" href="../admin-dashboard/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../admin-dashboard/assets/css/lineicons.css" />
    <link rel="stylesheet" href="admin-dashboard/assets/css/main.css" />
    <link rel="stylesheet" href="../resources/static/css/mainpage.css">
    <link rel="stylesheet" href="../../resources/static/css/owl.css">
    <link rel="stylesheet" href="../resources/static/css/postDetails.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
</head>
<body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!-- The Post Details Modal -->
<div id="postDetails" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>Details</h2>
        </div>
        <div class="modal-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <img src="" alt="img">
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
                        <div class="row">
                            <div class="col">
                                <b>Sold:</b>
                            </div>
                            <div class="col" id="postSold">
                                sold
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
                    <a href="${pageContext.request.contextPath}/jsp/profile.jsp" class="logo">
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
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
    let sectionFirst = document.getElementById("posts-section").innerHTML;
    $(document).ready(function () {
        getMyPosts();
    });
    // Get the modal
    var modal = document.getElementById("postDetails");
    var span = document.getElementsByClassName("close")[0];
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    span.onclick = function() {
        modal.style.display = "none";
    }

    const getMyPosts = () => {

        $.ajax({
                url: "${pageContext.request.contextPath}/post/myposts",
                method: "GET",
                success: function (response) {
                    let data = response.data;

                    let section = document.getElementById("posts-section");
                    section.innerHTML = sectionFirst;

                    data.forEach( element => {
                        //const content = fillContent(element);
                        //section.innerHTML += content;
                        //document.getElementById(element.postId).onclick = function() { openPostDetails(element.postId) }
                        const divParent = document.createElement("div");
                        divParent.className = "col-lg-3 col-md-4 col-sm-6 pb-1";
                        const divFirstChild = document.createElement("div");
                        divFirstChild.className = "product-item bg-light mb-4";
                        const divSecondChild = document.createElement("div");
                        divSecondChild.className = "product-img position-relative overflow-hidden";
                        const image = document.createElement("img");
                        image.className = "img-fluid w-100";
                        image.setAttribute("src","../resources/static/images/deha.jpg");
                        image.setAttribute("alt","pp.png");
                        const divThirdChild = document.createElement("div");
                        divThirdChild.className = "product-action";
                        divThirdChild.onclick = function() { openPostDetails(element); };
                        const aFirst = document.createElement("a");
                        aFirst.className = "btn btn-outline-dark btn-square";
                        aFirst.setAttribute("style","alignment: absolute");
                        aFirst.setAttribute("id","`+element.postId+`");
                        const i = document.createElement("i");
                        i.className = "fa fa-info-circle";
                        aFirst.append(i);
                        const divFourthChild = document.createElement("div");
                        divFourthChild.className = "text-center py-4";
                        const aSecond = document.createElement("a");
                        aSecond.className = "h6 text-decoration-none text-truncate";
                        aSecond.setAttribute("href","");
                        aSecond.setAttribute("id","`+element.postId+`");
                        aSecond.text = element.name;
                        const divFifthChild = document.createElement("div");
                        divFifthChild.className = "d-flex align-items-center justify-content-center mt-2";
                        const headingfive = document.createElement("h5");
                        headingfive.text = element.price;
                        const headingsix = document.createElement("h6");
                        headingsix.className = "text-muted ml-2";
                        divFifthChild.append(headingfive);
                        divFifthChild.append(headingsix);
                        divFourthChild.append(aSecond);
                        divFourthChild.append(divFifthChild);

                        divThirdChild.append(aFirst);
                        divSecondChild.append(image);
                        divSecondChild.append(divThirdChild);
                        divFirstChild.append(divSecondChild);
                        divFirstChild.append(divFourthChild);
                        divParent.append(divFirstChild);
                        section.append(divParent);

                    });
                },
                error: function () {
                    alert("error");
                }
            }
        );
    }


    const fillContent = (element) => {
        return `
                  <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <div class="product-item bg-light mb-4">
                      <div class="product-img position-relative overflow-hidden">
                        <img class="img-fluid w-100" src="../resources/static/images/deha.jpg" alt="">
                        <div class="product-action">
                            <a class="btn btn-outline-dark btn-square" style=" margin-left: 50px" id="`+element.postId+`" href=""><i class="fa fa-info-circle"></i></a>
                        </div>
                      </div>
                    <div class="text-center py-4">
                        <a class="h6 text-decoration-none text-truncate" href="">` +element.name+ `</a>
                        <div class="d-flex align-items-center justify-content-center mt-2">
                            <h5>$`+element.price+`</h5>
                            <h6 class="text-muted ml-2"></h6>
                        </div>
                    </div>
                  </div>
                 </div>
                `;
    }
    const addToCart = (id) => {
        // Add To Cart
    }

    const openPostDetails = (element) => {
        /*
        $.ajax({
                url: '${pageContext.request.contextPath}/category/details/',
                method: "GET",
                data: element.categoryId,
                success: function (response) {
                    let data = response.data;
                    document.getElementById("postCategoryName").innerText = data.name;
                },
                error: function () {
                    alert("error");
                }
            }
        );
        */
        document.getElementById("postDescription").innerText = element.description;
        document.getElementById("postName").innerText= element.name;
        document.getElementById("postSubCategoryName").innerText = "subcate";
        let soldRegex = "Yes";
        if(element.sold == true){
            soldRegex = "Yes";
        }
        else{
            soldRegex = "No";
        }
        document.getElementById("postStatus").innerText = element.status;
        document.getElementById("postCreationDate").innerText = element.startDate;
        document.getElementById("postSold").innerText = soldRegex;

        modal.style.display = "block";
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