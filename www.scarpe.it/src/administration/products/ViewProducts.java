package administration.products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import catalog.Detail;
import administration.product.EditableItemBean;

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
	
	public static ResultSet getProdottiInEsaurimento(){
		ResultSet result = null;
		
		try {
			result = Database.executeQuery(prodottiInEsaurimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getResultSetProdottiPerFasciaPrezzo(int min, int max){
		ResultSet result = null;
		
		PreparedStatement ps = Database.getPreparedStatement(prodottiPerFasciaPrezzo);
		
		try {
			ps.setInt(1, min);
			ps.setInt(2, max);
			System.out.println(ps);
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	public static ArrayList<EditableItemBean> getGoods(ResultSet result){
		ArrayList<EditableItemBean> goods = null;
		ArrayList<Detail> details = null;
		
		int size = 0;
		
		ResultSet dettagli = null;
		
		if(result != null){
			try {
				result.last(); //mi posiziono alla fine
				size = result.getRow(); //acquisisco il numero di righe del result set
				result.beforeFirst(); // torno all'inizio
				
				System.out.println("Size di result: " + size);
				PreparedStatement statDet = Database.getPreparedStatement(selectDettagliProdotto);
				goods = new ArrayList<EditableItemBean>();
				
				for(int i = 1; i <= size; i++){
					result.next();
					
					statDet.setInt(1, result.getInt("idScarpe"));
					dettagli = statDet.executeQuery();
					
					int sizeDettagli = 0;
					
					if(dettagli != null){
						
						dettagli.last();
						sizeDettagli = dettagli.getRow();
						dettagli.beforeFirst();
						
						System.out.println("size di dettagli: " + sizeDettagli);
						
						details = new ArrayList<Detail>();
						
						for(int z = 1; z <= sizeDettagli; z++){
							dettagli.next();
							details.add(new Detail(dettagli.getString("intestazione"), dettagli.getString("corpo")));
						}
						
						int id = result.getInt("idScarpe");
						String marca = result.getString("marca");
						String modello = result.getString("modello");
						int prezzo_vendita = result.getInt("prezzo_vendita");
						int prezzo_acquisto = result.getInt("prezzo_acquisto");
						int quantitaDisp = result.getInt("quantitaDisp");
						int scorta_minima = result.getInt("scorta_minima");
						String alt = result.getString("alt");
						String descrizione = result.getString("descrizione");
						
						goods.add(new EditableItemBean(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione, details));
					}	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Size di goods: " + goods.size());
		return goods;
	}
	
	@Deprecated
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
	private static String prodottiInEsaurimento;
	private static String prodottiPerFasciaPrezzo;
	private static String selectDettagliProdotto;
	
	static {
		prodottiSenzaFiltro = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione "
							+ "FROM scarpe;";
		prodottiCompleto = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione "
				 		 + "FROM scarpe "
				 		 + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) AND "
						 + "prezzo_vendita = ? AND prezzo_acquisto = ?;";
		prodottiNomePrezzoVendita = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione "
		 		 				  + "FROM scarpe "
		 		 				  + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) AND "
		 		 				  + "prezzo_vendita = ?";
		prodottiNomePrezzoAcquisti = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione "
		 		 				   + "FROM scarpe "
		 		 				   + "WHERE ((marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)) "
		 		 				   + "AND prezzo_acquisto = ?";
		prodottiPerNome = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione "
						+ "FROM scarpe "
						+ "WHERE (marca = ? OR modello = ?) OR (marca LIKE ? OR modello LIKE ?)";
		prodottiPerPrezzoVendita = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione FROM scarpe WHERE prezzo_vendita = ?";
		prodottiPerPrezzoAcquisto = "SELECT idScarpe, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione FROM scarpe WHERE prezzo_acquisto = ?";
		prodottiInEsaurimento = "SELECT * "
				  + "FROM scarpe "
				  + "WHERE quantitaDisp <= scorta_minima";

prodottiPerFasciaPrezzo = "SELECT * "
					+ "FROM scarpe "
					+ "WHERE prezzo_vendita BETWEEN ? AND ?";	
		selectDettagliProdotto = "SELECT * FROM dettagli WHERE scarpa = ?";
	}
}
