package operations;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import request.Quadruplet;

import java.lang.Math;
import org.apache.commons.math3.stat.descriptive.*;

//@RestController
public class statistics extends request.Associate{
	DescriptiveStatistics stats = new DescriptiveStatistics();
	Integer year;
	ArrayList<Quadruplet> matrix = new ArrayList<Quadruplet>();

	
	public statistics(Integer year) {
		this.year = year;
	}
	
}

	//@RequestMapping("/operations")
	/*public Float metodo(ArrayList<ArrayList<Float>> Money, ArrayList<Integer> Years, TreeSet<String> State) {

		Integer index = Years.indexOf(year);

		for(int i = 0; i < Money.get(index).size(); i = i+1) {
			num = num + Money.get(index).get(i);
		}

		Float avg = num/(State.size());
		return avg;
	}*/


	/*public statistics(String state) {

	}*/

