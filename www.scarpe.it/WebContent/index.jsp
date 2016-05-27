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
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/search.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<header>
		<h1>Scarpe da calcio</h1>
	</header>

	<nav>
		<ul>
			<li><a href="index.jsp"><span class="fa fa-home"></span></a></li>
			<li><a href="carrello.jsp"><span class="fa fa-shopping-cart"></span></a></li>
			<li><form class="search" action="">
					<select>
						<option value="option0">Tutte le categorie</option>
						<option value="option1">Option 1</option>
						<option value="option2">Option 2</option>
						<option value="option3">Option 3</option>
						<option value="option4">Option 4</option>
						<option value="option5">Option 5</option>
						<option value="option6">Option 6</option>
					</select> <input type="search" placeholder="Cerca" />
					<button>
						<span class="fa fa-search"></span>
					</button>
				</form></li>

			<%
				if (session.getAttribute("loggedUser") == null) {
			%>
			<li id="signup" class="access"><a href="registration.jsp"><span
					class="fa fa-user-plus"></span></a></li>
			<li id="login" class="access"><a href="#"><span
					class="fa fa-sign-in"></span>&nbsp;</a>

				<form id="login_form" action="login" method="post">

					<input id="login_username" class="login_field" name="username"
						type="text" placeholder="username" /><br /> <input
						id="login_password" class="login_field" name="password"
						type="password" placeholder="password" /><br />
					<div id="submit-div">
						<input type="submit" value="login" />
					</div>
					<%
						}
					%>
				</form></li>
			<li id="exit" class="access">
				<%
					if (session.getAttribute("loggedUser") != null) {
						System.out.println(
								"LoggedUser: " + session.getAttribute("loggedUser") + "\t" + new GregorianCalendar().getTime());
				%>

				<form id="logout" action="logout" method="post">
					<input id="logged-username" name="logged-username" type="hidden"
						value=<%=session.getAttribute("loggedUser")%> />

				</form>
				<button form="logout" style='color: white; background-color: blue;'
					type="submit" form="nameform" value="Submit">
					<span class="fa fa-sign-out"></span>
				</button> <%
 	}
 %>
			</li>
			<li id="welcome" class="access">
				<%
					if (session.getAttribute("loggedUser") != null) {
						System.out.println("Benvenuto " + session.getAttribute("loggedUser"));
				%><span style="color: white;"><%="Benvenuto " + session.getAttribute("loggedUser")%></span>
				<%
					}
				%>
			</li>
			<li id="error" class="access">
				<%
					if (session.getAttribute("error") != null) {
						System.out.println("ERROR: " + session.getAttribute("error"));
				%><span style="color: red; background-color: white;"><%=session.getAttribute("error")%></span>
				<%
					}
				%>
			</li>
		</ul>
	</nav>

	<section id="main-section">
		<h2>Scarpe da calcio</h2>
	</section>

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
		var mainSection = document.getElementById("main-section");
		var xmlhttp;

		if (window.XMLHttpRequest) {
			// code for modern browsers
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			alert("ReadyState: " + xmlhttp.readyState + " Status: "
					+ xmlhttp.status);

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				alert("xhttp: " + xmlhttp.responseText)
				mainSection.innerHTML = xmlhttp.responseText;
			}
		};

		xmlhttp.open("GET", "CatalogPage", true);
		xmlhttp.send();
	</script>
</body>
</html>