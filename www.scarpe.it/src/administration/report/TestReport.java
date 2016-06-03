package administration.report;

import java.sql.SQLException;

public class TestReport {

	public static void main(String[] args) throws SQLException {
		System.out.println(Report.makeReport(Report.getProdottiPerFasciaPrezzo(120, 200)));
	}
}
