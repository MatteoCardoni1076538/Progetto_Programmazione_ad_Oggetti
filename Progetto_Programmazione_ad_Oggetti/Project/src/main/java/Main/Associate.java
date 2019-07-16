package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import start.data.DataParseCollector;
import start.data.Quadruplet;


public class Associate extends DataParseCollector {
	
	String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
	File file = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");
	
	ArrayList<String> Lines = super.CreateLines(file);
	ArrayList<Integer> Y = super.Scan(Lines.get(0));
	TreeSet<String> S = super.Scan(Lines);
	ArrayList<ArrayList<Float>> MEUR = super.Scan("MEUR_KP_PRE", Lines);
	ArrayList<ArrayList<Float>> GDP = super.Scan("PC_GDP", Lines);

	ArrayList<String> SArray = new ArrayList<String>(S);
	ArrayList<String> matrix = new ArrayList<String>();


	public String matrixCreation(ArrayList<String> Lines, ArrayList<Integer> Y, TreeSet<String> S, ArrayList<ArrayList<Float>> MEUR, ArrayList<ArrayList<Float>> GDP ){

		for (int i = 0; i < SArray.size(); i++) {
			for (int j = 0; j < Y.size(); j++) {
				Quadruplet cell = new Quadruplet(SArray.get(i),GDP.get(i).get(j),MEUR.get(i).get(j),Y.get(j));
				matrix.add(cell.toString());
			}
		}
		Gson gson = new Gson();
		String ultimo = gson.toJson(matrix);
		System.out.println(ultimo);
		return(ultimo);
	}
}
