package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class strings_table {
	ArrayList<String> Lines = new ArrayList<String>();
	File input_file;
	
	public strings_table(File input_file) {
		this.input_file = input_file;
	}
	
	public ArrayList<String> CreateLines() {
		try {
			Scanner scanner = new Scanner(input_file);
			//Set the delimiter used in file
			scanner.useDelimiter("\n");
			while (scanner.hasNext()) {
				Lines.add(scanner.next());
			}
			scanner.close();
		}
		catch(FileNotFoundException exeption) {	
		}
		return(Lines);
	}

}
