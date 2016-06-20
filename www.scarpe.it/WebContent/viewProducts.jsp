<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="js/viewProducts.js"></script>
<select id="scegli" onchange="mostraNascondi();">
<option value="na">Imposta Filtro:</option>
<option value="all">Tutti</option>
<option value="criteria">Imposta Criteri</option>
</select>
<div id="tutti" style="display: none;">
	<button type="button" class="searchButton" name="btnsearchforall"
		onclick="mostraTutti();">Visualizza</button>
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
	<button type="button" class="searchButton" name="btnsearchforcriteria" onclick="perCriteri();">Visualizza</button>
</div>
<section id="visualizzazione">
<c:if test="${sessionScope.prodotti != null}">
	<table>
		<tr>
			<th>ID</th>
			<th>Marca</th>
			<th>Modello</th>
			<th>Prezzo Vendita</th>
			<th>Prezzo Acquisto</th>
			<th>Quantità Disponibile</th>
			<th>Scorta Minima</th>
			<th>Disponibilita</th>
			<th>Elimina</th>
		</tr>	
		<c:forEach var="prd" items="${sessionScope.prodotti}">
			<tr>
			 	<td>${prd.id}</td>
			 	<td>${prd.marca}</td>
			 	<td>${prd.modello}</td>
			 	<td>${prd.prezzo_vendita}</td>
			 	<td>${prd.prezzo_acquisto}</td>
			 	<td>${prd.quantitaDisp}</td>
			 	<td>${prd.scorta_minima}</td>
			 	<c:if test="${prd.quantitaDisp==0}">
			 	<td><a href='EditProduct?product=${prd.id}'>Modifica</a></td>
			 	<td><a href='deleteProduct?idDelete=${prd.id}'>Abilita</a></td>
			 	</c:if>
			 	<c:if test="${prd.quantitaDisp>0 }" >
			 	<td><a href='EditProduct?product=${prd.id}'>Modifica</a></td>
			 	<td><a href='deleteProduct?idDelete=${prd.id}'>Disabilita</a></td>
			 	</c:if>
			 	
			 </tr>
		</c:forEach>
	</table>
</c:if>
</section>