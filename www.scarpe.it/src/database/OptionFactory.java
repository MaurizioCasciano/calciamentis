package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OptionFactory
 */
@WebServlet("/OptionFactory")
public class OptionFactory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PROVINCE_MODE = "province", COMUNI_MODE = "comuni";

	@Override
	public void init() throws ServletException {
		Database.openConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String mode = request.getParameter("mode");
		String province = request.getParameter("provincia");
		//System.out.println("Mode: " + mode);

		if (mode.equalsIgnoreCase(PROVINCE_MODE)) {
			try {
				ResultSet resultSet = Database
						.executeQuery("select nomeProvincia from province order by nomeProvincia;");
				PrintWriter out = response.getWriter();
				//DBTablePrinter.printResultSet(resultSet);

				/*
				 * out.println(
				 * "<option selected = selected value='' style = 'display: none;'>Selezione una provincia</option>"
				 * );
				 */

				if (resultSet.next()) {
					String currentProvincia = resultSet.getString(1);

					out.println("<option value='" + currentProvincia + "' selected>" + currentProvincia + "</option>");
				}

				while (resultSet.next()) {
					String currentProvincia = resultSet.getString(1);

					out.println("<option value='" + currentProvincia + "'>" + currentProvincia + "</option>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (mode.equalsIgnoreCase(COMUNI_MODE)) {
			//System.out.println("COMUNI_MODE");
			//System.out.println("Provincia: " + province);

			try {
				ResultSet resultSet = Database.executeQuery(
						"SELECT c.comune FROM comuni c JOIN province p ON c.provincia = p.siglaprovincia WHERE p.nomeProvincia = '"
								+ province + "' ORDER BY c.comune;");
				//System.out.println("Comuni di " + province);
				//DBTablePrinter.printResultSet(resultSet);
				PrintWriter out = response.getWriter();

				if (resultSet.next()) {
					String currentComune = resultSet.getString(1);
					// MAKES the first element selected.
					out.println("<option value='" + currentComune + "' selected>" + currentComune + "</option>");
				}

				while (resultSet.next()) {
					String currentComune = resultSet.getString(1);

					out.println("<option value='" + currentComune + "'>" + currentComune + "</option>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
