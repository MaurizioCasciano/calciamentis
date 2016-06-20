package administration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminAccess
 */
@WebServlet("/AdminAccess")
public class AdminAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminAccess() {
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("adminUsername");
		String password = request.getParameter("adminPassword");
		
		if(DBAdmin.isValidAdmin(username, password)){
			session.setAttribute("loggedAdmin", DBAdmin.getAdmin(username));
			response.sendRedirect("management.jsp");
		} else {
			request.setAttribute("error", "wrongLogin");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
