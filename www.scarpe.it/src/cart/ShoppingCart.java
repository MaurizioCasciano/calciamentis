package cart;

import java.util.ArrayList;

import catalog.Item;
import database.Database;

public class ShoppingCart {

	public ShoppingCart() {
		this.itemsOrdered = new ArrayList<>();
	}

	public ArrayList<ItemOrder> getItemsOrdered() {
		return itemsOrdered;
	}

	public synchronized void addItem(int itemID) {
		ItemOrder order;

		for (int i = 0; i < itemsOrdered.size(); i++) {
			order = (ItemOrder) itemsOrdered.get(i);

			if (order.getItemID() == itemID) {
				order.incrementNumberOfItems();
				return;
			}
		}

		ItemOrder newOrder = new ItemOrder(Database.getItemById(itemID));
		itemsOrdered.add(newOrder);
	}

	public synchronized void setNumberOfItems(int itemID, int numberOfItems) {
		ItemOrder order;

		for (int i = 0; i < this.itemsOrdered.size(); i++) {
			order = itemsOrdered.get(i);
			if (order.getItemID() == itemID) {
				if (numberOfItems <= 0) {
					this.itemsOrdered.remove(i);
				} else {
					order.setNumberOfItems(numberOfItems);
				}
				return;
			}
		}

		ItemOrder newOrder = new ItemOrder(Database.getItemById(itemID));
		if (newOrder != null) {
			this.itemsOrdered.add(newOrder);
		}
	}

	public double getTotal() {
		double total = 0;
		for (int i = 0; i < this.itemsOrdered.size(); i++) {
			ItemOrder itemOrder = this.itemsOrdered.get(i);
			Item item = itemOrder.getItem();
			total += item.getPrezzo_vendita();
		}

		return total;
	}

	@Override
	public String toString() {
		return "ShoppingCart [itemsOrdered=" + itemsOrdered + "total=" + getTotal() + "]";
	}

	private ArrayList<ItemOrder> itemsOrdered;
}
