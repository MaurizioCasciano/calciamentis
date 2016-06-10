<%@page import="catalog.Item"%>
<%@page import="cart.ItemOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrello</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/search.css" />
<link rel="stylesheet" href="css/carrello.css" />

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

	<table>
		<tr>
			<th>Immagine</th>
			<th>Articolo</th>
			<th>Quantità</th>
			<th>Prezzo singolo</th>
		</tr>

		<%
			ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
			if (cart != null) {
				ArrayList<ItemOrder> items = cart.getItemsOrdered();

				for (int i = 0; i < items.size(); i++) {
					ItemOrder itemOrder = items.get(i);
					Item catalogItem = itemOrder.getItem();
		%>

		<tr>
			<td><img src="<%=catalogItem.getImages().get(0)%>"
				alt="<%=catalogItem.getAlt()%>" /></td>
			<td><%=catalogItem.getMarca() + " " + catalogItem.getModello()%></td>
			<td><input type="number" name="amount" min="1"
				max=<%=catalogItem.getQuantitaDisp()%> step="1"
				value="<%=itemOrder.getNumberOfItems()%>"
				onchange="updateTotal(<%=itemOrder.getItemID()%>,this.value )" />

				<button class="fa fa-trash-o remove-row"
					data-itemid="<%=itemOrder.getItemID()%>"></button></td>
			<td>&euro;&nbsp;<%=itemOrder.getUnitCost()%></td>
		</tr>
		<%
			}
			} else {
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
			}
		%>
		<tr>
			<th colspan="3" style="text-align: right; padding-right: 10px;">Totale</th>
			<th id="totalTag">&euro;&nbsp;<%=cart.getTotale()%></th>
		</tr>
	</table>

	<div style="margin-top: 50px; margin-bottom: 50px; text-align: center;">
		<a id="cassa" href="checkout">CASSA</a>
	</div>
	<footer>
		<svg height="50px" width="100px"
			style="border: 1px solid black; float: left;">

      <ellipse cx="50%" cy="85%" rx="45%" ry="15%" style="fill:purple" />
      <ellipse cx="50%" cy="80%" rx="40%" ry="13%" style="fill:lime" />
      <ellipse cx="50%" cy="75%" rx="35%" ry="10%" style="fill:yellow" />

      <defs>
        <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="0%">
          <stop offset="0%"
				style="stop-color:rgb(255,44,140);stop-opacity:1" />
          <stop offset="100%"
				style="stop-color:rgb(0,0,255);stop-opacity:1" />
        </linearGradient>
      </defs>

      <ellipse cx="50%" cy="50%" rx="20%" ry="20%" fill="url(#grad1)" />
      <text fill="#ffffff" font-size="100%" font-family="Verdana"
				x="50%" y="50%" text-anchor="middle" alignment-baseline="middle"
				style="dominant-baseline: middle;">SC</text>

      <ellipse cx="50%" cy="25%" rx="35%" ry="10%" style="fill:yellow" />
      <ellipse cx="50%" cy="20%" rx="40%" ry="13%" style="fill:lime" />
      <ellipse cx="50%" cy="15%" rx="45%" ry="15%" style="fill:purple" />
      Sorry, your browser does not support inline SVG.
    </svg>

		<p>Copyright &copy; Maurizio Casciano</p>
	</footer>
	<script>
  function updateTotal(itemID,value){
		$.ajax({
			type : "GET",
			data : {
				itemID : itemID,
				value : value
			},
			url : "updateQuantity",
			success : function(xml) {
				var totalElement = xml.getElementsByTagName("total")[0];
				var totalValue = totalElement.childNodes[0].nodeValue;
				$("#totalTag").html("&euro;&nbsp;"+totalValue);
				
				var totale = document.getElementById("totale");
				totale.innerHTML = "&nbsp;&euro;" + totalValue;
				
				//window.location.reload(true);/*Alternativa all'invio dell'xml con il totale*/
			}
		});
  }
  </script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>

	<script src="js/jquery-1.12.4.js"></script>

	<script>
	$(document).ready(function() {
		//alert("I'm ready");
		
		var trashButtons = $(".remove-row");
		alert("trashButtons: " + trashButtons);
		alert("trashButtons.length: " + trashButtons.length);
		
		for(var i = 0; i < trashButtons.length; i++){
			//alert("Ciclo i: " + i);
			
			$(trashButtons[i]).click(function(){
				//alert("Click me!");
				alert("ItemID: " + $(this).attr("data-itemid"));
				
				updateTotal($(this).attr("data-itemid"), 0);
				$(this).parents('tr').first().remove();
			});			
		}
	});
	</script>

	<script>
		function specialSearch() {
			var cat = document.getElementById("dropdown").value;
			var key = document.getElementById("search-box").value;
			var mainSection = document.getElementById("main-section");

			window.location.replace("index.jsp?cat=" + cat + "&key=" + key);

		}
	</script>
	<script src="js/sticky-menu.js"></script>
</body>
</html>