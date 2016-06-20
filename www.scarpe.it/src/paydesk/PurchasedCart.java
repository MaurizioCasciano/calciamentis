package paydesk;

import java.util.ArrayList;
import catalog.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import database.Database;

public class PurchasedCart {
	public PurchasedCart(int cartId, Timestamp date) {
		this.cartId = cartId;
		this.date = date;
		this.purchasedItems = new ArrayList<PurchasedItem>();

		String query = "SELECT * FROM dettagli_acquisti WHERE idAcquisti=?;";
		PreparedStatement psAq = Database.getPreparedStatement(query);
		try {
			psAq.setInt(1, this.cartId);
			ResultSet resultSet = psAq.executeQuery();

			while (resultSet.next()) {
				int itemId = resultSet.getInt("idScarpe");
				Item item = Database.getItem(itemId);
				int quantita = resultSet.getInt("quantita");
				int prezzo = resultSet.getInt("prezzo");
				PurchasedItem purchasedItem = new PurchasedItem(item, quantita, prezzo);
				this.purchasedItems.add(purchasedItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PurchasedCart(int cartId, Timestamp date, ArrayList<PurchasedItem> purchasedItems) {
		this.cartId = cartId;
		this.date = date;
		this.purchasedItems = purchasedItems;
	}

	public ArrayList<PurchasedItem> getAllPurchasedItem() {
		return purchasedItems;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public ArrayList<PurchasedItem> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(ArrayList<PurchasedItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	public double getTotale() {
		double total = 0;
		for (PurchasedItem purchasedItem : purchasedItems) {
			total = total + purchasedItem.getPrezzoTotal();
		}
		return total;
	}

	private int cartId;
	private Timestamp date;
	private ArrayList<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
}
