package start.filters;


import java.util.ArrayList;
//import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


//Implementazione dei filtri.
@RestController
@RequestMapping("/filter")
public class filters extends start.calculate.statistics_calculus {

	private ArrayList<start.calculate.statistic_years> matrix3 = super.calculate();
	private ArrayList<start.data.Quadruplet> matrix_temp = super.matrixCreation();
	private ArrayList<filtered> matrix4 = new ArrayList<filtered>();




	//Primo filtraggio, eseguito sulle medie dei MEUR. Sul ReadMe si trova esattamente che tipo di filtraggio esegue.
	@RequestMapping("/meur")
	public String filter(@RequestParam(value = "first_year") int first_year, @RequestParam(value = "second_year") int second_year, @RequestParam(value = "treshold") int treshold) {

		int i;
		int j;

		//try/catch definito in questo package, per evitare di avere anni inseriti fuori dal range possibile (2000-2017).
		try{
			retrieveYears years = new retrieveYears(first_year, second_year);
			years.checkYears();
			retrieveTreshold tresh = new retrieveTreshold(treshold);
			tresh.checkTreshold1Value();
			//Controllo effettuato per vedere quale dei due anni inseriti è il maggiore
			if (first_year < second_year) {
				i = first_year;
				j = second_year;
			} else {
				i = second_year;
				j = first_year;
			}
			
			//Algoritmo che esegue il filtraggio vero e proprio, dopo aver verificato la consistenza dei dati inseriti.
			while(i != j+1) {
				int index = getY().indexOf(i);
				if(matrix3.get(index).mean_m > treshold) {
					//Come già fatto, si crea un array di elementi del tipo filtered, contenenti il dato filtrato, con media dei MEUR relativa ed anno.
					filtered cell_stats_filtered = new filtered("all", "$in ", matrix3.get(index).year, matrix3.get(index).mean_m, "$gt " + treshold);
					matrix4.add(cell_stats_filtered);
				}
				i = i + 1;
			}

			//Conversione in JSON
			Gson output2 = new Gson();
			String temp2 = output2.toJson(matrix4);
			System.out.println(temp2);
			return(temp2);	
		}catch(myExceptionYears ex) {
			return ex.printError();
		}catch(myExceptionTreshold exTr) {
			return exTr.printError();
		}

	}




	//Secondo filtraggio, eseguito sulle medie dei GDP. Sul ReadMe si trova esattamente che tipo di filtraggio esegue.
	@RequestMapping("/gdp")
	public String filter(@RequestParam(value = "first_treshold") int first_treshold, @RequestParam(value = "second_treshold") int second_treshold) {

		int i;
		int j;

		retrieveTreshold tresh = new retrieveTreshold(first_treshold, second_treshold);
		//try/catch definiti in questo package.
		try {
			tresh.checkTreshold2Values();
			//Valutazione di qualle delle due treshold inserite è la maggiore
			if (first_treshold < second_treshold) {
				i = first_treshold;
				j = second_treshold;
			}else {
				i = second_treshold;
				j = first_treshold;
			}
			//Applicazione effettiva del filtro
			for(int k = 0; k < matrix3.size(); k = k + 1) {
				if(matrix3.get(k).mean_g > i && matrix3.get(k).mean_g < j) {
					filtered cell_stats_filtered = new filtered("all", "$in ", matrix3.get(k).year, matrix3.get(k).mean_g, "$bt " + i + " and " + j);
					matrix4.add(cell_stats_filtered);

				}
			}

			//Conversione in JSON
			Gson output2 = new Gson();
			String temp2 = output2.toJson(matrix4);
			System.out.println(temp2);
			return(temp2);
		}catch (myExceptionTreshold ex1) {
			return ex1.printError();
		}
	}
}
//N.B. i try/catch utilizzati sono definiti all'interno delle classi myExceptionTreshol e myExceptionYears, che fanno a loro volta utilizzo di
// retriveTreshold e retriveYears.

