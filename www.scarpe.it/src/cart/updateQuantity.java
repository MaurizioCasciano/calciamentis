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
 * Servlet implementation class updateQuantity
 */
@WebServlet("/updateQuantity")
public class updateQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		System.out.println(request.getParameter("value"));
		int value = Integer.parseInt(request.getParameter("value"));
		HttpSession session = request.getSession();
		System.out.println("Sono nella servlet");
		ShoppingCart currentCart = (ShoppingCart) session.getAttribute("shoppingCart");
		currentCart.getItem(itemID).setNumberOfItems(value);

		response.setContentType("application/xml");
		PrintWriter out = response.getWriter();

		Document cartDocument = currentCart.toXMLDocument();
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		xmlOutputter.output(cartDocument, out);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
