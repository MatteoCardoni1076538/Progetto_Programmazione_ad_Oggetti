package start.calculate;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import start.data.Associate;

import org.apache.commons.math3.stat.descriptive.*;

@RestController
@RequestMapping("/calculations")
public class statistics_calculus extends start.data.Associate {
	DescriptiveStatistics stats_meur = new DescriptiveStatistics();
	DescriptiveStatistics stats_gdp = new DescriptiveStatistics();
	ArrayList<statistic_years> matrix2 = new ArrayList<statistic_years>();
	ArrayList<Integer> yrs;


	public ArrayList<statistic_years> calculate() {
		for(int e = 0; e < Y.size(); e = e + 1) {
			for(int i = 0; i < S.size(); i = i + 1) {
				stats_meur.addValue(MEUR.get(i).get(e));
				stats_gdp.addValue(GDP.get(i).get(e));
			}

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
			
			statistic_years cell_data_stats = new statistic_years(Y.get(e), mean_m, min_m, max_m, dev_std_m, sum_m, count_m, mean_g, min_g, max_g, dev_std_g, sum_g, count_g);
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
}

