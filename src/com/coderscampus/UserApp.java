package com.coderscampus;

import java.util.Scanner;

public class UserApp {

	public static User[] users = new User[21];
	private static UserService userService = new UserService();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		userService.setUsers(FileService.populateUsersFromFile("users.txt"));
		Scanner scanner = null;
		

		try {
			scanner = new Scanner(System.in);

			boolean verifyLogin = false;
			int attempts = 0;

			while (!verifyLogin && attempts < 5) {
				System.out.println("Enter your username:");
				String username = scanner.nextLine();
				System.out.println("Enter your password: ");
				String password = scanner.nextLine();
				
			
				
				User currentUser = userService.getVerifiedUser(username, password);
				if (currentUser != null) {
					System.out.println("Welcome: " + currentUser.getName());
					verifyLogin = true;

				} else {
					System.out.println("Invalid login, please try again");
					attempts++;
					if (attempts >= 5) {
						System.out.println("Too many failed login attempts, you are now locked out.");
					}
				}
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}
}