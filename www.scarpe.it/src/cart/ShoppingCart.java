package cart;

import java.util.ArrayList;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import database.Database;

public class ShoppingCart {

	public ShoppingCart() {
		this.items = new ArrayList<>();
	}

	public ArrayList<ItemOrder> getItems() {
		return items;
	}

	public synchronized void addItem(int itemID) {
		ItemOrder order;

		for (int i = 0; i < items.size(); i++) {
			order = (ItemOrder) items.get(i);

			if (order.getItemID() == itemID) {
				order.incrementNumberOfItems();
				return;
			}
		}

		ItemOrder newOrder = new ItemOrder(Database.getItem(itemID));
		items.add(newOrder);
	}

	public synchronized void setNumberOfItems(int itemID, int numberOfItems) {
		ItemOrder order;

		for (int i = 0; i < this.items.size(); i++) {
			order = items.get(i);
			if (order.getItemID() == itemID) {
				if (numberOfItems <= 0) {
					this.items.remove(i);
					i--;
				} else {
					order.setNumberOfItems(numberOfItems);
				}
				return;
			}
		}

		ItemOrder newOrder = new ItemOrder(Database.getItem(itemID));
		if (newOrder != null) {
			this.items.add(newOrder);
		}
	}

	public ItemOrder getItem(int id) {
		for (ItemOrder i : items) {
			if (i.getItemID() == id) {
				return i;
			}
		}
		return null;
	}

	public double getTotale() {
		this.totale = 0;
		for (int i = 0; i < this.items.size(); i++) {
			ItemOrder itemOrder = this.items.get(i);
			this.totale += itemOrder.getTotalCost();
		}

		return this.totale;
	}

	@Override
	public String toString() {
		return "ShoppingCart [itemsOrdered=" + items + "total=" + getTotale() + "]";
	}

	public Document toXMLDocument() {
		DocType docType = new DocType("cart", "./WebContent/cart.dtd");

		// root element
		Element cartElement = new Element("cart");
		cartElement.setAttribute("time", System.currentTimeMillis() + "");
		Document document = new Document(cartElement);
		document.setDocType(docType);

		// child elements
		for (ItemOrder item : this.getItems()) {
			Element itemElement = new Element("item");
			itemElement.setAttribute("code", item.getItemID() + "");
			itemElement.setAttribute("price", item.getUnitCost() + "");
			itemElement.setAttribute("amount", item.getNumberOfItems() + "");
			itemElement.setAttribute("image", item.getMainImage());
			itemElement.setAttribute("rowTotal", item.getTotalCost()+"");
			document.getRootElement().addContent(itemElement);
		}

		Element totalElement = new Element("total");
		totalElement.setText(getTotale() + "");
		document.getRootElement().addContent(totalElement);

		return document;
	}

	private ArrayList<ItemOrder> items;
	private double totale;
}
