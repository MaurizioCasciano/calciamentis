package cart;

import java.util.ArrayList;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
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

		ItemOrder newOrder = new ItemOrder(Database.getItem(itemID));
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

		ItemOrder newOrder = new ItemOrder(Database.getItem(itemID));
		if (newOrder != null) {
			this.itemsOrdered.add(newOrder);
		}
	}

	public double getTotale() {
		this.totale = 0;
		for (int i = 0; i < this.itemsOrdered.size(); i++) {
			ItemOrder itemOrder = this.itemsOrdered.get(i);
			this.totale += itemOrder.getTotalCost();
		}

		return this.totale;
	}
	@Override
	public String toString() {
		return "ShoppingCart [itemsOrdered=" + itemsOrdered + "total=" + getTotale() + "]";
	}

	public Document toXMLDocument() {
		DocType docType = new DocType("cart");

		// root element
		Element cartElement = new Element("cart");
		cartElement.setAttribute("time", System.currentTimeMillis() + "");
		Document document = new Document(cartElement);
		document.setDocType(docType);

		// child elements
		for (ItemOrder item : this.getItemsOrdered()) {
			Element itemElement = new Element("item");
			itemElement.setAttribute("code", item.getItemID() + "");
			itemElement.setAttribute("price", item.getUnitCost() + "");
			itemElement.setAttribute("amount", item.getNumberOfItems() + "");
			itemElement.setAttribute("image", item.getMainImage());
			document.getRootElement().addContent(itemElement);
		}

		Element totalElement = new Element("total");
		totalElement.setText(getTotale() + "");
		document.getRootElement().addContent(totalElement);

		return document;
	}

	private ArrayList<ItemOrder> itemsOrdered;
	private double totale;
}
