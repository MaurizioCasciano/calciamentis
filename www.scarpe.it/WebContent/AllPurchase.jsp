<%@page import="paydesk.allPurch"%>
<%@page import="catalog.Item"%>
<%@page import="cart.ItemOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="paydesk.purchasedCart"%>
<%@page import="paydesk.purchasedItem"%>
<%@page import="paydesk.allPurch"%>
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
		<h1>Carrello</h1>
	</header>

	<nav class="menu">
		<ul>
			<li class="left"><a class="fa fa-home" href="index.jsp">&nbsp;Home</a>
			</li>
			<li class="left"><a class="fa fa-shopping-cart"
				href="carrello.jsp">&nbsp;Carrello</a></li>

			<li class="left"><span id="totale" class="fa fa-money">&nbsp;&euro;${sessionScope.shoppingCart == null ? 0.0 : sessionScope.shoppingCart.totale}</span></li>

			<li id="search" class="left">
				<div id="form-wrapper">
					<button onclick="specialSearch()" class="go-button fa fa-search"></button>
					<span class="nav-list"> <select id="dropdown">
							<option value="f0">Tutti i prezzi</option>
							<option value="f1">50&euro; -100&euro;</option>
							<option value="f2">100&euro; -200&euro;</option>
							<option value="f3">200&euro; -300&euro;</option>
							<option value="f4">300&euro; -500&euro;</option>
					</select>
					</span>
					<div class="in-wrap">
						<input type="text" name="query" id="search-box">
					</div>
				</div>
			</li>

			<%
				if (session.getAttribute("loggedUser") == null) {
			%>
			<li id="signup" class="right"><a class="fa fa-user-plus"
				href="registration.jsp">&nbsp;Signup</a></li>
			<li id="login" class="right"><span class="fa fa-sign-in">&nbsp;Login</span>
				<form id="login_form" action="login" method="post">
					<input id="login_username" class="login_field" name="username"
						type="text" placeholder="username" /> <br /> <input
						id="login_password" class="login_field" name="password"
						type="password" placeholder="password" /> <br />
					<div id="submit-div">
						<input type="submit" value="login" />
					</div>
				</form></li>
			<%
				} else {
			%>
			<li id="welcome" class="right"><span class="fa fa-user">&nbsp;<%=session.getAttribute("loggedUser")%></span>
				<div id="profile">
					<ul>
						<li><a href="#profilo">Profilo</a></li>
						<li><a href="#profilo">Aquisti</a></li>
						<li><a href="#profilo">Impostazioni</a></li>
						<li><a href="#profilo">Altro 1</a></li>
						<li><a href="#profilo">Altro 2</a></li>
					</ul>

					<form id="logout" action="logout" method="post">
						<label for="out-btn">Exit&nbsp;</label>
						<button id="out-btn" class="fa fa-sign-out" form="logout"
							type="submit" form="nameform" value="Submit"></button>
					</form>
				</div></li>
			<%
				}
			%>

			<%
				if (session.getAttribute("error") != null) {
					System.out.println("ERROR: " + session.getAttribute("error"));
			%>

			<li id="error" class="right"><span
				style="color: red; background-color: white;"><%=session.getAttribute("error")%></span>
			</li>
			<%
				}
			%>
		</ul>
	</nav>

	<%
		allPurch allP = new allPurch((String) session.getAttribute("loggedUser"));
		for (int j = 0; j < allP.getSize(); j++) {
			purchasedCart currentPurchCart = allP.getCart(j);
	%>

	<table>
		<tr>
			<th colspan="5" style="text-align: right; padding-right: 10px;">
				<%=allP.getDate(j).toString().subSequence(0, 19)%>
			</th>
		</tr>
		<tr>
			<th>Immagine</th>
			<th>Articolo</th>
			<th>Quantità</th>
			<th>Prezzo singolo</th>
			<th>Importo</th>
		</tr>

		<%
			if (currentPurchCart != null) {
					ArrayList<purchasedItem> items = currentPurchCart.getAllPurchasedItem();

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
			<td>&euro;&nbsp;<%=itemBuyed.getPrezzoTotal()%></td>
		</tr>
		<%
			}
				} else {

				}
		%>
		<tr>
			<th colspan="4" style="text-align: right; padding-right: 10px;">Totale</th>
			<th>&euro;&nbsp;<%=currentPurchCart.getTotaleCart()%></th>
		</tr>
	</table>
	<%
		}
	%>

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