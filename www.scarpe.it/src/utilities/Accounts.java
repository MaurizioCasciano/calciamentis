package utilities;

import java.util.ArrayList;

import utilities.user.User;

public class Accounts {

	/**
	 * Gets the User with the given username, null if not exists such an user.
	 * @param username the username of the User.
	 * @return the User with the given username or null.
	 */
	public static User getUser(String username) {
		for (User u : users) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}

		return null;
	}
	
	public static void addUser(User user) throws IllegalArgumentException{
		if(getUser(user.getUsername()) == null){
			users.add(user);
			
			for(User u : users){
				System.out.println(u);
			}
		}else{
			throw new IllegalArgumentException("Already regitered username!!!");
		}
	}

	public static ArrayList<User> users;

	/*static {
		users = new ArrayList<>();
		users.add(new User("pippo", "#P1pppp0"));
		users.add(new User("paperino", "P#p31n00"));
	}*/
}
