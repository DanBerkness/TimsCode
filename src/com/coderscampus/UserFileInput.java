package com.coderscampus;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class UserFileInput implements UserFiles {

	public static User[] users = new User[21];
	private static UserService userService = new UserService();

	@Override
	public String readLine(File file) throws IOException {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("users.txt"));

			String inputs = null;
			int i = 0;
			while ((inputs = reader.readLine()) != null) {
				String[] userInputs = inputs.split(", ");
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
		return null;

	}

	@Override
	public void writeLine(File file, String line) throws IOException {
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

	public static User[] populateUsersFromFile(String string) {

		return null;
	}
}
