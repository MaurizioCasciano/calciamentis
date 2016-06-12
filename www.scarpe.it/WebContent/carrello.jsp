<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="stylesheet" href="css/alert.css" />
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
		</tr>

		<c:forEach var="item" items="${sessionScope.shoppingCart.items}">
			<tr>
				<td><img src="${item.item.images[0]}" alt="${item.item.alt}" /></td>
				<td>${item.item.marca}&nbsp;${item.item.modello}</td>
				<td><input type="number" name="amount" min="1"
					max="${item.item.quantitaDisp}" step="1"
					value="${item.numberOfItems}"
					onchange="updateTotal(${item.itemID},this.value )" />

					<button class="fa fa-trash-o remove-row"
						data-itemid="${item.itemID}"></button></td>
				<td>&euro;&nbsp;${item.unitCost}</td>
			</tr>
		</c:forEach>

		<tr>
			<th colspan="3" style="text-align: right; padding-right: 10px;">Totale</th>
			<th id="totalTag">&euro;&nbsp;${sessionScope.shoppingCart == null ? 0.0 : sessionScope.shoppingCart.totale}</th>
		</tr>
	</table>

	<div style="margin-top: 50px; margin-bottom: 50px; text-align: center;">
		<a id="cassa" href="checkout">CASSA</a>
	</div>

	<%@ include file="include/footer.jsp"%>

	<script src="js/alert.js"></script>
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
		//alert("trashButtons: " + trashButtons);
		//alert("trashButtons.length: " + trashButtons.length);
		
		for(var i = 0; i < trashButtons.length; i++){
			//alert("Ciclo i: " + i);
			
			$(trashButtons[i]).click(function(){
				//alert("Click me!");
				//alert("ItemID: " + $(this).attr("data-itemid"));
				
				updateTotal($(this).attr("data-itemid"), 0);
				$(this).parents('tr').first().remove();
				showInfo("Prodotto rimosso dal carrello.")
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