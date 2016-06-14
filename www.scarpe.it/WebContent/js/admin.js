function showStuff(string){ 
//var eid = string; 
$("#div1").load(string); 
} 

function showHidden(){
	if(document.getElementById("choice").value === "prodottiFasciaPrezzo"){
		document.getElementById("impostaFascia").style.display = "block";
		document.getElementById("cercaProdottiEsaurimento").style.display = "none";
	} else {
		document.getElementById("cercaProdottiEsaurimento").style.display = "block";
		document.getElementById("impostaFascia").style.display = "none";
	}
	document.getElementById("report").innerHTML = "";
}

function perFascia(){
	var prezzomin;
	var prezzomax;
	
	prezzomin = document.getElementById("prezzomin").value;
	prezzomax = document.getElementById("prezzomax").value;
	
	alert("prezzomin " + prezzomin + " prezzomax " + prezzomax);
	var xhttp;

	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
	} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("report").innerHTML = xhttp.responseText;
		}
	}	
		xhttp.open("GET", "ProdottiPerFascia?prezzomin=" + prezzomin + "&prezzomax=" + prezzomax, true);
		xhttp.send();
}

function inEsaurimento(){
	var xhttp;

	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
	} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("report").innerHTML = xhttp.responseText;
		}
	}	
		xhttp.open("GET", "ProdottiInEsaurimento", true);
		xhttp.send();
}

function perNome(){
	var nome = document.getElementById("nameQuery").value;
	alert("nome: " + nome);
	var tipo = document.getElementById("tipo_ricerca").value;
	
	var xhttp;

	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
	} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("visualizzazione").innerHTML = xhttp.responseText;
		}
	}	
		xhttp.open("GET", "ProdottiPerNome?nome=" + nome + "&tipo=" + tipo, true);
		xhttp.send();
}