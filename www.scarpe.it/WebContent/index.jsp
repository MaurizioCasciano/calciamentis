<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scarpe da Calcio</title>

<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike" />
<meta name="description" content="Scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/catalog.css" />
<link rel="stylesheet" href="css/tooltip.css" />
<link rel="stylesheet" href="css/alert.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="js/jquery-1.12.4.js"></script>

<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>
<body>
	<!-- name = ${sessionScope.loggedUser.name} -->
	<!-- error = ${requestScope.error} -->

	<header>
		<h1>Scarpe da calcio</h1>
	</header>

	<%@ include file="include/menu.jsp"%>

	<section id="main-section"></section>

	<%@ include file="include/footer.jsp"%>

	<!-- JAVASCRIPT -->

	<script src="js/alert.js"></script>
	<script src="js/cart.js"></script>
	<script>
		function specialSearch() {
			var cat = document.getElementById("dropdown").value;
			var key = document.getElementById("search-box").value;
			var mainSection = document.getElementById("main-section");

			$.ajax({
				type : "GET",
				data : {
					cat : cat,
					key : key
				},
				url : "CatalogPage",
				success : function(data) {

					mainSection.innerHTML = data
				}
			});
		}
	</script>
	<script>
		function getURLParameter(name) {
			return decodeURIComponent((new RegExp('[?|&]' + name + '='
					+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
					.replace(/\+/g, '%20'))
					|| null;
		}

		var mainSection = document.getElementById("main-section");
		var xmlhttp;
		var redirect = location.search;

		if (window.XMLHttpRequest) {
			// code for modern browsers
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			//alert("ReadyState: " + xmlhttp.readyState + " Status: " + xmlhttp.status);

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (redirect != "") {
					cat = getURLParameter('cat');
					$("#dropdown").val(cat);
				}
				mainSection.innerHTML = xmlhttp.responseText;
			}
		};
		if (redirect == null) {
			xmlhttp.open("GET", "CatalogPage", true);
			xmlhttp.send();
		} else {
			xmlhttp.open("GET", "CatalogPage" + redirect, true);
			xmlhttp.send();
		}
	</script>
	<script src="js/sticky-menu.js"></script>
</body>
</html>