package derelict;
import java.util.HashMap;;

public class Player extends Entity {
	
	private String name;
	private HashMap<String,Equipment> PlayerEquipment;
	private int HealthMax,Health, SIMax,SI,OxygenMax,Oxygen,PowerMax,Power,AmmoMax,Ammo;
	
	public Player(String username) {
		name = username;
		PlayerEquipment = new HashMap<String,Equipment>();
		PlayerEquipment.put("Suit",new Equipment("Suit", 100));
		PlayerEquipment.put("Scanner",new Equipment("Scanner", 100));
		PlayerEquipment.put("Cutting Torch", new Equipment("Cutting Torch", 100));
		PlayerEquipment.put("Sidearm", new Equipment("Sidearm", 100));
		PlayerEquipment.put("Magnetic Boots", new Equipment("Magnetic Boots", 100));
	}
	
	public void DamageP(int damage) {
		Health -= damage;
		if (Health < 0) {Health = 0;}
	}
	
	public void Heal(int val) {
		Health += val;
		if (Health < HealthMax) {Health = HealthMax;}
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
	
	
	
	public int getHealth() {
		return Health;
	}
	
	public int getHealthM() {
		return HealthMax;
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
}
