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
<link rel="stylesheet" href="css/login.css" />
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
	<jsp:useBean id="shoppingCart" scope="session"
		class="cart.ShoppingCart"></jsp:useBean>
	<header>
		<h1>Nike Mercurial</h1>
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
					<button class="go-button fa fa-search"></button>
					<span class="nav-list"> <select id="dropdown">
							<option value="books-and-ebooks">Books &amp; eBooks</option>
							<option value="audiobooks">Audiobooks</option>
							<option value="dvds">DVDs</option>
							<option value="other-resources">Other Resources</option>
							<option value="random">Random</option>
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
						<li><a href="#profilo">Profilo</a></li>
						<li><a href="#profilo">Aquisti</a></li>
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

	<section id="main-section">
		<%
			Item currentItem = (Item) request.getAttribute("item");
			ArrayList<String> images = currentItem.getImages();
			ArrayList<Detail> details = currentItem.getDettagli();

			if (currentItem != null) {
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

	<footer>
		<svg height="50px" width="100px"
			style="border: 1px solid black; float: left;">

      <ellipse cx="50%" cy="85%" rx="45%" ry="15%" style="fill:purple" />
      <ellipse cx="50%" cy="80%" rx="40%" ry="13%" style="fill:lime" />
      <ellipse cx="50%" cy="75%" rx="35%" ry="10%" style="fill:yellow" />

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

      <ellipse cx="50%" cy="25%" rx="35%" ry="10%" style="fill:yellow" />
      <ellipse cx="50%" cy="20%" rx="40%" ry="13%" style="fill:lime" />
      <ellipse cx="50%" cy="15%" rx="45%" ry="15%" style="fill:purple" />
      Sorry, your browser does not support inline SVG.
    </svg>

		<p>Copyright &copy; Maurizio Casciano</p>
	</footer>

	<!--
  It is a good idea to place scripts at the bottom of the <body> element.
  This can improve page load, because script compilation can slow down the display.
-->

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