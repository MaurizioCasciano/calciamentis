package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

import catalog.Item;
import utilities.user.User;

public class Database {

	/**
	 * Attempts to establish a connection to the database. If a connection
	 * already exists, this method has no effects.
	 * 
	 * @author Maurizio
	 */
	public static void openConnection() {
		if (!isConnectionOpen()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(mySqlUrl, userInfo);
				System.out.println("Connection: " + connection);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if the connection with the database is open or not.
	 * 
	 * @return {@code true} if the connection is open, {@code false} otherwise.
	 * @author Maurizio
	 */
	public static boolean isConnectionOpen() {
		boolean isOpen = false;
		try {
			if (connection != null && !connection.isClosed()) {
				isOpen = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isOpen;
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
		if (isConnectionOpen()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ResultSet executeQuery(String query) throws SQLException {
		openConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		if (DEBUG) {
			DBTablePrinter.printResultSet(resultSet);
		}

		return resultSet;
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
		openConnection();
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
		openConnection();
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
		openConnection();
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

	public static ArrayList<Item> getItems() {
		openConnection();

		String query = "SELECT * FROM scarpe;";
		ResultSet resultSet;
		ArrayList<Item> productsList = new ArrayList<>();
		try {
			resultSet = Database.executeQuery(query);
			//System.out.println("result set" + resultSet);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String marca = resultSet.getString(2);
				String modello = resultSet.getString(3);
				int prezzo_vendita = resultSet.getInt(4);
				int prezzo_acquisto = resultSet.getInt(5);
				int quantitaDisp = resultSet.getInt(6);
				int scorta_minima = resultSet.getInt(7);
				ArrayList<String> images = new ArrayList<>();
				for (int i = 8; i <= 13; i++) {
					images.add(resultSet.getString(i));
				}
				String alt = resultSet.getString(14);
				String descrizione = resultSet.getString(15);
				ArrayList<String> dettagli = new ArrayList<>();

				for (int i = 16; i <= 20; i++) {
					dettagli.add(resultSet.getString(i));
				}

				Item currentItem = new Item(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp,
						scorta_minima, images, alt, descrizione, dettagli);

				//System.out.println("Current Item: " + currentItem);
				productsList.add(currentItem);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productsList;
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
