package start.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class DataParseCollector implements start.Parse {


	private ArrayList<Integer> Years_int = new ArrayList<Integer>();
	//private String Line;
	//private ArrayList<String> Table = new ArrayList<String>();
	ArrayList<String> Lines = new ArrayList<String>();
	private TreeSet<String> Names = new TreeSet<String>();
	//private ArrayList<String> InputTable = new ArrayList<String>();
	private ArrayList<ArrayList<Float>> cash1 = new ArrayList<ArrayList<Float>>();
	private ArrayList<ArrayList<Float>> cash2 = new ArrayList<ArrayList<Float>>();
	private ArrayList<ArrayList<Float>> cash = new ArrayList<ArrayList<Float>>();


	//Riferendosi all'interfaccia di Parse, crea l'array di righe a partire dal CSV.
	public ArrayList<String> CreateLines(File input_file) {

		try {
			Scanner scanner = new Scanner(input_file);
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

	//Definizione di Metodo per Years.
	public ArrayList<Integer> Scan(String Line){
		Scanner scanner = new Scanner(Line);
		scanner.useDelimiter(",|;");
		while (scanner.hasNext()) {

			try {
				String temp_str = scanner.next();
				String str = temp_str.replaceAll("\\s+","");
				int a = Integer.parseInt(str);
				Years_int.add(a);
			}
			catch(NumberFormatException e) {
			}
		}
		//System.out.print("\n" + Years_int);
		scanner.close();
		return(Years_int);
	}
	//Definizione di metodo per State
	public TreeSet<String> Scan(ArrayList<String> Table) {

		for(int i = 1; i < Table.size(); i++) {
			String[] parts = Table.get(i).split(";");
			Names.add(parts[1]);

		}
		//System.out.println("\n\n" + Names);
		return Names;
	}

	//Definizione di metodo per Money.
	public ArrayList<ArrayList<Float>> Scan(String ValueName, ArrayList<String> InputTable) {

		for(int i = 1; i < InputTable.size(); i++) {
			Scanner scanner = new Scanner(InputTable.get(i));
			scanner.useDelimiter(",|;");

			while(scanner.hasNext()) {
				if(scanner.next().contentEquals(ValueName)) {
					String[] parts = InputTable.get(i).split(",");
					ArrayList<Float> Temp = new ArrayList<Float>();

					for(int j = 0; j < parts.length; j++) {
						try {
							Temp.add(Float.parseFloat(parts[j]));
						}
						catch(NumberFormatException e) {
						}
					}
					
					if (ValueName == "MEUR_KP_PRE") {
						cash1.add(Temp);	
					}
					
					else {
						cash2.add(Temp);	
					}
					
				}
			}
			scanner.close();
		}
		//System.out.println("\n\n" + cash);
		if (ValueName == "MEUR_KP_PRE") {
			return cash1;	
		}
		
		else {
			return cash2;	
		}
		
	}
	
	

}



