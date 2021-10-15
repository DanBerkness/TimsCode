package com.coderscampus;

import java.util.Scanner;

public class UserLoginApplication {

	public static User[] users = new User[21];
	private static UserService userService = new UserService();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		// 1) Extending the functionality of Assignment #3
		
		userService.getUserInfo(FileService.sortUsersFromFile("users.txt"));
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			User currentUser = null;
			int userLogins = 1;

			while (currentUser == null && userLogins < 5) {
				System.out.println("Enter your email:");
				String username = scanner.nextLine();
				System.out.println("Enter your password: ");
				String password = scanner.nextLine();

				currentUser = userService.getVerifiedUser(username, password);
				if (currentUser == null) {
					System.out.println("Invalid login, please try again");
					userLogins++;
					if (userLogins >= 5) {
						System.out.println("Too many failed login attempts, you are now locked out.");
					}
				}
			}

		} finally

		{
			if (scanner != null)
				scanner.close();
		}
	}

}
