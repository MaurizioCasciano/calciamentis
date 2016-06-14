<%@ page import="administration.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestione Sito</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/management.css">
<link rel="stylesheet" href="css/prova.css">
<link rel="stylesheet" href="css/report.css">
<link rel="stylesheet" href="css/alert.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<script src="js/admin.js"></script>
<body>
	<header>
		<h1>Gestione</h1>
	</header>
	<nav id="defaultMenu">
		<ul>
			<li style="float: left; width: 35px; height: 100%;"><a
				class="menuIcons fa fa-home" href="/www.scarpe.it/index.jsp"></a></li>
			<li class="menu"><form id="logoutForm" action="LogoutAdmin"
					method="get">
					<label>Logged Admin: <%=(String) ((Admin) session.getAttribute("loggedAdmin")).getUsername()%></label>
					<button id="exitButton" class="fa fa-sign-out"
						onclick="logout(document.getElementById('logoutForm'))"></button>
				</form></li>
		</ul>
	</nav>
	<div class="alert success"></div>
	<div class="alert warning"></div>
	<nav id="mainMenu">
		<ul>
			<li>
				<button onclick="showStuff('viewProducts.jsp')">
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
				<button>
					<div class="internalToButton">
						<div class="toLeft fa fa-user"></div>
						<div class="toRight">Visualizza Info Clienti</div>
					</div>
				</button>
			</li>
			<li>
				<button onclick="showStuff('viewReport.jsp')">
					<div class="internalToButton">
						<div class="toLeft fa fa-book"></div>
						<div class="toRight">Visualizza Report</div>
					</div>
				</button>
			</li>
			<li>
				<button>
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
	<footer>
		<p>Copyright &copy; Maurizio Casciano - Domenico A. Tropeano -
			Gaetano Antonucci</p>
	</footer>
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
				id = getURLParameter('id');
				var message = getURLParameter('message');
				var feed = getURLParameter('feed');
				if (feed = ok) {
					$("div.success").text(message);
					$("div.success").fadeIn(300).delay(1500).fadeOut(600);
					if (id > 0) {
						window.open("LoadProductPage?id=" + id);
					}
				} else {
					var oldLoad = getURLParameter("oldLoad");
					showStuff(oldLoad);
					$("div.warning").text(message);
					$("div.warning").fadeIn(300).delay(1500).fadeOut(600);
				}

			}
		};
	</script>
</body>