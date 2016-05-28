package catalog;

public class Detail {

	public Detail(String intestazione, String corpo) {
		this.intestazione = intestazione;
		this.corpo = corpo;
	}

	public String getIntestazione() {
		return intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	private String intestazione, corpo;
}
