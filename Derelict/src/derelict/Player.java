package derelict;
import java.util.HashMap;;

public class Player extends Entity {
	
	private String name;
	private HashMap<String,Equipment> PlayerEquipment;
	private int Health;
	private int SuitIntegrity;
	private int Oxygen;
	private int Power;
	private int Ammo;
	
	public Player(String username) {
		name = username;
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
	
	public void DamageG(String ID, int damage) {
		if (PlayerEquipment.containsKey(ID)) {
			
		}
	}
}
