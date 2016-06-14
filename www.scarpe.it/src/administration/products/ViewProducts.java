package administration.products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import database.Database;

public class ViewProducts {

	public static ResultSet getProdottiPerNomeUguale(String nome){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerNomeUguale);
		try {
			statement.setString(1, nome);
			statement.setString(2, nome);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerNomeContiene(String nome){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerNomeContiene);
		try {
			statement.setString(1, "%"+nome+"%");
			statement.setString(2, "%"+nome+"%");
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerNomeInizia(String nome){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerNomeInizia);
		try {
			statement.setString(1, nome+"%");
			statement.setString(2, nome+"%");
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerNomeTermina(String nome){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerNomeTermina);
		try {
			statement.setString(1, "%"+nome);
			statement.setString(2, "%"+nome);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerPrezzoVendita(int salePrice){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerPrezzoVendita);
		try {
			statement.setInt(1, salePrice);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerPrezzoAcquisto(int purchasePrice){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerPrezzoAcquisto);
		try {
			statement.setInt(1, purchasePrice);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String makeView(ResultSet toView) throws SQLException{
		String htmlResult = null;
		ResultSetMetaData metaData = toView.getMetaData();
		int count = metaData.getColumnCount(); //number of column
		String columnName[] = new String[count];

		for (int i = 1; i <= count; i++) {
				columnName[i-1] = metaData.getColumnLabel(i); 
		}
		
		htmlResult = "<table>"
						  + "<tr>";
		for(int j = 0; j < columnName.length; j++){
			htmlResult +="<th>" + columnName[j] + "</th>";
		}
		
		
		
		while(toView.next()){
			htmlResult +="<tr>";
			
			for(int z = 1; z <= metaData.getColumnCount(); z++){
				htmlResult +="<td>" + toView.getObject(z) + "</td>";
			}
			htmlResult +="</tr>";
		}
		
		htmlResult += "</table>";
		return htmlResult;
	}
	
	private static String prodottiPerNomeUguale;
	private static String prodottiPerNomeContiene;
	private static String prodottiPerNomeInizia;
	private static String prodottiPerNomeTermina;
	private static String prodottiPerPrezzoVendita;
	private static String prodottiPerPrezzoAcquisto;
	
	static {
		prodottiPerNomeUguale = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE marca = ? OR modello = ?";
		prodottiPerNomeContiene = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE marca LIKE ? OR modello LIKE ?";
		prodottiPerNomeInizia = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE marca LIKE ? OR modello LIKE ?";
		prodottiPerNomeTermina = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE marca LIKE ? OR modello LIKE ?";
		prodottiPerPrezzoVendita = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE prezzo_vendita = ?";
		prodottiPerPrezzoAcquisto = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE prezzo_acquisto = ?";
	}
}
