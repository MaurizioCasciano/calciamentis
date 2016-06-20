package administration.customers;

import java.util.ArrayList;

import paydesk.PurchasedCart;
import paydesk.PurchasedItem;

public class TestCustomer {
	public static void main(String[] args) {
		ArrayList<PurchasedCart> ps = ViewCustomers.getAcquistiCliente("oromis95");
		for(PurchasedCart p: ps){
			System.out.println(p.getCartId() + " " + p.getDate() + " " + p.getTotale());
			for(PurchasedItem pi: p.getAllPurchasedItem()){
				System.out.println(pi.getCatalogItem().getAlt() + " " + pi.getQuantita());
			}
		}
		
	}

}
