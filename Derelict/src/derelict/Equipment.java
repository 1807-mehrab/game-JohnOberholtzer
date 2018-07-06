package derelict;

public class Equipment {
	private String name;
	private int IntegrityMax;
	private int IntegrityCurrent;
	
	public Equipment(String name, int I) {
		this.name = name;
		IntegrityMax = I;
		IntegrityCurrent = I;
	}
}
