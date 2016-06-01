package cart;

import java.util.ArrayList;

import catalog.Detail;
import catalog.Item;

/**
 * Associates a catalog Item with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the CatalogItem data without extracting the CatalogItem separately.
 */
public class ItemOrder {

	public ItemOrder(Item item) {
		this.item = item;
		this.setNumberOfItems(1);
	}

	public Item getItem() {
		return item;
	}

	public String getMainImage() {
		return getItem().getImages().get(0);
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItemID() {
		return getItem().getId();
	}

	public String getShortDescription() {
		return getItem().getDescrizione();
	}

	public ArrayList<Detail> getDetails() {
		return getItem().getDettagli();
	}

	public double getUnitCost() {
		return getItem().getPrezzo_vendita();
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public void incrementNumberOfItems() {
		this.setNumberOfItems(this.getNumberOfItems() + 1);
	}

	public void cancelOrder() {
		this.setNumberOfItems(0);
	}

	public double getTotalCost() {
		return this.getNumberOfItems() * this.getUnitCost();
	}

	@Override
	public String toString() {
		return "ItemOrder [item=" + item + ", numberOfItems=" + numberOfItems + "]";
	}

	private Item item;
	private int numberOfItems;
}
