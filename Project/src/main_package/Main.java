package main_package;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
			
	public static void main(String[] args) {
		String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
		File file = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");
		
		//downloading file
		read.DownloadCSV download = new read.DownloadCSV(link, file);
		download.FileCreation();
		
		//creation of an Array of strings, each string being a row of the CSV file
		data.strings_table table = new data.strings_table(file);
		ArrayList<String> Array_Lines = table.CreateLines();
		
		//creation of an Array containing the years
		data.Years years = new data.Years(Array_Lines.get(0));
		ArrayList<Integer> Years = years.Scan();
		
		//creation of an Array containing the names of the States
		data.States states = new data.States(Array_Lines);
		TreeSet<String> States = states.Scan();
		
		//MEUR Matrix creation
		data.Money MEURs = new data.Money(Array_Lines);
		ArrayList<ArrayList<Float>> MEURTable = MEURs.CreateMatrix("MEUR_KP_PRE");
		
		//GPD Matrix creation
		data.Money GDPs = new data.Money(Array_Lines);
		ArrayList<ArrayList<Float>> GPDTable = GDPs.CreateMatrix("PC_GDP");
	}
}
