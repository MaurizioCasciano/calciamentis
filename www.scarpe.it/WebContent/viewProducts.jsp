<script type="text/javascript" src="js/admin.js"></script>
<select id="scegli" onchange="#">
<option value="na">Imposta Filtro:</option>
<option value="all">Tutti</option>
<option value="criteria">Imposta Criteri</option>
</select>
<div id="criteri">
	<input type="checkbox" name="nome" value="nome" /> 

<div id="suNome">
	<select id="tipo_ricerca" onchange="#">
	<option value="uguale">uguale</option>
	<option value="contiene">contiene</option>
	<option value="inizia">inizia</option>
	<option value="termina">termina</option>
	</select><br />
	<input type="text" name="nameQuery"/>
</div>
	<input type="checkbox" name="prezzo_vendita" value="prezzo vendita"/> <br />
<div id="suPrezzoVendita">
	<input type="number" name="salePrice" min=0 />
</div>
	<input type="checkbox" name="prezzo_acquisto" value="prezzo acquisto"/> <br />
<div id="suPrezzoAcquisto">
	<input type="number" name="purchasePrice" min=0 />
</div>

</div>