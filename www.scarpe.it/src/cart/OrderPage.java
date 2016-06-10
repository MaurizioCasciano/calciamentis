package cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Servlet implementation class OrderPage
 */
@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		ShoppingCart cart;

		System.out.println("Hello from OrderPage");

		synchronized (session) {
			cart = (ShoppingCart) session.getAttribute("shoppingCart");
			// New visitors get a fresh shopping cart.
			// Previous visitors keep using their existing cart.
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
			}

			String stringItemID = request.getParameter("itemID");
			String stringNumberOfItems = request.getParameter("numItems");

			if (stringItemID != null) {
				int itemID = -1;

				try {
					itemID = Integer.parseInt(stringItemID);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (stringNumberOfItems == null) {
					// If request specified an ID but no number,
					// then customers came here via an "Add Item to Cart"
					// button on a catalog page.
					cart.addItem(itemID);
				} else {
					// If request specified an ID and number, then
					// customers came here via an "Update Order" button
					// after changing the number of items in order.
					// Note that specifying a number of 0 results
					// in item being deleted from cart.
					int numberOfItems = -1;
					try {
						numberOfItems = Integer.parseInt(stringNumberOfItems);
					} catch (NumberFormatException nfe) {
						numberOfItems = 1;
					}
					cart.setNumberOfItems(itemID, numberOfItems);
				}
			}
			
			response.setContentType("application/xml");
			PrintWriter out = response.getWriter();
			
			Document cartDocument = cart.toXMLDocument();
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(cartDocument, out);

			System.out.println("Cart xml: " + cartDocument);
			System.out.println("Cart xml text: ");
			xmlOutputter.output(cartDocument, System.out);
		} // END synchronized
	}

	private static final long serialVersionUID = 1L;
}
