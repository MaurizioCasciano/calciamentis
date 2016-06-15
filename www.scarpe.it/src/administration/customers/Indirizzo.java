package administration.customers;

public class Indirizzo {
	
	public Indirizzo(String via, int numeroCivico, int cap, String citta, String provincia) {
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}
	
	public String getVia() {
		return via;
	}
	public int getNumeroCivico() {
		return numeroCivico;
	}

	public int getCap() {
		return cap;
	}
	public String getCitta() {
		return citta;
	}
	public String getProvincia() {
		return provincia;
	}
	
	private String via;
	private int numeroCivico;
	private int cap;
	private String citta;
	private String provincia;

}
