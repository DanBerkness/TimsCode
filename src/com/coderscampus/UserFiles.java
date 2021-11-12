package com.coderscampus;

import java.io.File;
import java.io.IOException;

public interface UserFiles {

	static final String USERFILE = "users.txt";

	String readLine(File file);

	void writeLine(File file, String line) throws IOException;

}
