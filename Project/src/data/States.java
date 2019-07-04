package data;

import java.util.ArrayList;
import java.util.TreeSet;
import java.io.*;
import java.util.Scanner;

public class States {
	TreeSet<String> Names = new TreeSet<String>();
	ArrayList<String> Table = new ArrayList<String>();

	public States(ArrayList<String> Table){
		this.Table = Table;
	}

	public TreeSet<String> Scan(){
		for (int i = 1; i < Table.size(); i++){
			Names.add(Table.get(i).split(";")[1]);
		}
		//System.out.print(Names);
		return Names;
		//System.out.print(Years_int);
	}
}
