package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import items.Item;

/**
 * Servlet implementation class LoadItems
 */
@WebServlet("/LoadItems")
public class LoadItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		Database.openConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(new GregorianCalendar().getTimeInMillis());
		String query = "SELECT * FROM scarpe;";
		ResultSet resultSet;
		ArrayList<Item> productsList = new ArrayList<>();
		try {
			resultSet = Database.executeQuery(query);
			System.out.println("result set"+resultSet);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String marca=resultSet.getString(2);
				String modello=resultSet.getString(3);
				int prezzo_vendita= resultSet.getInt(4);
				int prezzo_acquisto= resultSet.getInt(5);
				int quantitaDisp= resultSet.getInt(6);
				int scorta_minima= resultSet.getInt(7);
				ArrayList<String> images=new ArrayList <>();
				for(int i=8;i<=13;i++){
					images.add(resultSet.getString(i));
				}
				String alt=resultSet.getString(14);
				String descrizione=resultSet.getString(15);
				ArrayList<String> dettagli=new ArrayList<>();
				
				for(int i=16;i<=20;i++){
					dettagli.add(resultSet.getString(i));
				}
				
				Item currentItem=new Item(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, images, alt, descrizione, dettagli);
				
				System.out.println("Current Item: "+currentItem);
				productsList.add(currentItem);
				
			}
			request.setAttribute("productsList", productsList);
			RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		Database.closeConnection();
	}

}
