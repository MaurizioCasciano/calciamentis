<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="utilities.user.User" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="user"></jsp:setProperty>

<!DOCTYPE html>
<html lang="it-IT">
<head>
<title>Registrazione</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Registrazione" />
<meta name="description" content="Registrazione utente" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/signup.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/font-awesome.css" />
<link rel="stylesheet" href="css/error.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />

<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>

	user.tostring = ${user.string}

	<header>
		<h1>Registrazione</h1>
	</header>

	<nav class="menu">
		<ul>
			<li class="left"><a class="fa fa-home" href="index.jsp">&nbsp;Home</a>
			</li>
			<li class="left"><a class="fa fa-shopping-cart"
				href="carrello.jsp">&nbsp;Carrello</a></li>

			<li class="left"><span id="totale" class="fa fa-money">&nbsp;&euro;${shoppingCart == null ? 0.0 : shoppingCart.totale}</span></li>

			<li id="search" class="left">
				<div id="form-wrapper">
					<button onclick="specialSearch()" class="go-button fa fa-search"></button>
					<span class="nav-list"> <select id="dropdown">
							<option value="f0">Tutti i prezzi</option>
							<option value="f1">50&euro; -100&euro;</option>
							<option value="f2">100&euro; -200&euro;</option>
							<option value="f3">200&euro; -300&euro;</option>
							<option value="f4">300&euro; -500&euro;</option>
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

	<form class="registration-form" action="register" method="get">

		<div class="layer">
			<fieldset id="anagrafica">
				<legend>Dati Anagrafici</legend>

				<p class="contact">
					<label for="name">Nome</label>
				</p>
				<input <%if (request.getAttribute("name") != null) {%> class="error"
					<%}%> id="name" type="text" name="name" placeholder="Nome"
					value="${user.name}" />

				<p class="contact">
					<label for="surname">Cognome</label>
				</p>
				<input <%if (request.getAttribute("surname") != null) {%>
					class="error" <%}%> id="surname" type="text" name="surname"
					placeholder="Cognome" value="${user.surname}" />


				<p class="contact">
					<label for="birthday">Data di nascita</label>
				</p>
				<input <%if (request.getAttribute("birthday") != null) {%>
					class="error" <%}%> id="birthday" type="date" name="birthday"
					value="${user.birthday}" />

				<p class="contact">
					<label for="codiceFiscale">Codice fiscale</label>
				</p>
				<input <%if (request.getAttribute("codiceFiscale") != null) {%>
					class="error" <%}%> id="cf" type="text" name="codiceFiscale"
					maxlength="16" pattern=".{16,16}" placeholder="Codice fiscale"
					value="${user.codiceFiscale}" />
			</fieldset>

			<fieldset id="access-data">
				<legend>Dati di Accesso</legend>
				<p class="contact">
					<label for="email">Email</label>
				</p>
				<input <%if (request.getAttribute("email") != null) {%>
					class="error" <%}%> id="email" name="email"
					placeholder="example@domain.com" type="email" value="${user.email}" />

				<p class="contact">
					<label for="username">Username</label>
				</p>
				<input <%if (request.getAttribute("username") != null) {%>
					class="error" <%}%> id="username" name="username"
					placeholder="Username" type="text" value="${user.username}" />


				<p class="contact">
					<label for="password">Password</label>
				</p>
				<input <%if (request.getAttribute("password") != null) {%>
					class="error" <%}%> type="password" id="password" name="password"
					placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					value="${user.password}" />


				<p class="contact">
					<label for="repassword">Conferma password</label>
				</p>
				<input <%if (request.getAttribute("repassword") != null) {%>
					class="error" <%}%> type="password" id="repassword"
					name="repassword" placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					value="${user.repassword}" />

			</fieldset>
		</div>

		<div class="layer">
			<fieldset id="home-address">
				<legend>Indirizzo di Residenza</legend>
				<p class="contact">
					<label for="homeStreet">Via</label>
				</p>
				<input <%if (request.getAttribute("homeStreet") != null) {%>
					class="error" <%}%> type="text" id="homeStreet" name="homeStreet"
					placeholder="Via" value="${user.homeStreet}" />


				<p class="contact">
					<label for="homeStreetNumber">Numero Civico</label>
				</p>
				<input <%if (request.getAttribute("homeStreetNumber") != null) {%>
					class="error" <%}%> type="text" id="homeStreetNumber"
					name="homeStreetNumber" placeholder="Numero Civico"
					value="${user.homeStreetNumber}" />

				<p class="contact">
					<label for="homeProvince">Provincia</label>
				</p>

				<select id="homeProvince" name="homeProvince"
					onchange="getComuni('homeCity', this.value)">
				</select> <span class="error"><%=request.getAttribute("homeProvince") != null ? request.getAttribute("homeProvince") : ""%></span>

				<p class="contact">
					<label for="homeCity">Città</label>
				</p>
				<select id="homeCity" name="homeCity" style="display: none;">
				</select> <span class="error"><%=request.getAttribute("homeCity") != null ? request.getAttribute("homeCity") : ""%></span>

				<p class="contact">
					<label for="homeCap">CAP</label>
				</p>
				<input <%if (request.getAttribute("homeCap") != null) {%>
					class="error" <%}%> type="text" id="homeCap" name="homeCap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}"
					value="${user.homeCap}" />
			</fieldset>

			<fieldset id="shipping-address"
				style="text-align: left; display: inline;">
				<legend>Indirizzo di Spedizione</legend>
				<p class="contact">
					<label for="shippingStreet">Via</label>
				</p>
				<input type="text"
					<%if (request.getAttribute("shippingStreet") != null) {%>
					class="error" <%}%> id="shippingStreet" name="shippingStreet"
					placeholder="Via" value="${user.shippingStreet}" />


				<p class="contact">
					<label for="shippingStreetNumber">Numero Civico</label>
				</p>
				<input
					<%if (request.getAttribute("shippingStreetNumber") != null) {%>
					class="error" <%}%> type="text" id="shippingStreetNumber"
					name="shippingStreetNumber" placeholder="Numero Civico"
					value="${user.shippingStreetNumber}" />


				<p class="contact">
					<label for="shippingProvince">Provincia</label>
				</p>

				<select id="shippingProvince" name="shippingProvince"
					onchange="getComuni('shippingCity', this.value)">
				</select> <span class="error"><%=request.getAttribute("shippingProvince") != null ? request.getAttribute("shippingProvince") : ""%></span>

				<p class="contact">
					<label for="shippingCity">Città</label>
				</p>

				<select id="shippingCity" name="shippingCity" style="display: none;">
				</select>

				<p class="contact">
					<label for="shippingCap">CAP</label>
				</p>
				<input <%if (request.getAttribute("shippingCap") != null) {%>
					class="error" <%}%> type="text" id="shippingCap" name="shippingCap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}"
					value="${user.shippingCap}" />
			</fieldset>
		</div>

		<div class="layer">
			<fieldset id="payment-method">
				<legend>Metodo di Pagamento</legend>
				<!--"required" attribute needed for just one input of type radio.-->
				<!--"name" attribute needed for making all radio buttons mutually exclusive.-->

				<input type="radio" id="bonifico" name="pay_method"
					value="Bonifico Bancario" onchange="modeBonificoBancario();" />Bonifico
				Bancario <br /> <input type="radio" id="carta-di-credito"
					name="pay_method" value="Carta di Credito"
					onchange="modeCartaDiCredito();" />Carta di Credito<br />

				<div id="payMethodExtra"></div>
				<input type="submit" value="Sign Up" />
			</fieldset>
		</div>

	</form>

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

	<script src="js/login.js"></script>
	<script src="js/signup.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/sticky-menu.js"></script>

	<script>
		var homeProvince;
		var shippingProvince;

		$(document).ready(function() {
			//alert("document is ready");

			homeProvince = document.getElementById("homeProvince");
			//alert("homeProvince: " + homeProvince.name);

			shippingProvince = document.getElementById("shippingProvince");
			//alert("shippingProvince: " + shippingProvince.name);

			getProvince(homeProvince);
			getProvince(shippingProvince);
		});

		function getProvince(element) {
			//alert("getting province");
			//alert("ElementName: " + element.name);

			var mode = "?mode=province";
			var xhttp;

			if (window.XMLHttpRequest) {
				// code for modern browsers
				xhttp = new XMLHttpRequest();
			} else {
				// code for IE6, IE5
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			//alert("After xhttp: " + xhttp);

			xhttp.onreadystatechange = function() {
				//alert("readyState " + xhttp.readyState + " status: " + xhttp.status);

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

			//alert("comuniElementId: " + comuniElementId);
			//alert("Select is changing");
			//alert("Getting comuni for: " + provincia);
			var comuniElement = document.getElementById(comuniElementId);
			//alert("comuniElement: " + comuniElement);

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
	</script>
	<script>
		function specialSearch() {
			var cat = document.getElementById("dropdown").value;
			var key = document.getElementById("search-box").value;
			var mainSection = document.getElementById("main-section");

			window.location.replace("index.jsp?cat=" + cat + "&key=" + key);

		}
	</script>
</body>
</html>
