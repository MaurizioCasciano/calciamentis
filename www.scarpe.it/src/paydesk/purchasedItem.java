package paydesk;

import catalog.Item;

public class purchasedItem {
	
	public purchasedItem(Item purcObj, int quantità, int prezzo) {
		this.purcObj = purcObj;
		this.quantità = quantità;
		this.prezzo = prezzo;
	}
	
	public Item getPurcObj() {
		return purcObj;
	}
	public int getQuantità() {
		return quantità;
	}
	public int getPrezzoTotal() {
		return prezzo*quantità;
	}

	public int getPrezzo() {
		return prezzo;
	}

	Item purcObj;
	int quantità;
	int prezzo;
}
