package Main;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import start.data.Quadruplet;

import org.apache.commons.math3.stat.descriptive.*;

public class statistics_calculus extends start.data.Associate {
	DescriptiveStatistics stats = new DescriptiveStatistics();
	ArrayList<statistic_years> matrix2 = new ArrayList<statistic_years>();

	public String calculate() {
		for(int e = 0; e < Y.size(); e = e + 1) {
			for(int i = 0; i < S.size(); i = i + 1) {
				stats.addValue(MEUR.get(i).get(e));	
			}

			double mean = stats.getMean();
			double min = stats.getMin();
			double max = stats.getMax();
			double dev_std = stats.getStandardDeviation();
			double sum = stats.getSum();
			long count = stats.getN();

			statistic_years cell_data_stats = new statistic_years(Y.get(e), mean, min, max, dev_std, sum, count);
			matrix2.add(cell_data_stats);

		}
		Gson output1 = new Gson();
		String temp1 = output1.toJson(matrix2);
		System.out.println(temp1);
		return(temp1);
	}
}

