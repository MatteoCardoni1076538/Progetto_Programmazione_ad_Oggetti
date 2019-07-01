package URL;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
		File out = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");

		DownloadCSV file = new DownloadCSV(link, out);
		file.FileCreation();
		
		ReadCSV read = new ReadCSV(out);
		read.Scan();
	}
}
