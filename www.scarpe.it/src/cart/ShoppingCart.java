package cart;

import java.io.Serializable;
import java.util.ArrayList;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;

import catalog.Item;
import database.Database;

public class ShoppingCart implements Serializable {

	public ShoppingCart() {
		this.lastAdded = false;
		this.items = new ArrayList<>();
	}

	/**
	 * Return all the Items in this ShoppingCart.
	 * 
	 * @return all the Items in this ShoppingCart.
	 */
	public ArrayList<ItemOrder> getItems() {
		return items;
	}

	/**
	 * Adds to this ShoppingCart the item with the ID equal to the given itemID.
	 * If an Item with the given ID is already in this ShoppingCart this method
	 * simply
	 * 
	 * @param itemID
	 *            The ID of the Item to add to this ShoppingCart.
	 * 
	 */
	public synchronized boolean addItem(int itemID) {
		ItemOrder order;
		this.lastAdded = false;

		for (int i = 0; i < items.size(); i++) {
			order = (ItemOrder) items.get(i);

			// SE GIA' PRESENTE VIENE AUMENTATA LA QUANTITA'
			if (order.getItemID() == itemID) {
				this.lastAdded = order.increaseNumberOfItems();
				return this.lastAdded;
			}
		}

		Item item = Database.getItem(itemID);
		if (item != null && item.getQuantitaDisp() > 0) {
			ItemOrder newOrder = new ItemOrder(item);
			this.items.add(newOrder);
			this.lastAdded = true;
		}

		return this.lastAdded;
	}

	public synchronized void setNumberOfItems(int itemID, int numberOfItems) {
		ItemOrder order;

		for (int i = 0; i < this.items.size(); i++) {
			order = items.get(i);
			if (order.getItemID() == itemID) {
				if (numberOfItems <= 0) {
					this.items.remove(i);
					i--;// 'cause an item has been removed, and also the size,
						// so the index.
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

	/**
	 * Returns true if the last call to addItem or simila had success.
	 * 
	 * @return true if the last call to addItem or simila had success, false
	 *         otherwise.
	 */
	public boolean isLastAdded() {
		return lastAdded;
	}

	/**
	 * Returns the number of items in this {@link ShoppingCart}. NOTE: if a
	 * ShoppingCart contains the same item twice, this function returns 2.
	 * 
	 * @return the number of items in this {@link ShoppingCart}.
	 */
	public int getItemsCount() {
		int count = 0;

		for (ItemOrder item : this.items) {
			count += item.getNumberOfItems();
		}

		return count;
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
		DocType docType = new DocType("cart");

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
			itemElement.setAttribute("rowTotal", item.getTotalCost() + "");
			document.getRootElement().addContent(itemElement);
		}

		Element itemsCountElement = new Element("itemsCount");
		itemsCountElement.setText(getItemsCount() + "");
		document.getRootElement().addContent(itemsCountElement);

		Element lastAddedElement = new Element("lastAdded");
		lastAddedElement.setText(isLastAdded() + "");
		document.getRootElement().addContent(lastAddedElement);

		Element totalElement = new Element("total");
		totalElement.setText(getTotale() + "");
		document.getRootElement().addContent(totalElement);

		return document;
	}

	private ArrayList<ItemOrder> items;
	private double totale;
	/**
	 * Added mantiene l'esito dell'ultima volta che è stato chiamato un metodo
	 * di aggiunta (incremento) di un prodotto.
	 */
	private boolean lastAdded = false;
	private static final long serialVersionUID = 3254202155421708764L;
}
