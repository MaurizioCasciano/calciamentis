package administration.report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			System.out.println(ps);
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	public static String makeReport(ResultSet report) throws SQLException{

		ResultSetMetaData metaData = report.getMetaData();
		int count = metaData.getColumnCount(); //number of column
		String columnName[] = new String[count];

		for (int i = 1; i <= count; i++) {
				columnName[i-1] = metaData.getColumnLabel(i); 
		}
		
		String htmlResult = "<table>"
						  + "<tr>";
		for(int j = 0; j < columnName.length; j++){
			htmlResult +="<th>" + columnName[j] + "</th>";
		}
		
		
		
		while(report.next()){
			htmlResult +="<tr>";
			
			for(int z = 1; z <= metaData.getColumnCount(); z++){
				htmlResult +="<td>" + report.getObject(z) + "</td>";
			}
			htmlResult +="</tr>";
		}
		
		htmlResult += "</table>";
		return htmlResult;
	}
	
	private static String prodottiInEsaurimento;
	private static String prodottiPerFasciaPrezzo;
	
	static {
		prodottiInEsaurimento = "SELECT idScarpe, marca, modello, quantitaDisp, scorta_minima "
							  + "FROM scarpe "
							  + "WHERE quantitaDisp < scorta_minima";
		
		prodottiPerFasciaPrezzo = "SELECT idScarpe, marca, modello, quantitaDisp, prezzo_vendita "
								+ "FROM scarpe "
								+ "WHERE prezzo_vendita BETWEEN ? AND ?";
	}

}
