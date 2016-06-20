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

	/**
	 * Returns the main image of this ItemOrder.
	 * 
	 * @return the main image of this ItemOrder.
	 */
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

	/**
	 * Tries to set the amount of this item to the given value.
	 * 
	 * @param numberOfItems
	 *            The new amount of this item.
	 * @return {@code true} if the amount of item has been updated,
	 *         {@code false} otherwise.
	 */
	public boolean setNumberOfItems(int numberOfItems) {
		System.out.println("SettingNumberOfItems\tNumberOfItems: " + numberOfItems);
		boolean added = false;

		// disponibilita' database - richiesta già nel carrello
		boolean available = this.getItem().getQuantitaDisp() >= numberOfItems;
		System.out.println("getQuantitaDisp: " + this.getItem().getQuantitaDisp());
		System.out.println("numberOfItems: " + numberOfItems);
		System.out.println("available: " + available);

		if (available) {
			System.out.println(this + " IS AVAILABLE");
			this.numberOfItems = numberOfItems;
			added = true;
		}

		return added;
	}

	/**
	 * Tries to increase the number of this item by one unit.
	 * 
	 * @return {@code true} if the amount of item has been updated,
	 *         {@code false} otherwise.
	 */
	public boolean increaseNumberOfItems() {
		return this.setNumberOfItems(this.getNumberOfItems() + 1);
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
