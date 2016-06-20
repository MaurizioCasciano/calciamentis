package paydesk;

import catalog.Item;

public class PurchasedItem {
	
	public PurchasedItem(Item catalogItem, int quantita, int prezzo) {
		this.catalogItem = catalogItem;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	
	public Item getCatalogItem() {
		return catalogItem;
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

	Item catalogItem;
	int quantita;
	int prezzo;
}
