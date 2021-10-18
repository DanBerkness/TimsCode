package com.coderscampus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileUtilities extends FileServiceUpdate {

	String standardFileName = "users.txt";

	String readLine(File file) throws FileNotFoundException, IOException {
		return null;
	}

	void writeLine(File file, String line) {
	}

}