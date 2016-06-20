package administration.report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteSessionBean
 */
@WebServlet("/DeleteSessionBean")
public class DeleteSessionBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSessionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bean = request.getParameter("deleterBean");
		String scope = request.getParameter("scope");
		System.out.println("bean da eliminare " +bean);
		
		if(scope.equals("sessionScope")){
			HttpSession session = request.getSession();
			session.removeAttribute(bean);
		}
		System.out.println("ok");
		response.sendRedirect("management.jsp?feed=no&oldLoad=vievProducts.jsp&message=yuppi");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
