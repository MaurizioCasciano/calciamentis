package paydesk;

import catalog.Item;

public class purchasedItem {
	
	public purchasedItem(Item purcObj, int quantit�, int prezzo) {
		this.purcObj = purcObj;
		this.quantit� = quantit�;
		this.prezzo = prezzo;
	}
	
	public Item getPurcObj() {
		return purcObj;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public int getPrezzoTotal() {
		return prezzo*quantit�;
	}

	public int getPrezzo() {
		return prezzo;
	}

	Item purcObj;
	int quantit�;
	int prezzo;
}
