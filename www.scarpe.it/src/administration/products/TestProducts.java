package administration.products;

import java.util.ArrayList;

import administration.product.EditableItemBean;

public class TestProducts {
	public static void main(String[] args) {
		ArrayList<EditableItemBean> array;
		array = ViewProducts.getGoods(ViewProducts.getProdottiInEsaurimento());
		System.out.println(array);
		for(EditableItemBean e: array){
			System.out.println(e.getMarca() + " " + e.getModello() + " " + e.getPrezzo_vendita());
		}
	}

}
