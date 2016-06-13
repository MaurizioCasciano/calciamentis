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
			alert("Il server ha dato risposta positiva");
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
			alert("Sono in ajax: inEsaurimento()")
			document.getElementById("report").innerHTML = xhttp.responseText;
		}
	}	
		xhttp.open("GET", "ProdottiInEsaurimento", true);
		xhttp.send();
}