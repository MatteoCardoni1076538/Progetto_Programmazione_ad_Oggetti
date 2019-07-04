package data;

//import java.util.TreeSet;
import java.util.ArrayList;
import java.io.*;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class Years {

	ArrayList<Integer> Years_int = new ArrayList<Integer>();
	FileReader f;
	String Line;

	public Years(String Line) {
		this.Line = Line;
	}

	public ArrayList<Integer> Scan(){
		Scanner scanner = new Scanner(Line);
		//Set the delimiter used in file
		scanner.useDelimiter(",|;");
		while (scanner.hasNext()) {
			try {
				String temp_str = scanner.next();
				String str = temp_str.replaceAll("\\s+","");
				//System.out.print(str2 + " ");
				int a = Integer.parseInt(str);
				//System.out.print(a + "\n");
				Years_int.add(a);
			}
			catch(NumberFormatException e) {
			}
		}
		//System.out.print(Years_int);
		scanner.close();
		return Years_int;
	}
}
