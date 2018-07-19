package originalderelict.entities;
import originalderelict.Entity;

public class Creature extends Entity {
	
	public Creature() {
		Name = "Creature";
		Health = 10;
		HealthMax = 10;
		Type = 2;
		Hostile = true;
		Damage = 30;
	}
}
