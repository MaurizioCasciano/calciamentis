function showWarning(message) {
	// alert("Warning Message: " + message);
	$("div.warning").text(message);
	$("div.warning").fadeIn(300).delay(1500).fadeOut(600);
}

function showSuccess(message) {
	// alert("Success Message: " + message);
	$("div.success").text(message);
	$("div.success").fadeIn(300).delay(1500).fadeOut(600);
}

function showInfo(message) {
	// alert("Info Message: " + message);
	$("div.info").text(message);
	$("div.info").fadeIn(300).delay(1500).fadeOut(600);
}