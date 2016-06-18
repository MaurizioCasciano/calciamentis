<%@page import="java.util.GregorianCalendar"%>
<%@page import="catalog.*"%>
<%@page import="database.Database"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Leather FG</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Leather, FG" />
<meta name="description"
	content="Scarpa da calcio Nike Mercurial Leather FG" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<script src="js/carousel.js"></script>
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<jsp:useBean id="shoppingCart" scope="session"
		class="cart.ShoppingCart"></jsp:useBean>

	<div class="wrapper">
		<header>
			<h1>Nike Mercurial</h1>
		</header>

		<%@ include file="include/menu.jsp"%>

		<section id="main-section">
			<%
				Item currentItem = (Item) request.getAttribute("item");

				if (currentItem != null) {
					ArrayList<String> images = currentItem.getImages();
					ArrayList<Detail> details = currentItem.getDettagli();
			%>
			<H2><%=currentItem.getMarca() + " " + currentItem.getModello()%></H2>
			<DIV id="image-viewer">
				<img id="main-img" alt=<%=currentItem.getAlt()%>
					src=<%=images.get(0)%>></img>
				<div id="thumbnails">
					<%
						for (int i = 0; i < images.size(); i++) {
					%>
					<IMG alt=<%=currentItem.getAlt()%> src=<%=images.get(i)%>
						onclick="mouseClick()" onmouseenter="mouseEnter()"
						onmouseover="mouseOver(this)" onmouseout="mouseOut()">
					<%
						}
					%>
				</div>
			</DIV>
			<%
				for (int j = 0; j < details.size(); j++) {
			%>
			<SECTION>
				<H3><%=details.get(j).getIntestazione()%></H3>
				<P><%=details.get(j).getCorpo()%></P>
			</SECTION>
			<%
				}
			%>
			<DIV id="buy-div">
				<A id="acquista" href="carrello.jsp">Acquista</A>
			</DIV>
			<%
				}
			%>


		</section>
		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<!--
  It is a good idea to place scripts at the bottom of the <body> element.
  This can improve page load, because script compilation can slow down the display.
-->
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