(function ($) {

    "use strict";

        $(window).scroll(function() {
          var scroll = $(window).scrollTop();
          var box = $('.header-text').height();
          var header = $('header').height();

          if (scroll >= box - header) {
            $("header").addClass("background-header");
          } else {
            $("header").removeClass("background-header");
          }
        });

        $('.filters ul li').click(function(){
            $('.filters ul li').removeClass('active');
            $(this).addClass('active');

              var data = $(this).attr('data-filter');
              $grid.isotope({
                filter: data
              })
            });

            var $grid = $(".grid").isotope({
              itemSelector: ".all",
              percentPosition: true,
              masonry: {
                columnWidth: ".all"
              }
            });


    const Accordion = {
        settings: {
            // Expand the first item by default
            first_expanded: false,
            // Allow items to be toggled independently
            toggle: false
        },

        openAccordion: function(toggle, content) {
            if (content.children.length) {
                toggle.classList.add("is-open");
                let final_height = Math.floor(content.children[0].offsetHeight);
                content.style.height = final_height + "px";
            }
        },

        closeAccordion: function(toggle, content) {
            toggle.classList.remove("is-open");
            content.style.height = 0;
        },

        init: function(el) {
            const _this = this;

            // Override default settings with classes
            let is_first_expanded = _this.settings.first_expanded;
            if (el.classList.contains("is-first-expanded")) is_first_expanded = true;
            let is_toggle = _this.settings.toggle;
            if (el.classList.contains("is-toggle")) is_toggle = true;

            // Loop through the accordion's sections and set up the click behavior
            const sections = el.getElementsByClassName("accordion");
            const all_toggles = el.getElementsByClassName("accordion-head");
            const all_contents = el.getElementsByClassName("accordion-body");
            for (let i = 0; i < sections.length; i++) {
                const section = sections[i];
                const toggle = all_toggles[i];
                const content = all_contents[i];

                // Click behavior
                toggle.addEventListener("click", function(e) {
                    if (!is_toggle) {
                        // Hide all content areas first
                        for (let a = 0; a < all_contents.length; a++) {
                            _this.closeAccordion(all_toggles[a], all_contents[a]);
                        }

                        // Expand the clicked item
                        _this.openAccordion(toggle, content);
                    } else {
                        // Toggle the clicked item
                        if (toggle.classList.contains("is-open")) {
                            _this.closeAccordion(toggle, content);
                        } else {
                            _this.openAccordion(toggle, content);
                        }
                    }
                });

                // Expand the first item
                if (i === 0 && is_first_expanded) {
                    _this.openAccordion(toggle, content);
                }
            }
        }
    };

    (function() {
        // Initiate all instances on the page
        const accordions = document.getElementsByClassName("accordions");
        for (let i = 0; i < accordions.length; i++) {
            Accordion.init(accordions[i]);
        }
    })();


    $('.owl-information-item').owlCarousel({
        items:3,
        loop:true,
        dots: true,
        nav: true,
        autoplay: true,
        margin:30,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:2
            },
            1000:{
                items:3
            }
        }
    })

    $('.owl-contributors-item').owlCarousel({
        items:4,
        loop:true,
        dots: true,
        nav: true,
        autoplay: true,
        margin:30,
        responsive:{
            0:{
                items:1
            },
            600:{
                items:2
            },
            1000:{
                items:4
            }
        }
    })


    $(document).ready(function () {
        $(document).on("scroll", onScroll);

        //smoothscroll
        $('.scroll-to-section a[href^="#"]').on('click', function (e) {
            e.preventDefault();
            $(document).off("scroll");

            $('.scroll-to-section a').each(function () {
                $(this).removeClass('active');
            })
            $(this).addClass('active');

            var target = this.hash,
                menu = target;
            var target = $(this.hash);
            $('html, body').stop().animate({
                scrollTop: (target.offset().top) - 79
            }, 500, 'swing', function () {
                window.location.hash = target;
                $(document).on("scroll", onScroll);
            });
        });
    });

    function onScroll(event){
        var scrollPos = $(document).scrollTop();
        $('.nav a').each(function () {
            var currLink = $(this);
            var refElement = $(currLink.attr("href"));
            if (refElement.position().top <= scrollPos && refElement.position().top + refElement.height() > scrollPos) {
                $('.nav ul li a').removeClass("active");
                currLink.addClass("active");
            }
            else{
                currLink.removeClass("active");
            }
        });
    }
})(window.jQuery);