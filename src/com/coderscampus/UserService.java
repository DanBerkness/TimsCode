package com.coderscampus;
	
	public class UserService {

		public static User[] users = new User[5];

		public User userVerified(String username, String password) {
			for (User user : this.users) {
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
			this.users = users;
		}

	}



