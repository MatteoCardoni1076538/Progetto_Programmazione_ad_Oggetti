package data;

import java.util.ArrayList;
import java.util.Scanner;

public class Money {

	ArrayList<String> InputTable = new ArrayList<String>();
	ArrayList<ArrayList<Float>> Matrix = new ArrayList<ArrayList<Float>>();
	
 	public Money(ArrayList<String> InputTable) {
 		this.InputTable = InputTable;
 	}
 	
 	public ArrayList<ArrayList<Float>> CreateMatrix(String ValueName){
 		for (int i = 1; i < InputTable.size(); i++) {
 			Scanner scanner = new Scanner(InputTable.get(i));
 			//Set the delimiter used in file
 			scanner.useDelimiter(",|;");
 			while (scanner.hasNext()) {
 				if (scanner.next().contentEquals(ValueName)){
 					String[] TempString =  InputTable.get(i).split(",");
 					ArrayList<Float> TempDouble = new ArrayList<Float>();
 					for (int j = 0; j < TempString.length; j++) {
 						try {
 							String TmpStr = TempString[j].replaceAll("\\s+","");
 							TempDouble.add(Float.parseFloat(TmpStr));
 						}
 						catch(NumberFormatException e) {
 						}
 					}
 					Matrix.add(TempDouble);
 				}
 			}
 		scanner.close();
 		}
 		System.out.print("\n" + Matrix);
 		return Matrix;
 	}
	
}
