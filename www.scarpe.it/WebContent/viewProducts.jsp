<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script>
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
</script>
<select id="scegli" onchange="mostraNascondi();">
<option value="na">Imposta Filtro:</option>
<option value="all">Tutti</option>
<option value="criteria">Imposta Criteri</option>
</select>
<div id="tutti" style="display:none;">
	<button type="button" name="btnsearchforall" onclick="mostraTutti();">Visualizza</button>
</div>
<div id="criteri" style="display:none;">
	<input type="checkbox" id="nome" name="nome" value="nome" onchange="visualizzaSuNome();"/> Nome <br />

	<div id="suNome" style="display:none;">
		<input type="text" id="nameQuery" name="nameQuery" value=""/>
	</div>
		<input type="checkbox" id="prezzo_vendita2" name="prezzo_vendita" value="prezzo_vendita" onchange="visualizzaSuPrezzoVendita();"/> Prezzo Vendita<br />
	<div id="suPrezzoVendita" style="display:none;">
		<input type="number" class="products" id="salePrice" name="salePrice" min=0 value="0"/>
	</div>
		<input type="checkbox" id="prezzo_acquisto2" name="prezzo_acquisto" value="prezzo_acquisto" onchange="visualizzaSuPrezzoAcquisto();"/> Prezzo Acquisto <br />
	<div id="suPrezzoAcquisto" style="display:none;">
		<input type="number" class="products" id="purchasePrice" name="purchasePrice" min=0 value="0"/>
	</div>
	<button type="button" name="btnsearchforcriteria" onclick="perCriteri();">Visualizza</button>
</div>
<section id="visualizzazione">

</section>