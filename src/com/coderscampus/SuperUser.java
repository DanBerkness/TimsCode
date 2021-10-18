package com.coderscampus;

public class SuperUser extends User {

	// The role assignement to a User will be either: normal_user or super_user

	public SuperUser(String username, String password, String name) {
		this.role = "super_user";
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);

	}
}