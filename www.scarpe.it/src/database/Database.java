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
import catalog.Detail;
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

	public static boolean closeConnection() {
		boolean isClosed = false;

		if (isConnectionOpen()) {
			try {
				connection.close();
				isClosed = connection.isClosed();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isClosed;
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
	 * Make a preparedStatement for the current connection
	 * 
	 * @param statement
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String statement) {
		openConnection();
		PreparedStatement preparedStatement = null;

		if (statement != null && !statement.equals("")) {
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
		openConnection();
		int count = 1;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT COUNT(*) FROM utenti WHERE username = ?;");
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			// DBTablePrinter.printResultSet(resultSet);

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
				String codiceAvviamentoPostaleResidenza = rs.getString("codiceAvviamentoPostaleResidenza");
				String numeroCivicoResidenza = rs.getString("numeroCivicoResidenza");

				// INDIRIZZO DI SPEDIZIONE
				String viaSpedizione = rs.getString("viaSpedizione");
				String provinciaSpedizione = rs.getString("provinciaSpedizione");
				String cittaSpedizione = rs.getString("cittaSpedizione");
				String codiceAvviamentoPostaleSpedizione = rs.getString("codiceAvviamentoPostaleSpedizione");
				String numeroCivicoSpedizione = rs.getString("numeroCivicoSpedizione");

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
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSurname());
				preparedStatement.setDate(3, new Date(user.getDataDiNascita().getTimeInMillis()));
				preparedStatement.setString(4, user.getCodiceFiscale());
				preparedStatement.setString(5, user.getEmail());
				preparedStatement.setString(6, user.getUsername());
				preparedStatement.setString(7, user.getPassword());
				preparedStatement.setString(8, user.getHomeStreet());
				preparedStatement.setString(9, user.getHomeProvince());
				preparedStatement.setString(10, user.getHomeCity());
				preparedStatement.setString(11, user.getHomeCap());
				preparedStatement.setString(12, user.getHomeStreetNumber());
				preparedStatement.setString(13, user.getShippingStreet());
				preparedStatement.setString(14, user.getShippingProvince());
				preparedStatement.setString(15, user.getShippingCity());
				preparedStatement.setString(16, user.getShippingCap());
				preparedStatement.setString(17, user.getShippingStreetNumber());

				preparedStatement.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Item getItem(int itemId) {
		openConnection();
		Item requiredItem = null;

		try {
			PreparedStatement selectScarpaStatement = connection
					.prepareStatement("SELECT * FROM scarpe WHERE idScarpe = ?;");
			PreparedStatement selectScarpaImmaginiStatement = connection
					.prepareStatement("SELECT * FROM immagini WHERE scarpa = ?;");
			PreparedStatement selectScarpaDettagliStatement = connection
					.prepareStatement("SELECT * FROM dettagli WHERE scarpa = ?;");

			selectScarpaStatement.setInt(1, itemId);
			selectScarpaImmaginiStatement.setInt(1, itemId);
			selectScarpaDettagliStatement.setInt(1, itemId);

			ResultSet scarpaResultSet = selectScarpaStatement.executeQuery();
			ResultSet immaginiResultSet = selectScarpaImmaginiStatement.executeQuery();
			ResultSet dettagliResultSet = selectScarpaDettagliStatement.executeQuery();

			// DBTablePrinter.printResultSet(scarpaResultSet);
			// DBTablePrinter.printResultSet(immaginiResultSet);
			// DBTablePrinter.printResultSet(dettagliResultSet);

			if (scarpaResultSet.next()) {
				int id = scarpaResultSet.getInt("idScarpe");
				String marca = scarpaResultSet.getString("marca");
				String modello = scarpaResultSet.getString("modello");
				int prezzo_vendita = scarpaResultSet.getInt("prezzo_vendita");
				int prezzo_acquisto = scarpaResultSet.getInt("prezzo_acquisto");
				int quantitaDisp = scarpaResultSet.getInt("quantitaDisp");
				int scorta_minima = scarpaResultSet.getInt("scorta_minima");
				String alt = scarpaResultSet.getString("alt");
				String descrizione = scarpaResultSet.getString("descrizione");
				/**********************************************/

				ArrayList<String> images = new ArrayList<>();
				immaginiResultSet = Database.executeQuery("SELECT * FROM immagini WHERE scarpa = " + id + ";");

				while (immaginiResultSet.next()) {
					images.add(immaginiResultSet.getString("url"));
				}

				/***********************************************/
				ArrayList<Detail> dettagli = new ArrayList<>();
				dettagliResultSet = Database.executeQuery("SELECT * FROM dettagli WHERE scarpa = " + id + ";");

				while (dettagliResultSet.next()) {
					String currentIntestazione = dettagliResultSet.getString("intestazione");
					String currentCorpo = dettagliResultSet.getString("corpo");

					Detail currentDetail = new Detail(currentIntestazione, currentCorpo);

					dettagli.add(currentDetail);
				}

				requiredItem = new Item(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp,
						scorta_minima, images, alt, descrizione, dettagli);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requiredItem;
	}

	public static int insertItem(Item newItem) {
		
		boolean checkDetail = false, checkImage = false,checkItem=false;
		int id = 0;
		// Adding scarpa to database
		String queryItem = "INSERT INTO scarpe (marca,modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione) VALUES (?,?,?,?,?,?,?,?);";
		PreparedStatement psItem = Database.getPreparedStatement(queryItem);
		
		try {
			psItem.setString(1, newItem.getMarca());
			psItem.setString(2, newItem.getModello());
			psItem.setInt(3,newItem.getPrezzo_vendita());
			psItem.setInt(4, newItem.getPrezzo_acquisto());
			psItem.setInt(5,newItem.getQuantitaDisp());
			psItem.setInt(6,newItem.getScorta_minima());
			psItem.setString(7, newItem.getAlt());
			psItem.setString(8,newItem.getDescrizione());
			System.out.println("ps "+psItem);
			checkItem=psItem.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query = "SELECT scarpe.idScarpe from scarpe WHERE scarpe.modello=?;";
		PreparedStatement psID = Database.getPreparedStatement(query);
		try {
			psID.setString(1, newItem.getModello());
			System.out.println("ps id "+psID);
			ResultSet rs = psID.executeQuery();
			while (rs.next()) {
				System.out.println("Il result set contiene qualcosa");
				id = rs.getInt("idScarpe");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("l'id generato è " + id);
		
		
		
		//adding details to database
		String queryDetails = "INSERT INTO dettagli (scarpa,intestazione,corpo) VALUES (?, ?, ?)";
		PreparedStatement psDetails = Database.getPreparedStatement(queryDetails);
		ArrayList<Detail> details = newItem.getDettagli();
		for (int i = 0; i < details.size(); i++) {
			try {
				psDetails.setInt(1, id);
				psDetails.setString(2, details.get(i).getIntestazione());
				psDetails.setString(3, details.get(i).getCorpo());
				System.out.println("ps "+psDetails);
				checkDetail = psDetails.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//adding images to database
		String queryImages = "INSERT INTO immagini (scarpa,url) VALUES (?, ?)";
		PreparedStatement psImage = Database.getPreparedStatement(queryImages);
		ArrayList<String> images = newItem.getImages();
		System.out.println("la size dell'array di immagini è "+ images.size());
		for (int i = 0; i < images.size(); i++) {
			try {
				psImage.setInt(1, id);
				psImage.setString(2, images.get(i));
				System.out.println("ps image "+psImage);
				checkImage = psImage.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			return id;
		
	}

	public static ArrayList<Item> getItems() {
		openConnection();

		String query = "SELECT * FROM scarpe;";
		ResultSet scarpeResultSet, immaginiResultSet, dettagliResultSet;
		ArrayList<Item> productsList = new ArrayList<>();

		try {
			scarpeResultSet = Database.executeQuery(query);
			// System.out.println("result set" + resultSet);
			while (scarpeResultSet.next()) {
				int id = scarpeResultSet.getInt("idScarpe");
				String marca = scarpeResultSet.getString("marca");
				String modello = scarpeResultSet.getString("modello");
				int prezzo_vendita = scarpeResultSet.getInt("prezzo_vendita");
				int prezzo_acquisto = scarpeResultSet.getInt("prezzo_acquisto");
				int quantitaDisp = scarpeResultSet.getInt("quantitaDisp");
				int scorta_minima = scarpeResultSet.getInt("scorta_minima");
				String alt = scarpeResultSet.getString("alt");
				String descrizione = scarpeResultSet.getString("descrizione");
				/**********************************************/

				ArrayList<String> images = new ArrayList<>();
				immaginiResultSet = Database.executeQuery("SELECT * FROM immagini WHERE scarpa = " + id + ";");

				while (immaginiResultSet.next()) {
					images.add(immaginiResultSet.getString("url"));
				}

				/***********************************************/
				ArrayList<Detail> dettagli = new ArrayList<>();
				dettagliResultSet = Database.executeQuery("SELECT * FROM dettagli WHERE scarpa = " + id + ";");

				while (dettagliResultSet.next()) {
					String currentIntestazione = dettagliResultSet.getString("intestazione");
					String currentCorpo = dettagliResultSet.getString("corpo");

					Detail currentDetail = new Detail(currentIntestazione, currentCorpo);

					dettagli.add(currentDetail);
				}

				Item currentItem = new Item(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp,
						scorta_minima, images, alt, descrizione, dettagli);

				// System.out.println("Current Item: " + currentItem);
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
	private static final boolean LOCAL = false;

	static {
		protocol = "jdbc:mysql://";

		if (LOCAL) {
			hostname = "localhost:";
			port = "3306/";
			dbName = "lisca";
			mySqlUrl = protocol + hostname + port + dbName;
			username = "root";
			password = "root";
			userInfo = new Properties();
			userInfo.put("user", username);
			userInfo.put("password", password);

		} else {
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
	}

	public static void main(String[] args) throws SQLException {
		openConnection();
		// executeQuery("select nomeProvincia from province order by
		// nomeProvincia;");

		String province = "Ascoli Piceno";
		String bugQuery = "SELECT c.comune FROM comuni c JOIN province p ON c.provincia = p.siglaprovincia WHERE p.nomeProvincia = '"
				+ province + "' ORDER BY c.comune;";

		executeQuery(bugQuery);
	}
}
