<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	th, td {text-align: center}
</style>
<script type="text/javascript" src="js/admin.js"></script>
<body>
report size = ${report.size()} <br />
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
		<c:if test="${requestScope.report} != null">
		<c:forEach var="report" items="${requestScope.report}">
			<tr>
			 <td>${report.id}</td>
			 <td>${report.marca}</td>
			 <td>${report.modello}</td>
			 <td>${report.prezzo_vendita}</td>
			 <td>${report.prezzo_acquisto}</td>
			 <td>${report.quantitaDis}</td>
			 <td>${report.scorta_minima}</td>
			 </tr>
		</c:forEach>
		</c:if>
	</table>
</section>
</body>