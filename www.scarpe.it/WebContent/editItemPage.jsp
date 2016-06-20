
<div class="layer">
<script src="js/addProduct.js"></script>
	<h1>Prodotto</h1>
	<form action="EditProduct" method="post" >
		<fieldset id="productStuff">
			<legend>Dati Prodotto</legend>
			<input type="hidden" id="id" name="id" value="${editableBean.id}" >
			<p class="product">
				<label for="marca">Marca</label>
			</p>
			<input id="marca" type="text" name="marca" value="${editableBean.marca}"/>

			<p class="product">
				<label for="modello">Modello</label>
			</p>
			<input id="modello" type="text" name="modello" value="${editableBean.modello}" required/>


			<p class="product">
				<label for="prezzo_vendita">Prezzo Vendita</label>
			</p>
			<input id="prezzo_vendita" type="number" name="prezzo_vendita" value="${editableBean.prezzo_vendita}" required />



			<p class="product">
				<label for="prezzo_acquisto">Prezzo Acquisto</label>
			</p>
			<input id="prezzo_acquisto" type="number" name="prezzo_acquisto" value="${editableBean.prezzo_acquisto}" required/>




			<p class="product">
				<label for="quantitaDisp">Quantità Disponibile</label>
			</p>
			<input id="quantitaDisp" type="number" name="quantitaDisp" value="${editableBean.quantitaDisp}" required/>



			<p class="product">
				<label for="scorta_minima">Scorta Minima</label>
			</p>
			<input id="scorta_minima" type="number" name="scorta_minima" value="${editableBean.scorta_minima}" required />


			<p class="product">
				<label for="alt">Alt</label>
			</p>
			<input id="alt" type="text" name="alt" value="${editableBean.alt}" required/>

			<p class="product">
				<label for="descrizione">Descrizione</label>
			</p>
			<input id="descrizione" type="text" name="descrizione"  value="${editableBean.descrizione}"required/>


		</fieldset>

		<fieldset id="productDetails">
			<legend>Descrizioni Prodotto</legend>
			<p class="product">
				<label for="intestazione1">Prima Intestazione</label>
			</p>
			<input type="text" id="intestazione1" name="intestazione1" value="${editableBean.dettagli[0].intestazione}" required>

			<p class="product">
				<label for="corpo1">Primo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo1" name="corpo1"   required>${editableBean.dettagli[0].corpo}</textarea>



			<p class="product">
				<label for="intestazione2">Seconda Intestazione</label>
			</p>
			<input type="text" id="intestazione2" name="intestazione2" value="${editableBean.dettagli[1].intestazione}" required>

			<p class="product">
				<label for="corpo2">Secondo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo2" name="corpo2"  required>${editableBean.dettagli[1].corpo}</textarea>

			<p class="product">
				<label for="intestazione3">Terza Intestazione</label>
			</p>
			<input type="text" id="intestazione3" name="intestazione3" value="${editableBean.dettagli[2].intestazione}" required>

			<p class="product">
				<label for="corpo3">Terzo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo3" name="corpo3" required>${editableBean.dettagli[2].corpo}</textarea>




			<p class="product">
				<label for="intestazione4">Quarta Intestazione</label>
			</p>
			<input type="text" id="intestazione4" name="intestazione4" value="${editableBean.dettagli[3].intestazione}" required>

			<p class="product">
				<label for="corpo4">Quarto Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo4" name="corpo4" required>${editableBean.dettagli[3].corpo}</textarea>

		</fieldset>
		<input type="submit" value="Salva Modifiche" id="btnInserisci">
	</form>
</div>