package com.coderscampus;

	public class NormalUser extends User {

		public NormalUser(String username, String password, String name) {
			this.role = "normal_user";
			this.setUsername(username);
			this.setPassword(password);
			this.setName(name);
}
}