package start;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public interface Parse {
	//Parsing generico del file: Salva il CSV in un array, in cui ogni cella equivale ad una riga.
	ArrayList<String> CreateLines(File input_file);
	
	//Metodo di parsing per gli anni.
	ArrayList<Integer> Scan(String Line);
	
	//Metodo di parsing per gli stati.
	TreeSet<String> Scan(ArrayList<String> Table);
	
	//Metodo di parsing per i MEUR e GDP.
	ArrayList<ArrayList<Float>> Scan(String ValueName, ArrayList<String> Table);
	
	//L'interfaccia sarà implementata dalla classe DataParseCollector, che ne definisce il funzionamento dei metodi. Si è sfruttato il sovraccarico 
	//del metodo Scan per il parsing.
	
}
