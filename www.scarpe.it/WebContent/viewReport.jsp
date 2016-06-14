<style>
	th, td {text-align: center}
</style>
<script type="text/javascript" src="js/admin.js"></script>
<select id="choice" onchange="showHidden()">
<option value="na">Scegli report:</option>
<option value="prodottiEsaurimento">Prodotti in Esaurimento</option>
<option value="prodottiFasciaPrezzo">Prodotti per fascia prezzo</option>
</select>
<div id="impostaFascia" style="display: none;">
<label for="prezzomin">Prezzo Minimo: </label> <br />
<input type="number" id="prezzomin" name="prezzomin" min=0> <br/>
<label for="prezzomax">Prezzo Massimo: </label> <br />
<input type="number" id="prezzomax" name="prezzomax" min=0> <br/>
<button type="button" id="btnsearch" name="btnsearch" onclick="perFascia();">Ricerca</button>
</div>
<div id="cercaProdottiEsaurimento" style="display: none;">
<button type="button" id="btnsearch2" name="btnsearch2" onclick="inEsaurimento()">Ricerca</button>
</div>
<section id="report">
</section>