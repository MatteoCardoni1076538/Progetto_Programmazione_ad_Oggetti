package start.data;

public class Quadruplet {
	public String name = new String();
	public Float MEUR;
	public Float GDP;
	public int year;

	public Quadruplet(String name, Float MEUR, Float GDP, int year) {
		this.name = name;
		this.MEUR = MEUR;
		this.GDP = GDP;
		this.year = year;
	}


	@Override
	public String toString() {

		String s = "";

		s += "{" + "State: "  + this.name +" ";
		s += "Year: " + this.year + " ";
		s += "MEUR: " + this.MEUR + " ";
		s += "GDP: " + this.GDP +"}";

		return s;
	}

}