package login;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.Database;
import utilities.Check;
import utilities.user.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

		super.init();
		Database.openConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Login doPost");

		Enumeration<String> parameters = request.getParameterNames();
		HttpSession session = request.getSession(true);
		boolean isComplete = true;
		boolean isValid = true;

		while (parameters.hasMoreElements()) {
			String nextElement = parameters.nextElement();
			String nextValue = request.getParameter(nextElement);

			if (!Check.isValid(nextValue)) {
				isComplete = false;
				request.setAttribute(nextElement, "*");
			}

			System.out.println(nextElement + " = " + nextValue);
		}

		String username = "", password = "";

		if (isComplete) {
			username = request.getParameter("username");
			password = request.getParameter("password");

			User user = Database.getUser(username);

			if (user == null) {
				isValid = false;
			} else if (!user.passwordMatch(password)) {
				isValid = false;
			} else {
				isValid = true;
			}

			if (isComplete && isValid) {
				session.setAttribute("loggedUser", user);
				System.out.println("LoggedUser: " + session.getAttribute("loggedUser"));
			} else if (isComplete && !isValid) {
				request.setAttribute("error", "ERROR: Wrong username or password!!!");
				System.out.println("Login fail: ERROR: Wrong username or password!!!");
			} else if (!isComplete) {
				request.setAttribute("error", "ERROR: Missing arguments!!!");
				System.out.println("Login fail: ERROR: Missing arguments!!!");
			}
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login doGet");

		doPost(request, response);
	}
}
