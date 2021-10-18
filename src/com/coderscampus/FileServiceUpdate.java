package com.coderscampus;

import java.util.Scanner;

public abstract class FileServiceUpdate extends UserService {
	static Scanner scanner = new Scanner(System.in);

	// User updates should output this information back to users.txt file and
	// make sure that the information is properly sorted.

	static String promptUsernameToUpdate() {
		System.out.println("Which user would you like to login as? (Type in a valid username)");
		String usernameToUpdate = scanner.nextLine();
		return usernameToUpdate;
	}

	static void promptUpdateName(User currentUser) {
		System.out.println("Please type in your new name: ");
		String name = scanner.nextLine();
		currentUser.setName(name);
	}

	static void promptUpdatePassword(User currentUser) {
		System.out.println("Please type in your new password: ");
		String password = scanner.nextLine();
		currentUser.setPassword(password);
	}

	static void promptUpdateUsername(User currentUser) {
		System.out.println("Please type in your new username: ");
		String username = scanner.nextLine();
		currentUser.setUsername(username);
	}

	static int promptOptions(User currentUser) {
		System.out.println("----------");
		System.out.println("Please choose from the following options:");
		if (currentUser instanceof SuperUser) {
			System.out.println("(0) Log in as another user ");
		}
		System.out.println("(1) Update username");
		System.out.println("(2) Update password");
		System.out.println("(3) Update name");
		System.out.println("(4) Exit");
		String option = scanner.nextLine();

		return Integer.parseInt(option);
	}

}