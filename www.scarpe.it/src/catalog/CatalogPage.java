package catalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import search.SearchQuery;

/**
 * Servlet implementation class CatalogPage
 */
@WebServlet("/CatalogPage")
public class CatalogPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String cat = request.getParameter("cat");

		String key = request.getParameter("key");
		ArrayList<Item> items = Database.getItems();
		if (cat != null && key != null) {

			if (cat.equals("f0")) {
				items = SearchQuery.getProdottiPerModelloEPrezzo(key, 0, 10000);
			} else if (cat.equals("f1")) {
				items = SearchQuery.getProdottiPerModelloEPrezzo(key, 50, 100);
			} else if (cat.equals("f2")) {
				items = SearchQuery.getProdottiPerModelloEPrezzo(key, 100, 200);
			} else if (cat.equals("3")) {
				items = SearchQuery.getProdottiPerModelloEPrezzo(key, 200, 300);
			} else if (cat.equals("f4")) {
				items = SearchQuery.getProdottiPerModelloEPrezzo(key, 300, 500);
			}
		} else if (cat != null) {
			if (cat.equals("f0")) {
				items = SearchQuery.getProdottiPerFasciaPrezzoCompleto(0, 10000);
			} else if (cat.equals("f1")) {
				items = SearchQuery.getProdottiPerFasciaPrezzoCompleto(50, 100);
			} else if (cat.equals("f2")) {
				items = SearchQuery.getProdottiPerFasciaPrezzoCompleto(100, 200);
			} else if (cat.equals("f3")) {
				items = SearchQuery.getProdottiPerFasciaPrezzoCompleto(200, 300);
			} else if (cat.equals("f4")) {
				items = SearchQuery.getProdottiPerFasciaPrezzoCompleto(300, 500);
			}
		} else if (key != null) {
			items = SearchQuery.getProdottiPerModello(key);
		}

		out.println("<ul class='products'>");

		for (int i = 0; i < 5; i++) {
			for (Item item : items) {
				// SOLO ITEM con quantità positiva
				if (item.getQuantitaDisp() > 0 && item.getImages().size() > 0) {
					// System.out.println("Item: " + item);
					// System.out.println("Image: " + item.getImages().get(0));

					out.println("<li>");
					out.println("<a href='LoadProductPage?id=" + item.getId() + "'>");
					out.println("<img src='" + item.getImages().get(0) + "' />");

					out.println("<h4>" + item.getMarca() + " " + item.getModello() + "</h4>");
					out.println("</a>");

					out.println("<div>");
					out.println("<span>&euro;&nbsp;" + item.getPrezzo_vendita() + "</span>");

					out.println("<button onclick = 'addToCart(" + item.getId() + ")'>");
					out.println("<span data-tooltip='Aggiungi al carrello'>");
					out.println("<span class = 'fa fa-shopping-cart'></span>");
					out.println("</span>"); // data-tooltip END tag
					out.println("</button>");
					out.println("</div>");
					out.println("</li>");
				}
			}
		}

		out.println("</ul>");
	}
}
