package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Properties;

import utilities.user.User;

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
			/*
			 * try { connection.close(); } catch (SQLException e) {
			 * e.printStackTrace(); }
			 */
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

	/**
	 * Make a preparedStatement for the current connection
	 * @param statement
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String statement){
		PreparedStatement preparedStatement = null;
		
		if(statement != null && !statement.equals("")){
			try {
				preparedStatement = connection.prepareStatement(statement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return preparedStatement;
	}
	
	/**
	 * Checks if the given username is available or not.
	 * 
	 * @param username
	 *            The username to check if is available.
	 * @return {@code true} if the given username is available, {@code false}
	 *         otherwise.
	 */
	public static boolean isAvailableUsername(String username) {
		int count = 1;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT COUNT(*) FROM utenti WHERE username = ?;");
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			DBTablePrinter.printResultSet(resultSet);

			if (resultSet.next()) {
				count = resultSet.getInt(1);
				System.out.println("ResultSet count(*): " + count);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count == 0; // NO USER WITH THE GIVEN USERNAME IN DB
	}

	public static User getUser(String insertUsername) {
		System.out.println(connection);
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM utenti WHERE utenti.username='" + insertUsername + "';";

			ResultSet rs = statement.executeQuery(query);

			if (rs.next()) {
				// DATI ANAGRAFICI
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				Date dataDiNascita = rs.getDate("dataDiNascita");
				GregorianCalendar birthday = new GregorianCalendar();
				birthday.setTime(dataDiNascita);
				String codiceFiscale = rs.getString("codicefiscale");
				// DATI DI ACCESSO
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");

				// INDIRIZZO DI RESIDENZA
				String viaResidenza = rs.getString("viaResidenza");
				String provinciaResidenza = rs.getString("provinciaResidenza");
				String cittaResidenza = rs.getString("cittaResidenza");
				int codiceAvviamentoPostaleResidenza = rs.getInt("codiceAvviamentoPostaleResidenza");
				int numeroCivicoResidenza = rs.getInt("numeroCivicoResidenza");

				// INDIRIZZO DI SPEDIZIONE
				String viaSpedizione = rs.getString("viaSpedizione");
				String provinciaSpedizione = rs.getString("provinciaSpedizione");
				String cittaSpedizione = rs.getString("cittaSpedizione");
				int codiceAvviamentoPostaleSpedizione = rs.getInt("codiceAvviamentoPostaleSpedizione");
				int numeroCivicoSpedizione = rs.getInt("numeroCivicoSpedizione");

				User us = new User(nome, cognome, birthday, codiceFiscale, email, username, password, viaResidenza,
						provinciaResidenza, cittaResidenza, codiceAvviamentoPostaleResidenza, numeroCivicoResidenza,
						viaSpedizione, provinciaSpedizione, cittaSpedizione, codiceAvviamentoPostaleSpedizione,
						numeroCivicoSpedizione);
				return us;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static boolean addUser(User user) {
		boolean result = false;

		if (isAvailableUsername(user.getUsername())) {

			try {
				/*
				 * INSERT INTO `lisca`.`utenti` (`nome`, `cognome`,
				 * `dataDiNascita`, `codiceFiscale`, `email`, `username`,
				 * `password`, `viaResidenza`, `provinciaResidenza`,
				 * `cittaResidenza`, `codiceAvviamentoPostaleResidenza`,
				 * `numeroCivicoResidenza`, `viaSpedizione`,
				 * `provinciaSpedizione`, `cittaSpedizione`,
				 * `codiceAvviamentoPostaleSpedizione`,
				 * `numeroCivicoSpedizione`)
				 */

				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO utenti VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setString(1, user.getNome());
				preparedStatement.setString(2, user.getCognome());
				preparedStatement.setDate(3, new Date(user.getDataDiNascita().getTimeInMillis()));
				preparedStatement.setString(4, user.getCodiceFiscale());
				preparedStatement.setString(5, user.getEmail());
				preparedStatement.setString(6, user.getUsername());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setString(8, user.getViaResidenza());
				preparedStatement.setString(9, user.getProvinciaResidenza());
				preparedStatement.setString(10, user.getCittaResidenza());
				preparedStatement.setInt(11, user.getCodiceAvviamentoPostaleResidenza());
				preparedStatement.setInt(12, user.getNumeroCivicoResidenza());
				preparedStatement.setString(13, user.getViaSpedizione());
				preparedStatement.setString(14, user.getProvinciaSpedizione());
				preparedStatement.setString(15, user.getCittaSpedizione());
				preparedStatement.setInt(16, user.getCodiceAvviamentoPostaleSpedizione());
				preparedStatement.setInt(17, user.getNumeroCivicoSpedizione());

				preparedStatement.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
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
	private static final boolean DEBUG = false;

	static {
		protocol = "jdbc:mysql://";
		hostname = "db4free.net:";
		port = "3306/";
		dbName = "lisca";
		mySqlUrl = protocol + hostname + port + dbName;

		/**********************************/
		username = "oromis95";
		password = "programmazioneweb2016";
		userInfo = new Properties();
		userInfo.put("user", username);
		userInfo.put("password", password);
	}

	public static void main(String[] args) throws SQLException {
		openConnection();
		// executeQuery("select nomeProvincia from province order by
		// nomeProvincia;");

		String province = "Ascoli Piceno";
		String bugQuery = "SELECT c.comune FROM comuni c JOIN province p ON c.provincia = p.siglaprovincia WHERE p.nomeProvincia = '"
				+ province + "' ORDER BY c.comune;";

		executeQuery(bugQuery);
		closeConnection();
	}
}
