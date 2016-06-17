package utilities.user;

import java.io.Serializable;
import java.util.ArrayList;

import administration.customers.Indirizzo;
import database.Database;
import paydesk.PurchasedCart;

public class User implements Serializable {

	public User() {
	}

	public User(String name, String surname, String birthday, String codiceFiscale, String email, String username,
			String password, String repassword, String viaResidenza, String provinciaResidenza, String cittaResidenza,
			String codiceAvviamentoPostaleResidenza, String numeroCivicoResidenza, String viaSpedizione,
			String provinciaSpedizione, String cittaSpedizione, String codiceAvviamentoPostaleSpedizione,
			String numeroCivicoSpedizione) {

		this.name = name;
		this.surname = surname;
		this.codiceFiscale = codiceFiscale;
		this.birthday = birthday;
		this.email = email;
		this.username = username;
		this.password = password;
		this.repassword = repassword;
		this.homeStreet = viaResidenza;
		this.homeProvince = provinciaResidenza;
		this.homeCity = cittaResidenza;
		this.homeCap = codiceAvviamentoPostaleResidenza;
		this.homeStreetNumber = numeroCivicoResidenza;
		this.shippingStreet = viaSpedizione;
		this.shippingProvince = provinciaSpedizione;
		this.shippingCity = cittaSpedizione;
		this.shippingCap = codiceAvviamentoPostaleSpedizione;
		this.shippingStreetNumber = numeroCivicoSpedizione;
	}

	public User(String name, String surname, String codiceFiscale, String birthday, String email, String username,
			String password, String repassword, Indirizzo inidirizzoResidenza, Indirizzo indirizzoSpedizione) {
		this.name = name;
		this.surname = surname;
		this.codiceFiscale = codiceFiscale;
		this.birthday = birthday;
		this.email = email;
		this.username = username;
		this.password = password;
		this.repassword = password;
		this.homeStreet = inidirizzoResidenza.getVia();
		this.homeProvince = inidirizzoResidenza.getProvincia();
		this.homeCity = inidirizzoResidenza.getCitta();
		this.homeCap = inidirizzoResidenza.getCap();
		this.homeStreetNumber = inidirizzoResidenza.getNumeroCivico();
		this.shippingStreet = indirizzoSpedizione.getVia();
		this.shippingProvince = indirizzoSpedizione.getProvincia();
		this.shippingCity = indirizzoSpedizione.getCitta();
		this.shippingCap = indirizzoSpedizione.getCap();
		this.shippingStreetNumber = indirizzoSpedizione.getNumeroCivico();
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
		return shippingProvince;
	}

	public void setShippingProvince(String shippingProvince) {
		this.shippingProvince = shippingProvince;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
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

	public ArrayList<PurchasedCart> getPurchasedCarts() {
		return Database.getPurchasedCarts(getUsername());
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", codiceFiscale=" + codiceFiscale + ", birthday="
				+ birthday + ", email=" + email + ", username=" + username + ", password=" + password + ", repassword="
				+ repassword + ", homeStreet=" + homeStreet + ", homeProvince=" + homeProvince + ", homeCity="
				+ homeCity + ", homeCap=" + homeCap + ", homeStreetNumber=" + homeStreetNumber + ", shippingStreet="
				+ shippingStreet + ", shippingProvince=" + shippingProvince + ", shippingCity=" + shippingCity
				+ ", shippingCap=" + shippingCap + ", shippingStreetNumber=" + shippingStreetNumber + "]";
	}

	/*
	 * DATI ANAGRAFICI
	 ***********************************************************************/
	private String name, surname, codiceFiscale, birthday;
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
	private String shippingStreet, shippingProvince, shippingCity, shippingCap, shippingStreetNumber;

	private static final long serialVersionUID = -546669731039043314L;
}
