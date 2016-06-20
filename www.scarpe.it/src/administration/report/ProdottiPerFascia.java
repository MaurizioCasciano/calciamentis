package administration.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import administration.products.ViewProducts;

@WebServlet("/ProdottiPerFascia")
public class ProdottiPerFascia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int prezzomin;
		int prezzomax;
		try {
			prezzomin = Integer.parseInt(request.getParameter("prezzomin"));
			prezzomax = Integer.parseInt(request.getParameter("prezzomax"));
		} catch (NumberFormatException e) {
			prezzomin = 0;
			prezzomax = Integer.MAX_VALUE;
		}
		HttpSession session = request.getSession();
		if (prezzomin >= 0 && prezzomax >= 0 && (prezzomin < prezzomax)) {
			session.setAttribute("report",
					ViewProducts.getGoods(ViewProducts.getResultSetProdottiPerFasciaPrezzo(prezzomin, prezzomax)));
			response.sendRedirect("management.jsp?feed=no&oldLoad=viewReport.jsp&message=caricamentoOttenuto");
		} else {
			response.sendRedirect("management.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
