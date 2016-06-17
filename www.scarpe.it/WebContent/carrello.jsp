<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
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
			<th>Importo</th>
		</tr>

		<c:forEach var="item" items="${sessionScope.shoppingCart.items}">
			<tr>
				<td><img src="${item.item.images[0]}" alt="${item.item.alt}" /></td>
				<td>${item.item.marca}&nbsp;${item.item.modello}</td>
				<td><input type="number" class="amountSpinner" name="amount" min="1"
					max="${item.item.quantitaDisp}" step="1"
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

	<div style="margin-top: 50px; margin-bottom: 50px; text-align: center;">
		<a id="cassa" href="Checkout">CASSA</a>
	</div>

	<%@ include file="include/footer.jsp"%>

	<script src="js/alert.js"></script>
<<<<<<< HEAD
	<script src="js/cart.js"></script>
=======
	<script>
  function updateTotal(itemID,value,importTd){
	//alert(this);
	 // alert("value" + value + "itemId " + itemID);
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
				//alert("xmlElement: " + xml.getElementsByTagName("item[code="+itemID+"]")[0]);
				var importoRiga = $(xml).find('item[code="'+itemID+'"]').attr("rowTotal");
				//alert("Importo Riga:" + importoRiga);
				
				$("#totalTag").html("&euro;&nbsp;"+totalValue);
				
				var totale = document.getElementById("totale");
				totale.innerHTML = "&nbsp;&euro;" + totalValue;
				importTd.html("&euro;&nbsp;"+importoRiga);
				
				//window.location.reload(true);/*Alternativa all'invio dell'xml con il totale*/
			}
		});
  }
  </script>
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
		//alert("Sono fuori dal for");
		var amountSpinners = $(".amountSpinner");
		//alert("Length di amountSpinner" + amountSpinners.length);
		for(var j = 0; j < amountSpinners.length; j++){
			//alert("Entro nel for degli spinner");
			
			$(amountSpinners[j]).change(function(){
				//alert("This di spinner: "+ this);
				var row = $(this).parents('tr').first();
				//alert("Row " + row);
				var importTd = row.children(".rowTotal").first(); 
				//alert("importTd " + importTd);
				//alert("this.value: " + this.value);
				updateTotal($(this).attr("data-itemid"), this.value, importTd);
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