package data;

//import java.util.TreeSet;
import java.util.ArrayList;
import java.io.*;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class Years {

	//ArrayList<String> Years_string = new ArrayList<String>();
	ArrayList<Integer> Years_int = new ArrayList<Integer>();
	//File input_file;
	FileReader f;
	//BufferedReader buffer;
	String Line;

	public Years(String Line) {
		this.Line = Line;
	}

	public void Scan(){
		//try {
		/*f = new FileReader(input_file);
			buffer = new BufferedReader(f);
			try {
				Line = buffer.readLine();*/
		//System.out.print(Line);
		//Scanner scanner = new Scanner(input_file);
		//Set the delimiter used in file
		//scanner.useDelimiter(",|;");
		//String terminatore = "\r";
		//do {
		/*while((scanner.hasNext()) && (scanner.next() != "\r")) {
					if (scanner.hasNextInt()) {
						years.add(scanner.nextInt());
					}
					else years.add;
				}*/
		/*while (scanner.hasNext()) {
				/*if(scanner.hasNextInt()) {
					years.add(scanner.nextInt());
				//System.out.print(scanner.next()+"|");
					System.out.print(years.first());
				}
				else {
					System.out.print(scanner.next() + "\n");
					System.out.print("Non ci sono interi\r\n");
				}
				years_string.add(scanner.next());
				if (scanner.equals(terminatore)) break;
				//System.out.print(years_string.first() + "|");
			}
			System.out.print(years_string);*/
		//}while(scanner.hasNextLine());
		//Close the scanner 
		//System.out.print(years);
		//scanner.close();
		//return years;
		//}
		/*catch(IOException e) {
				System.out.print("Imput output exception\n");
			}*/
		Scanner scanner = new Scanner(Line);
		//Set the delimiter used in file
		scanner.useDelimiter(",|;");
		while (scanner.hasNext()) {
			/*if(scanner.hasNextInt()) {
					Years.add(scanner.nextInt());
				//System.out.print(scanner.next()+"|");
					//System.out.print(Years.first());
				}*/
			//else {
			try {
				String temp_str = scanner.next();
				String str = temp_str.replaceAll("\\s+","");
				//System.out.print(str2 + " ");
				int a = Integer.parseInt(str);
				//System.out.print(a + "\n");
				Years_int.add(a);
			}
			catch(NumberFormatException e) {
				//System.out.print("Non convertibile\n");
			}
			//int a = Integer.parseInt(Years_string.
			//System.out.print("Non ci sono interi\r\n");
		}
		System.out.print(Years_int);
		scanner.close();



	}
	/*catch(FileNotFoundException exeption) {
			System.out.print("File non found\n");
		}*/
}
