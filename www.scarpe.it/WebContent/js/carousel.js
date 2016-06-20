var oldImg;
var clicked;

$(document).ready(function() {
	var thumbnails = $(".slides img").map(function() {
		return $(this).attr("src");
	}).get();

	/* set first thumbnail image as the view */
	$('#view').attr('src', thumbnails[0]);

	$('.slides li img').click(function() {
		clicked = true;

		$("#view").fadeOut('slow', function() {
			$('#view').attr('src', $(this).attr("src"));
		}).fadeIn(1000);
	});

	$('.slides li img').mouseenter(function() {
		clicked = false;
		oldImg = $('#view').attr('src');
		$("#view").attr("src", $(this).attr("src"));
	});

	$('.slides li img').mouseout(function() {
		if (!clicked) {
			$('#view').attr('src', oldImg);
		}
	});
});