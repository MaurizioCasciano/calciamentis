<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="administration.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Amministrazione</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<!-- <link rel="stylesheet" href="css/main.css" /> -->
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/alert.css">
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="js/alert.js"></script>
</head>
<body>
	<div class="wrapper">
		<c:if test="${sessionScope.loggedAdmin != null}">
			<script>
				$(document).ready(function() {
					document.location.href = "management.jsp"
				});
			</script>
		</c:if>
		<c:if test="${requestScope.error != null}">
			<script>
				$(document).ready(function() {
					showWarning("Invalid username or password !");
				});
			</script>
		</c:if>
		<header>
			<h1>Gestione</h1>
		</header>
		<div class="alert success"></div>
		<div class="alert info"></div>
		<div class="alert warning"></div>
		<div id="content">
			<div id="formAccessoAdmin">
				<form action="AdminAccess" method="post">
					<fieldset>
						<legend>Inserimento Credenziali</legend>
						<label>Nome Utente</label> <br /> <input type="text"
							name="adminUsername" placeholder="nome utente"> <br /> <label>Password</label>
						<br /> <input type="password" name="adminPassword"
							placeholder="password"> <br /> <input type="submit"
							name="submit" value="Accedi">
					</fieldset>
				</form>
				<a href="localhost:8080/www.scarpe.it">scarpe.it</a>
			</div>
		</div>
		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>