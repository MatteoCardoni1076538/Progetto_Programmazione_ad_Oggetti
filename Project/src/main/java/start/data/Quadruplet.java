package start.data;

public class Quadruplet {
	private String name = new String();
	private Float MEUR;
	private Float GDP;
	private Integer year;

	

	public Quadruplet(String name, Float MEUR, Float GDP, int year) {
		this.name = name;
		this.MEUR = MEUR;
		this.GDP = GDP;
		this.year = year;
	}
	

	public String getName() {
		return name;
	}
	
	public Float getMEUR() {
		return MEUR;
	}
	
	public Float getGDP() {
		return GDP;
	}
	
	public Integer getYear() {
		return year;
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