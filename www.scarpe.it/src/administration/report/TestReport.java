package administration.report;

import java.sql.SQLException;

public class TestReport {

	public static void main(String[] args) throws SQLException {
		//System.out.println(Report.makeReport(Report.getResultSetProdottiPerFasciaPrezzo(120, 200)));
		System.out.println(Report.makeReport(Report.getProdottiInEsaurimento()));
	}
}
