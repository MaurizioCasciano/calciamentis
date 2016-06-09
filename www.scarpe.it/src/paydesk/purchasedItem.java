package paydesk;

import catalog.Item;

public class purchasedItem {
	
	public purchasedItem(Item purcObj, int quantita, int prezzo) {
		this.purcObj = purcObj;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	
	public Item getPurcObj() {
		return purcObj;
	}
	public int getQuantita() {
		return quantita;
	}
	public int getPrezzoTotal() {
		return prezzo*quantita;
	}

	public int getPrezzo() {
		return prezzo;
	}

	Item purcObj;
	int quantita;
	int prezzo;
}
