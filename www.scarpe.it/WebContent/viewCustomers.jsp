<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<a href="ClientiPerVisualizzazione">Visualizza</a>
<section id="elencoClienti">
<c:if test="${sessionScope.clienti != null}">
	<table>
		<tr>
			<th>Cognome</th>
			<th>Nome</th>
			<th>Data di Nascita</th>
			<th>Codice Fiscale</th>
			<th>Email</th>
			<th>Indirizzo Residenza</th>
			<th>Indirizzo Spedizione</th>
			<th>Acquisti</th>
		</tr>
		<c:forEach var="clt" items="${sessionScope.clienti}">
			<tr>
				<td>${clt.surname}</td>
				<td>${clt.name}</td>
				<td>${clt.birthday}</td>
				<td>${clt.codiceFiscale}</td>
				<td>${clt.email}</td>
				<td><a href="viewAddress.jsp?indirizzo=">Visualizza</a></td>
				<td><a href="viewAddress.jsp?indirizzo=">Visualizza</a></td>
				<td><a href="#">Visualizza</a></td>
			</tr>
		</c:forEach>	
	</table>
</c:if>
</section>