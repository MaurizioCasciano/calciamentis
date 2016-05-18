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
	 */
	public static void openConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(mySqlUrl, userInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Releases this Connection object's database and JDBC resources immediately
	 * instead of waiting for them to be automatically released.
	 * 
	 * Calling the method close on a Connection object that is already closed is
	 * a no-operation.
	 * 
	 */
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ResultSet executeQuery(String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		if (DEBUG) {
			DBTablePrinter.printResultSet(resultSet);
		}

		return resultSet;
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
	private static final boolean DEBUG = true;

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
		//executeQuery("select nomeProvincia from province order by nomeProvincia;");
		
		String province = "Ascoli pIceno";
		String bugQuery = "SELECT c.comune FROM comuni c JOIN province p ON c.provincia = p.siglaprovincia WHERE p.nomeProvincia = '"
				+ province + "' ORDER BY c.comune;";
		
		executeQuery(bugQuery);
		closeConnection();
	}
}
