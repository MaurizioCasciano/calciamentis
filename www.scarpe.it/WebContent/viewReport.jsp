<script type="text/javascript" src="js/admin.js"></script>
<select id="choice" onchange="showHidden()">
<option value="na">Scegli report:</option>
<option value="prodottiEsaurimento">Prodotti in Esaurimento</option>
<option value="prodottiFasciaPrezzo">Prodotti per fascia prezzo</option>
</select>
<div id="impostaFascia" style="display: none;">
<form action="ProdottiPerFascia" method="post">
<label for="prezzomin">Prezzo Minimo: </label> <br />
<input type="number" id="prezzomin" name="prezzomin" min=0> <br/>
<label for="prezzomax">Prezzo Massimo: </label> <br />
<input type="number" id="prezzomax" name="prezzomax" min=0> <br/>
<input type="submit" id="btnsearch" name="btnsearch" value="Ricerca">
</form>
</div>
<div id="cercaProdottiEsaurimento" style="display: none;">
<form action="ProdottiInEsaurimento" method="post">
<input type="submit" id="btnsearch2" name="btnsearch2" value="Ricerca">
</form>
</div>
<section id="report">
</section>