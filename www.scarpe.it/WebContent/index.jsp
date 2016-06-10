<%@page import="cart.ShoppingCart"%>
<%@page import="catalog.Item"%>
<%@page import="database.Database"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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


<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>
<body>
	<jsp:useBean id="shoppingCart" scope="session"
		class="cart.ShoppingCart"></jsp:useBean>
	<!-- shoppingCartTotale = ${sessionScope.shoppingCart == null ? 0.0 : sessionScope.shoppingCart.totale} -->

	<header>
		<h1>Scarpe da calcio</h1>
	</header>

	<nav class="menu">
		<ul>
			<li class="left"><a class="fa fa-home" href="index.jsp">&nbsp;Home</a>
			</li>
			<li class="left"><a class="fa fa-shopping-cart"
				href="carrello.jsp">&nbsp;Carrello</a></li>

			<li class="left"><span id="totale" class="fa fa-money">&nbsp;&euro;${sessionScope.shoppingCart == null ? 0.0 : sessionScope.shoppingCart.totale}</span></li>

			<li id="search" class="left">
				<div id="form-wrapper">
					<button onclick="specialSearch()" class="go-button fa fa-search"></button>
					<span class="nav-list"> <select id="dropdown">
							<option value="f0">Tutti i prezzi</option>
							<option value="f1">50&euro; - 100&euro;</option>
							<option value="f2">100&euro; - 200&euro;</option>
							<option value="f3">200&euro; - 300&euro;</option>
							<option value="f4">300&euro; - 500&euro;</option>
					</select>
					</span>
					<div class="in-wrap">
						<input type="text" name="query" id="search-box">
					</div>
				</div>
			</li>

			<%
				if (session.getAttribute("loggedUser") == null) {
			%>
			<li id="signup" class="right"><a class="fa fa-user-plus"
				href="registration.jsp">&nbsp;Signup</a></li>
			<li id="login" class="right"><span class="fa fa-sign-in">&nbsp;Login</span>
				<form id="login_form" action="login" method="post">
					<input id="login_username" class="login_field" name="username"
						type="text" placeholder="username" /> <br /> <input
						id="login_password" class="login_field" name="password"
						type="password" placeholder="password" /> <br />
					<div id="submit-div">
						<input type="submit" value="login" />
					</div>
				</form></li>
			<%
				} else {
			%>
			<li id="welcome" class="right"><span class="fa fa-user">&nbsp;<%=session.getAttribute("loggedUser")%></span>
				<div id="profile">
					<ul>
						<li><a href="summary.jsp">Profilo</a></li>
						<li><a href="AllPurchase.jsp">Aquisti</a></li>
						<li><a href="#profilo">Impostazioni</a></li>
						<li><a href="#profilo">Altro 1</a></li>
						<li><a href="#profilo">Altro 2</a></li>
					</ul>

					<form id="logout" action="logout" method="post">
						<label for="out-btn">Exit&nbsp;</label>
						<button id="out-btn" class="fa fa-sign-out" form="logout"
							type="submit" form="nameform" value="Submit"></button>
					</form>
				</div></li>
			<%
				}
			%>

			<%
				if (session.getAttribute("error") != null) {
					System.out.println("ERROR: " + session.getAttribute("error"));
			%>

			<li id="error" class="right"><span
				style="color: red; background-color: white;"><%=session.getAttribute("error")%></span>
			</li>
			<%
				}
			%>
		</ul>
	</nav>

	<div class="alert success">
		<!--<span class="closebtn">×</span> <strong>Success!</strong> Indicates a
		successful or positive action.-->
	</div>

	<div class="alert info">
		<span class="closebtn">×</span> <strong>Info!</strong> Indicates a
		neutral informative change or action.
	</div>

	<div class="alert warning">
		<span class="closebtn">×</span> <strong>Warning!</strong> Indicates a
		warning that might need attention.
	</div>

	<section id="main-section"></section>

	<footer>
		<svg height="50px" width="100px"
			style="border: 1px solid black; float: left;">

      <ellipse cx="50%" cy="85%" rx="45%" ry="15%" style="fill:purple;" />
      <ellipse cx="50%" cy="80%" rx="40%" ry="13%" style="fill:lime;" />
      <ellipse cx="50%" cy="75%" rx="35%" ry="10%" style="fill:yellow;" />

      <defs>
        <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="0%">
          <stop offset="0%"
				style="stop-color:rgb(255,44,140);stop-opacity:1" />
          <stop offset="100%"
				style="stop-color:rgb(0,0,255);stop-opacity:1" />
        </linearGradient>
      </defs>

      <ellipse cx="50%" cy="50%" rx="20%" ry="20%" fill="url(#grad1)" />
      <text fill="#ffffff" font-size="100%" font-family="Verdana"
				x="50%" y="50%" text-anchor="middle" alignment-baseline="middle"
				style="dominant-baseline: middle;">SC</text>

      <ellipse cx="50%" cy="25%" rx="35%" ry="10%" style="fill:yellow;" />
      <ellipse cx="50%" cy="20%" rx="40%" ry="13%" style="fill:lime;" />
      <ellipse cx="50%" cy="15%" rx="45%" ry="15%" style="fill:purple;" />
      Sorry, your browser does not support inline SVG.
    </svg>

		<p>Copyright &copy; Maurizio Casciano</p>
	</footer>

	<script src="js/login.js"></script>
	<script src="js/loadXML.js"></script>
	<script>
		//window.onload = loadXMLDoc("xml/catalog.xml");
	</script>
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


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>

	<script src="js/cart.js"></script>
	<script>
		totale = document.getElementById("totale");

		function updateCart(xml) {
			var totalElement = xml.getElementsByTagName("total")[0];
			var totalValue = totalElement.childNodes[0].nodeValue;
			//alert("totalValue" + totalValue);
			totale.innerHTML = "&nbsp;&euro;" + totalValue;
		}

		function addToCart(itemID) {
			$.ajax({
				type : "POST",
				data : {
					itemID : itemID
				},
				url : "OrderPage",
				success : function(xml) {
					//alert("XML: " + xml);
					updateCart(xml);
					$("div.success").text("Prodotto aggiunto al carrello.");
					$("div.success").fadeIn(300).delay(1500).fadeOut(600);
					//window.location.reload(true);/*Alternativa all'invio dell'xml con il totale*/
				}
			});
		}
	</script>

	<script>
		var close = document.getElementsByClassName("closebtn");
		var i;

		for (i = 0; i < close.length; i++) {
			close[i].onclick = function() {
				var div = this.parentElement;
				div.style.opacity = "0";
				setTimeout(function() {
					div.style.display = "none";
				}, 5000);
			}
		}
	</script>

	<script src="js/sticky-menu.js"></script>
</body>
</html>