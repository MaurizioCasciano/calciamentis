package administration.products;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProdottiPerVisualizzazione")
public class ProdottiPerVisualizzazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nome = request.getParameter("nome");
		int prezzoVendita;
		int prezzoAcquisto;
		if(request.getParameter("prezzoVendita") != null && request.getParameter("prezzoAcquisto") != null){
			prezzoVendita = Integer.parseInt(request.getParameter("prezzoVendita"));
			prezzoAcquisto = Integer.parseInt(request.getParameter("prezzoAcquisto"));
		} else {
			prezzoVendita = 0;
			prezzoAcquisto = 0;
		}
		
		if((nome == null || nome.equals("")) && (prezzoVendita == 0) && (prezzoAcquisto == 0)){
			
				session.setAttribute("prodotti", ViewProducts.getGoods(ViewProducts.getProdottiAll()));
				response.sendRedirect("management.jsp?feed=no&oldLoad=viewProducts.jsp&message=caricamentoOttenuto");
		} else {
				try {
					session.setAttribute("prodotti", ViewProducts.getGoods(ViewProducts.getProdotti(nome, prezzoVendita, prezzoAcquisto)));
					response.sendRedirect("management.jsp?feed=no&oldLoad=viewProducts.jsp&message=caricamentoOttenuto");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
