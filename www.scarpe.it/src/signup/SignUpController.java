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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
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
			// DATI ANAGRAFICI
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String birthday = request.getParameter("birthday");
			String codiceFiscale = request.getParameter("codiceFiscale");

			// DATI DI ACCESSO
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");

			// INDIRIZZO DI RESIDENZA
			String viaResidenza = request.getParameter("homeStreet");
			String provinciaResidenza = request.getParameter("homeProvince");
			String cittaResidenza = request.getParameter("homeCity");
			String codiceAvviamentoPostaleResidenza = request.getParameter("homeCap");
			String numeroCivicoResidenza = request.getParameter("homeStreetNumber");

			// INDIRIZZO DI SPEDIZIONE
			String viaSpedizione = request.getParameter("shippingStreet");
			String provinciaSpedizione = request.getParameter("shippingProvince");
			String cittaSpedizione = request.getParameter("shippingCity");
			String codiceAvviamentoPostaleSpedizione = request.getParameter("shippingCap");
			String numeroCivicoSpedizione = request.getParameter("shippingStreetNumber");

			User user = new User(nome, cognome, birthday, codiceFiscale, email, username, repassword, viaResidenza,
					provinciaResidenza, cittaResidenza, codiceAvviamentoPostaleResidenza, numeroCivicoResidenza,
					viaSpedizione, provinciaSpedizione, cittaSpedizione, codiceAvviamentoPostaleSpedizione,
					numeroCivicoSpedizione);

			if (!Database.isAvailableUsername(username)) {
				isValid = false;
				request.setAttribute("username", "already registered");
			}

			if (!password.equals(repassword)) {
				isValid = false;
				request.setAttribute("password", "password mismatch");
				request.setAttribute("repassword", "password mismatch");
			}

			if (isValid) {
				Database.addUser(user);
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
