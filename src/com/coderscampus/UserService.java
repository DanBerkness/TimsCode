package com.coderscampus;

public class UserService {

	public User getVerifiedUser(String username, String password) {
		for (User user : UserApp.users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				return user;
			}
		}return null;

	}

	public User getUserByUsername(String username) {
		for (User user : UserApp.users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

	public String getUserInfo(User user) {
		return user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n";
	}

	public void setUsers(User[] populateUsersFromFile) {
		// TODO Auto-generated method stub
		
	}

}
