package com.coderscampus;

public class User implements Comparable<User> {
	String username;
	String password;
	String name;
	String role;

	@Override
	public int compareTo(User that) {
		if (this.role.equals(that.role)) {
			return -1;
		} else if (this.password.equals(that.password)) {
			return 0;
		} else {
			return 1;

		}
	}
}