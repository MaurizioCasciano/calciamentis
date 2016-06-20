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
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		ShoppingCart shoppingCart;

		System.out.println("Hello from OrderPage");

		synchronized (session) {
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			// New visitors get a fresh shopping cart.
			// Previous visitors keep using their existing cart.
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCart();
				session.setAttribute("shoppingCart", shoppingCart);
			}

			String stringItemID = request.getParameter("itemID");

			if (stringItemID != null) {
				int itemID = -1;

				try {
					itemID = Integer.parseInt(stringItemID);
				} catch (Exception e) {
					e.printStackTrace();
				}

				shoppingCart.addItem(itemID);
			}

			response.setContentType("application/xml");
			PrintWriter out = response.getWriter();

			Document cartDocument = shoppingCart.toXMLDocument();
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(cartDocument, out);

			System.out.println("Cart xml: " + cartDocument);
			System.out.println("Cart xml text: ");
			xmlOutputter.output(cartDocument, System.out);
		} // END synchronized
	}

	private static final long serialVersionUID = 1L;
}
