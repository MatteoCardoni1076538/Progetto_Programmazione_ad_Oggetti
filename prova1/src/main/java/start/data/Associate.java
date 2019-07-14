package start.data;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;


@RestController
@RequestMapping("/association")
public class Associate extends DataParseCollector {

	String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
	File file = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");

	public File down() {

		Read.DownloadCSV download = new Read.DownloadCSV(link, file);
		download.method();
		return file;
	}

	public ArrayList<String> Lines1 = super.CreateLines(file);
	public ArrayList<Integer> Y = super.Scan(Lines.get(0));
	public TreeSet<String> S = super.Scan(Lines);
	public ArrayList<ArrayList<Float>> MEUR = super.Scan("MEUR_KP_PRE", Lines1);
	public ArrayList<ArrayList<Float>> GDP = super.Scan("PC_GDP", Lines1);


	ArrayList<String> SArray = new ArrayList<String>(S);
	ArrayList<Quadruplet> matrix = new ArrayList<Quadruplet>();


	public ArrayList<Quadruplet> matrixCreation() {
		for (int i = 0; i < SArray.size(); i++) {
			for (int j = 0; j < Y.size(); j++) {
				Quadruplet cell = new Quadruplet(SArray.get(i),GDP.get(i).get(j),MEUR.get(i).get(j),Y.get(j));
				matrix.add(cell);
			}
		}
		
		return(matrix);
		
	}
	@RequestMapping("/data")
	public String data_converter() {
		matrixCreation();
		Gson output = new Gson();
		String temp = output.toJson(matrix);
		return(temp);
	}
	
	
	
	
}
