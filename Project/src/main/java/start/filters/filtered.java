package start.filters;

public class filtered {
	private String states;
	private String logical;
	private int year;
	private double value;
	private String condition;
	
	public filtered(String states, String logical, int year, double value, String condition) {
		this.states = states;
		this.logical = logical;
		this.year = year;
		this.value = value;
		this.condition = condition;
	}
}
