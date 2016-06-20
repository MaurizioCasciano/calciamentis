package administration.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import administration.products.ViewProducts;

@WebServlet("/ProdottiInEsaurimento")
public class ProdottiInEsaurimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		session.setAttribute("report", ViewProducts.getGoods(ViewProducts.getProdottiInEsaurimento()));
		System.out.println("Sono la Servlet ProdottiInEsaurimento");
		System.out.println(session.getAttribute("report"));
		response.sendRedirect("management.jsp?feed=no&oldLoad=viewReport.jsp&message=caricamentoOttenuto");
		//request.getRequestDispatcher("viewReport.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
