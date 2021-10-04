package com.coderscampus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public abstract class AppService extends UserLoginApplication {

	User validUser = null;
	int userLogins = 1;
	private String username;
	{
		while (validUser == null && userLogins < 5) {
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();

			validUser = userService.userVerified(username, password);
			if (validUser == null) {
				System.out.println("Invalid login, please try again");
				userLogins++;
				if (userLogins >= 5) {
					System.out.println("Too many failed login attempts, you are now locked out.");
				}
			}
		}

		if (validUser != null) {
			int option = 0;
			while (option != 4) {
				System.out.println("Welcome: " + validUser.getName());
				option = userCheckList(validUser);
				if (option == 0 && "super_user".equals(validUser.getRole())) {
					String usernameToUpdate = userUsernameToUpdate();
					User userToUpdate = userService.getUserByUsername(usernameToUpdate);
					if (userToUpdate == null) {
						System.out.println("Invalid username.");
					} else {
						validUser = userToUpdate;
					}
				} else if (option == 1) {
					userUpdateUsername(validUser);
				} else if (option == 2) {
					usertUpdatePassword(validUser);
				} else if (option == 3) {
					userUpdateName(validUser);
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
			} catch (IOException e) {
				System.out.println("Wow another I/O Exception");
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						System.out.println("Oops, there was an I/O Exception");
					}
				}
			}
		}
	}

	protected abstract int userCheckList(User loggedInUser2);

	protected abstract void userUpdateName(User loggedInUser2);

	protected abstract void usertUpdatePassword(User loggedInUser2);

	protected abstract void userUpdateUsername(User loggedInUser2);

	protected abstract String userUsernameToUpdate();

}
