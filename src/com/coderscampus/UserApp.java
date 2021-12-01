package com.coderscampus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserApp {

	public static void main(String[] args) throws FileNotFoundException {
		List<User> loginID = new ArrayList<>();
		Scanner scanner = new Scanner(new File("users.txt"));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (null != line && !line.trim().isEmpty()) {
				String elements[] = line.split(",");
				if (null != elements && elements.length >= 4) {
					User dataInput = new User();
					dataInput.username = elements[0].trim();
					dataInput.password = elements[1].trim();
					dataInput.name = elements[2].trim();
					dataInput.role = elements[3].trim();
					loginID.add(dataInput);
				}
			}
		}
		scanner.close();

		scanner = new Scanner(System.in);

		User verifiedLogin = null;
		int userLogins = 1;

		while (verifiedLogin == null && userLogins < 5) {
			System.out.println("Enter your email:");
			String username = scanner.next();
			System.out.println("Enter your password: ");
			String password = scanner.next();
			scanner.nextLine();

			// this is my UserService Class
			for (User input : loginID) {
				if (input.username.equals(username) && input.password.equals(password)) {
					verifiedLogin = input;
					break;
				}
			}
			if (null == verifiedLogin) {
				System.out.println("Invalid login, please try again");
				userLogins++;
				if (userLogins >= 5) {
					System.out.println("Too many failed login attempts, you are now locked out.");

				}
				// this is my NormalUser & SuperUser cLass
			} else {
				int userChoice = 0;
				System.out.println("Welcome: " + verifiedLogin.name);
				System.out.println("---------------\n");
				while (userChoice != 4) {
					if ("super_user".equals(verifiedLogin.role)) {
						superUserOption();
						userChoice = getSuperUser(scanner);
						if (userChoice != 4) {
							validateUserOption(userChoice, scanner, loginID, verifiedLogin);
						}

					} else if ("normal_user".equals(verifiedLogin.role)) {
						normalUserOptions();
						userChoice = getValidUserData(scanner);
						if (userChoice != 4) {
							validateUserOption(userChoice, scanner, loginID, verifiedLogin);
						}
					}
				}
				scanner.close();
				sortAndWriteToFile(loginID);
			}
		}
	}

	private static void superUserOption() {
		System.out.println("Please choose from following options:");
		System.out.println("(0) Login as another user:");
		System.out.println("(1) Update username:");
		System.out.println("(2) Update password:");
		System.out.println("(3) Update name:");
		System.out.println("(4) Exit:");
	}

	// this is my ValidateUser method substitute for using another class
	private static void validateUserOption(int userLogin, Scanner scanner, List<User> loginID, User dataInput) {
		switch (userLogin) {
		case 0: {
			System.out.println("Which user would you like to login?as(Type in a valid username)");
			String userid = scanner.next();
			scanner.nextLine();
			User loggedInUser = null;
			for (User currentUser : loginID) {
				if (currentUser.username.equals(userid)) {
					loggedInUser = currentUser;
					break;
				}
			}
			if (null == loggedInUser) {
				System.out.println("Invalid User name");
			} else {
				int inputFromUser = 0;
				System.out.println("Welcome: " + loggedInUser.name);
				System.out.println("--------------\n-");
				while (inputFromUser != 4) {
					if ("normal_user".equals(loggedInUser.role)) {
						normalUserOptions();
						inputFromUser = getValidUserData(scanner);
						if (inputFromUser != 4) {
							validateUserOption(inputFromUser, scanner, loginID, loggedInUser);
						}
					}
				}
				scanner.close();
				sortAndWriteToFile(loginID);
				System.exit(0);
			}
			break;
		}
		case 1: {
			System.out.println("Please type in ypur new username:");

			String username = scanner.next();
			if (null != username && !username.trim().isEmpty()) {
				boolean found = false;
				for (User customers : loginID) {
					if (!customers.username.equals(dataInput.username) && customers.username.equals(username)) {
						found = true;
						break;
					}
				}
				if (!found) {
					for (User customers : loginID) {
						if (customers.username.equals(dataInput.username)) {
							customers.username = username;
						}
					}
				}
			}
			break;
		}
		case 2: {
			System.out.println("Please type in ypur new password:");
			String password = scanner.next();
			if (null != password && !password.trim().isEmpty()) {
				for (User consumers : loginID) {
					if (consumers.username.equals(dataInput.username)) {
						consumers.password = password;
					}
				}
			}
			break;
		}
		case 3: {
			System.out.println("Please type in your new name:");
			String name = scanner.nextLine();
			if (null != name && !name.trim().isEmpty()) {
				for (User clients : loginID) {
					if (clients.username.equals(dataInput.username)) {
						clients.name = name;
					}
				}
			}
			break;
		}
		}
	}

	// my method to sort and write file
	private static void sortAndWriteToFile(List<User> loginID) {
		Collections.sort(loginID, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.username.compareTo(o2.username);
			}
		});

		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File("users.txt")));
			for (User clientInput : loginID) {
				bufferedWriter.write(clientInput.username + ", " + clientInput.password + ", " + clientInput.name + ", "
						+ clientInput.role + "\n");
			}
		} catch (IOException e) {
		} finally {
			if (null != bufferedWriter) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
				}
			}

		}
	}

	private static int getValidUserData(Scanner scanner) {
		int userEntry = 0;
		while (userEntry == 0) {
			try {
				userEntry = Integer.valueOf(scanner.next());
				if (userEntry < 1 || userEntry > 4) {
					userEntry = 0;
				}
			} catch (Exception e) {
				userEntry = 0;
			}
			if (userEntry == 0) {
				System.out.println("Invalid choice!!!");
			}
		}
		scanner.nextLine();
		return userEntry;
	}

	private static int getSuperUser(Scanner scanner) {
		int userAccess = -1;
		while (userAccess == -1) {
			try {
				userAccess = Integer.valueOf(scanner.next());
				if (userAccess < 0 || userAccess > 4) {
					userAccess = -1;
				}
			} catch (Exception e) {
				userAccess = -1;
			}
			if (userAccess == -1) {
				System.out.println("Invalid choice!!!");
			}
		}
		scanner.nextLine();
		return userAccess;
	}

	private static void normalUserOptions() {
		System.out.println("Please choose from following options:");
		System.out.println("(1) Update username:");
		System.out.println("(2) Update password:");
		System.out.println("(3) Update name:");
		System.out.println("(4) Exit:");

	}
}