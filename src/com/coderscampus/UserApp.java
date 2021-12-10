package com.coderscampus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class UserApp {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<User> loginID = new ArrayList<>();
		Scanner scanner = new Scanner(new File("users.txt"));
		String[] elements = new String[4];
		User dataInput = new User();
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (null != line && !line.trim().isEmpty()) {
				elements = line.split(",");
				if (null != elements && elements.length >= 4) {
					dataInput = new User();
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
		int userLogins = 0;

		while (verifiedLogin == null && userLogins < 5) {
			System.out.println("Enter your email:");
			String username = scanner.next();
			System.out.println("Enter your password: ");
			String password = scanner.next();
			scanner.nextLine();

			for (User input : loginID) {
				if (input.username.equalsIgnoreCase(username) && input.password.equalsIgnoreCase(password)) {
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
			} else {
				int userChoice = 0;
				System.out.println("Welcome: " + verifiedLogin.name);
				System.out.println("---------------\n");
				while (userChoice != 4) {
					if ("super_user".equals(verifiedLogin.role)) {
						superUserOption();
						userChoice = getSuperUser(scanner);
						if (userChoice != 4) {
							validateUserOption(userChoice, scanner, loginID, verifiedLogin, elements);
						}

					} else if ("normal_user".equals(verifiedLogin.role)) {
						normalUserOptions();
						userChoice = getValidUserData(scanner);
						if (userChoice != 4) {
							validateUserOption(userChoice, scanner, loginID, verifiedLogin, elements);
						}
					}
				}
				scanner.close();
				sortAndWriteToFile(loginID, elements);
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

	private static void validateUserOption(int userLogin, Scanner scanner, ArrayList<User> loginID, User dataInput, String[] elements) {
		switch (userLogin) {
		case 0: {
			System.out.println("Which user would you like to login as? (Type in a valid username)");
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
							validateUserOption(inputFromUser, scanner, loginID, loggedInUser, elements);
						}
					}
				}
				scanner.close();
				sortAndWriteToFile(loginID, elements);
				System.exit(0);
			}
			break;
		}
		case 1: {
			System.out.println("Please type in your new username:");

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
			System.out.println("Please type in your new password:");
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

	private static void sortAndWriteToFile(ArrayList<User> loginID, String[] elements, User dataInput) {
		
		
		for(User validUser : loginID) {
			if(validUser.role.equals("super_user")) {
				SuperUser superUser = new SuperUser(validUser.username, validUser.password, validUser.name, validUser.role);
				ArrayList<SuperUser> superUsers = new ArrayList<>();
				superUsers.addAll(superUsers);
				Arrays.sort(elements);
				
//				Collections.sort(superUsers, new Comparator<User>() {
//					public int compare(User o1, User o2) {
//							return o1.role.compareTo(o2.role);
//						
//					}
//				});
				
			} else if (validUser.role.equals("normal_user")) {
				Arrays.sort(elements);
//				Collections.sort(loginID, new Comparator<User>() {
//					public int compare(User o1, User o2) {
//						return o1.username.compareTo(o2.username);
//					}
//				});
			}
			
		}

		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File("users.txt")));
			for (User clientInput : elements) {
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
				System.out.println("Invalid option!!!");
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
				System.out.println("Invalid option!!!");
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
