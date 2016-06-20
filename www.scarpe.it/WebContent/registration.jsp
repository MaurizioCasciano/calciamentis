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
<link rel="stylesheet" href="css/footer.css" />

<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
	<div class="wrapper">
		<header>
			<h1>Registrazione</h1>
		</header>

		<%@ include file="include/menu.jsp"%>

		<form class="registration-form" action="register" method="post">

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
					<input <c:if test="${surname != null}">class="error"</c:if>
						id="surname" type="text" name="surname" placeholder="Cognome"
						value="${user.surname}" />


					<p class="contact">
						<label for="birthday">Data di nascita</label>
					</p>
					<input <c:if test="${birthday != null}">class="error"</c:if>
						id="birthday" type="date" name="birthday" value="${user.birthday}" 
						pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$"/>

					<p class="contact">
						<label for="codiceFiscale">Codice fiscale</label>
					</p>
					<input <c:if test="${codiceFiscale != null}">class="error"</c:if>
						id="cf" type="text" name="codiceFiscale" maxlength="16"
						pattern=".{16,16}" placeholder="Codice fiscale"
						value="${user.codiceFiscale}" />
				</fieldset>

				<fieldset id="access-data">
					<legend>Dati di Accesso</legend>
					<p class="contact">
						<label for="email">Email</label>
					</p>
					<input <c:if test="${email != null}">class="error"</c:if>
						id="email" name="email" placeholder="example@domain.com"
						type="email" value="${user.email}" />

					<p class="contact">
						<label for="username">Username</label>
					</p>
					<input <c:if test="${username != null}">class="error"</c:if>
						id="username" name="username" placeholder="Username" type="text"
						value="${user.username}" />


					<p class="contact">
						<label for="password">Password</label>
					</p>
					<input <c:if test="${password != null}">class="error"</c:if>
						type="password" id="password" name="password"
						placeholder="Password"
						pattern="^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\S+$).{8,})$"
						value="${user.password}" />


					<p class="contact">
						<label for="repassword">Conferma password</label>
					</p>
					<input <c:if test="${repassword != null}">class="error"</c:if>
						type="password" id="repassword" name="repassword"
						placeholder="Password"
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
					<input <c:if test="${homeStreet != null}">class="error"</c:if>
						type="text" id="homeStreet" name="homeStreet" placeholder="Via"
						value="${user.homeStreet}" />

					<p class="contact">
						<label for="homeStreetNumber">Numero Civico</label>
					</p>
					<input
						<c:if test="${homeStreetNumber != null}">class="error"</c:if>
						type="text" id="homeStreetNumber" name="homeStreetNumber"
						placeholder="Numero Civico" value="${user.homeStreetNumber}" />

					<p class="contact">
						<label for="homeProvince">Provincia</label>
					</p>

					<select id="homeProvince" name="homeProvince"
						onchange="getComuni('homeCity', this.value)">
					</select>

					<p class="contact">
						<label for="homeCity">Città</label>
					</p>
					<select id="homeCity" name="homeCity" style="display: none;">
					</select>

					<p class="contact">
						<label for="homeCap">CAP</label>
					</p>
					<input <c:if test="${homeCap != null}">class="error"</c:if>
						type="text" id="homeCap" name="homeCap" placeholder="CAP"
						maxlength="5" pattern=".{5,5}" value="${user.homeCap}" />
				</fieldset>

				<fieldset id="shipping-address"
					style="text-align: left; display: inline;">
					<legend>Indirizzo di Spedizione</legend>
					<p class="contact">
						<label for="shippingStreet">Via</label>
					</p>
					<input type="text"
						<c:if test="${shippingStreet != null}">class="error"</c:if>
						id="shippingStreet" name="shippingStreet" placeholder="Via"
						value="${user.shippingStreet}" />


					<p class="contact">
						<label for="shippingStreetNumber">Numero Civico</label>
					</p>
					<input
						<c:if test="${shippingStreetNumber != null}">class="error"</c:if>
						type="text" id="shippingStreetNumber" name="shippingStreetNumber"
						placeholder="Numero Civico" value="${user.shippingStreetNumber}" />


					<p class="contact">
						<label for="shippingProvince">Provincia</label>
					</p>

					<select id="shippingProvince" name="shippingProvince"
						onchange="getComuni('shippingCity', this.value)">
					</select>

					<p class="contact">
						<label for="shippingCity">Città</label>
					</p>

					<select id="shippingCity" name="shippingCity"
						style="display: none;">
					</select>

					<p class="contact">
						<label for="shippingCap">CAP</label>
					</p>
					<input <c:if test="${shippingCap != null}">class="error"</c:if>
						type="text" id="shippingCap" name="shippingCap" placeholder="CAP"
						maxlength="5" pattern=".{5,5}" value="${user.shippingCap}" />
				</fieldset>
			</div>

			<div class="layer">
				<fieldset style="text-align: center;">
					<input type="submit" value="Sign Up" />
				</fieldset>
			</div>

		</form>

		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<script src="js/login.js"></script>
	<script src="js/signup.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/sticky-menu.js"></script>

	<script src="js/city.js"></script>
	<script src="js/specialSearch.js"></script>
</body>
</html>
