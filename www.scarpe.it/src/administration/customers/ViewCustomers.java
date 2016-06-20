package administration.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.Database;
import paydesk.PurchasedCart;
import utilities.user.User;

public class ViewCustomers {

	public static ResultSet getUtenti() {
		ResultSet result = null;

		try {
			result = Database.executeQuery(selectUtenti);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Indirizzo getIndirizzoResidenza(String username) {
		ResultSet result = null;
		Indirizzo indirizzo = null;
		PreparedStatement statement = Database.getPreparedStatement(selectIndirizzoResidenza);

		try {
			statement.setString(1, username);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result != null) {
			try {
				result.next();
				String via = result.getString("viaResidenza");
				String numeroCivico = result.getString("numeroCivicoResidenza");
				String cap = result.getString("codiceAvviamentoPostaleResidenza");
				String citta = result.getString("cittaResidenza");
				String provincia = result.getString("provinciaResidenza");

				indirizzo = new Indirizzo(via, numeroCivico, cap, citta, provincia);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return indirizzo;
	}

	public static Indirizzo getIndirizzoSpedizione(String username) {
		ResultSet result = null;
		Indirizzo indirizzo = null;

		PreparedStatement statement = Database.getPreparedStatement(selectIndirizzoSpedizione);
		try {
			statement.setString(1, username);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result != null) {
			try {
				result.next();
				String via = result.getString("viaSpedizione");
				String numeroCivico = result.getString("numeroCivicoSpedizione");
				String cap = result.getString("codiceAvviamentoPostaleSpedizione");
				String citta = result.getString("cittaSpedizione");
				String provincia = result.getString("provinciaSpedizione");

				indirizzo = new Indirizzo(via, numeroCivico, cap, citta, provincia);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return indirizzo;
	}

	public static ArrayList<User> getClienti(ResultSet utenti) {
		ArrayList<User> customer = null;
		int size = 0;
		String cognome, nome, username, password, codiceFiscale, birthday, email;
		Indirizzo indirizzoResidenza, indirizzoSpedizione;

		if (utenti != null) {
			customer = new ArrayList<User>();
			try {
				utenti.last();
				size = utenti.getRow();
				utenti.beforeFirst();

				for (int i = 1; i <= size; i++) {
					utenti.next();
					cognome = utenti.getString("cognome");
					nome = utenti.getString("nome");
					username = utenti.getString("username");
					password = utenti.getString("password");
					codiceFiscale = utenti.getString("codiceFiscale");
					birthday = utenti.getString("dataDiNascita");
					email = utenti.getString("email");
					indirizzoResidenza = getIndirizzoResidenza(username);
					indirizzoSpedizione = getIndirizzoSpedizione(username);
					customer.add(new User(nome, cognome, birthday, codiceFiscale, email, username, password,
							indirizzoResidenza, indirizzoSpedizione));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return customer;
	}

	public static ArrayList<PurchasedCart> getAcquistiCliente(String username) {
		ArrayList<PurchasedCart> acquisti = null;
		ResultSet result = null;
		ResultSet count = null;
		PreparedStatement statement = Database.getPreparedStatement(filtraAcquistiCliente);
		PreparedStatement countStatement = Database.getPreparedStatement(sizeAcquistiCliente);

		try {
			countStatement.setString(1, username);
			statement.setString(1, username);
			count = countStatement.executeQuery();
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int size = 0;

		if (count != null && result != null) {
			try {
				count.next();
				size = count.getInt(1);
				acquisti = new ArrayList<PurchasedCart>();

				for (int i = 1; i <= size; i++) {
					result.next();
					int id = result.getInt("idAcquisti");
					Timestamp date = result.getTimestamp("data");
					PurchasedCart pc = new PurchasedCart(id, date);
					acquisti.add(pc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return acquisti;
	}

	private static String selectUtenti;
	private static String selectIndirizzoResidenza;
	private static String selectIndirizzoSpedizione;
	private static String filtraAcquistiCliente;
	private static String sizeAcquistiCliente;

	static {
		selectUtenti = "SELECT cognome, nome, dataDiNascita, codiceFiscale, email, username, password " + "FROM utenti";
		selectIndirizzoResidenza = "SELECT viaResidenza, numeroCivicoResidenza, codiceAvviamentoPostaleResidenza, cittaResidenza, provinciaResidenza "
				+ "FROM utenti " + "WHERE username = ?";
		selectIndirizzoSpedizione = "SELECT viaSpedizione, numeroCivicoSpedizione, codiceAvviamentoPostaleSpedizione, cittaSpedizione, provinciaSpedizione "
				+ "FROM utenti " + "WHERE username = ?";
		filtraAcquistiCliente = "SELECT * FROM acquisti WHERE username = ?"; // idAcquisti,
																				// username,
																				// data
		sizeAcquistiCliente = "SELECT COUNT(*) FROM acquisti WHERE username = ?";
	}
}
