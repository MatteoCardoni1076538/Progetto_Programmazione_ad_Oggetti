package start.filters;

public class retrieveTreshold {
	int tresh1;
	int tresh2;
	
	public retrieveTreshold(int tresh1, int tresh2) {
		this.tresh1 = tresh1;
		this.tresh2 = tresh2;
	}

	public void checkTreshold() throws myExceptionTreshold{
		if (tresh1 == tresh2) {
			throw new myExceptionTreshold();
		}
	}
}
