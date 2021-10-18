package com.coderscampus;

public class NormalUser extends User {

	// The role assignement to a User will be either: normal_user or super_user

	public NormalUser(String username, String password, String name) {
		this.role = "normal_user";
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}
}