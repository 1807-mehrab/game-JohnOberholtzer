package derelict;

public class Equipment {
	private String name;
	private int IntegrityMax;
	private int Integrity;
	
	public Equipment(String name, int I) {
		this.name = name;
		IntegrityMax = I;
		Integrity = I;
	}
	
	public void Damage(int damage) {
		Integrity -= damage;
		if (Integrity < 0) { Integrity = 0; }
	}
	
	public int getIntegrity() {
		return Integrity;
	}
	
	public int getIntegrityMax() {
		return IntegrityMax;
	}
}
