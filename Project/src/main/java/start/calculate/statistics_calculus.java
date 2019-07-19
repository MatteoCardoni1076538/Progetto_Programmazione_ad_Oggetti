package start.calculate;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import org.apache.commons.math3.stat.descriptive.*;


//Calcolo dei parametri statistici.
@RestController
@RequestMapping("/calculations")
public class statistics_calculus extends start.data.Associate {

	private DescriptiveStatistics stats_meur = new DescriptiveStatistics();
	private DescriptiveStatistics stats_gdp = new DescriptiveStatistics();
	private ArrayList<statistic_years> matrix2 = new ArrayList<statistic_years>();



	//Metodo che calcola i parametri statistici, sia sui MEUR che GDP.
	public ArrayList<statistic_years> calculate() {
		//Un singolo valore di MEUR/GDP è individuato dal relativo anno e stato. I cicli for servono ad identificare lo specifico valore su
		//anno e stato.

		//scorrimento sugli anni.
		for(int e = 0; e < getY().size(); e = e + 1) {
			//scorrimento sugli stati.
			for(int i = 0; i < getS().size(); i = i + 1) {
				//Calcolo delle statistiche per MEUR e per GDP, su due variabili diverse.
				stats_meur.addValue(getMEUR().get(i).get(e));
				stats_gdp.addValue(getGDP().get(i).get(e));
			}

			//calcoli.
			double mean_m = stats_meur.getMean();
			double min_m = stats_meur.getMin();
			double max_m = stats_meur.getMax();
			double dev_std_m = stats_meur.getStandardDeviation();
			double sum_m = stats_meur.getSum();
			long count_m = stats_meur.getN();

			double mean_g = stats_meur.getMean();
			double min_g = stats_meur.getMin();
			double max_g = stats_meur.getMax();
			double dev_std_g = stats_meur.getStandardDeviation();
			double sum_g = stats_meur.getSum();
			long count_g = stats_meur.getN();


			//Creazione dell'array di oggetti, contenenti singolarmente tutte le statistiche di MEUR e GDP (stesso algoritmo utilizzato in Associate).
			statistic_years cell_data_stats = new statistic_years(getY().get(e), mean_m, min_m, max_m, dev_std_m, sum_m, count_m, mean_g, min_g, max_g, dev_std_g, sum_g, count_g);
			matrix2.add(cell_data_stats);
			stats_meur.clear();
			stats_gdp.clear();

		}
		return(matrix2);
	}


	@RequestMapping("/stats")
	public String stats_converter() {
		calculate();
		Gson output1 = new Gson();
		String temp1 = output1.toJson(matrix2);
		return(temp1);
	}

	@RequestMapping("/stats_states")
	public String unique_state() {
		HashMap<String, Integer> values = new HashMap<String, Integer>();

		for(int e = 0; e < getSArray().size(); e++) {
			int count = 0;
			for(int i = 0; i < getLines().size(); i ++) {
				if (getLines().get(i).contains(getSArray().get(e)) == true ) {
					count = count + 1; 
				}
				values.put(getSArray().get(e), count);
			}
		}

		Gson outputs = new Gson();
		String temps = outputs.toJson(values);
		return(temps);
	}

}
