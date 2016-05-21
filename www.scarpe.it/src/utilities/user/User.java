package utilities.user;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class User implements Serializable {

	public User(String nome, String cognome, GregorianCalendar dataDiNascita, String codiceFiscale, String email,
			String username, String password, String viaResidenza, String provinciaResidenza, String cittaResidenza,
			int codiceAvviamentoPostaleResidenza, int numeroCivicoResidenza, String viaSpedizione,
			String provinciaSpedizione, String cittaSpedizione, int codiceAvviamentoPostaleSpedizione,
			int numeroCivicoSpedizione) {

		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.email = email;
		this.username = username;
		this.password = password;
		this.viaResidenza = viaResidenza;
		this.provinciaResidenza = provinciaResidenza;
		this.cittaResidenza = cittaResidenza;
		this.codiceAvviamentoPostaleResidenza = codiceAvviamentoPostaleResidenza;
		this.numeroCivicoResidenza = numeroCivicoResidenza;
		this.viaSpedizione = viaSpedizione;
		this.provinciaSpedizione = provinciaSpedizione;
		this.cittaSpedizione = cittaSpedizione;
		this.codiceAvviamentoPostaleSpedizione = codiceAvviamentoPostaleSpedizione;
		this.numeroCivicoSpedizione = numeroCivicoSpedizione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public GregorianCalendar getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(GregorianCalendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getViaResidenza() {
		return viaResidenza;
	}

	public void setViaResidenza(String viaResidenza) {
		this.viaResidenza = viaResidenza;
	}

	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public String getCittaResidenza() {
		return cittaResidenza;
	}

	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}

	public int getCodiceAvviamentoPostaleResidenza() {
		return codiceAvviamentoPostaleResidenza;
	}

	public void setCodiceAvviamentoPostaleResidenza(int codiceAvviamentoPostaleResidenza) {
		this.codiceAvviamentoPostaleResidenza = codiceAvviamentoPostaleResidenza;
	}

	public int getNumeroCivicoResidenza() {
		return numeroCivicoResidenza;
	}

	public void setNumeroCivicoResidenza(int numeroCivicoResidenza) {
		this.numeroCivicoResidenza = numeroCivicoResidenza;
	}

	public String getViaSpedizione() {
		return viaSpedizione;
	}

	public void setViaSpedizione(String viaSpedizione) {
		this.viaSpedizione = viaSpedizione;
	}

	public String getProvinciaSpedizione() {
		return provinciaSpedizione;
	}

	public void setProvinciaSpedizione(String provinciaSpedizione) {
		this.provinciaSpedizione = provinciaSpedizione;
	}

	public String getCittaSpedizione() {
		return cittaSpedizione;
	}

	public void setCittaSpedizione(String cittaSpedizione) {
		this.cittaSpedizione = cittaSpedizione;
	}

	public int getCodiceAvviamentoPostaleSpedizione() {
		return codiceAvviamentoPostaleSpedizione;
	}

	public void setCodiceAvviamentoPostaleSpedizione(int codiceAvviamentoPostaleSpedizione) {
		this.codiceAvviamentoPostaleSpedizione = codiceAvviamentoPostaleSpedizione;
	}

	public int getNumeroCivicoSpedizione() {
		return numeroCivicoSpedizione;
	}

	public void setNumeroCivicoSpedizione(int numeroCivicoSpedizione) {
		this.numeroCivicoSpedizione = numeroCivicoSpedizione;
	}

	public boolean passwordMatch(String password){
		if(password.equals(this.password)){
			return true;
		}
		
		return false;
	}

	/*
	 * DATI ANAGRAFICI
	 ***********************************************************************/
	private String nome, cognome, codiceFiscale;
	private GregorianCalendar dataDiNascita;
	/*
	 * DATI DI ACCESSO
	 ***********************************************************************/
	private String email, username, password;
	/*
	 * INDIRIZZO DI RESIDENZA
	 *****************************************************************/
	private String viaResidenza, provinciaResidenza, cittaResidenza;
	private int codiceAvviamentoPostaleResidenza, numeroCivicoResidenza;
	/*
	 * INDIRIZZO DI SPEDIZIONE
	 ***************************************************************/
	private String viaSpedizione, provinciaSpedizione, cittaSpedizione;
	private int codiceAvviamentoPostaleSpedizione, numeroCivicoSpedizione;

	private static final long serialVersionUID = -546669731039043314L;
}
