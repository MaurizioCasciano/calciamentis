package administration;

public class Admin {
	
	public Admin(){
		/*this.cognome = "-";
		this.nome = "-";
		this.username = "Not Valid";
		this.password = "Not Valid";*/
	}
	
	public Admin(String cognome, String nome, String username, String password) {
		this.cognome = cognome;
		this.nome = nome;
		this.username = username;
		this.password = password;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNome() {
		return nome;
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

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj != null){
			Admin adminToCompare = (Admin) obj;
		
			if(adminToCompare.getUsername().equals(this.username)){
			result = true;
			}
		}
		
		return result;
	}

	private String cognome;
	private String nome;
	private String username;
	private String password;

}
