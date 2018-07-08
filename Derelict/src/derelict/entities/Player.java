package derelict.entities;
import java.util.HashMap;
import derelict.Entity;
import derelict.Equipment;
import java.lang.reflect.Field;

public class Player extends Entity {
	
	private String name;
	private HashMap<String,Equipment> PlayerEquipment;
	private int OxygenMax,Oxygen,PowerMax,Power;
	
	public Player() {
		Type = 1;
		Name = "Player";
		PlayerEquipment = new HashMap<String,Equipment>();
		PlayerEquipment.put("Suit",new Equipment("Suit", 100));
		PlayerEquipment.put("Scanner",new Equipment("Scanner", 100));
		PlayerEquipment.put("Cutting Torch", new Equipment("Cutting Torch", 100));
		PlayerEquipment.put("Sidearm", new Equipment("Sidearm", 100));
		PlayerEquipment.put("Magnetic Boots", new Equipment("Magnetic Boots", 100));
		Health = 100;
		HealthMax = 100;
		Oxygen = 100;
		OxygenMax = 100;
		Power = 5;
		PowerMax = 5;
		Hostile = false;
		Damage = 20;
	}
	
	public void attack(Entity target) {
		if (Power > 0) {
			target.DamageE(Damage);
			Power -= 1;
		}
	}
	
	public void DamageG(String ID, int damage) {
		if (PlayerEquipment.containsKey(ID)) {
			Equipment E = PlayerEquipment.get(ID);
			E.Damage(damage);
		}
	}
	
	public int EquipmentInt(String ID, int m) {
		if (PlayerEquipment.containsKey(ID)) {
			if(m==0){
				return PlayerEquipment.get(ID).getIntegrity();
			} else {
				return PlayerEquipment.get(ID).getIntegrityMax();
			}
		} else {
			return -1;
		}
	}
	
	public int getVal(String Fieldname) {
		try {
			Field f = this.getClass().getDeclaredField(Fieldname);
			try {
				return f.getInt(this);
			} catch (IllegalAccessException ex) {
				ex.getMessage();
				return 0;
			}
		} catch (NoSuchFieldException ex) {
			ex.getMessage();
			return 0;
		}
		
	}
	

	
	public int getOxygen() {
		return Oxygen;
	}
	
	public int getOxygenM() {
		return OxygenMax;
	}
	
	public int getPower() {
		return Power;
	}
	
	public int getPowerM() {
		return PowerMax;
	}
	
	public void restorePower(int amount) {
		Power += amount;
		if (Power > PowerMax) { Power = PowerMax; }
	}
	
	public void drainPower(int amount) {
		Power -= amount;
		if (Power < 0) { Power = 0; }
	}
	
	public void restoreOxygen(int amount) {
		Oxygen += amount;
		if (Oxygen > OxygenMax) { Oxygen = OxygenMax; }
	}
	
	public void drainOxygen(int amount) {
		Oxygen -= amount;
		if (Oxygen < 0) { Oxygen = 0; }
		if (Oxygen == 0) {System.out.println("[! SUIT OXYGEN DEPLETED !");}
	}
	
}
