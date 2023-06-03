<%--
  Created by IntelliJ IDEA.
  User: cemtabarmacbook
  Date: 24.05.2023
  Time: 15:24
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
    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/mainpage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/owl.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/fontawesome.css">
    <!-- FontAwesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
          integrity="sha512-vKMx8UnXk60zUwyUnUPM3HbQo8QfmNx7+ltw8Pm5zLusl1XIfwcxo8DbWCqMGKaWeNxWA8yrx5v3SaVpMvR3CA=="
          crossorigin="anonymous"
          referrerpolicy="no-referrer" />
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
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
                        <li class="scroll-to-section"><a href="#top" class="active">Home</a></li>
                        <li class="scroll-to-section"><a href="#aboutus">About Us</a></li>
                        <li class="scroll-to-section"><a href="#contact">Contact Us</a></li>
                        <li class="scroll-to-section-button">
                            <div class="main-button-red-login">
                                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></div>
                            </div>
                        </li>
                        <li class="scroll-to-section-button">
                            <div class="main-button-red-login">
                                <div class="scroll-to-section-button"><a href="${pageContext.request.contextPath}/jsp/register.jsp">Signup</a></div>
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
<section class="section main-banner" id="top" data-section="section1">
    <video autoplay muted loop id="pd-video">
        <source src="${pageContext.request.contextPath}/resources/static/images/pad.mp4" type="video/mp4" />
    </video>

    <div class="video-overlay header-text">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="caption">
                        <h6>Hello UniPD Members</h6>
                        <h2>Welcome to DAMACANAN Marketplace</h2>
                        <h6>This the new marketpalce for University of Padua members for <br>selling their products and buying new products.</h6>
                        <div class="main-button-red">
                            <div class="scroll-to-section"><a href="#contact">Contact with Us!</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ***** Main Banner Area End ***** -->

<section class="information">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="owl-information-item owl-carousel">
                    <div class="item">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/resources/static/images/icon.png" alt="">
                        </div>
                        <div class="down-content">
                            <h4>Best Quality Products</h4>
                            <p>Each posted product is analysed and checked by our quality detection team.</p>
                        </div>
                    </div>

                    <div class="item">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/resources/static/images/icon.png" alt="">
                        </div>
                        <div class="down-content">
                            <h4>Best Team</h4>
                            <p>We are assuring that each member of the team is available 7/24</p>
                        </div>
                    </div>

                    <div class="item">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/resources/static/images/icon.png" alt="">
                        </div>
                        <div class="down-content">
                            <h4>7/24 Online Support</h4>
                            <p>You can access our customer service for your question and problems any time.</p>
                        </div>
                    </div>

                    <div class="item">
                        <div class="icon">
                            <img src="${pageContext.request.contextPath}/resources/static/images/icon.png" alt="">
                        </div>
                        <div class="down-content">
                            <h4>Best Networking</h4>
                            <p>See each UniPD members intereset and communicate with them to sell or buy products</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>


<section class="aboutus" id="aboutus">
    <div class="container" style="align-content: center; margin-left: 50px">
        <div class="row" style="align-content: center; margin-left: 140px; margin-right: 10px">
            <div class="col-lg-6" style="width: 900px">
                <div class="accordions is-first-expanded">
                    <article class="accordion">
                        <div class="accordion-head">
                            <span>Our Ideology Behind the Marketplace</span>
                            <span class="icon">
                <i class="icon fa fa-chevron-right"></i>
              </span>
                        </div>
                        <div class="accordion-body">
                            <div class="content">
                                <p>As students, we experienced and understood that there is an enormous need for a reliable marketplace for product exchange. As a culture, nowadays students are not only for searchin new products but also looking for second-hand options. That is why we started our initiative from this perpective and providing much more reliable network for product exchange between the University of Padua members.</p>
                            </div>
                        </div>
                    </article>
                    <article class="accordion">
                        <div class="accordion-head">
                            <span>Why Only for University of Padua Members?</span>
                            <span class="icon">
                        <i class="icon fa fa-chevron-right"></i>
              </span>
                        </div>
                        <div class="accordion-body">
                            <div class="content">
                                <p>Since we are a small community, our first preferance is to develop a sustainable marketplace only starting for our university members. We are still progressing for an expansion and working for other universities that are within Veneto Region of Italy.<br><br>
                                    Members does not mean that we are only providing this platform only for active university members. It is open and accessible for graduates, academy members and other members that have relationship directly with University of Padua. That is why in order to access the marketplace you need to have credentials that are authentiacted by University of Padua.</p>
                            </div>
                        </div>
                    </article>
                    <article class="accordion">
                        <div class="accordion-head">
                            <span>Who we are?</span>
                            <span class="icon">
                        <i class="icon fa fa-chevron-right"></i>
                    </span>
                        </div>
                        <div class="accordion-body">
                            <div class="content">
                                <p>We are master students in University of Padua, ICT for Internet and Multimedia Masters Program.<br><br>
                                    This initiative started from the idea of a project in Web Applications that is supervised by the main instructor Nicola Ferro.</p>
                            </div>
                        </div>
                    </article>
                    <article class="accordion last-accordion">
                        <div class="accordion-head">
                            <span>How you can help us?</span>
                            <span class="icon">
                        <i class="icon fa fa-chevron-right"></i>
                    </span>
                        </div>
                        <div class="accordion-body">
                            <div class="content">
                                <p>If the marketplace is not avaliable for your university or your region, we are assuring you that we are trying to make progress.<br><br>
                                    If you are interested in our initiative and capable to help, send us an email and be part of the team.</p>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="contributors" id="contributors">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-heading">
                    <h2>Our Contributors</h2>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="owl-contributors-item owl-carousel">
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/merve.jpg" alt="Merve" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Merve Ofluoglu</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/cem.jpg" alt="Cem" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Omer Cem Tabar</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/ayse.jpg" alt="Ayse" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Ayse Kilic</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/anil.jpg" alt="Anıl" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Anil Ozfirat</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/deha.jpg" alt="Deha" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Ismail Deha Kose</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/adnan.jpg" alt="Adnan" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Adnan Kerem Aksoy</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/mehmet.jpg" alt="Mehmet" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Mehmet Sanisoglu</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/isil.jpg" alt="Isıl" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Isil Atabek</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/gorkem.jpg" alt="Gorkem" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Gorkem Yilmaz</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/resources/static/images/samet.jpg" alt="Samet" style="border-top-left-radius: 20px; border-top-right-radius: 20px">
                        <div class="down-content">
                            <h4>Sametcan Zorlu</h4>
                            <h4>MSc Student ICT for Internet and Multimedia</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="contact-us" id="contactus">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 align-self-center">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="contact">
                            <div class="row">
                                <div class="col-lg-12">
                                    <h2>Contact with us!</h2>
                                </div>
                                <div class="col-lg-4">
                                    <fieldset>
                                        <input name="name" type="text" id="name" placeholder="Name Surname" required="">
                                    </fieldset>
                                </div>
                                <div class="col-lg-4">
                                    <fieldset>
                                        <input name="email" type="text" id="email" placeholder="Email" required="">
                                    </fieldset>
                                </div>
                                <div class="col-lg-4">
                                    <fieldset>
                                        <input name="subject" type="text" id="subject" placeholder="Subject" required="">
                                    </fieldset>
                                </div>
                                <div class="col-lg-12">
                                    <fieldset>
                                        <textarea name="message" type="text" class="form-control" id="message" placeholder="Your Message" required=""></textarea>
                                    </fieldset>
                                </div>
                                <div class="col-lg-12">
                                    <fieldset>
                                        <button id="form-submit" type="submit" class="button">SEND</button>
                                    </fieldset>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="right-info">
                    <ul>
                        <li>
                            <h6>Phone Number</h6>
                            <span>049 827 3131</span>
                        </li>
                        <li>
                            <h6>Email Address</h6>
                            <span>damacanan@gmail.com</span>
                        </li>
                        <li>
                            <h6>Base Area Location</h6>
                            <span>Università degli Studi di Padova, Lungargine del Piovego, 2</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>Copyright © 2022 Damacanan Marketplace Co., Ltd. All Rights Reserved.</p>
    </div>
</section>



<!-- Scripts -->
<!-- Bootstrap core JavaScript -->


<script src="${pageContext.request.contextPath}/resources/js/owl-carousel.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<script>
    $(document).ready(function () {
        $('.nav li:first').addClass('active');
    });

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

    $('#contact').on('submit', function(event) {
        event.preventDefault(); // prevent reload

        var formData = new FormData(this);
        formData.append('service_id', 'service_7eq1jgk');
        formData.append('template_id', 'template_rmx05yh');
        formData.append('user_id', 'em-idJUpyufBKIgjt');
        formData.append('from_name','DamacaNaN Team');
        formData.append('to_name',$("#contact [name='name']").val());
        formData.append('reply_to','damacananapplication@gmail.com');
        formData.append('reply_to_email',$("#contact [name='email']").val());
        $.ajax('https://api.emailjs.com/api/v1.0/email/send-form', {
            type: 'POST',
            data: formData,
            contentType: false, // auto-detection
            processData: false // no need to parse formData to string
        }).done(function () {
            document.getElementById("contact").reset();
        }).fail(function (error) {
            alert('Oops... ' + JSON.stringify(error));
        });
    });




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
</body>

</html>
