<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.item.modello}</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Leather, FG" />
<meta name="description"
	content="Scarpa da calcio Nike Mercurial Leather FG" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/alert.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script src="js/carousel.js"></script>
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<header>
		<h1>Nike Mercurial</h1>
	</header>

	<%@ include file="include/menu.jsp"%>

	<section id="main-section">

		<h2>${requestScope.item.marca}&nbsp;${requestScope.item.modello}</h2>
		<div id="image-viewer">
			<img id="main-img" alt="${requestScope.item.alt}"
				src="${requestScope.item.images[0]}" />
			<div id="thumbnails">
				<c:forEach var="image" items="${requestScope.item.images}">
					<img alt="${requestScope.item.alt}" src="${image}"
						onclick="mouseClick()" onmouseenter="mouseEnter()"
						onmouseover="mouseOver(this)" onmouseout="mouseOut()" />
				</c:forEach>
			</div>
		</div>

		<c:forEach var="detail" items="${requestScope.item.dettagli}">
			<SECTION>
				<H3>${detail.intestazione}</H3>
				<P>${detail.corpo}</P>
			</SECTION>
		</c:forEach>


		<div id="buy-div">
			<a id="acquista" onclick="addToCart('${requestScope.item.id}')">Acquista</a>
		</div>
	</section>

	<%@ include file="include/footer.jsp"%>

	<!--
  It is a good idea to place scripts at the bottom of the <body> element.
  This can improve page load, because script compilation can slow down the display.
-->
	<script src="js/alert.js"></script>
	<script src="js/cart.js"></script>
	<script>
		function specialSearch() {
			var cat = document.getElementById("dropdown").value;
			var key = document.getElementById("search-box").value;
			var mainSection = document.getElementById("main-section");

			window.location.replace("index.jsp?cat=" + cat + "&key=" + key);

		}
	</script>
	<script src="js/login.js"></script>
	<script src="js/loadXML.js"></script>
	<script>
		//window.onload = loadXMLDoc("xml/catalog.xml", "a");
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/sticky-menu.js"></script>
</body>
</html>