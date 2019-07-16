package filters;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import operations.statistic_years;

@RestController
@RequestMapping("/aa")
public class filter_MEUR extends operations.statistics_calculus {
	
	ArrayList<statistic_years> matrix3 = new ArrayList<statistic_years>();
	ArrayList<filteredElement> matrix_filt = new ArrayList<filteredElement>();
	
	@RequestMapping("/orcodio")
	public String filter(@RequestParam("years1") int yearMinor, @RequestParam("years2") int yearMajor, @RequestParam("treshold") int treshold) {
		matrix3 = super.calculate();
		for (int i = 0; i < matrix3.size(); i++) {
			if ((matrix3.get(i).year > yearMinor) &&(matrix3.get(i).year < yearMajor) && (matrix3.get(i).mean_m > treshold)) {
				filteredElement filt = new filteredElement(matrix3.get(i).year, matrix3.get(i).mean_m);
				matrix_filt.add(filt);
			}
		}
		Gson output = new Gson();
		String temp = output.toJson(matrix_filt);
		return(temp);
	}
}
