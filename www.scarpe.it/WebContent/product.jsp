<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test="${item!=null}">${item.marca}&nbsp;${item.modello}</c:if></title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Leather, FG" />
<meta name="description"
	content="Scarpa da calcio Nike Mercurial Leather FG" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/carousel.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet" href="css/alert.css" />
<link rel="stylesheet" href="css/tooltip.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<div class="wrapper">
		<header>
			<c:if test="${item!=null}">
				<h1>${item.marca}&nbsp;${item.modello}</h1>
			</c:if>
		</header>

		<%@ include file="include/menu.jsp"%>
		<section id="main-section">
			<c:if test="${item!=null}">
				<h2>${item.marca}&nbsp;${item.modello}</h2>

				<div class="container">
					<div class="carousel">
						<img id="view" />
					</div>
					<div id="slider">
						<ul class="slides">
							<c:forEach var="image" items="${item.images}">
								<li class="slide"><img alt="${item.alt}" src="${image}" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<c:forEach var="detail" items="${item.dettagli}">
					<section>
						<H3>${detail.getIntestazione()}</H3>
						<P>${detail.getCorpo()}</P>
					</section>
				</c:forEach>

				<div id="buy-div">
					<span>&euro;&nbsp;${item.prezzo_vendita}</span>
					<button style="height: 70px; width: 70px; font-size: 40px;"
						onclick='addToCart(${item.id})'>
						<span data-tooltip='Aggiungi al carrello'> <span
							class='fa fa-shopping-cart'></span>
						</span>
					</button>
				</div>

			</c:if>
		</section>

		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<!--
  It is a good idea to place scripts at the bottom of the <body> element.
  This can improve page load, because script compilation can slow down the display.
-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/carousel.js"></script>
	<script src="js/specialSearch.js"></script>
	<script src="js/login.js"></script>
	<script src="js/loadXML.js"></script>
	<script src="js/cart.js"></script>
	<script src="js/alert.js"></script>
	<script src="js/sticky-menu.js"></script>
</body>
</html>