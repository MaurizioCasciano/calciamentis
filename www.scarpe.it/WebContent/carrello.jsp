<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrello</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/alert.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet" href="css/carrello.css" />
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
			<h1>Carrello</h1>
		</header>

		<%@ include file="include/menu.jsp"%>

		<table>
			<tr>
				<th>Immagine</th>
				<th>Articolo</th>
				<th>Quantità</th>
				<th>Prezzo</th>
				<th>Importo</th>
			</tr>

			<c:forEach var="item" items="${sessionScope.shoppingCart.items}">
				<tr>
					<td><img src="${item.item.images[0]}" alt="${item.item.alt}" /></td>
					<td>${item.item.marca}&nbsp;${item.item.modello}</td>
					<td><input type="number" class="amountSpinner" name="amount"
						min="1" max="${item.item.quantitaDisp}" step="1"
						value="${item.numberOfItems}" data-itemid="${item.itemID}" />

						<button class="fa fa-trash-o remove-row"
							data-itemid="${item.itemID}"></button></td>
					<td>&euro;&nbsp;${item.unitCost}</td>
					<td class="rowTotal">&euro;&nbsp;${item.totalCost}</td>
				</tr>
			</c:forEach>

			<tr>
				<th colspan="4" style="text-align: right; padding-right: 10px;">Totale</th>
				<th id="totalTag">&euro;&nbsp;${sessionScope.shoppingCart == null ? 0.0 : sessionScope.shoppingCart.totale}</th>
			</tr>
		</table>

		<div
			style="margin-top: 50px; margin-bottom: 50px; text-align: center;">
			<a id="cassa" href="Checkout">CASSA</a>
		</div>
		<div class="push"></div>
	</div>

	<%@ include file="include/footer.jsp"%>

	<script src="js/alert.js"></script>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/cart.js"></script>

	<script src="js/specialSearch.js"></script>
	<script src="js/sticky-menu.js"></script>
</body>
</html>