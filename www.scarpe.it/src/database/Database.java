package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import administration.product.EditableItemBean;
import catalog.Detail;
import catalog.Item;
import paydesk.PurchasedCart;
import paydesk.PurchasedItem;
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

	public static void toggleItem(int id) {
		String queryCheck = "SELECT quantitaDisp, scorta_minima FROM scarpe WHERE idScarpe=?;";
		PreparedStatement psCheck = Database.getPreparedStatement(queryCheck);
		int quantitaDisp = 0, scorta_minima = 0;

		String queryToggle = "UPDATE scarpe SET quantitaDisp=? WHERE idScarpe=?;";
		PreparedStatement psToggle = Database.getPreparedStatement(queryToggle);
		try {
			psCheck.setInt(1, id);
			ResultSet rs = psCheck.executeQuery();
			while (rs.next()) {
				quantitaDisp = rs.getInt("quantitaDisp");
				scorta_minima = rs.getInt("scorta_minima");
			}
			if (quantitaDisp == 0) {
				psToggle.setInt(1, scorta_minima);
				psToggle.setInt(2, id);
			} else {
				psToggle.setInt(1, 0);
				psToggle.setInt(2, id);
			}
			psToggle.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static User getUser(String insertUsername) {
		openConnection();
		System.out.println(connection);
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM utenti WHERE utenti.username='" + insertUsername + "';";

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				User user = new User();

				user.setName(rs.getString("nome"));
				user.setSurname(rs.getString("cognome"));
				user.setBirthday(rs.getString("dataDiNascita"));
				user.setCodiceFiscale(rs.getString("codicefiscale"));

				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRepassword(rs.getString("password"));

				user.setHomeStreet(rs.getString("viaResidenza"));
				user.setHomeProvince(rs.getString("provinciaResidenza"));
				user.setHomeCity(rs.getString("cittaResidenza"));
				user.setHomeCap(rs.getString("codiceAvviamentoPostaleResidenza"));
				user.setHomeStreetNumber(rs.getString("numeroCivicoResidenza"));

				user.setShippingStreet(rs.getString("viaSpedizione"));
				user.setShippingProvince(rs.getString("provinciaSpedizione"));
				user.setShippingCity(rs.getString("cittaSpedizione"));
				user.setShippingCap(rs.getString("codiceAvviamentoPostaleSpedizione"));
				user.setShippingStreetNumber(rs.getString("numeroCivicoSpedizione"));

				return user;
			}
		} catch (SQLException e) {
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

				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO utenti (`nome`, `cognome`,`dataDiNascita`, `codiceFiscale`, `email`, `username`,`password`, `viaResidenza`, `provinciaResidenza`,`cittaResidenza`, `codiceAvviamentoPostaleResidenza`,`numeroCivicoResidenza`, `viaSpedizione`,`provinciaSpedizione`, `cittaSpedizione`,`codiceAvviamentoPostaleSpedizione`,`numeroCivicoSpedizione`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getSurname());
				preparedStatement.setString(3, user.getBirthday());
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

	public static EditableItemBean getEditableItem(int itemId) {
		openConnection();
		EditableItemBean requiredItem = null;

		try {
			PreparedStatement selectScarpaStatement = connection
					.prepareStatement("SELECT * FROM scarpe WHERE idScarpe = ?;");
			PreparedStatement selectScarpaDettagliStatement = connection
					.prepareStatement("SELECT * FROM dettagli WHERE scarpa = ?;");

			selectScarpaStatement.setInt(1, itemId);
			selectScarpaDettagliStatement.setInt(1, itemId);
			ResultSet scarpaResultSet = selectScarpaStatement.executeQuery();
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

				ArrayList<Detail> dettagli = new ArrayList<>();
				dettagliResultSet = Database.executeQuery("SELECT * FROM dettagli WHERE scarpa = " + id + ";");

				while (dettagliResultSet.next()) {
					String currentIntestazione = dettagliResultSet.getString("intestazione");
					String currentCorpo = dettagliResultSet.getString("corpo");

					Detail currentDetail = new Detail(currentIntestazione, currentCorpo);

					dettagli.add(currentDetail);
				}

				requiredItem = new EditableItemBean(id, marca, modello, prezzo_vendita, prezzo_acquisto, quantitaDisp,
						scorta_minima, alt, descrizione, dettagli);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requiredItem;
	}

	public static void EditItem(EditableItemBean myBean) {
		String queryUpdateItem = "UPDATE scarpe SET marca=? ,modello=?, prezzo_vendita=?, "
				+ "prezzo_acquisto= ?, quantitaDisp=?, scorta_minima=? ,alt=? ,descrizione=? WHERE idScarpe=?;";

		String queryGetIdDetail = "SELECT dettagli.id  from scarpe join dettagli "
				+ "on(scarpe.idScarpe=dettagli.scarpa) WHERE scarpe.idScarpe=?;";

		String queryUpdateDetail = "UPDATE dettagli SET intestazione=?, corpo=? WHERE id=?;";

		PreparedStatement psIdDetails = Database.getPreparedStatement(queryGetIdDetail);
		PreparedStatement psUpdateItem = Database.getPreparedStatement(queryUpdateItem);
		PreparedStatement psUpdateDetail;// =
											// Database.getPreparedStatement(queryUpdateDetail);
		ArrayList<Integer> idArray = new ArrayList<>();

		// get details id
		try {
			psIdDetails.setInt(1, myBean.getId());
			ResultSet rsIdDetails = psIdDetails.executeQuery();
			while (rsIdDetails.next()) {
				int id = rsIdDetails.getInt("id");
				idArray.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("size degli id " + idArray.size());
		// update item
		try {
			psUpdateItem.setString(1, myBean.getMarca());
			psUpdateItem.setString(2, myBean.getModello());
			psUpdateItem.setInt(3, myBean.getPrezzo_vendita());
			psUpdateItem.setInt(4, myBean.getPrezzo_acquisto());
			psUpdateItem.setInt(5, myBean.getQuantitaDisp());
			psUpdateItem.setInt(6, myBean.getScorta_minima());
			psUpdateItem.setString(7, myBean.getAlt());
			psUpdateItem.setString(8, myBean.getDescrizione());
			psUpdateItem.setInt(9, myBean.getId());
			psUpdateItem.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// update detail

		try {
			for (int i = 0; i < idArray.size(); i++) {
				psUpdateDetail = Database.getPreparedStatement(queryUpdateDetail);
				psUpdateDetail.setString(1, myBean.getDettagli().get(i).getIntestazione());
				psUpdateDetail.setString(2, myBean.getDettagli().get(i).getCorpo());
				psUpdateDetail.setInt(3, idArray.get(i));
				psUpdateDetail.executeUpdate();
				System.out.println("query item " + i + " contenuto " + psUpdateDetail);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int insertItem(Item newItem) {

		int id = 0;
		// Adding scarpa to database
		String queryItem = "INSERT INTO scarpe (marca,modello, prezzo_vendita, prezzo_acquisto, quantitaDisp, scorta_minima, alt, descrizione) VALUES (?,?,?,?,?,?,?,?);";
		PreparedStatement psItem = Database.getPreparedStatement(queryItem);

		try {
			psItem.setString(1, newItem.getMarca());
			psItem.setString(2, newItem.getModello());
			psItem.setInt(3, newItem.getPrezzo_vendita());
			psItem.setInt(4, newItem.getPrezzo_acquisto());
			psItem.setInt(5, newItem.getQuantitaDisp());
			psItem.setInt(6, newItem.getScorta_minima());
			psItem.setString(7, newItem.getAlt());
			psItem.setString(8, newItem.getDescrizione());
			System.out.println("ps " + psItem);
			psItem.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query = "SELECT scarpe.idScarpe from scarpe WHERE scarpe.modello=?;";
		PreparedStatement psID = Database.getPreparedStatement(query);
		try {
			psID.setString(1, newItem.getModello());
			System.out.println("ps id " + psID);
			ResultSet rs = psID.executeQuery();
			while (rs.next()) {
				System.out.println("Il result set contiene qualcosa");
				id = rs.getInt("idScarpe");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("l'id generato � " + id);

		// adding details to database
		String queryDetails = "INSERT INTO dettagli (scarpa,intestazione,corpo) VALUES (?, ?, ?)";
		PreparedStatement psDetails = Database.getPreparedStatement(queryDetails);
		ArrayList<Detail> details = newItem.getDettagli();
		for (int i = 0; i < details.size(); i++) {
			try {
				psDetails.setInt(1, id);
				psDetails.setString(2, details.get(i).getIntestazione());
				psDetails.setString(3, details.get(i).getCorpo());
				System.out.println("ps " + psDetails);
				psDetails.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// adding images to database
		String queryImages = "INSERT INTO immagini (scarpa,url) VALUES (?, ?)";
		PreparedStatement psImage = Database.getPreparedStatement(queryImages);
		ArrayList<String> images = newItem.getImages();
		System.out.println("la size dell'array di immagini � " + images.size());
		for (int i = 0; i < images.size(); i++) {
			try {
				psImage.setInt(1, id);
				psImage.setString(2, images.get(i));
				System.out.println("ps image " + psImage);
				psImage.execute();
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

	public static ArrayList<PurchasedCart> getPurchasedCartsOld(String username) {
		long start = System.currentTimeMillis();

		PreparedStatement selectAcquistiUsername = getPreparedStatement(
				"SELECT idAcquisti, data FROM acquisti WHERE username = ?;");
		PreparedStatement selectDettagliAcquisti = getPreparedStatement(
				"SELECT idScarpe, quantita, prezzo FROM dettagli_acquisti WHERE idAcquisti = ?;");

		try {
			selectAcquistiUsername.setString(1, username);
			ResultSet idAcquisti = selectAcquistiUsername.executeQuery();
			ArrayList<PurchasedCart> purchasedCarts = new ArrayList<>();

			while (idAcquisti.next()) {
				int currentIdAcquisto = idAcquisti.getInt("idAcquisti");
				// System.out.print("currentIdAcquisto: " + currentIdAcquisto);
				Timestamp data = idAcquisti.getTimestamp("data");
				// System.out.println("\tdata: " + data);

				selectDettagliAcquisti.setInt(1, currentIdAcquisto);
				ResultSet dettagliAcquisti = selectDettagliAcquisti.executeQuery();
				// DBTablePrinter.printResultSet(dettagliAcquisti);
				ArrayList<PurchasedItem> purchasedItems = new ArrayList<>();

				while (dettagliAcquisti.next()) {
					int idScarpe = dettagliAcquisti.getInt("idScarpe");
					Item item = Database.getItem(idScarpe);
					int quantita = dettagliAcquisti.getInt("quantita");
					int prezzo = dettagliAcquisti.getInt("prezzo");

					PurchasedItem purchasedItem = new PurchasedItem(item, quantita, prezzo);
					purchasedItems.add(purchasedItem);
				}

				PurchasedCart purchasedCart = new PurchasedCart(currentIdAcquisto, data, purchasedItems);
				purchasedCarts.add(purchasedCart);
			}

			long end = System.currentTimeMillis();
			System.out.println("getPurchasedCartsOld Millis: " + (end - start));
			return purchasedCarts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* TESTING to speed-up */
	public static ArrayList<PurchasedCart> getPurchasedCarts(String username) {
		long start = System.currentTimeMillis();

		PreparedStatement selectAcquistiUsername = getPreparedStatement(
				"SELECT idAcquisti, data FROM acquisti WHERE username = ?;");
		PreparedStatement selectDettagliAcquisti = getPreparedStatement(
				"SELECT idScarpe, quantita, prezzo FROM dettagli_acquisti WHERE idAcquisti = ?;");

		try {
			selectAcquistiUsername.setString(1, username);
			ResultSet idAcquisti = selectAcquistiUsername.executeQuery();
			ArrayList<PurchasedCart> purchasedCarts = new ArrayList<>();

			while (idAcquisti.next()) {
				// int currentIdAcquisto = idAcquisti.getInt("idAcquisti");
				// System.out.print("currentIdAcquisto: " + currentIdAcquisto);
				// Timestamp data = idAcquisti.getTimestamp("data");
				// System.out.println("\tdata: " + data);

				selectDettagliAcquisti.setInt(1, idAcquisti.getInt("idAcquisti"));
				ResultSet dettagliAcquisti = selectDettagliAcquisti.executeQuery();
				// DBTablePrinter.printResultSet(dettagliAcquisti);
				ArrayList<PurchasedItem> purchasedItems = new ArrayList<>();

				while (dettagliAcquisti.next()) {
					// int idScarpe = dettagliAcquisti.getInt("idScarpe");
					// Item item =
					// Database.getItem(dettagliAcquisti.getInt("idScarpe"));
					// int quantita = dettagliAcquisti.getInt("quantita");
					// int prezzo = dettagliAcquisti.getInt("prezzo");

					/*
					 * PurchasedItem purchasedItem = new PurchasedItem(
					 * Database.getItem(dettagliAcquisti.getInt("idScarpe")),
					 * dettagliAcquisti.getInt("quantita"),
					 * dettagliAcquisti.getInt("prezzo"));
					 */
					purchasedItems.add(new PurchasedItem(Database.getItem(dettagliAcquisti.getInt("idScarpe")),
							dettagliAcquisti.getInt("quantita"), dettagliAcquisti.getInt("prezzo")));
				}

				PurchasedCart purchasedCart = new PurchasedCart(idAcquisti.getInt("idAcquisti"),
						idAcquisti.getTimestamp("data"), purchasedItems);
				purchasedCarts.add(purchasedCart);
			}

			long end = System.currentTimeMillis();
			System.out.println("getPurchasedCarts Millis: " + (end - start));
			return purchasedCarts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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

		/*
		 * String province = "Ascoli Piceno"; String bugQuery =
		 * "SELECT c.comune FROM comuni c JOIN province p ON c.provincia = p.siglaprovincia WHERE p.nomeProvincia = '"
		 * + province + "' ORDER BY c.comune;";
		 */

		// executeQuery(bugQuery);

		for(int i = 0; i < 5; i++){
			getPurchasedCarts("oromis95");
			getPurchasedCartsOld("oromis95");
			System.out.println();
		}
	}
}
