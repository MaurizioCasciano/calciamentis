package utilities.user;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class User implements Serializable {

	public User() {
	}

	public User(String nome, String cognome, GregorianCalendar dataDiNascita, String codiceFiscale, String email,
			String username, String password, String viaResidenza, String provinciaResidenza, String cittaResidenza,
			String codiceAvviamentoPostaleResidenza, String numeroCivicoResidenza, String viaSpedizione,
			String provinciaSpedizione, String cittaSpedizione, String codiceAvviamentoPostaleSpedizione,
			String numeroCivicoSpedizione) {

		this.name = nome;
		this.surname = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.email = email;
		this.username = username;
		this.password = password;
		this.homeStreet = viaResidenza;
		this.homeProvince = provinciaResidenza;
		this.homeCity = cittaResidenza;
		this.homeCap = codiceAvviamentoPostaleResidenza;
		this.homeStreetNumber = numeroCivicoResidenza;
		this.shippingStreet = viaSpedizione;
		this.provinciaSpedizione = provinciaSpedizione;
		this.cittaSpedizione = cittaSpedizione;
		this.shippingCap = codiceAvviamentoPostaleSpedizione;
		this.shippingStreetNumber = numeroCivicoSpedizione;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getHomeStreet() {
		return homeStreet;
	}

	public void setHomeStreet(String homeStreet) {
		this.homeStreet = homeStreet;
	}

	public String getHomeProvince() {
		return homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeCap() {
		return homeCap;
	}

	public void setHomeCap(String homeCap) {
		this.homeCap = homeCap;
	}

	public String getHomeStreetNumber() {
		return homeStreetNumber;
	}

	public void setHomeStreetNumber(String homeStreetNumber) {
		this.homeStreetNumber = homeStreetNumber;
	}

	public String getShippingStreet() {
		return shippingStreet;
	}

	public void setShippingStreet(String viaSpedizione) {
		this.shippingStreet = viaSpedizione;
	}

	public String getShippingProvince() {
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

	public String getShippingCap() {
		return shippingCap;
	}

	public void setShippingCap(String shippingCap) {
		this.shippingCap = shippingCap;
	}

	public String getShippingStreetNumber() {
		return shippingStreetNumber;
	}

	public void setShippingStreetNumber(String shippingStreetNumber) {
		this.shippingStreetNumber = shippingStreetNumber;
	}

	public boolean passwordMatch(String password) {
		if (password.equals(this.password)) {
			return true;
		}

		return false;
	}

	
	
	public String getString() {
		return "User [name=" + name + ", surname=" + surname + ", codiceFiscale=" + codiceFiscale + ", birthday="
				+ birthday + ", dataDiNascita=" + dataDiNascita + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", repassword=" + repassword + ", homeStreet=" + homeStreet
				+ ", homeProvince=" + homeProvince + ", homeCity=" + homeCity + ", homeCap=" + homeCap
				+ ", homeStreetNumber=" + homeStreetNumber + ", shippingStreet=" + shippingStreet
				+ ", provinciaSpedizione=" + provinciaSpedizione + ", cittaSpedizione=" + cittaSpedizione
				+ ", shippingCap=" + shippingCap + ", shippingStreetNumber=" + shippingStreetNumber + "]";
	}



	/*
	 * DATI ANAGRAFICI
	 ***********************************************************************/
	private String name, surname, codiceFiscale, birthday;
	private GregorianCalendar dataDiNascita;
	/*
	 * DATI DI ACCESSO
	 ***********************************************************************/
	private String email, username, password, repassword;
	/*
	 * INDIRIZZO DI RESIDENZA
	 *****************************************************************/
	private String homeStreet, homeProvince, homeCity, homeCap, homeStreetNumber;
	/*
	 * INDIRIZZO DI SPEDIZIONE
	 ***************************************************************/
	private String shippingStreet, provinciaSpedizione, cittaSpedizione, shippingCap,
			shippingStreetNumber;

	private static final long serialVersionUID = -546669731039043314L;
}
