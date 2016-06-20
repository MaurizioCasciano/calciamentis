$(document).ready(function() {
	var stickyNavTop = $('nav.menu').offset().top;

	var stickyNav = function() {
		var scrollTop = $(window).scrollTop();

		if ($(window).scrollTop() >= stickyNavTop) {
			$('nav.menu').addClass('fixed');
			$('div.alert').addClass('fixed');
		} else {
			$('nav.menu').removeClass('fixed');
			$('div.alert').removeClass('fixed');
		}
	};

	stickyNav();

	$(window).scroll(function() {
		stickyNav();
	});
});