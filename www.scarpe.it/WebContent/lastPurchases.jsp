<%@page import="catalog.Item"%>
<%@page import="cart.ItemOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="paydesk.purchasedCart"%>
<%@page import="paydesk.purchasedItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Riepilogo Acquisto</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/login.css" />
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
	<jsp:useBean id="shoppingCart" scope="session"
		class="cart.ShoppingCart"></jsp:useBean>
	<header>
		<h1>Riepilogo Aquisto</h1>
	</header>

	<%@ include file="include/menu.jsp"%>
	<table>
		<tr>
			<th>Immagine</th>
			<th>Articolo</th>
			<th>Quantità</th>
			<th>Prezzo singolo</th>
		</tr>

		<%
			purchasedCart pc = (purchasedCart) request.getAttribute("Acquisti");
			if (pc != null) {
				ArrayList<purchasedItem> items = pc.getAllPurchasedItem();

				for (int i = 0; i < items.size(); i++) {
					purchasedItem itemBuyed = items.get(i);
					Item catalogItem = itemBuyed.getPurcObj();
		%>

		<tr>
			<td><img src="<%=catalogItem.getImages().get(0)%>"
				alt="<%=catalogItem.getAlt()%>" /></td>
			<td><%=catalogItem.getMarca() + " " + catalogItem.getModello()%></td>
			<td><%=itemBuyed.getQuantita()%></td>
			<td>&euro;&nbsp;<%=itemBuyed.getPrezzo()%></td>
		</tr>
		<%
			}
			} else {

			}
		%>
		<tr>
			<th colspan="3" style="text-align: right; padding-right: 10px;">Totale</th>
			<th>&euro;&nbsp;<%=pc.getTotaleCart()%></th>
		</tr>
	</table>

	<%@ include file="include/footer.jsp"%>

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