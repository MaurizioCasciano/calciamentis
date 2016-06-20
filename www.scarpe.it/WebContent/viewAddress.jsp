<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<section id="indirizzoCliente">
<p> Username: ${username} </p>
<c:if test="${sessionScope.clienti != null}">
	<table>
		<tr>
			<th>Cognome</th>
			<th>Nome</th>
			<th>Indirizzo</th>
			<th>Numero Civico</th>
			<th>CAP</th>
			<th>Città</th>
			<th>Provincia</th>
		</tr>
		<c:forEach var="clt" items="${sessionScope.clienti}">
			<c:if test="${clt.username == requestScope.username}">
			<tr>
				<td>${clt.surname}</td>
				<td>${clt.name}</td>
				<td>${clt.username}</td>
				<td>${clt.birthday}</td>
				<td>${clt.codiceFiscale}</td>
				<td>${clt.email}</td>
				<td class="link"><a href="viewAddress.jsp?username=${clt.username}">Visualizza</a></td>
			</tr>
			</c:if>
		</c:forEach>	
	</table>
</c:if>
</section>