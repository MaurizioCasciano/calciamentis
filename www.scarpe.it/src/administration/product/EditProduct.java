package administration.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import catalog.Detail;
import database.Database;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int itemID = Integer.parseInt(request.getParameter("product"));
		EditableItemBean editableItemBean = Database.getEditableItem(itemID);

		request.getSession().setAttribute("editableBean", editableItemBean);

		response.sendRedirect("management.jsp?oldLoad=editItemPage.jsp&red=ok");
		// request.getRequestDispatcher("management.jsp").forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EditableItemBean myBean = new EditableItemBean();
		try {
			BeanUtils.populate(myBean, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(myBean.getDescrizione());
		ArrayList<Detail> dettagli = new ArrayList<Detail>();
		for (int i = 1; i < 5; i++) {
			Detail d = new Detail(request.getParameter("intestazione" + i), request.getParameter("corpo" + i));
			dettagli.add(d);
		}
		myBean.setDettagli(dettagli);
		Database.EditItem(myBean);
		response.sendRedirect("management.jsp?feed=ok&message=Modifica_Eseguita");

	}

}
