<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Summary</title>

<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Leather, FG" />
<meta name="description"
	content="Scarpa da calcio Nike Mercurial Leather FG" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/signup.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/search.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<%
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String birthday = request.getParameter("birthday");
		String cf = request.getParameter("cf");

		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		String homeStreet = request.getParameter("home-street");
		String homeStreetNumber = request.getParameter("home-street-number");
		String homeCity = request.getParameter("home-city");
		String homeProvince = request.getParameter("home-province");
		String homeCap = request.getParameter("home-cap");

		String shippingStreet = request.getParameter("shipping-street");
		String shippingStreetNumber = request.getParameter("shipping-street-number");
		String shippingCity = request.getParameter("shipping-city");
		String shippingProvince = request.getParameter("shipping-province");
		String shippingCap = request.getParameter("shipping-cap");

		String payMethod = request.getParameter("pay_method");
		String iban = request.getParameter("iban");
		String cardNumber = request.getParameter("card-number");
		String expirationDate = request.getParameter("expiration-date");
	%>

	<header>
		<h1>Riepilogo dei Dati</h1>
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

	<div id="form-div">
		<h2>Registrazione avvenuta con successo</h2>

		<form id="contact-form" class="registration-form" method="get">

			<fieldset id="anagrafica"
				style="text-align: center; display: inline;">
				<legend>Dati Anagrafici</legend>

				<p class="contact">
					<label for="name">Nome</label>
				</p>
				<input id="name" type="text" name="name" placeholder="Nome" readonly
					required value="<%=name%>" />

				<p class="contact">
					<label for="surname">Cognome</label>
				</p>
				<input id="surname" type="text" name="surname" placeholder="Cognome"
					readonly required value="<%=surname%>" />

				<p class="contact">
					<label for="birthday">Data di nascita</label>
				</p>
				<input id="birthday" type="date" name="birthday" readonly required
					value="<%=birthday%>" />

				<p class="contact">
					<label for="cf">Codice fiscale</label>
				</p>
				<input id="cf" type="text" name="cf" maxlength="16"
					pattern=".{16,16}" placeholder="Codice fiscale" readonly required
					value="<%=cf%>" />
			</fieldset>


			<fieldset id="access-data"
				style="text-align: center; display: inline;">
				<legend>Dati di Accesso</legend>
				<p class="contact">
					<label for="email">Email</label>
				</p>
				<input id="email" name="email" placeholder="example@domain.com"
					readonly required type="email" value="<%=email%>" />

				<p class="contact">
					<label for="username">Username</label>
				</p>
				<input id="username" name="username" placeholder="Username" readonly
					required tabindex="2" type="text" value="<%=username%>" />

				<p class="contact">
					<label for="password">Password</label>
				</p>
				<input type="password" id="password" name="password"
					placeholder="Password" readonly required value="<%=password%>" />
				<p class="contact">
					<label for="repassword">Conferma password</label>
				</p>
				<input type="password" id="repassword" name="repassword"
					placeholder="Password" readonly required value="<%=repassword%>" />
			</fieldset>


			<fieldset id="home-address"
				style="text-align: left; display: inline;">
				<legend>Indirizzo di Residenza</legend>
				<p class="contact">
					<label for="home-street">Via</label>
				</p>
				<input type="text" id="home-street" name="home-street"
					placeholder="Via" readonly required value="<%=homeStreet%>" />

				<p class="contact">
					<label for="home-street-number">Numero Civico</label>
				</p>
				<input type="text" id="home-street-number" name="home-street-number"
					placeholder="Numero Civico" readonly required
					value="<%=homeStreetNumber%>" />

				<p class="contact">
					<label for="home-province">Provincia</label>
				</p>
				<input type="text" id="home-province" name="home-province"
					placeholder="Provincia" readonly required value="<%=homeProvince%>" />

				<p class="contact">
					<label for="home-city">Città</label>
				</p>
				<input type="text" id="home-city" name="home-city"
					placeholder="Città" readonly required value="<%=homeCity%>" />

				<p class="contact">
					<label for="home-cap">CAP</label>
				</p>
				<input type="text" id="home-cap" name="home-cap" placeholder="CAP"
					maxlength="5" pattern=".{5,5}" readonly required
					value="<%=homeCap%>" />
			</fieldset>


			<fieldset id="shipping-address"
				style="text-align: left; display: inline;">
				<legend>Indirizzo di Spedizione</legend>
				<p class="contact">
					<label for="shipping-street">Via</label>
				</p>
				<input type="text" id="shipping-street" name="shipping-street"
					placeholder="Via" readonly required value="<%=shippingStreet%>" />

				<p class="contact">
					<label for="shipping-street-number">Numero Civico</label>
				</p>
				<input type="text" id="shipping-street-number"
					name="shipping-street-number" placeholder="Numero Civico" readonly
					required value="<%=shippingStreetNumber%>" />

				<p class="contact">
					<label for="shipping-province">Provincia</label>
				</p>
				<input type="text" id="shipping-province" name="shipping-province"
					placeholder="Provincia" readonly required
					value="<%=shippingProvince%>" />

				<p class="contact">
					<label for="shipping-city">Città</label>
				</p>
				<input type="text" id="shipping-city" name="shipping-city"
					placeholder="Città" readonly required value="<%=shippingCity%>" />



				<p class="contact">
					<label for="shipping-cap">CAP</label>
				</p>
				<input type="text" id="shipping-cap" name="shipping-cap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}" readonly required
					value="<%=shippingCap%>" />
			</fieldset>

			<fieldset id="payment-method"
				style="text-align: left; display: block;">
				<legend>Metodo di Pagamento</legend>

				<!-- <input type="text" id="bonifico" name="pay_method"
					value="<%=payMethod%>" readonly />-->

				<input type="radio" id="bonifico" name="pay_method"
					value="Bonifico Bancario" onchange="modeBonificoBancario();"
					required />Bonifico Bancario<br /> <input type="radio"
					id="carta-di-credito" name="pay_method" value="Carta di Credito"
					onchange="modeCartaDiCredito();" />Carta di Credito<br />

				<div id="payMethodExtra"
					style="background-color: lightblue; padding: 5px;"></div>
			</fieldset>

			<input type="button" onclick="window.location.replace('index.jsp')"
				value="Close" />
		</form>
	</div>

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

	<script>
		var payMethod =
	"<%=payMethod%>";
		var iban =
	"<%=iban%>";
		var cardNumber =
	"<%=cardNumber%>";
		var expirationDate =
	"<%=expirationDate%>
		";

		window.onload = function() {
			if (payMethod === "Bonifico Bancario") {
				//$("#bonifico").trigger('click');
				$("#bonifico").click()
				$("#iban").attr("readonly", "true");
				$("#iban").attr("value", iban);
			} else if (payMethod === "Carta di Credito") {
				//$("#carta-di-credito").trigger('click');
				$("#carta-di-credito").click();
				$("#card-number").attr("readonly", "true");
				$("#card-number").attr("value", cardNumber);

				$("#expiration-date").attr("readonly", "true");
				$("#expiration-date").attr("value", expirationDate);
			}

			document.getElementById("bonifico").setAttribute("onclick",
					"return false");
			document.getElementById("carta-di-credito").setAttribute("onclick",
					"return false");
		}
	</script>

	<script src="js/signup.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</body>
</html>