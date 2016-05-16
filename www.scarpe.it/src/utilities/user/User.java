package utilities.user;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class User implements Serializable {

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * public String getPassword() { return password; }
	 */

	public void setPassword(String oldPassword, String newPassword) {
		if (this.password.equals(oldPassword)) {
			this.password = newPassword;
		}
	}

	public boolean passwordMatch(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [username=" + username + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/*DATI ANAGRAFICI***********************************************************************/
	private String nome, cognome, codiceFiscale;
	private GregorianCalendar dataDiNascita;
	/*DATI DI ACCESSO***********************************************************************/
	private String email, username, password;
	/*****************************************************************/
	
	private static final long serialVersionUID = -546669731039043314L;
}
