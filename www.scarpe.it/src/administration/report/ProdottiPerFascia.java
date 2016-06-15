package administration.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import administration.products.ViewProducts;

@WebServlet("/ProdottiPerFascia")
public class ProdottiPerFascia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prezzomin = Integer.parseInt(request.getParameter("prezzomin"));
		int prezzomax = Integer.parseInt(request.getParameter("prezzomax"));
		
		if(prezzomin != 0 && prezzomax != 0 && (prezzomin < prezzomax)){
			request.setAttribute("report", ViewProducts.getGoods(ViewProducts.getResultSetProdottiPerFasciaPrezzo(prezzomin, prezzomax)));
			request.getRequestDispatcher("management.jsp").forward(request, response);
		} else {
			response.sendRedirect("management.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
