<%@ page import="administration.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestione Sito</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/management.css">
<link rel="stylesheet" href="css/prova.css">
<link rel="stylesheet" href="css/report.css">
<link rel="stylesheet" href="css/alert.css">
<link rel="stylesheet" href="css/adminTable.css">
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>

<body>
	<div class="wrapper">
		<header>
			<h1>Gestione</h1>
		</header>
		<%@ include file="include/menuAdmin.jsp"%>

		<nav id="mainMenu">
			<ul>
				<li>
					<button onclick="setFather();showStuff('viewProducts.jsp')">
						<div class="internalToButton">
							<div class="toLeft fa fa-pencil-square-o"></div>
							<div class="toRight">Visualizza/Modifica Prodotti</div>
						</div>
					</button>
				</li>
				<!-- Fornire il collegamento alle servlet -->
				<li>
					<button onclick="showStuff('addItemPage.jsp')">
						<div class="internalToButton">
							<span class="toLeft fa fa-plus-square"></span> <span
								class="toRight"> Aggiungi Prodotto</span>
						</div>
					</button>
				</li>
				<li>
					<button onclick="setFather();showStuff('viewCustomers.jsp')">
						<div class="internalToButton">
							<div class="toLeft fa fa-user"></div>
							<div class="toRight">Visualizza Info Clienti</div>
						</div>
					</button>
				</li>
				<li>
					<button onclick="setFather();showStuff('viewReport.jsp')">
						<div class="internalToButton">
							<div class="toLeft fa fa-book"></div>
							<div class="toRight">Visualizza Report</div>
						</div>
					</button>
				</li>
				<li>
					<button onclick="showStuff('exportDB.jsp')">
						<div class="internalToButton">
							<div class="toLeft fa fa-download"></div>
							<div class="toRight">Esporta Database</div>
						</div>
					</button>
				</li>
				<li>
					<button onclick="showStuff('importDB.jsp')">
						<div class="internalToButton">
							<div class="toLeft fa fa-upload"></div>
							<div class="toRight">Importa Database</div>
						</div>
					</button>
				</li>
			</ul>
		</nav>
		<section id="div1"></section>

		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script src="js/admin.js"></script>
	<script src="js/alert.js"></script>

	<script>
		function getURLParameter(name) {
			return decodeURIComponent((new RegExp('[?|&]' + name + '='
					+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
					.replace(/\+/g, '%20'))
					|| null;
		}
		window.onload = function() {
			var redirect = location.search;
			if (redirect != "") {
				var id = getURLParameter('id');
				var message = getURLParameter('message');
				var feed = getURLParameter('feed');
				var red = getURLParameter('red');
				var oldLoad = getURLParameter("oldLoad");
				if (red == 'yes') {
					showStuff(oldLoad);

				} else {

					if (feed == 'ok') {
						if (id > 0) {
							window.open("LoadProductPage?id=" + id);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(
									600);
						} else if (id == -1) {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.warning").text(message);
							$("div.warning").fadeIn(300).delay(1500).fadeOut(
									600);
						} else {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(
									600);
						}
					} else {
						if (id == -1) {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.warning").text(message);
							$("div.warning").fadeIn(300).delay(1500).fadeOut(
									600);
						} else {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(
									600);
						}
					}
				}
			}
		}
	</script>
</body>