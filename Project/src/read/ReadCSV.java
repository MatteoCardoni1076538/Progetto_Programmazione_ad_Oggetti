package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ReadCSV{
	File out;

	public ReadCSV(File out) {
		this.out = out;
	}

	 public void Scan(){
		  try {
		   Scanner scanner = new Scanner(out);
		   //Set the delimiter used in file
		   scanner.useDelimiter("\r");

		   //Get all tokens and store them in some data structure
		   //I am just printing them
		   while (scanner.hasNext())
		   {
		    System.out.print(scanner.next() + "|");
		   }
		   //Do not forget to close the scanner 
		   scanner.close();
		  }
		  catch(FileNotFoundException e) {
		   System.out.print("File non found");
		  }
	}
}
