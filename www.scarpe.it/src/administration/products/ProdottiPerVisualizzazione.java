package administration.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProdottiPerVisualizzazione")
public class ProdottiPerVisualizzazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		 
		
		PrintWriter out = response.getWriter();
		
		if((nome == null || nome.equals("")) && (prezzoVendita == 0) && (prezzoAcquisto == 0)){
			try {
				out.println(ViewProducts.makeView(ViewProducts.getProdottiAll()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		
			try {
				System.out.println(ViewProducts.makeView(ViewProducts.getProdotti(nome, prezzoVendita, prezzoAcquisto)));
				out.println(ViewProducts.makeView(ViewProducts.getProdotti(nome, prezzoVendita, prezzoAcquisto)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
