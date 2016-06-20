$(document).ready(function() {
	// alert("I'm ready");

	var trashButtons = $(".remove-row");
	// alert("trashButtons: " + trashButtons);
	// alert("trashButtons.length: " + trashButtons.length);

	for (var i = 0; i < trashButtons.length; i++) {
		// alert("Ciclo i: " + i);

		$(trashButtons[i]).click(function() {
			// alert("Click me!");
			// alert("ItemID: " + $(this).attr("data-itemid"));

			updateTotal($(this).attr("data-itemid"), 0);
			$(this).parents('tr').first().remove();
			showInfo("Prodotto rimosso dal carrello.")
		});
	}
	// alert("Sono fuori dal for");
	var amountSpinners = $(".amountSpinner");
	// alert("Length di amountSpinner" + amountSpinners.length);
	for (var j = 0; j < amountSpinners.length; j++) {
		// alert("Entro nel for degli spinner");

		$(amountSpinners[j]).change(function() {
			// alert("This di spinner: "+ this);
			var row = $(this).parents('tr').first();
			// alert("Row " + row);
			var importTD = row.children(".rowTotal").first();
			// alert("importTD " + importTD);
			// alert("this.value: " + this.value);
			updateTotal($(this).attr("data-itemid"), this.value, importTD);
		});
	}
});

$(document).ready(function() {
	totale = document.getElementById("totale");
});

function updateCart(xml) {
	var totalElement = xml.getElementsByTagName("total")[0];
	var totalValue = totalElement.childNodes[0].nodeValue;
	// alert("totalValue" + totalValue);
	totale.innerHTML = "&nbsp;&euro;" + totalValue;
}

function updateTotal(itemID, value, importTD) {
	// alert("updateTotal - itemID: " + itemID);
	// alert(this);
	// alert("value" + value + "itemId " + itemID);
	$.ajax({
		type : "GET",
		data : {
			itemID : itemID,
			value : value
		},
		url : "UpdateAmountController",
		success : function(xml) {
			var totalElement = xml.getElementsByTagName("total")[0];
			var totalValue = totalElement.childNodes[0].nodeValue;
			// alert("xmlElement: " +
			// xml.getElementsByTagName("item[code="+itemID+"]")[0]);
			var importoRiga = $(xml).find('item[code="' + itemID + '"]').attr(
					"rowTotal");
			// alert("Importo Riga:" + importoRiga);

			$("#totalTag").html("&euro;&nbsp;" + totalValue);

			var totale = document.getElementById("totale");
			totale.innerHTML = "&nbsp;&euro;" + totalValue;
			if (importTD != undefined) {
				importTD.html("&euro;&nbsp;" + importoRiga);
			}

			// window.location.reload(true);/*Alternativa all'invio dell'xml con
			// il totale*/
		}
	});
}

function addToCart(itemID) {
	// alert("itemID: " + itemID + " added to cart.");

	$.ajax({
		type : "POST",
		data : {
			itemID : itemID
		},
		url : "AddToCartController",
		success : function(xml) {
			//alert("XML:\n\n" + (new XMLSerializer()).serializeToString(xml));
			var lastAddedElement = xml.getElementsByTagName("lastAdded")[0];
			var lastAddedValue = lastAddedElement.childNodes[0].nodeValue;
			//alert("lastAddedValue: " + lastAddedValue);

			if (lastAddedValue === "true") {
				//alert("Last item was successfully added to the cart.");
				updateCart(xml);
				showSuccess("Prodotto aggiunto al carrello.");
			} else {
				//alert("Last item was NOT added to the cart.");
				updateCart(xml);
				showWarning("Prodotto esaurito.");
			}

			// window.location.reload(true);/*Alternativa all'invio dell'xml con
			// il totale*/
		}
	});
}