package start.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;



@RestController
@RequestMapping("/filter")
public class filters extends start.calculate.statistics_calculus {

	ArrayList<start.calculate.statistic_years> matrix3 = super.calculate();
	ArrayList<start.data.Quadruplet> matrix_temp = super.matrixCreation();
	ArrayList<filtered> matrix4 = new ArrayList<filtered>();

	
	

	@RequestMapping("/meur")
	public String filter(@RequestParam(value = "first_year") int first_year, @RequestParam(value = "second_year") int second_year, @RequestParam(value = "treshold") int treshold) {

		int i;
		int j;
		try{
			retrieveYears years = new retrieveYears(first_year, second_year);
			years.checkYears();
		}catch(myExceptionYears ex) {
		}
		if (first_year > second_year) {
			i = first_year;
			j = second_year;
		} else {
			i = second_year;
			j = first_year;
		}
		
	
		
		//int i = first_year;
		while(i != j+1) {
			int index = Y.indexOf(i);
			if(matrix3.get(index).mean_m > treshold) {
				filtered cell_stats_filtered = new filtered("all", "$in ", matrix3.get(index).year, matrix3.get(index).mean_m, "$gt " + treshold);
				matrix4.add(cell_stats_filtered);
			}
			i = i + 1;
		}

		Gson output2 = new Gson();
		String temp2 = output2.toJson(matrix4);
		System.out.println(temp2);
		return(temp2);	
	}




	@RequestMapping("/gdp")
	public String filter(@RequestParam(value = "first_treshold") int first_treshold, @RequestParam(value = "second_treshold") int second_treshold) {
		
		retrieveTreshold tresh = new retrieveTreshold(first_treshold, second_treshold);
		try {
			tresh.checkTreshold();
		}catch (myExceptionTreshold ex1) {
			
		}
		for(int i = 0; i < matrix3.size(); i = i + 1) {
			if(matrix3.get(i).mean_g > first_treshold && matrix3.get(i).mean_g < second_treshold) {
				filtered cell_stats_filtered = new filtered("all", "$in ", matrix3.get(i).year, matrix3.get(i).mean_g, "$bt " + first_treshold + " and " + second_treshold);
				matrix4.add(cell_stats_filtered);
				
			}
		}
		
		Gson output2 = new Gson();
		String temp2 = output2.toJson(matrix4);
		System.out.println(temp2);
		return(temp2);
	}
}

