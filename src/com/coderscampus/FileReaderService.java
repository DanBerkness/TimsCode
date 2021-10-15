package com.coderscampus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileReaderService {

	String standardFileName = "users.txt";
	
	String readLine (File file) throws FileNotFoundException, IOException;
	
	void writeLine(File file, String line);
}
