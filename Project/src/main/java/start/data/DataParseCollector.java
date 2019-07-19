package start.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

//Classe dedicata alla definizione dei metodi dell'interfaccia Parse. Questa classe si dedica al download e salvataggio del file, ed al parsing
//dei dati.
public class DataParseCollector implements start.Parse {

	//URL da cui si scarica il file .csv.
	String link = "https://webgate.ec.europa.eu/comp/redisstat/api/dissemination/sdmx/2.1/data/comp_mare_sa_x?format=csv&compressed=false";
	//Directory dove il codice salva il file scaricato. Coincide con quella del progetto (Project).
	File file = new File("CSV.csv");

	private ArrayList<Integer> Years_int = new ArrayList<Integer>();

	ArrayList<String> Lines = new ArrayList<String>();
	private TreeSet<String> Names = new TreeSet<String>();
	private ArrayList<ArrayList<Float>> cash1 = new ArrayList<ArrayList<Float>>();
	private ArrayList<ArrayList<Float>> cash2 = new ArrayList<ArrayList<Float>>();


	//Metodo che richiama il metodo dentro la classe DownloadCSV. Ritorna il file "popolato", che sarà passato ai successivi metodi per il parsing.
	public File down() {
		start.DownloadCSV download = new start.DownloadCSV(link, file);
		download.method();
		return file;
	}

	//Questo metodo è il primo passo del parsing.
	//L'algoritmo consiste nel fare la scansione riga per riga, utilizzando la classe Scanner ed il relativo metodo useDelimiter, che permette
	//di identificare la fine della riga con il terminatore \n.
	//In questo modo si crea un array di stringhe, in cui ogni cella contiene una stringa che è relativa ad una riga.
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
		return(Lines); //Lines è l'array di stringhe. Come detto ogni cella contiene una righe del file in ingresso.
	}

	//Definizione di Metodo per il parsing degli anni.
	//Gli anni sono contenuti solo nella prima riga del file, perciò per quanto detto per il metodo sopra, in ingresso alla funzione
	//è sufficiente dare una stringa, che rappresenta appunto la prima riga del file.
	//L'algoritmo prevede l'individuazione degli anni, tenendo conto che sono tutti separati da una virgola, e la trasformazione tramite il metodo 
	//parseInt delle stringhe nei corrispondenti numeri. Visto che la riga contiene, nella parte iniziale, anche parole, 
	//il try/catch è stato utilizzato affinchè quando il programma incontra parole salti al carattere successivo (definito dal delimitatore con useDelimiter).
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

		scanner.close();
		return(Years_int);
	}
	//Definizione di metodo per il parsing degli stati.
	//In questo caso l'ingresso alla funzione è tutto il file, trasformato in array di righe tramite il metodo precedentemente definito.
	//La sigla identificativa del nome dello stato si trova tra due punti e virgola, e ce ne è una per ogni cella (riga) dell'array,
	//perciò si è usata la funzione split per identificare il carattere.
	//E' importante notare la struttura dati in uscita dal metodo. Si è usato un TreeSet perchè nel file le sigle sono ripetute due volte,
	//ed il TreeSet non ammette ripetizioni.
	public TreeSet<String> Scan(ArrayList<String> Table) {

		for(int i = 1; i < Table.size(); i++) {
			String[] parts = Table.get(i).split(";");
			Names.add(parts[1]);

		}
		return Names;
	}

	//Definizione di metodo per MEUR e GDP.
	//L'algoritmo è lo stesso per entrambi i valori, quello che cambia è ValueName, che permette di ricercare i MEUR o i GDP,
	//a seconda della stringa che si inserisce. Da quest'ultima dipende anche il valore di ritorno ("cash1", "cash2").
	//Ogni valore è separato da una virgola, ed è di tipo Float.
	//L'algoritmo è molto simile a quelli sopra, utilizzando sempre la funzione split, e fornendo in ingresso al metodo tutto il file,
	//suddiviso in righe con l'array di stringhe creato dal metodo CreateLines().
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
		if (ValueName == "MEUR_KP_PRE") {
			return cash1;	
		}

		else {
			return cash2;	
		}

	}


}



