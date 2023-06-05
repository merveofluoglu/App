<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27.05.2023
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="DAMACANAN">
  <title>DAMACANAN</title>
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
  <link rel="stylesheet" href="../admin-dashboard/assets/css/main.css" />
  <link rel="stylesheet" href="../../resources/static/css/mainpage.css">
  <link rel="stylesheet" href="../../resources/static/css/owl.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
        integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
        crossorigin="anonymous"
        referrerpolicy="no-referrer" />
  <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.10/dist/sweetalert2.min.css" rel="stylesheet">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky" style="background-color: darkred; position: inherit !important;">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <nav class="main-nav">
          <!-- ***** Logo Start ***** -->
          <a href="${pageContext.request.contextPath}/jsp/enterance.jsp" class="logo">
            DAMACANAN
          </a>
          <!-- ***** Logo End ***** -->
          <!-- ***** Menu Start ***** -->
          <ul class="nav">
            <li class="scroll-to-section-button">
              <div class="main-button-red-login">
                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/profile.jsp">Profile</a></div>
              </div>
            </li>

            <li class="scroll-to-section-button">
              <div class="main-button-red-login">
                <div class="scroll-to-section-button"><a onclick="openModal()">Post</a></div>
              </div>
            </li>

            <li class="scroll-to-section-button">
              <div class="main-button-red-login">
                <div class="scroll-to-section-button"><a onclick="logout()">Log out</a></div>
              </div>
            </li>
            <%
              String role = (String) session.getAttribute("role");
              if (Objects.equals(role, "admin")) { %>
            <li class="scroll-to-section-button">
              <div class="main-button-red-login">
                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/admin-dashboard/index.html" >Admin Dashboard</a></div>
              </div>
            </li>
            <% }
            %>
          </ul>
          <!-- ***** Menu End ***** -->
        </nav>
      </div>
    </div>
  </div>
</header>

<!-- ***** Header Area End ***** -->

<!------------------    ADD POST   ----------------------------------->

  <div class="modal fade" id="addPost" tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="myModalLabel">Add Post</h4>
          <button type="button" class="btn-close" target="#addPost" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" id="addModal">
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
          <button class="btn btn-success" id="add" data-dismiss="modal" onclick="addPost()">Next</button>
        </div>
      </div>
    </div>
  </div>

<!------------------    ADD FILE   ----------------------------------->

<form method="post" class="form-group" enctype= "multipart/form-data" action="${pageContext.request.contextPath}/postFiles/upload">
  <div class="modal fade" id="addFile" tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="addModalFile">Add Post</h4>
          <button type="button" class="btn-close" target="#addFile" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" id="addFileModal">
          <div class="form-group" style="display:none">
            <label for="postId">Post Id: </label>
            <input id="postId" type="text" class="form-control" name="postId" size="50" />
          </div>

          <div class="form-group">
            <label for="file">File:</label>
            <input id="file" type="file" name="file" />
          </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-toolbar" target="#addFile" data-bs-dismiss="modal">Close</button>
          <input type="submit" value="Save" class="btn btn-success">
        </div>
      </div>
    </div>
  </div>
 </div>
</form>

<!------------------    ADD POST END   ------------------------------->

<!------------------  The Post Details Modal ------------------------->

<div class="modal fade" id="showPost" tabindex="-1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="showPostModal">Post Details</h4>
        <button type="button" class="btn-close" target="#showPost" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="showPostDiv">
        <div class="form-group">
          <img src="" alt="img" id="postImage" width="500" height="500">
        </div>
        <div class="form-group">
          <label id="postName">Name: </label>
        </div>
        <div class="form-group">
          <label id="postDesc">Description: </label>
        </div>
        <div class="form-group">
          <label id="postCat">Category:</label>
        </div>
        <div class="form-group">
          <label id="postSubCat">Sub Category: </label>
        </div>
        <div class="form-group">
          <label id="postStatus">Status: </label>
        </div>
        <div class="form-group">
          <label id="postPrice">Price: </label>
        </div>
        <div class="form-group">
          <label id="postPublishDate">Publish Date: </label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-toolbar" target="#showPost" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- ***** Main Banner Area Start ***** -->
<aside class="sidebar-nav-wrapper" style="background-color: transparent; width: 270px; overflow-y: auto; top: unset; position: absolute">
  <div>
    <div class="navbar-text" style="font-weight: bold;
    font-size: x-large;
    padding-left: 25px;
    color: darkred;">CATEGORIES
    </div>
    <nav class="sidebar-nav" style="padding-left: 25px;">
      <ul id="sidebar-nav">

      </ul>
    </nav>
  </div>
</aside>
<div class="overlay"></div>

<div class="container-fluid pt-5 pb-3" style="padding-left: 250px; background-color: #f5f5f5">
  <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span>Featured Products</span></h2>
  <div class="row px-xl-5" id="posts-section">
  </div>
</div>
<!-- ***** Main Banner Area End ***** -->
<div class="sticky" id="messageBox">
  <div id="chats">
  </div>
  <div id="messages">

  </div>
  <div id="sendMessage">
    <input id="newMessage" type="text"><button id="sendButton" onclick="sendMessage()">Send</button>

  </div>
  <span id="messageButton" onclick="expandMessages()">Messages</span>
</div>

<!-- Scripts -->
<!-- Bootstrap core JavaScript -->

<script>

  let sectionFirst = document.getElementById("posts-section").innerHTML;

  $(document).ready(function () {
    getAllCategories();
  });

  let categories;
  let subcategories;
  let posts;


  const logout = () => {
    $.ajax({
              url: "${pageContext.request.contextPath}/user/logout",
              method: "GET",
              success: function (response) {
                toastr.success("Successfully logged out!");
                window.location.href = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/jsp/enterance.jsp"; // redirect
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const createCategories = (categoryName) => {
    return `<li class="nav-item nav-item-has-children">
            <a
              data-bs-toggle="collapse"
              data-bs-target="#`+ categoryName +`"
              aria-controls="`+ categoryName +`"
              aria-expanded="false"
              id="`+ categoryName +`"
              href="#"
              aria-label="Toggle navigation"
            >
              <span class="icon">
                <svg width="22" height="22" viewBox="0 0 22 22">
                  <path
                    d="M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z"
                  />
                </svg>
              </span>
              <span class="text">`+ categoryName.replaceAll("-", " ") +`</span>
            </a>
          </li>
    `;
  }

  const getAllCategories = () => {
    $.ajax({
      method: "GET",
      url: "${pageContext.request.contextPath}/category/getAll",
      success: function (response) {
        categories = JSON.parse(response).data;
        let sidebar = document.getElementById("sidebar-nav");

        categories.forEach(option => {
          let categoryName = option.categoryName;
          sidebar.innerHTML += createCategories(categoryName.replaceAll(" ", "-"));

        });

        getAllSubCategories();
      }
    })
  }

  getCategoryNameById = (id) => {
    return categories.filter(item => item.categoryId === id)[0].categoryName;
  }
  getSubCategoryNameById = (id) => {
    return subcategories.filter(item => item.subcategoryId === id)[0].subcategoryName;
  }
  const getAllSubCategories = () => {
    $.ajax({
      method: "GET",
      url: "${pageContext.request.contextPath}/subcategory/getAllSubCategories",
      success: function (response) {
        let subcategories2 = JSON.parse(response).data;

        subcategories = subcategories2;

        subcategories2.forEach(option => {

          let categoryName = getCategoryNameById(option.categoryId);

          console.log(option);

          let category = document.getElementById(categoryName.replaceAll(" ", "-")).parentNode;

          const ul = document.createElement("ul");

          ul.className = "dropdown-nav collapse show";
          ul.id = categoryName.replaceAll(" ", "-");

          const li = document.createElement("li");
          const node = document.createElement("a");

          node.className = "active";
          node.text = option.subcategoryName;
          node.id = option.subcategoryId;
          node.onclick = function() { getPostsBySubCategory(option.subcategoryId); };

          li.append(node);
          ul.append(li);
          category.append(ul);

        displayCategories();

        getAllPosts();
      });
    }
    });
  }

  const fillContent = (element) => {
    let section = document.getElementById("posts-section");

    let src = 'data:image/jpeg;base64,'+ element.base64;
    if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
      src = "images/img.jpg";
    }

    const divParent = document.createElement("div");
    divParent.className = "col-lg-3 col-md-4 col-sm-6 pb-1";
    divParent.setAttribute("style","border-style: solid; border-color: orange;");
    const divFirstChild = document.createElement("div");
    divFirstChild.className = "product-item bg-light mb-4";
    divFirstChild.setAttribute("style","border-style: solid; border-color: orange;");
    const divSecondChild = document.createElement("div");
    divSecondChild.className = "product-img position-relative overflow-hidden";
    const image = document.createElement("img");
    image.className = "img-fluid w-100";
    image.setAttribute("src",src);
    image.setAttribute("style","width:300px; height:300px");
    image.setAttribute("alt","pp.png");
    const divThirdChild = document.createElement("div");
    divThirdChild.className = "product-action";

    const aFirst = document.createElement("a");
    aFirst.className = "btn btn-outline-dark btn-square";
    aFirst.setAttribute("style","alignment: absolute");
    aFirst.setAttribute("id", "show-details")


    //aFirst.onclick = onclick = function() { if(event.target.className)  openPostDetails(element); };

    const i = document.createElement("i");
    i.className = "fa fa-info-circle";
    i.onclick =  function() { openPostDetails(element); };
    aFirst.append(i);

    const aFourth = document.createElement("a");
    aFourth.className = "btn btn-outline-dark btn-square";
    aFourth.setAttribute("style","alignment: absolute");
    aFourth.onclick = function() { addToFavourite(element.postId); };

    const isecond = document.createElement("i");
    isecond.className = "far fa-heart";
    aFourth.append(isecond);

    const aFifth = document.createElement("a");
    aFifth.className = "btn btn-outline-dark btn-square";
    aFifth.setAttribute("style","alignment: absolute");
    aFifth.onclick = function() { sendMessageToPostUser(element.postId); };

    const ithird = document.createElement("i");
    ithird.className = "fa fa-envelope";
    aFifth.append(ithird);

    const aSixth = document.createElement("a");
    aSixth.className = "btn btn-outline-dark btn-square";
    aSixth.setAttribute("style","alignment: absolute");
    aSixth.onclick = function() { openSwal(element.postId); }

    const ifourth = document.createElement("i");
    ifourth.className = "fa fa-shopping-cart";
    aSixth.append(ifourth);

    const divFourthChild = document.createElement("div");
    divFourthChild.className = "text-center py-4";
    const aSecond = document.createElement("a");
    aSecond.className = "h6 text-decoration-none text-truncate";
    aSecond.setAttribute("href","");
    aSecond.setAttribute("id",element.postId);
    aSecond.text = element.name;
    const divFifthChild = document.createElement("div");
    divFifthChild.className = "d-flex align-items-center justify-content-center mt-2";
    const aThird = document.createElement("a");
    aThird.className = "h6 text-decoration-none text-truncate";
    aThird.setAttribute("href","");
    aThird.text = element.price;
    divFifthChild.append(aThird);
    divFourthChild.append(aSecond);
    divFourthChild.append(divFifthChild);
    divThirdChild.append(aFirst);
    divThirdChild.append(aFourth);
    divThirdChild.append(aFifth);
    divThirdChild.append(aSixth);
    divSecondChild.append(image);
    divSecondChild.append(divThirdChild);
    divFirstChild.append(divSecondChild);
    divFirstChild.append(divFourthChild);
    divParent.append(divFirstChild);
    section.append(divParent);

  }

  openSwal = (id) => {
    Swal.fire({
      title: 'Are you sure to buy this product?',
      text: "Please, select yes or no!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, i want to buy this product!'
    }).then((result) => {
      if (result.isConfirmed) {
        buyPost(id)
      }
    })
  }

  const buyPost = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/post/buyRequest/' + id,
              method: "POST",
              success: function (response) {
                toastr.success("Your buy request sent to the post owner! If owner accepts your product will be displayed on 'MY Posts' section of your profile!");
              },
              error: function (response) {
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

    document.getElementById("postDesc").innerText += element.description;
    document.getElementById("postName").innerText += element.name;
    document.getElementById("postCat").innerText += getCategoryNameById(element.categoryId);
    document.getElementById("postSubCat").innerText += getSubCategoryNameById(element.subcategoryId);
    document.getElementById("postPublishDate").innerText += element.startDate;
    document.getElementById("postPrice").innerText += element.price + "$";
    document.getElementById("postStatus").innerText += element.status;
    document.getElementById("postImage").src = lolo;

    $("#showPost").modal('show');


  }

  const getAllPosts = () => {
    $.ajax({
              url: "${pageContext.request.contextPath}/post/getAll",
              method: "GET",
              success: function (response) {
                let data = JSON.parse(response).data;
                posts = data;

                let section = document.getElementById("posts-section");
                section.innerHTML = sectionFirst;

                data.forEach( element => {

                  fillContent(element);

                });
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const getPostsBySubCategory = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/post/getPostsBySubCategoryId/' + id,
              method: "GET",
              success: function (response) {

                let data = JSON.parse(response).data;
                console.log(data);

                let section = document.getElementById("posts-section");
                section.innerHTML = sectionFirst;

                if (data.length === 0) {

                  window.location.href = '${pageContext.request.contextPath}/jsp/main-page/oops-page.jsp';

                  return;
                }
                data.forEach( element => {

                  fillContent(element);

                });
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  const openModal = () => {
    $("#addPost [name='Name']").val("");
    $("#addPost [name='Description']").val("");
    $("#addPost [name='Price']").val(null);
    $("#addPost [name='CategoryId']").val(null);
    $("#addPost [name='SubCategoryId']").val(null);
    $("#addPost [name='file']").val(null);
    $("#addPost").modal('show');
  }

  const addPost = () => {
    const _data = {
      name: $("#addPost [name='Name']").val(),
      description: $("#addPost [name='Description']").val(),
      price: $("#addPost [name='Price']").val(),
      categoryId: $("#addPost [name='CategoryId']").val(),
      subcategoryId: $("#addPost [name='SubCategoryId']").val(),
    };

    if(!checkValidity(_data)) {
      return;
    }

    $.ajax({
              url: "${pageContext.request.contextPath}/post/add",
              method: "POST",
              data: _data,
              success: function (response) {
                let id = JSON.parse(response).data;

                $("#addPost").modal('hide');

                $("#addFile [name='postId']").val(id);
                $("#addFile [name='file']").val("");
                $("#addFile").modal('show');

                toastr.success("Post added succesfully!");
              },
              error: function (response) {
                alert("error");
              }
            }
    );
  }

  $('#addFile').submit(function (e) {

    let frm = $('#addFile');

    e.preventDefault();

    $.ajax({
      type: frm.attr('method'),
      url: "${pageContext.request.contextPath}/postFiles/upload",
      data: frm.serialize(),
      success: function (data) {

        window.location.href = '${pageContext.request.contextPath}/jsp/main-page/mainpage.jsp';
        console.log('Submission was successful.');
        console.log(data);
      },
      error: function (data) {
        console.log('An error occurred.');
        console.log(data);
      },
    });
  });

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


  const sendMessageToPostUser = (userId) => {
    // Add To Cart
    console.log("Called");
    loadSelectedChatPage([], userId);
  }

  const addToFavourite = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/favourite/add/' + id,
              method: "POST",
              success: function (response) {
                toastr.success("Added to favourites!");

              },
              error: function (response) {
                toastr.info("This product is already in your favourites!");
              }
            }
    );
  }
  const removeFavourite = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/favourite/remove/' + id,
              method: "POST",
              success: function (response) {
                toastr.success("Removed from favourites!");

              },
              error: function () {
                alert("error");
              }
            }
    );
  }

  showSection = (section, isAnimate) => {
    var
            direction = section.replace(/#/, ''),
            reqSection = $('.section').filter('[data-section="' + direction + '"]'),
            reqSectionPos = reqSection.offset().top - 0;

    if (isAnimate) {
      $('body, html').animate({
                scrollTop: reqSectionPos },
              800);
    } else {
      $('body, html').scrollTop(reqSectionPos);
    }

  };

  checkSection = () => {
    $('.section').each(function () {
      var
              $this = $(this),
              topEdge = $this.offset().top - 80,
              bottomEdge = topEdge + $this.height(),
              wScroll = $(window).scrollTop();
      if (topEdge < wScroll && bottomEdge > wScroll) {
        var
                currentId = $this.data('section'),
                reqLink = $('a').filter('[href*=\\#' + currentId + ']');
        reqLink.closest('li').addClass('active').
        siblings().removeClass('active');
      }
    });
  };

  $('.main-menu, .responsive-menu, .scroll-to-section').on('click', 'a', function (e) {
    e.preventDefault();
    showSection($(this).attr('href'), true);
  });
  $('.scroll-to-section-button').on('click', 'a', function (e) {

  });

  $(window).scroll(function () {
    checkSection();
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.10/dist/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="../admin-dashboard/assets/js/bootstrap.bundle.min.js"></script>
<script src="../admin-dashboard/assets/js/main.js"></script>

<script>


  function expandMessages(){
    const messageBox = document.getElementById("messageBox");
    const chats = document.getElementById("chats");
    const messages = document.getElementById("messages");
    const sendMessage = document.getElementById("sendMessage");


    if (messageBox.style.height === '300px'){
      messageBox.style.height = '30px';
      chats.style.display = "none";
      messages.style.display = "none";
      sendMessage.style.display = "none";
      return
    }

    loadChats();
  }

  function loadChats(){
    const messageBox = document.getElementById("messageBox");
    const chats = document.getElementById("chats");
    const messages = document.getElementById("messages");
    const sendMessage = document.getElementById("sendMessage");

    messageBox.style.height = '300px';
    chats.style.display = "block";
    messages.style.display = "none";
    sendMessage.style.display = "none";

    $.ajax({
              url: "${pageContext.request.contextPath}/message/userchatreceivers",
              method: "GET",
              data: {},
              success: function (response) {
                showChat(response.data);
              },
              error: function () {
                alert("error, couldn't get user chats");
              }
            }
    );
  }

  function showChat(chatsReceivers){
    const chatsBox = document.getElementById('chats');
    chatsBox.innerHTML = "";

    for (let i = 0; i < chatsReceivers.length; i++) {
      chatsBox.appendChild(createChatNode(chatsReceivers[i]));
    }
  }

  function createChatNode(recipientDict){
    const receiver = recipientDict['name'] + " " + recipientDict['surname'];
    const chatNode = document.createElement("div");

    const receiverNameNode = document.createElement("span");
    const receiverTextNode = document.createTextNode(receiver);
    receiverNameNode.appendChild(receiverTextNode);

    const receiverId = document.createElement("span");
    const receiverIdTextNode = document.createTextNode(recipientDict['userId']);
    receiverId.appendChild(receiverIdTextNode);
    receiverId.setAttribute("style", "display: none;");

    chatNode.appendChild(receiverNameNode);
    chatNode.appendChild(receiverId);

    chatNode.addEventListener("click", function (){
      const recipientIdVal = chatNode.children[1].textContent;
      loadSelectedChatMessages(recipientIdVal);
    } )

    chatNode.setAttribute("class", "chat");
    return chatNode;
  }

  function loadSelectedChatMessages(recipientIdVal){
    $.ajax({
              url: "${pageContext.request.contextPath}/message/messages_of_chat",
              method: "GET",
              data: {
                recipientId: recipientIdVal
              },
              success: function (response) {
                loadSelectedChatPage(response.data, response.data[0]['recipientId'])
              },
              error: function () {
                alert("error, couldn't get messages of chat");
              }
            }
    );
  }

  function loadSelectedChatPage(messagesList, recipientId){
    const messageBox = document.getElementById("messageBox");
    messageBox.setAttribute("style", "height: 300px;")

    const chats = document.getElementById("chats");
    chats.setAttribute("style", "display: none;");

    const chatSection = document.getElementById("messages");
    chatSection.setAttribute("style", "display: block;");
    chatSection.innerHTML = "";


    const recipientIdNode = document.createElement("span");

    if( !recipientId ){
      $.ajax({
                url: "${pageContext.request.contextPath}/message/message_owner",
                method: "GET",
                data: {
                  creatorId: messagesList[0]['creatorId']
                },
                success: function (response) {
                  if (response.data === "user"){
                    const recipientIdTextNode = document.createTextNode(messagesList[0]['recipientId']);
                    recipientIdNode.appendChild(recipientIdTextNode);
                  }
                  else{
                    const recipientIdTextNode = document.createTextNode(messagesList[0]['creatorId']);
                    recipientIdNode.appendChild(recipientIdTextNode);
                  }
                },
                error: function () {
                  alert("error");
                }
              }
      );
    }
    else{
      const recipientIdTextNode = document.createTextNode(recipientId);
      recipientIdNode.appendChild(recipientIdTextNode);
    }

    recipientIdNode.setAttribute("style", "display: none;");
    chatSection.appendChild(recipientIdNode);


    const backButtonNode = document.createElement("div");
    const backButtonTextNode = document.createTextNode("Back");
    backButtonNode.setAttribute("class", "backButton");
    backButtonNode.appendChild(backButtonTextNode);

    backButtonNode.addEventListener("click", function(){
      loadChats();
    })

    const messageSection = document.createElement("div");
    messageSection.setAttribute("id", "messagesSection");

    chatSection.appendChild(backButtonNode);
    chatSection.appendChild(messageSection);
    messagesList.forEach(createMessageNode)

    messageSection.scrollTop = messageSection.scrollHeight;

    const newMessage = document.getElementById("sendMessage");
    newMessage.setAttribute("style", "display: block; height: 10%;");
  }

  function createMessageNode(messageDict){
    const messagesSection = document.getElementById("messagesSection");

    const messageNode = document.createElement("span");
    const messageTextNode = document.createTextNode(messageDict["messageBody"]);
    messageNode.appendChild(messageTextNode);

    console.log("Checking the owner of" + messageDict['recipientId']);

    $.ajax({
              url: "${pageContext.request.contextPath}/message/message_owner",
              method: "GET",
              data: {
                creatorId: messageDict['creatorId']
              },
              success: function (response) {
                if (response.data === "user"){
                  messageNode.setAttribute("class", "message userMessage");
                }
                else{
                  messageNode.setAttribute("class", "message otherMessage");
                }
              },
              error: function () {
                alert("error");
              }
            }
    );
    const lineNode = document.createElement("div");
    lineNode.setAttribute("class", "messageLine");
    lineNode.appendChild(messageNode);
    messagesSection.appendChild(lineNode);
  }

  function sendMessage(){
    const message = document.getElementById("newMessage").value;
    const recipientId = document.getElementById("messages").children[0].textContent;

    const _data = {
      'recipientId': recipientId,
      'subject': "testing",
      'messageBody': message,
    };

    $.ajax({
              url: "${pageContext.request.contextPath}/message/add",
              method: "POST",
              data: _data,
              success: function (response) {
                loadSelectedChatMessages(recipientId);
                document.getElementById("newMessage").value ="";
              },
              error: function () {
                alert("error");
              }
            }
    );
  }

</script>

<style>
  #messageBox {
    position: fixed;
    float: right;
    height: 30px;
    width: 300px;
    bottom: 0;
    right: 0;
    background-color: darkred;
    text-align: center;
    border-style: solid;
    border-width: 1px;
    border-color: darkred;
  }
  .backButton{
    background-color: darkred;
    color: white;
    margin-bottom: 10px;
    cursor: pointer;
  }
  #chats{
    position: relative;
    display: none;
    height: 90%;
    background-color: #f5f5f5;
  }
  .chat{
    position: relative;
    border-width: 2px;
    border-style: solid;
    background-color: white;
    cursor: pointer;
    margin-bottom: 3px;
  }
  #messages{
    display: none;
    height: 80%;
    background-color: white;
  }
  #messagesSection{
    overflow: scroll;
    height: 80%;
  }
  .messageLine{
    display: block;
    width: 100%;
    height: 40px;
  }
  .message{
    display: block;
    padding: 2px 1px 2px 1px;
    border-radius: 15px;
    background-color: #f5f5f5;
    position: relative;
  }
  .userMessage{
    color: black;
    float: left;
    left: 10px;

  }
  .otherMessage{
    color: darkred;
    float: right;
    right: 10px;

  }
  #sendMessage{
    display: none;
    height: 10%;
    background-color: white;
  }
  #newMessage{
    position: relative;
    width: 70%;
    height: 95%;
    border-radius: 10px;
    border-width: 1px;
  }
  #sendButton{
    position: relative;
    width: 20%;
    height: 95%;
    left: 10px;
    right: 5px;
    color: white;
    background-color: darkred;
    border-radius: 15px;
    border-width: 1px;
  }
  #messageButton{
    position: relative;
    display: block;
    height: 10%;
    width: 100%;
    color: white;
    cursor: pointer;
  }

</style>

</body>
