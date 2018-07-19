package originalderelict.entities;
import java.util.HashMap;

import originalderelict.Entity;

import java.lang.reflect.Field;

public class Player extends Entity {
	
	private String name;
	private int OxygenMax,Oxygen,PowerMax,Power;
	
	public Player() {
		Type = 1;
		Name = "Player";
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
