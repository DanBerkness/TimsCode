package com.coderscampus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/// repair code errors
	private static UserService userService = new UserService();

	public static void main(String[] args) throws IOException {
		userService.setUsers(FileService.populateUsersFromFile("users.txt"));
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			boolean verifyLogin = false;
			int attempts = 0;

			while (!verifyLogin && attempts < 5) {
				System.out.println("Enter your email:");
				String username = scanner.nextLine();
				System.out.println("Enter your password: ");
				String password = scanner.nextLine();

				User loggedUser = userService.userVerified(username, password);
				if (loggedUser != null) {
					System.out.println("Welcome: " + loggedUser.getName());
					verifyLogin = true;

				}
			}
		} finally {
			if (scanner != null)
				scanner.close();

			File file = new File("users.txt");

			try (FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				String line;

				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}

			} catch (IOException e) {
				System.out.println("Unable to read file " + file.toString());
			}

			try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {

				writer.write("Please choose thr following options:");
				writer.write("Update username");
				writer.write("Update password");
				writer.write("Update name");
				writer.write("Exit");

				if (writer != null)
					writer.close();
			}

		}
	}
}
}
