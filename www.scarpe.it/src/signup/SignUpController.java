package signup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import database.Database;
import utilities.Check;
import utilities.user.User;

@SuppressWarnings("serial")
@WebServlet("/register")
public class SignUpController extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("getLocalPort: " + request.getLocalPort());
		System.out.println("getRemotePort: " + request.getRemotePort());
		System.out.println("getServerPort: " + request.getServerPort());

		Enumeration<String> parameters = request.getParameterNames();
		boolean isComplete = true;
		boolean isValid = true;

		User userBean = (User) request.getAttribute("user");
		if (userBean == null) {
			System.out.println("UserBean has been created.");
			userBean = new User();
			request.setAttribute("user", userBean);
		}

		try {
			BeanUtils.populate(userBean, request.getParameterMap());
			System.out.println("UserBean has been populated.");
			System.out.println("UserBean: " + userBean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

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
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");


			if (!Database.isAvailableUsername(userBean.getUsername())) {
				isValid = false;
				request.setAttribute("username", "already registered");
			}

			if (!password.equals(repassword)) {
				isValid = false;
				request.setAttribute("password", "password mismatch");
				request.setAttribute("repassword", "password mismatch");
			}

			if (isValid) {
				System.out.println("NUOVO UTENTE: " + userBean);

				Database.addUser(userBean);
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
