package com.coderscampus;

	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;

	public class FileService {

		public static User[] populateUsersFromFile(String filePath) {
			User[] users = null;
			BufferedReader br = null;
			try {
				try {
					br = new BufferedReader(new FileReader(filePath));
				} catch (FileNotFoundException e) {
					System.out.println("File not found.");
					e.printStackTrace();
				}

				users = new User[50];
				String line = null;
				int i = 0;
				try {
					while ((line = br.readLine()) != null) {
						users[i] = new User();
						i++;

					}
				} catch (IOException e) {
					System.out.println("Oops, there was an I/O Exception");
					e.printStackTrace();
				}
			} finally {
				if (br != null)
					try {
						br.close();
					} catch (IOException e) {
						System.out.println("Wow another I/O Exception");
						e.printStackTrace();
					}
			}

			return users;
		}
	}



