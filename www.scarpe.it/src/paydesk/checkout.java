package paydesk;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		ShoppingCart cart=(ShoppingCart) session.getAttribute("shopping-cart");
		ArrayList<ItemOrder> itemsOrdered=cart.getItemsOrdered();
		String username=(String) session.getAttribute("username");
		java.util.Date date = new Date();
		int idAcquisti = 0;
		
		
		Object param = new java.sql.Timestamp(date.getTime());
		// The JDBC driver knows what to do with a java.sql type:
		
		
		
		String statementAcquisti="INSERT INTO acquisti (data, username) VALUES (?,?);";
		PreparedStatement pSAcquisti =Database.getPreparedStatement(statementAcquisti);
		try {
			pSAcquisti.setObject(1, param); 
			pSAcquisti.setString(2, username);
			pSAcquisti.executeUpdate();
			String query="SELECT Acquisti.Id from Acquisti WHERE Acquisti.Datetime=?";
			PreparedStatement psID=Database.getPreparedStatement(query);
			psID.setObject(1,param);
			ResultSet rs=psID.executeQuery();
			while(rs.next()){
				idAcquisti=rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(ItemOrder io:itemsOrdered){
			int quantitaP=io.getNumberOfItems();
			int idP=io.getItemID();
			double P=io.getUnitCost();
			
			
			String statementDettagliAcquisti="INSERT INTO dettagli_acquisti (idAcquisti,idScarpe,quantita) VALUES (?,?,?);";
			
			
			String statementProduct="UPDATE scarpe SET scarpe.quantitaDisp=scarpe.quantitaDisp-?, WHERE scarpe.idScarpe=?;";
			
			
			try {
				PreparedStatement psAcquisti =Database.getPreparedStatement(statementAcquisti);
				psAcquisti.setInt(1, idAcquisti);
				psAcquisti.setInt(2, idP);
				psAcquisti.setInt(3, quantitaP);
				psAcquisti.executeUpdate();
				
				
				PreparedStatement psProduct =Database.getPreparedStatement(statementProduct);
				psProduct.setInt(1, quantitaP);
				psProduct.executeUpdate();
				
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
