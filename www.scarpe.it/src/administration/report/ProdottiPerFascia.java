package administration.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProdottiPerFascia")
public class ProdottiPerFascia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int prezzomin = Integer.parseInt(request.getParameter("prezzomin"));
		int prezzomax = Integer.parseInt(request.getParameter("prezzomax"));
		
		PrintWriter out = response.getWriter();
		try {
			out.println(Report.makeReport(Report.getResultSetProdottiPerFasciaPrezzo(prezzomin, prezzomax)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
