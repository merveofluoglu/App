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
                <div class="scroll-to-section-button"><a onclick="logout()">Log out</a></div>
              </div>
            </li>
          </ul>
          <!-- ***** Menu End ***** -->
        </nav>
      </div>
    </div>
  </div>
</header>

<!-- ***** Header Area End ***** -->

<!-- ***** Main Banner Area Start ***** -->
<section class="section main-banner" id="top" data-section="section1" style="background-color: #f5f5f5">
  <div class="overlay"></div>
</section>
<div class="container-fluid pt-5 pb-3 hidden" style="padding-left: 250px;" id="oops" >
  <div class="row px-xl-5" id="oops-section">
    <div class="cont_principal cont_error_active">
      <div class="cont_error">

        <h1>Oops</h1>
        <p>There are no posts for this category.</p>
      </div>
      <div class="cont_aura_1"></div>
      <div class="cont_aura_2"></div>
    </div>
  </div>
</div>
<!-- ***** Main Banner Area End ***** -->



<!-- Scripts -->
<!-- Bootstrap core JavaScript -->


<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/isotope.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

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
<script src="../admin-dashboard/assets/js/bootstrap.bundle.min.js"></script>
<script src="../admin-dashboard/assets/js/main.js"></script>
</body>

</html>
