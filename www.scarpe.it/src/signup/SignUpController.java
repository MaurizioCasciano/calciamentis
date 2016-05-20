package signup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

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
				//System.err.println("INVALID: " + nextElement + " = " + nextValue);
			} else {
				//System.out.println(nextElement + " = " + nextValue);
			}
		}

		System.out.println("isComplete: " + isComplete);

		if (isComplete) {
			//DATI ANAGRAFICI
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String birthDay = request.getParameter("birthday");
			
			System.out.println("BIRTHDAY: " + birthDay);
			
			String codiceFiscale = request.getParameter("cf");
			//EXTRA 1
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date;
			GregorianCalendar dataDiNascita = null;
			
			try {
				date = dateFormat.parse(birthDay);
				dataDiNascita = new GregorianCalendar();
				dataDiNascita.setTime(date);
				
				System.out.println("DATA DI NASCITA: " + dataDiNascita);
				
			} catch (ParseException e1) {
				e1.printStackTrace();
				request.setAttribute("birthday", "Error");
			}
			
			
			//DATI DI ACCESSO
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			
			//INDIRIZZO DI RESIDENZA
			String viaResidenza = request.getParameter("home-street");
			String provinciaResidenza = request.getParameter("home-province");
			String cittaResidenza = request.getParameter("home-city");
			int codiceAvviamentoPostaleResidenza = Integer.parseInt(request.getParameter("home-cap"));
			int numeroCivicoResidenza = Integer.parseInt(request.getParameter("home-street-number"));
			
			//INDIRIZZO DI SPEDIZIONE
			String viaSpedizione = request.getParameter("shipping-street");
			String provinciaSpedizione = request.getParameter("shipping-province");
			String cittaSpedizione = request.getParameter("shipping-city");
			int codiceAvviamentoPostaleSpedizione = Integer.parseInt(request.getParameter("shipping-cap"));
			int numeroCivicoSpedizione = Integer.parseInt(request.getParameter("shipping-street-number"));
			
			

			User user = new User(nome, cognome, dataDiNascita, codiceFiscale, email, username, repassword, viaResidenza, provinciaResidenza, cittaResidenza, codiceAvviamentoPostaleResidenza, numeroCivicoResidenza, viaSpedizione, provinciaSpedizione, cittaSpedizione, codiceAvviamentoPostaleSpedizione, numeroCivicoSpedizione);

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
