function mostraNascondi(){
		
		if(document.getElementById("scegli").value === "criteria"){
			document.getElementById("criteri").style.display = "block";
			document.getElementById("tutti").style.display = "none";
		} else {
			document.getElementById("criteri").style.display = "none";
			document.getElementById("tutti").style.display = "block";
		}
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuNome(){
		if(document.getElementById("nome").checked === true){
			document.getElementById("suNome").style.display = "block";
		} else {
			document.getElementById("suNome").style.display = "none";
			document.getElementById("nameQuery").value = "";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuPrezzoVendita(){
		if(document.getElementById("prezzo_vendita2").checked === true){
			document.getElementById("suPrezzoVendita").style.display = "block";
		} else {
			document.getElementById("suPrezzoVendita").style.display = "none";
			document.getElementById("salePrice").value = "0";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuPrezzoAcquisto(){
		if(document.getElementById("prezzo_acquisto2").checked === true){
			document.getElementById("suPrezzoAcquisto").style.display = "block";
		} else {
			document.getElementById("suPrezzoAcquisto").style.display = "none";
			document.getElementById("purchasePrice").value = "0";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}