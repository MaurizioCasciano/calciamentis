package cart;

import java.util.ArrayList;

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
		
		ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID));
		itemsOrdered.add(newOrder);
	}

	private ArrayList<ItemOrder> itemsOrdered;
}
