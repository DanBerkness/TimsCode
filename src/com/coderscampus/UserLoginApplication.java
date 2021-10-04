package com.coderscampus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UserLoginApplication {

	static UserService userService = new UserService();
	static Scanner scanner = new Scanner(System.in);
	static User[] users = new User[21];

	public static void main(String[] args) throws IOException {

		Scanner scanner = null;

		try {

			scanner = new Scanner(System.in);

			File file = new File("users.txt");

			try (FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader)) {

				String line = null;
				int i = 0;

				while ((line = bufferedReader.readLine()) != null) {

					String[] elements = line.split(", ");
					if ("super_user".equals(elements[3])) {
						users[i++] = new SuperUser(elements[0], elements[1], elements[2]);
					} else {
						users[i++] = new NormalUser(elements[0], elements[1], elements[2]);
					}
				}
			}

		} catch (IOException e) {

		}

		{
			if (scanner != null)
				scanner.close();
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
				{
				}
			}
		}
	}
}