package paydesk;

import java.util.ArrayList;
import catalog.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Database;

public class purchasedCart {
	public purchasedCart(int idAcquisti) {
		String query = "SELECT * FROM dettagli_acquisti WHERE idAcquisti=?;";
		PreparedStatement psAq = Database.getPreparedStatement(query);
		try {
			psAq.setInt(1, idAcquisti);
			ResultSet rs = psAq.executeQuery();
			while (rs.next()) {
				int itemId = rs.getInt("idScarpe");
				Item purcObj = Database.getItem(itemId);
				int quantita = rs.getInt("quantita");
				int prezzo = rs.getInt("prezzo");
				purchasedItem e = new purchasedItem(purcObj, quantita, prezzo);
				finalCart.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<purchasedItem> getAllPurchasedItem() {
		return finalCart;
	}

	public int getTotaleCart() {
		int total = 0;
		for (purchasedItem pc : finalCart) {
			total = total + pc.getPrezzoTotal();
		}
		return total;
	}

	private ArrayList<purchasedItem> finalCart = new ArrayList<>();
}
