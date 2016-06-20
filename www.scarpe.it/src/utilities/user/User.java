package utilities.user;

import java.io.Serializable;
import java.util.ArrayList;

import administration.customers.Indirizzo;
import database.Database;
import paydesk.PurchasedCart;

public class User implements Serializable {

	public User() {
		this.inidirizzoResidenza = new Indirizzo();
		this.indirizzoSpedizione = new Indirizzo();
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
		this.inidirizzoResidenza = new Indirizzo(viaResidenza, numeroCivicoResidenza, codiceAvviamentoPostaleResidenza,
				cittaResidenza, provinciaResidenza);
		/*
		 * this.homeStreet = viaResidenza; this.homeProvince =
		 * provinciaResidenza; this.homeCity = cittaResidenza; this.homeCap =
		 * codiceAvviamentoPostaleResidenza; this.homeStreetNumber =
		 * numeroCivicoResidenza; this.shippingStreet = viaSpedizione;
		 */
		this.indirizzoSpedizione = new Indirizzo(viaSpedizione, numeroCivicoSpedizione,
				codiceAvviamentoPostaleSpedizione, cittaSpedizione, provinciaSpedizione);
		/*
		 * this.shippingProvince = provinciaSpedizione; this.shippingCity =
		 * cittaSpedizione; this.shippingCap =
		 * codiceAvviamentoPostaleSpedizione; this.shippingStreetNumber =
		 * numeroCivicoSpedizione;
		 */
	}

	public User(String name, String surname, String birthday, String codiceFiscale, String email, String username,
			String password, Indirizzo inidirizzoResidenza, Indirizzo indirizzoSpedizione) {
		this.name = name;
		this.surname = surname;
		this.codiceFiscale = codiceFiscale;
		this.birthday = birthday;
		this.email = email;
		this.username = username;
		this.password = password;
		this.repassword = password;
		this.inidirizzoResidenza = inidirizzoResidenza;
		this.indirizzoSpedizione = indirizzoSpedizione;
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
		return this.inidirizzoResidenza.getVia();
		// return homeStreet;
	}

	public void setHomeStreet(String homeStreet) {
		this.inidirizzoResidenza.setVia(homeStreet);
		// this.homeStreet = homeStreet;
	}

	public String getHomeProvince() {
		return this.inidirizzoResidenza.getProvincia();
		// return homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.inidirizzoResidenza.setProvincia(homeProvince);
		// this.homeProvince = homeProvince;
	}

	public String getHomeCity() {
		return this.inidirizzoResidenza.getCitta();
		// return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.inidirizzoResidenza.setCitta(homeCity);
		// this.homeCity = homeCity;
	}

	public String getHomeCap() {
		return this.inidirizzoResidenza.getCap();
		// return homeCap;
	}

	public void setHomeCap(String homeCap) {
		this.inidirizzoResidenza.setCap(homeCap);
		// this.homeCap = homeCap;
	}

	public String getHomeStreetNumber() {
		return this.inidirizzoResidenza.getNumeroCivico();
		// return homeStreetNumber;
	}

	public void setHomeStreetNumber(String homeStreetNumber) {
		this.inidirizzoResidenza.setNumeroCivico(homeStreetNumber);
		// this.homeStreetNumber = homeStreetNumber;
	}

	public String getShippingStreet() {
		return this.indirizzoSpedizione.getVia();
		// return shippingStreet;
	}

	public void setShippingStreet(String viaSpedizione) {
		this.indirizzoSpedizione.setVia(viaSpedizione);
		// this.shippingStreet = viaSpedizione;
	}

	public String getShippingProvince() {
		return this.indirizzoSpedizione.getProvincia();
		// return shippingProvince;
	}

	public void setShippingProvince(String shippingProvince) {
		this.indirizzoSpedizione.setProvincia(shippingProvince);
		// this.shippingProvince = shippingProvince;
	}

	public String getShippingCity() {
		return this.indirizzoSpedizione.getCitta();
		// return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.indirizzoSpedizione.setCitta(shippingCity);
		// this.shippingCity = shippingCity;
	}

	public String getShippingCap() {
		return this.indirizzoSpedizione.getCap();
		// return shippingCap;
	}

	public void setShippingCap(String shippingCap) {
		this.indirizzoSpedizione.setCap(shippingCap);
		// this.shippingCap = shippingCap;
	}

	public String getShippingStreetNumber() {
		return this.indirizzoSpedizione.getNumeroCivico();
		// return shippingStreetNumber;
	}

	public void setShippingStreetNumber(String shippingStreetNumber) {
		this.indirizzoSpedizione.setNumeroCivico(shippingStreetNumber);
		// this.shippingStreetNumber = shippingStreetNumber;
	}

	public Indirizzo getInidirizzoResidenza() {
		return inidirizzoResidenza;
	}

	public void setInidirizzoResidenza(Indirizzo inidirizzoResidenza) {
		this.inidirizzoResidenza = inidirizzoResidenza;
	}

	public Indirizzo getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(Indirizzo indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
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
				+ repassword + ", homeStreet=" + getHomeStreet() + ", homeProvince=" + getHomeProvince() + ", homeCity="
				+ getHomeCity() + ", homeCap=" + getHomeCap() + ", homeStreetNumber=" + getHomeStreetNumber()
				+ ", shippingStreet=" + getShippingStreet() + ", shippingProvince=" + getShippingProvince()
				+ ", shippingCity=" + getShippingCity() + ", shippingCap=" + getShippingCap()
				+ ", shippingStreetNumber=" + getShippingStreetNumber() + "]";
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
	private Indirizzo inidirizzoResidenza;
	// private String homeStreet, homeProvince, homeCity, homeCap,
	// homeStreetNumber;
	/*
	 * INDIRIZZO DI SPEDIZIONE
	 ***************************************************************/
	private Indirizzo indirizzoSpedizione;
	// private String shippingStreet, shippingProvince, shippingCity,
	// shippingCap, shippingStreetNumber;

	private static final long serialVersionUID = -546669731039043314L;

	public static void main(String[] args) {
		User u = new User("Gaetano", "Antonucci", "22/06/1994", "XXXXXXXXXXXXXXXX", "gno@123.it", "gnoanto94",
				"P@ssw0rd", "P@ssw0rd", "cc", "CC", "ll", "1111", "6/c", "idem", "idem", "idem", "idem", "idem");

		System.out.println(u);
	}
}
