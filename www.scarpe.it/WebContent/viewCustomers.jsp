<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script type="text/javascript" src="js/customersManagement.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<section id="elencoClienti">
<c:if test="${sessionScope.clienti != null}">
	<table id="tabellaClienti">
		<tr style="display: table-row;">
			<th>Cognome</th>
			<th>Nome</th>
			<th>Username</th>
			<th>Data di Nascita</th>
			<th>Codice Fiscale</th>
			<th>Email</th>
			<th>Indirizzo Residenza</th>
			<th>Indirizzo Spedizione</th>
			<th>Acquisti</th>
		</tr>
		<c:forEach var="clt" items="${sessionScope.clienti}">
			<tr class="rigaDati">
				<td>${clt.surname}</td>
				<td>${clt.name}</td>
				<td>${clt.username}</td>
				<td>${clt.birthday}</td>
				<td>${clt.codiceFiscale}</td>
				<td>${clt.email}</td>
				<td class="link"><div class="indirizzoResidenza fa fa-plus-square">Mostra</div></td>
				<td class="link"><button class="indirizzoSpedizione">Mostra</button></td>
				<td class="link"><button class="acquisti">Visualizza</button></td>
			</tr>
			<tr style="display: none;">
					<td colspan="9">
								<table>
									<tr class="dettagli">
										<th>Indirizzo</th>
										<th>Numero Civico</th>
										<th>CAP</th>
										<th>Città</th>
										<th>Provincia</th>
									</tr>
									<tr class="dettagli">
										<td>${clt.homeStreet}</td>
										<td>${clt.homeStreetNumber}</td>
										<td>${clt.homeCap}</td>
										<td>${clt.homeCity}</td>
										<td>${clt.homeProvince}</td>
									</tr>
									</table>
				</td>
			</tr>
		</c:forEach>	
	</table>
</c:if>
</section>