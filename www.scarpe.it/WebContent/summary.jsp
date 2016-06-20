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
<link rel="stylesheet" href="css/signup.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
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
					<input id="name" type="text" name="name" placeholder="Nome"
						value="${requestScope.user.name}" readonly />

					<p class="contact">
						<label for="surname">Cognome</label>
					</p>
					<input id="surname" type="text" name="surname"
						placeholder="Cognome" value="${requestScope.user.surname}"
						readonly />


					<p class="contact">
						<label for="birthday">Data di nascita</label>
					</p>
					<input id="birthday" type="date" name="birthday"
						value="${requestScope.user.birthday}" readonly />

					<p class="contact">
						<label for="codiceFiscale">Codice fiscale</label>
					</p>
					<input id="cf" type="text" name="codiceFiscale" maxlength="16"
						pattern=".{16,16}" placeholder="Codice fiscale"
						value="${requestScope.user.codiceFiscale}" readonly />
				</fieldset>

				<fieldset id="access-data">
					<legend>Dati di Accesso</legend>
					<p class="contact">
						<label for="email">Email</label>
					</p>
					<input id="email" name="email" placeholder="example@domain.com"
						type="email" value="${requestScope.user.email}" readonly />

					<p class="contact">
						<label for="username">Username</label>
					</p>
					<input id="username" name="username" placeholder="Username"
						type="text" value="${requestScope.user.username}" readonly />


					<p class="contact">
						<label for="password">Password</label>
					</p>
					<input type="password" id="password" name="password"
						placeholder="Password"
						pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
						value="${requestScope.user.password}" readonly />


					<p class="contact">
						<label for="repassword">Conferma password</label>
					</p>
					<input type="password" id="repassword" name="repassword"
						placeholder="Password"
						pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
						value="${requestScope.user.repassword}" readonly />

				</fieldset>
			</div>

			<div class="layer">
				<fieldset id="home-address">
					<legend>Indirizzo di Residenza</legend>
					<p class="contact">
						<label for="homeStreet">Via</label>
					</p>
					<input type="text" id="homeStreet" name="homeStreet"
						placeholder="Via" value="${requestScope.user.homeStreet}" readonly />


					<p class="contact">
						<label for="homeStreetNumber">Numero Civico</label>
					</p>
					<input type="text" id="homeStreetNumber" name="homeStreetNumber"
						placeholder="Numero Civico"
						value="${requestScope.user.homeStreetNumber}" readonly />

					<p class="contact">
						<label for="homeProvince">Provincia</label>
					</p>

					<input id="homeProvince" name="homeProvince" type="text"
						value="${requestScope.user.homeProvince}" readonly />

					<p class="contact">
						<label for="homeCity">Città</label>
					</p>
					<input id="homeCity" name="homeCity" type="text"
						value="${requestScope.user.homeCity}" readonly />

					<p class="contact">
						<label for="homeCap">CAP</label>
					</p>
					<input type="text" id="homeCap" name="homeCap" placeholder="CAP"
						maxlength="5" pattern=".{5,5}"
						value="${requestScope.user.homeCap}" readonly />
				</fieldset>

				<fieldset id="shipping-address"
					style="text-align: left; display: inline;">
					<legend>Indirizzo di Spedizione</legend>
					<p class="contact">
						<label for="shippingStreet">Via</label>
					</p>
					<input type="text" id="shippingStreet" name="shippingStreet"
						placeholder="Via" value="${requestScope.user.shippingStreet}"
						readonly />


					<p class="contact">
						<label for="shippingStreetNumber">Numero Civico</label>
					</p>
					<input type="text" id="shippingStreetNumber"
						name="shippingStreetNumber" placeholder="Numero Civico"
						value="${requestScope.user.shippingStreetNumber}" readonly />


					<p class="contact">
						<label for="shippingProvince">Provincia</label>
					</p>

					<input id="shippingProvince" name="shippingProvince" type="text"
						value="${requestScope.user.shippingProvince}" readonly />

					<p class="contact">
						<label for="shippingCity">Città</label>
					</p>

					<input id="shippingCity" name="shippingCity" type="text"
						value="${requestScope.user.shippingCity}" readonly />

					<p class="contact">
						<label for="shippingCap">CAP</label>
					</p>
					<input type="text" id="shippingCap" name="shippingCap"
						placeholder="CAP" maxlength="5" pattern=".{5,5}"
						value="${requestScope.user.shippingCap}" readonly />
				</fieldset>
			</div>
		</form>

		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<script>
		
	</script>

	<script src="js/signup.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/sticky-menu.js"></script>
	<script src="js/specialSearch.js"></script>

</body>
</html>