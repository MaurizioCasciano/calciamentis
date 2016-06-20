function specialSearch() {
	var cat = document.getElementById("dropdown").value;
	var key = document.getElementById("search-box").value;
	var mainSection = document.getElementById("main-section");

	window.location.replace("index.jsp?cat=" + cat + "&key=" + key);

}