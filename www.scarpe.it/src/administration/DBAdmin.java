package administration;

import java.util.ArrayList;

public class DBAdmin {
	
	public static void addAdmin(String cognome, String nome, String username, String password){
		Admin newAdmin = new Admin(cognome, nome, username, password);
		if(!admins.contains(newAdmin)){
			admins.add(newAdmin);
		} else {
			System.out.println("Errore !!! Admin già presente");
		}
	}
	
	public static void removeAdmin(String username){
		for(int i = 0; i < admins.size(); i++){
			if(admins.get(i).getUsername().equals(username)){
				admins.remove(i);
				i--;
			}
		}
	}
	private static ArrayList<Admin> admins;
	
	static {
		admins = new ArrayList<Admin>();
		DBAdmin.addAdmin("Antonucci", "Gaetano", "gnoanto94", "P@ssw0rd");
		DBAdmin.addAdmin("Casciano", "Maurizio", "izio7", "P@ssw0rd");
		DBAdmin.addAdmin("Tropeano", "Domenico", "oromis95", "P@ssw0rd");
	}

}
