package cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import catalog.Item;

/**
 * Servlet implementation class OrderPage
 */
@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet {
	
	ShoppingCart cart = new ShoppingCart();
	
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object lock = session.getAttribute("SessionLock");
		if (lock == null) {
		      lock = new Object();
		      System.out.println(lock);
		      session.setAttribute("SessionLock", lock);
		}

		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("Request URI: " + request.getRequestURI());
		System.out.println("Request contextPath: " + request.getContextPath());

		System.out.println("Hello from OrderPage");

		synchronized (lock) {
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
			PrintWriter out = response.getWriter();
			out.println("Items in the cart: " + cart.getItemsOrdered().size());
			for (ItemOrder itemOrder : cart.getItemsOrdered()) {
				Item item = itemOrder.getItem();
				out.println(itemOrder.getNumberOfItems() + " x " + item.getModello());
			}

			out.println("SERVLET CART TOTAL: " + cart.getTotal());
			session.setAttribute("shoppingCart", cart);
			//DEBUG
			System.out.println("SessionCartTotal: " + ((ShoppingCart) session.getAttribute("shoppingCart")).getTotal());
			System.out.println("ServletCartTotal: " + cart.getTotal());
		} // END synchronized

		
	}

	private static final long serialVersionUID = 1L;
}
