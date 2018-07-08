package derelict;

public abstract class Entity {
	public String Name;
	protected int HealthMax;
	protected int Health;
	protected int Damage;
	public int Type = 0;
	public boolean Hostile = false;
	
	public void attack(Entity target) {
		target.DamageE(Damage);
	}
	
	public int getHealth() {
		return Health;
	}
	
	public int getHealthM() {
		return HealthMax;
	}
	
	public void DamageE(int damage) {
		Health -= damage;
		if (Health < 0) {Health = 0;}
	}
	
	public void Heal(int val) {
		Health += val;
		if (Health < HealthMax) {Health = HealthMax;}
	}
}
