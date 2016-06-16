<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<header>
		<h1>Registrazione</h1>
	</header>

	<%@ include file="include/menu.jsp"%>

	<form class="registration-form" action="register" method="get">

		<div class="layer">
			<fieldset id="anagrafica">
				<legend>Dati Anagrafici</legend>

				<p class="contact">
					<label for="name">Nome</label>
				</p>
				<input <c:if test="${name != null}">class="error"</c:if> id="name"
					type="text" name="name" placeholder="Nome" value="${user.name}" />

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

	<%@ include file="include/footer.jsp"%>

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
