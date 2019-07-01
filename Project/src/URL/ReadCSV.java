package URL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV{
	File out;
	public ReadCSV(File out) {
		this.out = out;
	}

	public void Scan(){
		try {
			
			Scanner scanner = new Scanner(out);
			//Set the delimiter used in file
			scanner.useDelimiter(",|;");

			//Get all tokens and store them in some data structure
			//I am just printing them
			while (scanner.hasNext())
			{
				System.out.print(scanner.next() + "|");
			}
			//Close the scanner 
			scanner.close();
		}
		catch(FileNotFoundException exeption) {
			System.out.print("File non found");
		}
	}
}
