package start.data;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;


@RestController
@RequestMapping("/association")
public class Associate extends DataParseCollector {

	private File file = down();

	private ArrayList<String> Lines = CreateLines(file);
	private ArrayList<Integer> Y = Scan(getLines().get(0));
	private TreeSet<String> S = Scan(getLines());
	private ArrayList<ArrayList<Float>> MEUR = Scan("MEUR_KP_PRE", getLines());
	private ArrayList<ArrayList<Float>> GDP = Scan("PC_GDP", getLines());

	private ArrayList<String> SArray = new ArrayList<String>(getS());
	private ArrayList<Quadruplet> matrix = new ArrayList<Quadruplet>();



	public ArrayList<Quadruplet> matrixCreation() {
		for (int i = 0; i < getSArray().size(); i++) {
			for (int j = 0; j < getY().size(); j++) {
				Quadruplet cell = new Quadruplet(getSArray().get(i),getGDP().get(i).get(j),getMEUR().get(i).get(j),getY().get(j));
				matrix.add(cell);
			}
		}

		return(matrix);

	}

	@RequestMapping("/data")
	public String data_converter() {
		matrixCreation();
		Gson output = new Gson();
		String temp = output.toJson(matrix);
		return(temp);
	}

	@RequestMapping("/metadata")
	public String metadata() throws NoSuchFieldException, SecurityException {

		ArrayList<Quadruplet> matrix = matrixCreation();
		String value1 = matrix.get(0).getName().getClass().getSimpleName();
		String value2 = matrix.get(0).getYear().getClass().getSimpleName();
		String value3 = matrix.get(0).getMEUR().getClass().getSimpleName();
		String value4 = matrix.get(0).getGDP().getClass().getSimpleName();

		metadatas meta1 = new metadatas(value1, value2, value3, value4);
		Gson output1 = new Gson();
		String temp1 = output1.toJson(meta1);
		return(temp1);

	}

	public ArrayList<Integer> getY() {
		return Y;
	}

	public void setY(ArrayList<Integer> y) {
		Y = y;
	}

	public TreeSet<String> getS() {
		return S;
	}

	public void setS(TreeSet<String> s) {
		S = s;
	}

	public ArrayList<ArrayList<Float>> getMEUR() {
		return MEUR;
	}

	public void setMEUR(ArrayList<ArrayList<Float>> mEUR) {
		MEUR = mEUR;
	}

	public ArrayList<ArrayList<Float>> getGDP() {
		return GDP;
	}

	public void setGDP(ArrayList<ArrayList<Float>> gDP) {
		GDP = gDP;
	}

	public ArrayList<String> getLines() {
		return Lines;
	}

	public void setLines(ArrayList<String> lines) {
		Lines = lines;
	}

	public ArrayList<String> getSArray() {
		return SArray;
	}

	public void setSArray(ArrayList<String> sArray) {
		SArray = sArray;
	}

}