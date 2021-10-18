package com.coderscampus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UserLoginApplication extends FileUtilities {

	static UserService userService = new UserService();
	static Scanner scanner = new Scanner(System.in);
	public static User[] users = new User[21];

	//  Extending the functionality of Assignment #3

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("users.txt"));

			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] userInputs = line.split(", ");
				if ("super_user".equals(userInputs[3])) {
					users[i++] = new SuperUser(userInputs[0], userInputs[1], userInputs[2]);
				} else {
					users[i++] = new NormalUser(userInputs[0], userInputs[1], userInputs[2]);
				}
			}

		} finally {
			if (reader != null)
				reader.close();
		}

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

		if (currentUser != null) {
			int option = 0;
			while (option != 4) {
				System.out.println("Welcome: " + currentUser.getName());
				option = promptOptions(currentUser);
				if (option == 0 && "super_user".equals(currentUser.getRole())) {
					String usernameToUpdate = promptUsernameToUpdate();
					User userToUpdate = userService.getUserByUsername(usernameToUpdate);
					if (userToUpdate == null) {
						System.out.println("Invalid username.");
					} else {
						currentUser = userToUpdate;
					}
				} else if (option == 1) {
					promptUpdateUsername(currentUser);
				} else if (option == 2) {
					promptUpdatePassword(currentUser);
				} else if (option == 3) {
					promptUpdateName(currentUser);
				} else if (option != 4) {
					System.out.println("Invalid input, try again");
				}
			}

			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter("users.txt"));
				Arrays.sort(users);

				for (User user : users) {
					writer.write(userService.getUserInfo(user));
				}
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		}

	}

}