package derelict.entities;
import derelict.Entity;

public class Civilian extends Entity {
	
	public Civilian() {
		Name = "Civilian";
		Health = 10;
		HealthMax = 10;
		Type = 2;
		Hostile = false;
		Damage = 5;
	}
}
