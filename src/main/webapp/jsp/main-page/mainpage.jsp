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

<!---------ADD POST--------->

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
            <label>Status:</label>
            <input type="text" name="Status" id="Status" class="form-control" />
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
          <button type="button" class="btn-close" target="#addPost" data-bs-dismiss="modal" aria-label="Close"></button>
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

<!---------ADD POST END--------->

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

  const createSubCategories = (categoryName, subcategoryName, subcategoryId) => {
    return `
            <ul id="`+ categoryName +`" class="dropdown-nav collapse show">
              <li>
                <a class="btn" href="" id="`+ subcategoryId +`"> `+ subcategoryName +` </a>
              </li>
            </ul>`;
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

          /*
          console.log(option);
          const li = document.createElement("li");
          li.className = "nav-item nav-item-has-children";
          const node = document.createElement("a");
          let categoryName = option.categoryName;
          categoryName = categoryName.replaceAll(" ", "-");
          node.text = option.categoryName;
          node.id = categoryName;
          node.setAttribute("data-bs-toggle", "collapse");
          node.setAttribute("data-bs-target", "#" + categoryName);
          node.setAttribute("aria-controls", categoryName);
          node.setAttribute("aria-expanded", "false");
          node.setAttribute("aria-label", "Toggle navigation");
          node.href="#0";
          let span = document.createElement("span");
          span.className = "icon";
          let svg = document.createElement("svg");
          svg.setAttribute("viewBox", "0 0 22 22");
          svg.setAttribute("width", "22");
          svg.setAttribute("height", "22");
          let path = document.createElement("path");
          path.setAttribute("d", "M17.4167 4.58333V6.41667H13.75V4.58333H17.4167ZM8.25 4.58333V10.0833H4.58333V4.58333H8.25ZM17.4167 11.9167V17.4167H13.75V11.9167H17.4167ZM8.25 15.5833V17.4167H4.58333V15.5833H8.25ZM19.25 2.75H11.9167V8.25H19.25V2.75ZM10.0833 2.75H2.75V11.9167H10.0833V2.75ZM19.25 10.0833H11.9167V19.25H19.25V10.0833ZM10.0833 13.75H2.75V19.25H10.0833V13.75Z");
          svg.append(path);
          span.append(svg);
          node.append(span);
          li.append(node);
          sidebar.append(li);
          */
        });

        getAllSubCategories();
      }
    })
  }

  getCategoryNameById = (id) => {
    return categories.filter(item => item.categoryId === id)[0].categoryName;
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
          //category.innerHTML += createSubCategories(categoryName.replaceAll(" ", "-"), option.subcategoryName, option.subcategoryId);
          //document.getElementById(option.subcategoryId).onclick = function() { getPostsBySubCategory.bind(this, option.subcategoryId); };

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

        });

        displayCategories();

        getAllPosts();
      }
    })
  }

  const fillContent = (element) => {
    let src = 'data:image/jpeg;base64,'+ element.base64;
    if(element.base64 == null || element.base64 == undefined || element.base64 == "") {
      src = "images/img.jpg";
    }
    return `
                  <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <div class="product-item bg-light mb-4">
                      <div class="product-img position-relative overflow-hidden">
                        <img class="img-fluid w-100" src=`+ src +` alt="">
                        <div class="product-action">
                            <a class="btn btn-outline-dark btn-square" id="message-to-creator" href=""><i class="fa fa-envelope"></i></a>
                            <a class="btn btn-outline-dark btn-square" id="favourite" href=""><i class="far fa-heart"></i></a>
                            <a class="btn btn-outline-dark btn-square" id="open-post" href=""><i class="fa fa-search"></i></a>
                        </div>
                      </div>
                    <div class="text-center py-4">
                        <a class="h6 text-decoration-none text-truncate" href="">` +element.name+ `</a>
                        <h5>` +element.description+ `</h5>
                        <div class="d-flex align-items-center justify-content-center mt-2">
                            <h5>$`+element.price+`</h5><h6 class="text-muted ml-2"></h6>
                        </div>
                    </div>
                  </div>
                 </div>
                `;
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
                  const content = fillContent(element);
                  section.innerHTML += content;
                  document.getElementById("favourite").onclick = function() { addFavourite(element.postId); };
                  document.getElementById("message-to-creator").onclick = function() { sendMessage(element.userId); }
                  document.getElementById("open-post").onclick = function() { openPostDetails(element.postId) }
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

                  const content = fillContent(element);
                  section.innerHTML += content;
                  document.getElementById("favourite").onclick = function() { addToFavourite(element.postId); };
                  document.getElementById("message-to-creator").onclick = function() { sendMessage(element.userId); }
                  document.getElementById("open-post").onclick = function() { openPostDetails(element.postId) }
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
    $("#addPost [name='Status']").val("");
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
      status: $("#addPost [name='Status']").val(),
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

  const addPostFiles = () => {

  }

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

    if(data.status == "" || data.status == null || data.status == undefined) {
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

  const sendMessage = (userId) => {
    // Add To Cart
  }

  const openPostDetails = (id) => {
    // Post details will be displayed.
  }

  const addToFavourite = (id) => {
    $.ajax({
              url: '${pageContext.request.contextPath}/favourite/add/' + id,
              method: "POST",
              success: function (response) {
                toastr.success("Added to favourites!");
                document.getElementById("favourite").removeClass("far fa-heart");
                document.getElementById("favourite").addClass("fa solid fa-heart");
              },
              error: function () {
                alert("error");
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
                document.getElementById("favourite").removeClass("fa solid fa-heart");
                document.getElementById("favourite").addClass("far fa-heart");
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
<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="../admin-dashboard/assets/js/bootstrap.bundle.min.js"></script>
<script src="../admin-dashboard/assets/js/main.js"></script>
</body>
