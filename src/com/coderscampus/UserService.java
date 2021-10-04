package com.coderscampus;

public class UserService {

	public static User[] users = new User[21];

	public User userVerified(String username, String password) {
		for (User user : UserService.users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		UserService.users = users;
	}

	public User getUserByUsername(String usernameToUpdate) {
		return null;
	}

	public String getUserInfo(User user) {
		return user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n";
	}
}
