<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it-IT">
<head>
<title>Registrazione</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Registrazione" />
<meta name="description" content="Registrazione utente" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/signup.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />

<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>

<body>
	<header>
		<h1>Registrazione</h1>
	</header>

	<nav>
		<ul>
			<li><a href="index.jsp"><span class="fa fa-home"></span></a></li>
			<li><a href="carrello.jsp"><span class="fa fa-shopping-cart"></span></a></li>

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
					type="submit" form="nameform" value="Submit"><span class = "fa fa-sign-out"></span></button> <%
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
		<form id="signup-form" class="form" action="register" method="get">
			<!-- action="javascript:registration();" -->

			<fieldset id="anagrafica"
				style="text-align: center; display: inline;">
				<legend>Dati Anagrafici</legend>

				<p class="contact">
					<label for="name">Nome</label>
				</p>
				<input id="name" type="text" name="name" placeholder="Nome"
					value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>" />
				<span class="error"><%=request.getAttribute("name") != null ? request.getAttribute("name") : ""%></span>
				<!-- required -->

				<p class="contact">
					<label for="surname">Cognome</label>
				</p>
				<input id="surname" type="text" name="surname" placeholder="Cognome"
					value="<%=request.getParameter("surname") != null ? request.getParameter("surname") : ""%>" />
				<span class="error"><%=request.getAttribute("surname") != null ? request.getAttribute("surname") : ""%></span>

				<p class="contact">
					<label for="birthday">Data di nascita</label>
				</p>
				<input id="birthday" type="date" name="birthday"
					value="<%=request.getParameter("birthday") != null ? request.getParameter("birthday") : ""%>" />
				<span class="error"><%=request.getAttribute("birthday") != null ? request.getAttribute("birthday") : ""%></span>


				<p class="contact">
					<label for="cf">Codice fiscale</label>
				</p>
				<input id="cf" type="text" name="cf" maxlength="16"
					pattern=".{16,16}" placeholder="Codice fiscale"
					value="<%=request.getParameter("cf") != null ? request.getParameter("cf") : ""%>" />
				<span class="error"><%=request.getAttribute("cf") != null ? request.getAttribute("cf") : ""%></span>
			</fieldset>

			<fieldset id="access-data"
				style="text-align: center; display: inline;">
				<legend>Dati di Accesso</legend>
				<p class="contact">
					<label for="email">Email</label>
				</p>
				<input id="email" name="email" placeholder="example@domain.com"
					type="email"
					value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>" />
				<span class="error"><%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%></span>


				<p class="contact">
					<label for="username">Username</label>
				</p>
				<input id="username" name="username" placeholder="Username"
					tabindex="2" type="text"
					value="<%=request.getParameter("username") != null ? request.getParameter("username") : ""%>" />
				<span class="error"><%=request.getAttribute("username") != null ? request.getAttribute("username") : ""%></span>

				<p class="contact">
					<label for="password">Password</label>
				</p>
				<input type="password" id="password" name="password"
					placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					required
					value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>" />
				<span class="error"><%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%></span>

				<p class="contact">
					<label for="repassword">Conferma password</label>
				</p>
				<input type="password" id="repassword" name="repassword"
					placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					required
					value="<%=request.getParameter("repassword") != null ? request.getParameter("repassword") : ""%>" />
				<span class="error"><%=request.getAttribute("repassword") != null ? request.getAttribute("repassword") : ""%></span>
			</fieldset>

			<fieldset id="home-address"
				style="text-align: left; display: inline;">
				<legend>Indirizzo di Residenza</legend>
				<p class="contact">
					<label for="home-street">Via</label>
				</p>
				<input type="text" id="home-street" name="home-street"
					placeholder="Via"
					value="<%=request.getParameter("home-street") != null ? request.getParameter("home-street") : ""%>" />
				<span class="error"><%=request.getAttribute("home-street") != null ? request.getAttribute("home-street") : ""%></span>

				<p class="contact">
					<label for="home-street-number">Numero Civico</label>
				</p>
				<input type="text" id="home-street-number" name="home-street-number"
					placeholder="Numero Civico"
					value="<%=request.getParameter("home-street-number") != null ? request.getParameter("home-street-number")
					: ""%>" />
				<span class="error"><%=request.getAttribute("home-street-number") != null ? request.getAttribute("home-street-number")
					: ""%></span>

				<p class="contact">
					<label for="home-city">Città</label>
				</p>
				<input type="text" id="home-city" name="home-city"
					placeholder="Città"
					value="<%=request.getParameter("home-city") != null ? request.getParameter("home-city") : ""%>" />
				<span class="error"><%=request.getAttribute("home-city") != null ? request.getAttribute("home-city") : ""%></span>


				<p class="contact">
					<label for="home-province">Provincia</label>
				</p>
				<input type="text" id="home-province" name="home-province"
					placeholder="Provincia"
					value="<%=request.getParameter("home-province") != null ? request.getParameter("home-province") : ""%>" />
				<span class="error"><%=request.getAttribute("home-province") != null ? request.getAttribute("home-province") : ""%></span>


				<p class="contact">
					<label for="home-cap">CAP</label>
				</p>
				<input type="text" id="home-cap" name="home-cap" placeholder="CAP"
					maxlength="5" pattern=".{5,5}"
					value="<%=request.getParameter("home-cap") != null ? request.getParameter("home-cap") : ""%>" />
				<span class="error"><%=request.getAttribute("home-cap") != null ? request.getAttribute("home-cap") : ""%></span>
			</fieldset>

			<fieldset id="shipping-address"
				style="text-align: left; display: inline;">
				<legend>Indirizzo di Spedizione</legend>
				<p class="contact">
					<label for="shipping-street">Via</label>
				</p>
				<input type="text" id="shipping-street" name="shipping-street"
					placeholder="Via"
					value="<%=request.getParameter("shipping-street") != null ? request.getParameter("shipping-street") : ""%>" />
				<span class="error"><%=request.getAttribute("shipping-street") != null ? request.getAttribute("shipping-street") : ""%></span>

				<p class="contact">
					<label for="shipping-street-number">Numero Civico</label>
				</p>
				<input type="text" id="shipping-street-number"
					name="shipping-street-number" placeholder="Numero Civico"
					value="<%=request.getParameter("shipping-street-number") != null
					? request.getParameter("shipping-street-number") : ""%>" />
				<span class="error"><%=request.getAttribute("shipping-street-number") != null
					? request.getAttribute("shipping-street-number") : ""%></span>

				<p class="contact">
					<label for="shipping-city">Città</label>
				</p>
				<input type="text" id="shipping-city" name="shipping-city"
					placeholder="Città"
					value="<%=request.getParameter("shipping-city") != null ? request.getParameter("shipping-city") : ""%>" />
				<span class="error"><%=request.getAttribute("shipping-cap") != null ? request.getAttribute("shipping-cap") : ""%></span>

				<p class="contact">
					<label for="shipping-province">Provincia</label>
				</p>
				<input type="text" id="shipping-province" name="shipping-province"
					placeholder="Provincia"
					value="<%=request.getParameter("shipping-province") != null ? request.getParameter("shipping-province") : ""%>" />
				<span class="error"><%=request.getAttribute("shipping-province") != null ? request.getAttribute("shipping-province") : ""%></span>

				<p class="contact">
					<label for="shipping-cap">CAP</label>
				</p>
				<input type="text" id="shipping-cap" name="shipping-cap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}"
					value="<%=request.getParameter("shipping-cap") != null ? request.getParameter("shipping-cap") : ""%>" />
				<span class="error"><%=request.getAttribute("shipping-cap") != null ? request.getAttribute("shipping-cap") : ""%></span>
			</fieldset>

			<fieldset id="payment-method"
				style="text-align: left; display: block;">
				<legend>Metodo di Pagamento</legend>
				<!--"required" attribute needed for just one input of type radio.-->
				<!--"name" attribute needed for making all radio buttons mutually exclusive.-->

				<input type="radio" id="bonifico" name="pay_method"
					value="Bonifico Bancario" onchange="modeBonificoBancario();" />Bonifico
				Bancario <br /> <input type="radio" id="carta-di-credito"
					name="pay_method" value="Carta di Credito"
					onchange="modeCartaDiCredito();" />Carta di Credito<br />

				<div id="payMethodExtra"
					style="background-color: lightblue; padding: 5px;"></div>
			</fieldset>

			<input type="submit" value="Sign Up" />
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

	<script src="js/login.js"></script>
	<script src="js/signup.js"></script>

</body>
</html>
