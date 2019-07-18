package start.calculate;

public class statistic_years {
	public int year;
	public double mean_m;
	private double min_m;
	private double max_m;
	private double dev_std_m;
	private double sum_m;
	private long count_m;
	
	public double mean_g;
	private double min_g;
	private double max_g;
	private double dev_std_g;
	private double sum_g;
	private long count_g;

	public statistic_years(int year, double mean_m, double min_m, double max_m, double dev_std_m, double sum_m, long count_m, double mean_g, double min_g, double max_g, double dev_std_g, double sum_g, long count_g) {
		this.year = year;
		this.mean_m = mean_m;
		this.min_m = min_m;
		this.max_m = max_m;
		this.dev_std_m = dev_std_m;
		this.sum_m = sum_m;
		this.count_m = count_m;
		
		this.mean_g = mean_g;
		this.min_g = min_g;
		this.max_g = max_g;
		this.dev_std_g = dev_std_g;
		this.sum_g = sum_g;
		this.count_g = count_g;
	}
	
	
}


