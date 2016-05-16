package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

	/**
	 * Attempts to establish a connection to the database.
	 * 
	 * @throws SQLException
	 */
	public static void openConnection() throws SQLException {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(mySqlUrl, userInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Statement statement = connection.createStatement();

		ResultSet result = statement.executeQuery("SHOW TABLES;");

		DBTablePrinter.printResultSet(result);
	}

	private static String protocol;
	private static String hostname;
	private static String port;
	private static String username;
	private static String password;
	private static String dbName;
	private static Properties userInfo;
	private static Connection connection;
	private static String mySqlUrl;

	static {
		protocol = "jdbc:mysql://";
		hostname = "localhost:";
		port = "3306/";
		dbName = "ecommerce";
		mySqlUrl = protocol + hostname + port + dbName;

		/**********************************/
		username = "root";
		password = "root";
		userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
	}

	public static void main(String[] args) throws SQLException {
		openConnection();
	}
}
