package start.data;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;


//Richiama i metodi definiti da DataParseCollector, che viene ereditata.
@RestController
@RequestMapping("/association")
public class Associate extends DataParseCollector {

	//Richiamo dei metodi definiti nella superclasse. Qui viene effettuato il parsing.
	private File file = down();
	private ArrayList<String> Lines = CreateLines(file);
	private ArrayList<Integer> Y = Scan(getLines().get(0)); //si utilizza get(0) per considerare la prima riga.
	private TreeSet<String> S = Scan(getLines());
	private ArrayList<ArrayList<Float>> MEUR = Scan("MEUR_KP_PRE", getLines());
	private ArrayList<ArrayList<Float>> GDP = Scan("PC_GDP", getLines());

	//Conversione per comodità del TreeSet in ArrayList, per la ricerca degli elementi contenuti.
	private ArrayList<String> SArray = new ArrayList<String>(getS());

	//Creazione di una "matrice" fatta da oggetti di tipo Quadruplet, contenenti una quadrupla composta da Stato, anno, MEUR e GDP.
	private ArrayList<Quadruplet> matrix = new ArrayList<Quadruplet>();


	//Metodo dedicato a popolare l'array di Quadruplet, tramite l'ingresso al costruttore dei parametri richiesti (attributi), ottenuti dopo l'operazione di 
	//parsing.
	public ArrayList<Quadruplet> matrixCreation() {
		for (int i = 0; i < getSArray().size(); i++) {
			for (int j = 0; j < getY().size(); j++) {
				Quadruplet cell = new Quadruplet(getSArray().get(i),getGDP().get(i).get(j),getMEUR().get(i).get(j),getY().get(j));
				matrix.add(cell);
			}
		}

		return(matrix);

	}

	//Conversione in JSON della matrice di quadruple.
	@RequestMapping("/data")
	public String data_converter() {
		matrixCreation();
		Gson output = new Gson();
		String temp = output.toJson(matrix);
		return(temp);
	}

	//Metodo per la creazione dei metadati.
	//Si sfrutta la classe metadatas, allo stesso modo del metodo matrixCreation, per memorizzare gli attributi.
	@RequestMapping("/metadata")
	public String metadata() throws NoSuchFieldException, SecurityException {

		ArrayList<Quadruplet> matrix = matrixCreation();
		//Ricerca degli attributi e dei tipi di attributi.
		String value1 = matrix.get(0).getName().getClass().getSimpleName();
		String value2 = matrix.get(0).getYear().getClass().getSimpleName();
		String value3 = matrix.get(0).getMEUR().getClass().getSimpleName();
		String value4 = matrix.get(0).getGDP().getClass().getSimpleName();

		//Costruzione dell'oggetto.
		metadatas meta1 = new metadatas(value1, value2, value3, value4);
		//Conversione in JSON
		Gson output1 = new Gson();
		String temp1 = output1.toJson(meta1);
		return(temp1);

	}

	public ArrayList<Integer> getY() {
		return Y;
	}

	public TreeSet<String> getS() {
		return S;
	}


	public ArrayList<ArrayList<Float>> getMEUR() {
		return MEUR;
	}


	public ArrayList<ArrayList<Float>> getGDP() {
		return GDP;
	}


	public ArrayList<String> getLines() {
		return Lines;
	}


	public ArrayList<String> getSArray() {
		return SArray;
	}

}