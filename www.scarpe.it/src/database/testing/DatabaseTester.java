package database.testing;

import database.Database;

public class DatabaseTester {

	public static void main(String[] args) {
		Database.openConnection();
		
		Database.isAvailableUsername("àòASDL;;:/%&&&ç°ç°çdsa°opsd°lFVASF");
		
		Database.closeConnection();
	}
}
