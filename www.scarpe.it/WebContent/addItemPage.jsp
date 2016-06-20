
<div class="layer">
<script src="js/addProduct.js"></script>
	<h1>Prodotto</h1>
	<form action="AddProduct" method="post" enctype="multipart/form-data">
		<fieldset id="productStuff">
			<legend>Dati Prodotto</legend>
			<p class="product">
				<label for="marca">Marca</label>
			</p>
			<input id="marca" type="text" name="marca" required/>

			<p class="product">
				<label for="modello">Modello</label>
			</p>
			<input id="modello" type="text" name="modello" required/>


			<p class="product">
				<label for="prezzo_vendita">Prezzo Vendita</label>
			</p>
			<input id="prezzo_vendita" type="number" name="prezzo_vendita" required />



			<p class="product">
				<label for="prezzo_acquisto">Prezzo Acquisto</label>
			</p>
			<input id="prezzo_acquisto" type="number" name="prezzo_acquisto" required/>




			<p class="product">
				<label for="quantitaDisp">Quantità Disponibile</label>
			</p>
			<input id="quantitaDisp" type="number" name="quantitaDisp" required/>



			<p class="product">
				<label for="scorta_minima">Scorta Minima</label>
			</p>
			<input id="scorta_minima" type="number" name="scorta_minima" required />


			<p class="product">
				<label for="alt">Alt</label>
			</p>
			<input id="alt" type="text" name="alt" required/>

			<p class="product">
				<label for="alt">Descrizione</label>
			</p>
			<input id="descrizione" type="text" name="descrizione" required/>


		</fieldset>

		<fieldset id="productDetails">
			<legend>Descrizioni Prodotto</legend>
			<p class="product">
				<label for="intestazione1">Prima Intestazione</label>
			</p>
			<input type="text" id="intestazione1" name="intestazione1" required>

			<p class="product">
				<label for="corpo1">Primo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo1" name="corpo1" required></textarea>



			<p class="product">
				<label for="intestazione2">Seconda Intestazione</label>
			</p>
			<input type="text" id="intestazione2" name="intestazione2" required>

			<p class="product">
				<label for="corpo2">Secondo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo2" name="corpo2"></textarea>



			<p class="product">
				<label for="intestazione1">Terza Intestazione</label>
			</p>
			<input type="text" id="intestazione3" name="intestazione3" required>

			<p class="product">
				<label for="corpo3">Terzo Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo3" name="corpo3" required></textarea>




			<p class="product">
				<label for="intestazione4">Quarta Intestazione</label>
			</p>
			<input type="text" id="intestazione4" name="intestazione4" required>

			<p class="product">
				<label for="corpo4">Quarto Corpo</label>
			</p>
			<textarea rows='5' cols='50' id="corpo4" name="corpo4" required></textarea>

		</fieldset>
		<fieldset id="productImages">
			<legend>Immagini Prodotto</legend>
			<p class="images">
				<label for="image1">Immagine 1</label>
			</p>
			<input type="file" name="image1" id="image1"  accept=".jpg,.png,.gif,.bmp" required>

			<p class="images">
				<label for="image2">Immagine 2</label>
			</p>
			<input type="file" name="image2" id="image2" accept=".jpg,.png,.gif,.bmp" required>


			<p class="images">
				<label for="image3">Immagine 3</label>
			</p>
			<input type="file" name="image3" id="image3" accept=".jpg,.png,.gif,.bmp" required>



			<p class="images">
				<label for="image4">Immagine 4</label>
			</p>
			<input type="file" name="image4" id="image4" accept=".jpg,.png,.gif,.bmp" required>

			<p class="images">
				<label for="image5">Immagine 5</label>
			</p>
			<input type="file" name="image5" id="image5" accept=".jpg,.png,.gif,.bmp" required>
		</fieldset>
		<input type="submit" value="Inserisci" id="btnInserisci">
	</form>
</div>