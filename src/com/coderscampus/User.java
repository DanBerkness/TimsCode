package com.coderscampus;
                                       
// Adding an additional field to User Object to include "role" add as well
// username, password, and name.

//                          Sort super users before normal users
public class User implements Comparable<User> {

	private String username;
	private String password;
	private String name;
	protected String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}

	@Override
	public int compareTo(User that) {
		if (this.role.equals(that.role)) {
			return -1;
		} else if (this.username.equals(that.username)) {
			return 0;
		} else {
			return 1;
		}

	}
}
