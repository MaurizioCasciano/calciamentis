var homeProvince;
var shippingProvince;

$(document).ready(function() {
	// alert("document is ready");

	homeProvince = document.getElementById("homeProvince");
	// alert("homeProvince: " + homeProvince.name);

	shippingProvince = document.getElementById("shippingProvince");
	// alert("shippingProvince: " + shippingProvince.name);

	getProvince(homeProvince);
	getProvince(shippingProvince);
});

function getProvince(element) {
	// alert("getting province");
	// alert("ElementName: " + element.name);

	var mode = "?mode=province";
	var xhttp;

	if (window.XMLHttpRequest) {
		// code for modern browsers
		xhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	// alert("After xhttp: " + xhttp);

	xhttp.onreadystatechange = function() {
		// alert("readyState " + xhttp.readyState + " status: " + xhttp.status);

		if (xhttp.readyState === 4 && xhttp.status === 200) {
			element.innerHTML = xhttp.responseText;
			$(element).trigger("change");
		}
		;
	}

	xhttp.open("GET", "OptionFactory" + mode, true);
	xhttp.send();
}

function getComuni(comuniElementId, provincia) {

	// alert("comuniElementId: " + comuniElementId);
	// alert("Select is changing");
	// alert("Getting comuni for: " + provincia);
	var comuniElement = document.getElementById(comuniElementId);
	// alert("comuniElement: " + comuniElement);

	var mode = "?mode=comuni";
	var provincia = "&provincia=" + provincia;
	var xhttp;

	if (window.XMLHttpRequest) {
		// code for modern browsers
		xhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			comuniElement.innerHTML = xhttp.responseText;
			comuniElement.style.display = "block";
		}
	};

	xhttp.open("GET", "OptionFactory" + mode + provincia, true);
	xhttp.send();
}