package start.filters;

public class retrieveYears{
	int year1;
	int year2;
	
	public retrieveYears(int year1, int year2) {
		this.year1 = year1;
		this.year2 = year2;
	}
	
	public void checkYears() throws myExceptionYears{
		if ((year1 < 2000) || (year1 > 2017) || (year2 < 2000) || (year2 > 2017)) {
			throw new myExceptionYears();
		}
	}
}
