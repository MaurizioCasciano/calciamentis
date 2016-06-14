<script type="text/javascript" src="js/admin.js"></script>
<script>
	function mostraNascondi(){
		if(document.getElementById("scegli").value === "criteria"){
			document.getElementById("criteri").style.display = "block";
		} else {
			document.getElementById("criteri").style.display = "none";
		}
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuNome(){
		if(document.getElementById("nome").checked === true){
			document.getElementById("suNome").style.display = "block";
			//document.getElementById("suPrezzoVendita").style.display = "none";
			//document.getElementById("suPrezzoAcquisto").style.display = "none";
		} else {
			document.getElementById("suNome").style.display = "none";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuPrezzoVendita(){
		if(document.getElementById("prezzo_vendita").checked === true){
			document.getElementById("suPrezzoVendita").style.display = "block";
			//document.getElementById("suNome").style.display = "none";
			//document.getElementById("suPrezzoAcquisto").style.display = "none";
		} else {
			document.getElementById("suPrezzoVendita").style.display = "none";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}
	
	function visualizzaSuPrezzoAcquisto(){
		if(document.getElementById("prezzo_acquisto").checked === true){
			document.getElementById("suPrezzoAcquisto").style.display = "block";
			//document.getElementById("suNome").style.display = "none";
			//document.getElementById("suPrezzoVendita").style.display = "none";
		} else {
			document.getElementById("suPrezzoAcquisto").style.display = "none";
		}
		
		document.getElementById("visualizzazione").innerHTML = "";
	}
</script>
<select id="scegli" onchange="mostraNascondi();">
<option value="na">Imposta Filtro:</option>
<option value="all">Tutti</option>
<option value="criteria">Imposta Criteri</option>
</select>
<div id="criteri" style="display:none;">
	<input type="checkbox" id="nome" name="nome" value="nome" onchange="visualizzaSuNome();"/> Nome <br />

<div id="suNome" style="display:none;">
	<input type="text" id="nameQuery" name="nameQuery"/>
	<button type="button" name="btnsearchforname" onclick="perNome();">Ricerca</button>
</div>
	<input type="checkbox" id="prezzo_vendita" name="prezzo_vendita" value="prezzo_vendita" onchange="visualizzaSuPrezzoVendita();"/> Prezzo Vendita<br />
<div id="suPrezzoVendita" style="display:none;">
	<input type="number" id="salePrice" name="salePrice" min=0 />
	<button type="button" name="btnsearchforsaleprice" onclick="#">Ricerca</button>
</div>
	<input type="checkbox" id="prezzo_acquisto" name="prezzo_acquisto" value="prezzo_acquisto" onchange="visualizzaSuPrezzoAcquisto();"/> Prezzo Acquisto <br />
<div id="suPrezzoAcquisto" style="display:none;">
	<input type="number" id="purchasePrice" name="purchasePrice" min=0 />
	<button type="button" name="btnsearchforpurchaseprice" onclick="#">Ricerca</button>
</div>
</div>
<section id="visualizzazione"></section>