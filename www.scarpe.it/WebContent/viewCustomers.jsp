<%@page import="paydesk.PurchasedItem"%>
<%@page import="paydesk.PurchasedCart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utilities.user.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/admin.js"></script>
<script type="text/javascript" src="js/customersManagement.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
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
					<td class="link1"><div
							class="indirizzoResidenza fa fa-plus-square"></div></td>
					<td class="link2"><div
							class="indirizzoSpedizione fa fa-plus-square"></div></td>
					<td class="link3"><div class="acquisti fa fa-plus-square"></div></td>
				</tr>
				<tr class="dettagli1" style="display: none;">
					<td colspan="9">
						<div>
							<p>
								Indirizzo: <span>${clt.homeStreet}</span>
							</p>
							<p>
								Numero Civico: <span>${clt.homeStreetNumber}</span>
							</p>
							<p>
								CAP: <span>${clt.homeCap}</span>
							</p>
							<p>
								Città: <span>${clt.homeCity}</span>
							</p>
							<p>
								Provincia: <span>${clt.homeProvince}</span>
							</p>
						</div>
					</td>
				</tr>
				<tr class="dettagli2" style="display: none;">
					<td colspan="9">
						<div>
							<p>
								Indirizzo: <span>${clt.shippingStreet}</span>
							</p>
							<p>
								Numero Civico: <span>${clt.shippingStreetNumber}</span>
							</p>
							<p>
								CAP: <span>${clt.shippingCap}</span>
							</p>
							<p>
								Città: <span>${clt.shippingCity}</span>
							</p>
							<p>
								Provincia: <span>${clt.shippingProvince}</span>
							</p>
						</div>
					</td>
				</tr>
				<tr class="dettagli3" style="display: none;">
					<td colspan="9">
						<%
							@SuppressWarnings("unchecked")
									ArrayList<User> users = (ArrayList<User>) session.getAttribute("clienti");

									User u = (User) pageContext.getAttribute("clt");

									ArrayList<PurchasedCart> carts = u.getPurchasedCarts();
									if (carts.size() == 0) {
										out.println("Non ci sono acquisti per questo cliente");
									}

									for (PurchasedCart cart : carts) {
										out.println(
												"<table>" + "<tr class ='purchaseRow'>" + "<th class='sinistra'>ID Acquisto:&nbsp;" + cart.getCartId()
														+ "</th>" + "<th colspan='4' style='text-align: right; padding-right: 10px;'>"
														+ cart.getDate() + "</th></tr>");
										out.println("<tr class ='purchaseRow'>" + "<th class='intestazioniInterne'>Articolo</th>"
												+ "<th class='intestazioniInterne'>Quantità</th>"
												+ "<th class='intestazioniInterne'>Prezzo</th>"
												+ "<th class='intestazioniInterne'>Importo</th></tr>");

										ArrayList<PurchasedItem> items = (ArrayList<PurchasedItem>) cart.getAllPurchasedItem();

										for (PurchasedItem item : items) {
											out.println("<tr class ='purchaseRow'>");
											out.println("<td>" + item.getCatalogItem().getMarca() + " "
													+ item.getCatalogItem().getModello() + "</td>");
											out.println("<td class='numeri'>" + item.getQuantita() + "</td>");
											out.println("<td class='numeri'>&nbsp; " + item.getPrezzo() + "</td>");
											out.println("<td class='numeri'>&euro;&nbsp;" + item.getPrezzoTotal() + "</td>");
											out.println("</tr>");
										}
										out.println("<tr class ='purchaseRow'>");
										out.println("<th colspan='3' style='text-align: right;'>Totale: </th>");
										out.println("<th class='numeri' style='padding: 8px;'>&nbsp;&euro;&nbsp;" + cart.getTotale()
												+ "</th>");
										out.println("</tr>");
										out.println("</table>");
									}
						%>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</section>