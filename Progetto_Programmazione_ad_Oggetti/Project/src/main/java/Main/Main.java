
package Main;

import java.io.File;
import com.google.gson.*;

import Data_v2.Quadruplet;

import java.util.ArrayList;
import java.util.TreeSet;

abstract public class Main {
	
	public static void main(String[] args) {
		String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
		File file = new File("C:\\Users\\matte\\Documents\\Università\\Programmazione ad Oggetti\\Progetto\\CSV.csv");

		Read.DownloadCSV download = new Read.DownloadCSV(link, file);
		download.method();
		
		Data_v2.DataParseCollector extra = new Data_v2.DataParseCollector();
		ArrayList<String> LN = extra.CreateLines(file);
		ArrayList<Integer> Y = extra.Scan(LN.get(0));
		TreeSet<String> S = extra.Scan(LN);
		
		ArrayList<ArrayList<Float>> MEUR = extra.Scan("MEUR_KP_PRE", LN);
		
		Data_v2.DataParseCollector extraGDP = new Data_v2.DataParseCollector();
		ArrayList<ArrayList<Float>> GDP = extraGDP.Scan("PC_GDP", LN);
		
		Data_v2.Associate ass = new Data_v2.Associate();
		ass.matrixCreation(LN, Y, S, MEUR, GDP);
		
		statistics_calculus prova = new statistics_calculus();
		prova.calculate();

		
		
		
		
		
		
		/*Data.strings_table table = new Data.strings_table(file);
		ArrayList<String> Array_Lines = table.CreateLines();
		Data.Years years = new Data.Years(Array_Lines.get(0));
		ArrayList<Integer> Years = years.Scan_v2();
		
		Data.State states = new Data.State(Array_Lines);
		TreeSet<String> States = states.Scan_State();
		
		//MEURS PARSE
		Data.Money MEURS = new Data.Money(Array_Lines);
		ArrayList<ArrayList<Float>> MEUR_table = MEURS.Create_Matrix("MEUR_KP_PRE");
		
		//GDP PARSE
		Data.Money GDPS = new Data.Money(Array_Lines);
		ArrayList<ArrayList<Float>> GDPS_table = GDPS.Create_Matrix("PC_GDP");*/
	}
}