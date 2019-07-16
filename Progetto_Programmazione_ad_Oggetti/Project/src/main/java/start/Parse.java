package start;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public interface Parse {
	//Parsing generico del file: Salva il CSV in un array, in cui ogni cella equivale ad una riga.
	ArrayList<String> CreateLines(File input_file);
	
	//Metodo di parsing per Years.
	ArrayList<Integer> Scan(String Line);
	
	//Metodo di parsing per State.
	TreeSet<String> Scan(ArrayList<String> Table);
	
	//Metodo di parsing per Money.
	ArrayList<ArrayList<Float>> Scan(String ValueName, ArrayList<String> Table);
	
}
