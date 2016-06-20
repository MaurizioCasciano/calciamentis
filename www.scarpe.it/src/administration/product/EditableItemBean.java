package administration.product;

import java.util.ArrayList;

import catalog.Detail;

public class EditableItemBean {
	public EditableItemBean(){}
	public EditableItemBean(int id, String marca, String modello, int prezzo_vendita, int prezzo_acquisto,
			int quantitaDisp, int scorta_minima, String alt, String descrizione, ArrayList<Detail> dettagli) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.prezzo_vendita = prezzo_vendita;
		this.prezzo_acquisto = prezzo_acquisto;
		this.quantitaDisp = quantitaDisp;
		this.scorta_minima = scorta_minima;
		this.alt = alt;
		this.descrizione = descrizione;
		this.dettagli = dettagli;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getPrezzo_vendita() {
		return prezzo_vendita;
	}

	public void setPrezzo_vendita(int prezzo_vendita) {
		this.prezzo_vendita = prezzo_vendita;
	}

	public int getPrezzo_acquisto() {
		return prezzo_acquisto;
	}

	public void setPrezzo_acquisto(int prezzo_acquisto) {
		this.prezzo_acquisto = prezzo_acquisto;
	}

	public int getQuantitaDisp() {
		return quantitaDisp;
	}

	public void setQuantitaDisp(int quantitaDisp) {
		this.quantitaDisp = quantitaDisp;
	}

	public int getScorta_minima() {
		return scorta_minima;
	}

	public void setScorta_minima(int scorta_minima) {
		this.scorta_minima = scorta_minima;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public ArrayList<Detail> getDettagli() {
		return dettagli;
	}

	public void setDettagli(ArrayList<Detail> dettagli) {
		this.dettagli = dettagli;
	}

	private int id;
	private String marca;
	private String modello;
	private int prezzo_vendita;
	private int prezzo_acquisto;
	private int quantitaDisp;
	private int scorta_minima;
	private String alt;
	private String descrizione;
	private ArrayList<Detail> dettagli;
}
