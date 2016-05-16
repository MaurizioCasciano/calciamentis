package utilities.user;

public class Address {

	public Address(String via, String provincia, String comune, int numeroCivico, int codiceAvviamentoPostale) {
		this.via = via;
		this.provincia = provincia;
		this.comune = comune;
		this.numeroCivico = numeroCivico;
		this.codiceAvviamentoPostale = codiceAvviamentoPostale;
	}
	
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public int getNumeroCivico() {
		return numeroCivico;
	}
	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	public int getCodiceAvviamentoPostale() {
		return codiceAvviamentoPostale;
	}
	public void setCodiceAvviamentoPostale(int codiceAvviamentoPostale) {
		this.codiceAvviamentoPostale = codiceAvviamentoPostale;
	}


	private String via, provincia, comune;
	private int numeroCivico, codiceAvviamentoPostale;
}
