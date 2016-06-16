$(document).ready(function() {
	totale = document.getElementById("totale");
});

function updateCart(xml) {
	var totalElement = xml.getElementsByTagName("total")[0];
	var totalValue = totalElement.childNodes[0].nodeValue;
	// alert("totalValue" + totalValue);
	totale.innerHTML = "&nbsp;&euro;" + totalValue;
}

function addToCart(itemID) {
	//alert("itemID: " + itemID + " added to cart.");
	
	$.ajax({
		type : "POST",
		data : {
			itemID : itemID
		},
		url : "AddToCartController",
		success : function(xml) {
			alert("XML:\n\n" + (new XMLSerializer()).serializeToString(xml));
			updateCart(xml);
			showSuccess("Prodotto aggiunto al carrello.");
			// window.location.reload(true);/*Alternativa all'invio dell'xml con
			// il totale*/
		}
	});
}