package main_package;

import java.io.File;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
		File file = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");

		read.DownloadCSV download = new read.DownloadCSV(link, file);
		download.FileCreation();
		data.strings_table table = new data.strings_table(file);
		ArrayList<String> Array_Lines = table.CreateLines();
		data.Years years = new data.Years(Array_Lines.get(0));
		years.Scan();
	}
}
