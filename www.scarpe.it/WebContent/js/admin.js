function showStuff(string){ 
alert(string);
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
}