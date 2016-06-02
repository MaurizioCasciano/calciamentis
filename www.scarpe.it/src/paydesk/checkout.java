package paydesk;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cart.ItemOrder;
import cart.ShoppingCart;
import database.Database;

/**
 * Servlet implementation class checkout
 */
@WebServlet("/checkout")
public class checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ShoppingCart cart=(ShoppingCart) session.getAttribute("shoppingCart");
		ArrayList<ItemOrder> itemsOrdered=cart.getItemsOrdered();
		String username=(String) session.getAttribute("loggedUser");
		int idAcquisti = 0;
		
		GregorianCalendar g=new GregorianCalendar();
		java.util.Date date = g.getTime();
		Timestamp timestamp = new Timestamp(date.getTime());
		// The JDBC driver knows what to do with a java.sql type:
		
		
		
		String statementAcquisti="INSERT INTO acquisti (data, username) VALUES (?,?);";
		PreparedStatement pSAcquisti =Database.getPreparedStatement(statementAcquisti);
		try {
			pSAcquisti.setObject(1, timestamp); 
			pSAcquisti.setString(2, username);
			pSAcquisti.executeUpdate();
			//Inserito l'acquisto nel db
			String query="SELECT acquisti.idAcquisti from acquisti WHERE acquisti.data=?;";
			PreparedStatement psID=Database.getPreparedStatement(query);
			
			psID.setTimestamp(1, timestamp);
			System.out.println(psID);
			ResultSet rs=psID.executeQuery();
			while(rs.next()){
				System.out.println("Il result set contiene qualcosa");
				idAcquisti = rs.getInt("idAcquisti");
			}
			
			System.out.println("l'id generato � "+idAcquisti);
			//ottengo l'id generato automaticamente
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(ItemOrder io:itemsOrdered){
			int quantitaP=io.getNumberOfItems();
			int idP=io.getItemID();
			double P=io.getUnitCost();
			
			
			String statementDettagliAcquisti="INSERT INTO dettagli_acquisti (idAcquisti,idScarpe,quantita) VALUES (?,?,?);";
			
			
			String statementProduct="UPDATE scarpe SET scarpe.quantitaDisp=scarpe.quantitaDisp-? WHERE scarpe.idScarpe=?;";
			
			
			try {
				PreparedStatement psDetails=Database.getPreparedStatement(statementDettagliAcquisti);
				psDetails.setInt(1, idAcquisti);
				psDetails.setInt(2, idP);
				psDetails.setInt(3, quantitaP);
				System.out.println("l'id aquisti � "+ idAcquisti+" l'id prodotto � "+idP+" la quantita � "+quantitaP);
				psDetails.executeUpdate();
				//inserisco i dettagli
				PreparedStatement psProduct =Database.getPreparedStatement(statementProduct);
				psProduct.setInt(1, quantitaP);
				psProduct.setInt(2, idP);
				psProduct.executeUpdate();
				//aggiorno quantit� prodotto
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
