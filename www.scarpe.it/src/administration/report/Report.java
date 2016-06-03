package administration.report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

public class Report {
	
	public static ResultSet getProdottiInEsaurimento(){
		ResultSet result = null;
		
		try {
			result = Database.executeQuery(prodottiInEsaurimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ResultSet getProdottiPerFasciaPrezzo(int min, int max){
		ResultSet result = null;
		
		PreparedStatement ps = Database.getPreparedStatement(prodottiPerFasciaPrezzo);
		
		try {
			ps.setInt(1, min);
			ps.setInt(2, max);
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	private static String prodottiInEsaurimento;
	private static String prodottiPerFasciaPrezzo;
	
	static {
		prodottiInEsaurimento = "SELECT idScarpe, marca, modello, quantitaDisp, scorta_minima "
							  + "FROM scarpe "
							  + "WHERE quantitaDisp < scorta_minima";
		
		prodottiPerFasciaPrezzo = "SELECT idScarpe, marca, modello, quantitaDisp, prezzo_vendita"
								+ "FROM scarpe"
								+ "WHERE prezzo_vendita BETWEEN ? AND ?";
	}

}
