package com.coderscampus;

public class FileService extends UserLoginApplication {

	public static String userUsernameToUpdate() {
		System.out.println("Which user would you like to login as? (Type in a valid username)");
		String usernameToUpdate = scanner.nextLine();
		return usernameToUpdate;
	}

	public static void userUpdateName(User loggedInUser) {
		System.out.println("Please type in your new name: ");
		String name = scanner.nextLine();
		loggedInUser.setName(name);
	}

	public static void usertUpdatePassword(User loggedInUser) {
		System.out.println("Please type in your new password: ");
		String password = scanner.nextLine();
		loggedInUser.setPassword(password);
	}

	public static void userUpdateUsername(User loggedInUser) {
		System.out.println("Please type in your new username: ");
		String username = scanner.nextLine();
		loggedInUser.setUsername(username);
	}

	public static int userCheckList(User loggedInUser) {
		System.out.println("----------");
		System.out.println("Please choose from the following options:");
		if (loggedInUser instanceof SuperUser) {
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
