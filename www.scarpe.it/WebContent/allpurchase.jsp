<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acquisti</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
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
			<h1>Acquisti</h1>
		</header>

		<%@ include file="include/menu.jsp"%>

		<c:forEach var="cart"
			items="${sessionScope.loggedUser.purchasedCarts}">
			<table>
				<tr>
					<th colspan="5" style="text-align: right; padding-right: 10px;">
						${cart.date}</th>
				</tr>
				<tr>
					<th>Immagine</th>
					<th>Articolo</th>
					<th>Quantità</th>
					<th>Prezzo</th>
					<th>Importo</th>
				</tr>

				<c:forEach var="item" items="${cart.purchasedItems}">
					<tr>
						<td><img src="${item.catalogItem.images[0]}"
							alt="${item.catalogItem.alt}" /></td>
						<td>${item.catalogItem.marca}&nbsp;${item.catalogItem.modello}</td>
						<td>${item.quantita}</td>
						<td>&euro;&nbsp;${item.prezzo}</td>
						<td>&euro;&nbsp;${item.prezzoTotal}</td>
					</tr>
				</c:forEach>

				<tr>
					<th colspan="4" style="text-align: right; padding-right: 10px;">Totale</th>
					<th id="totalTag">&euro;&nbsp;${cart.totale}</th>
				</tr>
			</table>
		</c:forEach>
		<div class="push"></div>
	</div>
	<%@ include file="include/footer.jsp"%>
	<script src="js/specialSearch.js"></script>
</body>
</html>