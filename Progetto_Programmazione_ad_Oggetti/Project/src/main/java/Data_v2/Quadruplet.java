package Data_v2;

public class Quadruplet {
 private String name = new String();
 private Float MEUR;
 private Float GDP;
 private int year;
 
 public Quadruplet(String name, Float MEUR, Float GDP, int year) {
  this.name = name;
  this.MEUR = MEUR;
  this.GDP = GDP;
  this.year = year;
 }


@Override
public String toString() {
	
	String s = "";
	
	s += "[ State: " + this.name +"\n";
	s += "Year: " + this.year +"\n";
	s += "MEUR: " + this.MEUR +"\n";
	s += "GDP: " + this.GDP +"\n ]";
	
	return s;
}

}