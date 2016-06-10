package catalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administration.report.Report;
import database.Database;

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
		System.out.println(key);
		ArrayList<Item> items = Database.getItems();
		if (cat != null && key != null) {
			switch (cat) {
			case "f0": {
				items = Report.getProdottiPerModelloEPrezzo(key, 0, 10000);
				break;
			}
			case "f1": {
				items = Report.getProdottiPerModelloEPrezzo(key, 50, 100);
				break;
			}
			case "f2": {
				items = Report.getProdottiPerModelloEPrezzo(key, 100, 200);
				break;
			}
			case "f3": {
				items = Report.getProdottiPerModelloEPrezzo(key, 200, 300);
				break;
			}
			case "f4": {
				items = Report.getProdottiPerModelloEPrezzo(key, 300, 500);
				break;
			}

			}
		} else if (cat != null) {
			switch (cat) {
			case "f0": {
				items = Report.getProdottiPerFasciaPrezzo(0, 10000);
				break;
			}
			case "f1": {
				items = Report.getProdottiPerFasciaPrezzo(50, 100);
				break;
			}
			case "f2": {
				items = Report.getProdottiPerFasciaPrezzo(100, 200);
				break;
			}
			case "f3": {
				items = Report.getProdottiPerFasciaPrezzo(200, 300);
				break;
			}
			case "f4": {
				items = Report.getProdottiPerFasciaPrezzo(300, 500);
				break;
			}

			}
		} else if (key != null) {
			items=Report.getProdottiPerModello(key);
		}

		out.println("<ul class='products'>");

		for (int i = 0; i < 10; i++) {
			for (Item item : items) {
				if (item.getQuantitaDisp() > 0) {
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
