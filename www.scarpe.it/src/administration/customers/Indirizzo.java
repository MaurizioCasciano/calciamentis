package administration.customers;

import java.io.Serializable;

public class Indirizzo implements Serializable {

	public Indirizzo() {
		this.via = "";
		this.numeroCivico = "";
		this.cap = "";
		this.citta = "";
		this.provincia = "";
	}

	public Indirizzo(String via, String numeroCivico, String cap, String citta, String provincia) {
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	public String getVia() {
		return via;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public String getCap() {
		return cap;
	}

	public String getCitta() {
		return citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	private String via;
	private String numeroCivico;
	private String cap;
	private String citta;
	private String provincia;
	private static final long serialVersionUID = 7904130916189629297L;
}
