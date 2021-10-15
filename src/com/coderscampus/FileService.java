package com.coderscampus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;


public class FileService extends UserService implements FileReaderService {

	public static User[] sortUsersFromFile(String filePath) {
		User[] users = null;
		BufferedReader br = null;
		try {
			try {
				br = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
				e.printStackTrace();
			}

			users = new User[21];
			String line = null;
			int i = 0;
			try {
				while ((line = br.readLine()) != null) {
//					users[i] = new User(line.split(","));
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

	@Override
	public String readLine(File file) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			return reader.readLine();
		} finally {
			if (reader != null)
				reader.close();

		}
	}

	@Override
	public void writeLine(File file, String line) {
		
	}
}