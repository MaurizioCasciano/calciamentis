package signup;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.Accounts;
import utilities.Check;
import utilities.user.User;

@SuppressWarnings("serial")
@WebServlet("/register")
public class SignUpController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> parameters = request.getParameterNames();
		boolean isComplete = true;
		boolean isValid = true;

		while (parameters.hasMoreElements()) {
			String nextElement = parameters.nextElement();
			String nextValue = request.getParameter(nextElement);

			if (!Check.isValid(nextValue)) {
				isComplete = false;
				request.setAttribute(nextElement, "Error");
				System.err.println("INVALID: " + nextElement + " = " + nextValue);
			} else {
				System.out.println(nextElement + " = " + nextValue);
			}
		}

		System.out.println("isComplete: " + isComplete);

		if (isComplete) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");

			User user = new User(username, password);

			if (Accounts.getUser(username) != null) {
				isValid = false;
				request.setAttribute("username", "already registered");
			}

			if (!password.equals(repassword)) {
				isValid = false;
				request.setAttribute("password", "password mismatch");
				request.setAttribute("repassword", "password mismatch");
			}

			if (isValid) {
				try {
					Accounts.addUser(user);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					// Already registered username
					// Viene effettuato già il controllo e la gestione nell'if
					// più esterno
				}

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("summary.jsp");
				requestDispatcher.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
				rd.forward(request, response);
			}

		} else {
			isValid = false;
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.forward(request, response);
		}
	}
}
