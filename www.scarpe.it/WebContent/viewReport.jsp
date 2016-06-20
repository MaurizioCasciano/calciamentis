<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	th, td {text-align: center}
</style>
<script type="text/javascript" src="js/admin.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<body>
<select id="choice" onchange="showHidden()">
<option value="na">Scegli report:</option>
<option value="prodottiEsaurimento">Prodotti in Esaurimento</option>
<option value="prodottiFasciaPrezzo">Prodotti per fascia prezzo</option>
</select>
<div id="impostaFascia" style="display: none;">
<label for="prezzomin">Prezzo Minimo: </label> <br />
<input type="number" class="caselleParametri" id="prezzomin" name="prezzomin" min=0> <br/>
<label for="prezzomax">Prezzo Massimo: </label> <br />
<input type="number" class="caselleParametri" id="prezzomax" name="prezzomax" min=0> <br/>
<button type="button" class="searchButton" id="btnsearch" name="btnsearch" onclick="perFascia();">Ricerca</button>
</div>
<div id="cercaProdottiEsaurimento" style="display: none;">
<button type="button" class="searchButton" id="btnsearch2" name="btnsearch2" onclick="inEsaurimento()">Ricerca</button>
</div>
<section id="report">
<c:if test="${sessionScope.report != null}">
	<table>
		<tr>
			<th>ID</th>
			<th>Marca</th>
			<th>Modello</th>
			<th>Prezzo Vendita</th>
			<th>Prezzo Acquisto</th>
			<th>Quantità Disponibile</th>
			<th>Scorta Minima</th>
		</tr>
		<c:forEach var="rpt" items="${sessionScope.report}">
			<tr>
			 	<td>${rpt.id}</td>
			 	<td>${rpt.marca}</td>
			 	<td>${rpt.modello}</td>
			 	<td>${rpt.prezzo_vendita}</td>
			 	<td>${rpt.prezzo_acquisto}</td>
			 	<td>${rpt.quantitaDisp}</td>
			 	<td>${rpt.scorta_minima}</td>
			 </tr>
		</c:forEach>
	</table>
</c:if>
</section>
</body>