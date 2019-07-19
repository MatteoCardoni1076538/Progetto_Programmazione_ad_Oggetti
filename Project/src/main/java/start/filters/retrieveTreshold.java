package start.filters;

public class retrieveTreshold {
	private int tresh1;
	private int tresh2;

	public retrieveTreshold(int tresh1, int tresh2) {
		this.tresh1 = tresh1;
		this.tresh2 = tresh2;
	}

	public retrieveTreshold(int tresh1) {
		this.tresh1 = tresh1;
	}

	public void checkTreshold1Value() throws myExceptionTreshold{
		if (tresh1 < 0) {
			throw new myExceptionTreshold();
		}
	}

	public void checkTreshold2Values() throws myExceptionTreshold{
		if ((tresh1 < 0) || (tresh2 < 0)) {
			throw new myExceptionTreshold();
		}
	}
}
