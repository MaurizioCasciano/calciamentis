package database.testing;

import database.Database;

public class DatabaseTester {

	public static void main(String[] args) {
		Database.openConnection();
		
		//System.out.println(Database.getItems());
		System.out.println(Database.getItemById(1));
		
		Database.closeConnection();
	}
}
