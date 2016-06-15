package administration.products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import database.Database;

public class ViewProducts {
	
	public static ResultSet getProdottiAll(){
		ResultSet result = null;
		
		try {
			result = Database.executeQuery(prodottiSenzaFiltro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerNome(String nome){
		ResultSet result = null;
		PreparedStatement statement = Database.getPreparedStatement(prodottiPerNome);
		try {
			statement.setString(1, nome);
			statement.setString(2, nome);
			statement.setString(3, "%"+nome+"%");
			statement.setString(4, "%"+nome+"%");
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
	
	public static ResultSet getProdotti(String nome, int salePrice, int purchasePrice) throws SQLException{
		ResultSet result = null;
		PreparedStatement statement;
		
		if((nome != null && !nome.equals("")) && (salePrice != 0) && ( purchasePrice != 0)){
			statement = Database.getPreparedStatement(prodottiCompleto);
			statement.setString(1, nome);
			statement.setString(2, nome);
			statement.setString(3, "%"+nome+"%");
			statement.setString(4, "%"+nome+"%");
			statement.setInt(5, salePrice);
			statement.setInt(6, purchasePrice);
			
			result = statement.executeQuery();
		
		} else if((nome != null && !nome.equals("")) && (salePrice != 0) && ( purchasePrice == 0)){
			statement = Database.getPreparedStatement(prodottiNomePrezzoVendita);
			statement.setString(1, nome);
			statement.setString(2, nome);
			statement.setString(3, "%"+nome+"%");
			statement.setString(4, "%"+nome+"%");
			statement.setInt(5, salePrice);
			
			result = statement.executeQuery();
		
		} else if((nome != null && !nome.equals("")) && (salePrice == 0) && ( purchasePrice != 0)){
			statement = Database.getPreparedStatement(prodottiNomePrezzoAcquisti);
			statement.setString(1, nome);
			statement.setString(2, nome);
			statement.setString(3, "%"+nome+"%");
			statement.setString(4, "%"+nome+"%");
			statement.setInt(5, purchasePrice);
			
			result = statement.executeQuery();
		} else if((nome != null && !nome.equals("")) && (salePrice == 0) && ( purchasePrice == 0)){
			result = ViewProducts.getProdottiPerNome(nome);
		
		} else if(nome == null || nome.equals("")){
			if(salePrice == 0 && purchasePrice != 0){
				result = ViewProducts.getProdottiPerPrezzoAcquisto(purchasePrice);
			}
			if(salePrice != 0 && purchasePrice == 0){
				result = ViewProducts.getProdottiPerPrezzoVendita(salePrice);
			}
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
		
		htmlResult +="<th>Modifica</th></tr>";
		
		while(toView.next()){
			htmlResult +="<tr>";
			
			for(int z = 1; z <= metaData.getColumnCount(); z++){
				htmlResult +="<td>" + toView.getObject(z) + "</td>";
			}
			htmlResult +="<td><a href='editProduct.jsp?id="+toView.getInt(1)+"'>Modifica</a></td></tr>";
		}
		
		htmlResult += "</table>";
		return htmlResult;
	}
	
	private static String prodottiSenzaFiltro;
	private static String prodottiCompleto;
	private static String prodottiNomePrezzoVendita;
	private static String prodottiNomePrezzoAcquisti;
	private static String prodottiPerNome;
	private static String prodottiPerPrezzoVendita;
	private static String prodottiPerPrezzoAcquisto;
	
	static {
		prodottiSenzaFiltro = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima "
							+ "FROM scarpe;";
		prodottiCompleto = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima "
				 		 + "FROM scarpe "
				 		 + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) AND "
						 + "prezzo_vendita = ? AND prezzo_acquisto = ?;";
		prodottiNomePrezzoVendita = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima "
		 		 				  + "FROM scarpe "
		 		 				  + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) AND "
		 		 				  + "prezzo_vendita = ?";
		prodottiNomePrezzoAcquisti = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima "
		 		 				   + "FROM scarpe "
		 		 				   + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) "
		 		 				   + "AND prezzo_acquisto = ?";
		prodottiPerNome = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima "
						+ "FROM scarpe "
						+ "WHERE (marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)";
		prodottiPerPrezzoVendita = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE prezzo_vendita = ?";
		prodottiPerPrezzoAcquisto = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima FROM scarpe WHERE prezzo_acquisto = ?";
	}
}
