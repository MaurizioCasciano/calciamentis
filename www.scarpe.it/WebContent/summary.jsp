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
<link rel="stylesheet" href="css/signup.css" />
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
	<header>
		<h1>Riepilogo dei Dati</h1>
	</header>

	<%@ include file="include/menu.jsp"%>

	<form class="registration-form" action="register" method="get">

		<div class="layer">
			<fieldset id="anagrafica">
				<legend>Dati Anagrafici</legend>

				<p class="contact">
					<label for="name">Nome</label>
				</p>
				<input <%if (request.getAttribute("name") != null) {%> class="error"
					<%}%> id="name" type="text" name="name" placeholder="Nome"
					value="${user.name}" readonly />

				<p class="contact">
					<label for="surname">Cognome</label>
				</p>
				<input <%if (request.getAttribute("surname") != null) {%>
					class="error" <%}%> id="surname" type="text" name="surname"
					placeholder="Cognome" value="${user.surname}" readonly />


				<p class="contact">
					<label for="birthday">Data di nascita</label>
				</p>
				<input <%if (request.getAttribute("birthday") != null) {%>
					class="error" <%}%> id="birthday" type="date" name="birthday"
					value="${user.birthday}" readonly />

				<p class="contact">
					<label for="codiceFiscale">Codice fiscale</label>
				</p>
				<input <%if (request.getAttribute("codiceFiscale") != null) {%>
					class="error" <%}%> id="cf" type="text" name="codiceFiscale"
					maxlength="16" pattern=".{16,16}" placeholder="Codice fiscale"
					value="${user.codiceFiscale}" readonly />
			</fieldset>

			<fieldset id="access-data">
				<legend>Dati di Accesso</legend>
				<p class="contact">
					<label for="email">Email</label>
				</p>
				<input <%if (request.getAttribute("email") != null) {%>
					class="error" <%}%> id="email" name="email"
					placeholder="example@domain.com" type="email" value="${user.email}"
					readonly />

				<p class="contact">
					<label for="username">Username</label>
				</p>
				<input <%if (request.getAttribute("username") != null) {%>
					class="error" <%}%> id="username" name="username"
					placeholder="Username" type="text" value="${user.username}"
					readonly />


				<p class="contact">
					<label for="password">Password</label>
				</p>
				<input <%if (request.getAttribute("password") != null) {%>
					class="error" <%}%> type="password" id="password" name="password"
					placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					value="${user.password}" readonly />


				<p class="contact">
					<label for="repassword">Conferma password</label>
				</p>
				<input <%if (request.getAttribute("repassword") != null) {%>
					class="error" <%}%> type="password" id="repassword"
					name="repassword" placeholder="Password"
					pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
					value="${user.repassword}" readonly />

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
					placeholder="Via" value="${user.homeStreet}" readonly />


				<p class="contact">
					<label for="homeStreetNumber">Numero Civico</label>
				</p>
				<input <%if (request.getAttribute("homeStreetNumber") != null) {%>
					class="error" <%}%> type="text" id="homeStreetNumber"
					name="homeStreetNumber" placeholder="Numero Civico"
					value="${user.homeStreetNumber}" readonly />

				<p class="contact">
					<label for="homeProvince">Provincia</label>
				</p>

				<input id="homeProvince" name="homeProvince" type="text"
					value="${user.homeProvince}" readonly />

				<p class="contact">
					<label for="homeCity">Città</label>
				</p>
				<input id="homeCity" name="homeCity" type="text"
					value="${user.homeCity}" readonly />

				<p class="contact">
					<label for="homeCap">CAP</label>
				</p>
				<input <%if (request.getAttribute("homeCap") != null) {%>
					class="error" <%}%> type="text" id="homeCap" name="homeCap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}"
					value="${user.homeCap}" readonly />
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
					placeholder="Via" value="${user.shippingStreet}" readonly />


				<p class="contact">
					<label for="shippingStreetNumber">Numero Civico</label>
				</p>
				<input
					<%if (request.getAttribute("shippingStreetNumber") != null) {%>
					class="error" <%}%> type="text" id="shippingStreetNumber"
					name="shippingStreetNumber" placeholder="Numero Civico"
					value="${user.shippingStreetNumber}" readonly />


				<p class="contact">
					<label for="shippingProvince">Provincia</label>
				</p>

				<input id="shippingProvince" name="shippingProvince" type="text"
					value="${user.shippingProvince}" readonly />

				<p class="contact">
					<label for="shippingCity">Città</label>
				</p>

				<input id="shippingCity" name="shippingCity" type="text"
					value="${user.shippingCity}" readonly />

				<p class="contact">
					<label for="shippingCap">CAP</label>
				</p>
				<input <%if (request.getAttribute("shippingCap") != null) {%>
					class="error" <%}%> type="text" id="shippingCap" name="shippingCap"
					placeholder="CAP" maxlength="5" pattern=".{5,5}"
					value="${user.shippingCap}" readonly />
			</fieldset>
		</div>
	</form>

	<%@ include file="include/footer.jsp"%>

	<script>
		
	</script>

	<script src="js/signup.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/sticky-menu.js"></script>
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