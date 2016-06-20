function getURLParameter(name) {
			return decodeURIComponent((new RegExp('[?|&]' + name + '='
					+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
					.replace(/\+/g, '%20'))
					|| null;
		}
		window.onload = function() {
			var redirect = location.search;
			if (redirect != "") {
				var id = getURLParameter('id');
				var message = getURLParameter('message');
				var feed = getURLParameter('feed');
				var red = getURLParameter('red');
				var oldLoad = getURLParameter("oldLoad");
				if (red == 'yes') {
					showStuff(oldLoad);

				} else {

					if (feed == 'ok') {
						if (id > 0) {
							window.open("LoadProductPage?id=" + id);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(600);
						} else if (id == -1) {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.warning").text(message);
							$("div.warning").fadeIn(300).delay(1500).fadeOut(600);
						} else {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(600);
						}
					} else {
						if (id == -1) {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.warning").text(message);
							$("div.warning").fadeIn(300).delay(1500).fadeOut(600);
						} else {
							var oldLoad = getURLParameter("oldLoad");
							showStuff(oldLoad);
							$("div.success").text(message);
							$("div.success").fadeIn(300).delay(1500).fadeOut(600);
						}
					}
				}
			}
		}
function showStuff(string) {
	// var eid = string;
	if (string === "viewProducts.jsp") {
		if (isFather()) {
			$.ajax({
				type : "GET",
				data : {
					deleterBean : "prodotti",
					scope : "sessionScope"
				},
				url : "DeleteSessionBean",
				success : function() {

				}
			});
		}
	} else if (string === "viewReport.jsp") {
		if (isFather()) {
			$.ajax({
				type : "GET",
				data : {
					deleterBean : "report",
					scope : "sessionScope"
				},
				url : "DeleteSessionBean",
				success : function() {

				}
			});
		}

	} else if (string === "viewCustomers.jsp") {
		if (isFather()) {
			$.ajax({
				type : "GET",
				data : {
					deleterBean : "clienti",
					scope : "sessionScope"
				},
				url : "DeleteSessionBean",
				success : function() {
					document.location.href="ClientiPerVisualizzazione";
				}
			});
		}
	}
	$("#div1").load(string);
}
father = false;
function setFather() {
	father = true;
}
function isFather() {
	if (father) {
		var oldValue = father;
		father = false;
		return oldValue;
	}
}

function showHidden() {
	if (document.getElementById("choice").value === "prodottiFasciaPrezzo") {
		document.getElementById("impostaFascia").style.display = "block";
		document.getElementById("cercaProdottiEsaurimento").style.display = "none";
	} else {
		document.getElementById("cercaProdottiEsaurimento").style.display = "block";
		document.getElementById("impostaFascia").style.display = "none";
	}
	document.getElementById("report").innerHTML = "";
}

function perFascia() {
	var prezzomin;
	var prezzomax;

	prezzomin = document.getElementById("prezzomin").value;
	prezzomax = document.getElementById("prezzomax").value;

	document.location.href = "ProdottiPerFascia?prezzomin=" + prezzomin
			+ "&prezzomax=" + prezzomax;
	// document.getElementById("link").href="ProdottiPerFascia?prezzomin="+prezzomin
	// + "&prezzomax=" + prezzomax;
	// return "ProdottiPerFascia?prezzomin="+prezzomin + "&prezzomax=" +
	// prezzomax;
	/*
	 * alert("prezzomin " + prezzomin + " prezzomax " + prezzomax); var xhttp;
	 * 
	 * if (window.XMLHttpRequest) { xhttp = new XMLHttpRequest(); } else { xhttp =
	 * new ActiveXObject("Microsoft.XMLHTTP"); }
	 * 
	 * xhttp.onreadystatechange = function() { if (xhttp.readyState == 4 &&
	 * xhttp.status == 200) { document.getElementById("report").innerHTML =
	 * xhttp.responseText; } } xhttp.open("GET", "ProdottiPerFascia?prezzomin=" +
	 * prezzomin + "&prezzomax=" + prezzomax, true); xhttp.send();
	 */
}

/*
 * function inEsaurimento(){ var xhttp;
 * 
 * 
 * if (window.XMLHttpRequest) { xhttp = new XMLHttpRequest(); } else { xhttp =
 * new ActiveXObject("Microsoft.XMLHTTP"); }
 * 
 * xhttp.onreadystatechange = function() { if (xhttp.readyState == 4 &&
 * xhttp.status == 200) { //document.getElementById("report").innerHTML =
 * xhttp.responseText; } } xhttp.open("GET", "ProdottiInEsaurimento", true);
 * xhttp.send(); }
 */

function inEsaurimento() {
	//alert("Sono nella funzione js");
	document.location.href="ProdottiInEsaurimento";
}

function perCriteri() {
	var nome = document.getElementById("nameQuery").value;
	var prezzoVendita = document.getElementById("salePrice").value;
	var prezzoAcquisto = document.getElementById("purchasePrice").value;

	document.location.href = "ProdottiPerVisualizzazione?nome=" + nome
			+ "&prezzoVendita=" + prezzoVendita + "&prezzoAcquisto="
			+ prezzoAcquisto;
	/*
	 * alert("nome: " + nome + " prezzoVendita: " + prezzoVendita + "
	 * prezzoAcquisto: " + prezzoAcquisto); var xhttp;
	 * 
	 * if (window.XMLHttpRequest) { xhttp = new XMLHttpRequest(); } else { xhttp =
	 * new ActiveXObject("Microsoft.XMLHTTP"); }
	 * 
	 * xhttp.onreadystatechange = function() { if (xhttp.readyState == 4 &&
	 * xhttp.status == 200) { alert("risposta positiva Server");
	 * document.getElementById("visualizzazione").innerHTML =
	 * xhttp.responseText; alert("Risposta Server: " + xhttp.responseText); } }
	 * xhttp.open("GET", "ProdottiPerVisualizzazione?nome=" + nome +
	 * "&prezzoVendita=" + prezzoVendita + "&prezzoAcquisto=" + prezzoAcquisto,
	 * true); xhttp.send();
	 */
}

function mostraTutti() {
	document.location.href = "ProdottiPerVisualizzazione";

	/*
	 * var xhttp;
	 * 
	 * 
	 * if (window.XMLHttpRequest) { xhttp = new XMLHttpRequest(); } else { xhttp =
	 * new ActiveXObject("Microsoft.XMLHTTP"); }
	 * 
	 * 
	 * xhttp.onreadystatechange = function() { if (xhttp.readyState == 4 &&
	 * xhttp.status == 200) { alert("risposta positiva Server");
	 * document.getElementById("visualizzazione").innerHTML =
	 * xhttp.responseText; alert("Risposta Server: " + xhttp.responseText); } }
	 * xhttp.open("GET", "ProdottiPerVisualizzazione", true); xhttp.send();
	 */
}