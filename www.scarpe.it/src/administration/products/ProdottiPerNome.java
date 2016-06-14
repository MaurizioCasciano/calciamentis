package administration.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProdottiPerNome")
public class ProdottiPerNome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String tipo = request.getParameter("tipo");
		PrintWriter out = response.getWriter();
		try {
			switch(tipo){
				case "uguale":
					out.println(ViewProducts.makeView(ViewProducts.getProdottiPerNomeUguale(nome)));
					break;
				case "contiene":
					out.println(ViewProducts.makeView(ViewProducts.getProdottiPerNomeContiene(nome)));
					break;
				case "inizia":
					out.println(ViewProducts.makeView(ViewProducts.getProdottiPerNomeInizia(nome)));
					break;
				case "termina":
					out.println(ViewProducts.makeView(ViewProducts.getProdottiPerNomeTermina(nome)));
					break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
