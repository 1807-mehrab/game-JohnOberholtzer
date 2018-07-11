package derelict;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import derelict.entities.Player;

import java.util.HashMap;

public class Room {
//Container of entities
	private boolean visited = false;
	private boolean scanned = false;
	private LinkedList<Entity> Entities;
	private LinkedList<Item> Items;
	private int xloc, yloc, ID;
	private HashMap<String,Room> Adjacents;
	
	public Room(int xloc, int yloc) {
		Entities = new LinkedList<>();
		Adjacents = new HashMap<>();
		this.xloc = xloc;
		this.yloc = yloc;
	}
	
	public Entity entityFirst() {
		if (Entities.size() != 0) {
			return Entities.getFirst();
		} else {
			return null;
		}
	}
	
	public boolean visited() {
		return visited;
	}
	
	public boolean scanned() {
		return scanned;
	}
	
	public void setScan(boolean b) {
		scanned = b;
	}
	
	public boolean canPath(Room Target) {
		return true;
	}
	
	public void iterate(Player P) {
		List<Entity> killList = new ArrayList<Entity>();
		for (Entity E : Entities) {
			if (E.Hostile == true) {
				Random rand = new Random();
				int chance = rand.nextInt(100);
				if (E.getHealth() == 0) {
					killList.add(E);
				} else {
					if (playerHere()) { //In same room as player
						if (chance < 75) { //75% chance for a creature to attack if in the room with you.
							E.attack(P);
							System.out.println("[<! WARNING: SUIT INTEGRITY DAMAGED !");
						}
					} else { //Chance to move around
						if (chance < 50) { //50% chance to attempt a movement
							LinkedList<Room> ValidTargets = new LinkedList<Room>();
							for (Room R : Adjacents.values()) {
								if (R.entityCount()==0) {
									ValidTargets.add(R);
								}
							}
							if(ValidTargets.size() != 0) { //Valid to move
								Room T = ValidTargets.get(rand.nextInt(ValidTargets.size()));
								transferEntity(T, E);
							}
					
						}
				
					}
			
				}
			}
		}
		for (Entity E : killList) {
			removeEntity(E);
			System.out.println("[! Life Signature Extinguished !");
		}
	}
	
	public String visit(Player P) {
		String popups = "";
		if(ID==0) {
			if(P.getOxygen() < P.getOxygenM()) {
				P.restoreOxygen(10); //Sealed Compartment, Restores O2
				popups += "[* Oxygen Replenishing *\n";
			}
		} else  if (ID==1){
			P.drainOxygen(10); //Hull Breach
			popups += "[! Oxygen Depleting !\n";
		} else {
			P.drainOxygen(20); //Open Space
			popups += "[! Oxygen Depleting !\n";
		}
		if (!visited) {
			if((ID==0)&&(P.getPower() < P.getPowerM())) {
				visited = true;
				P.restorePower(1);	//Unvisited Intact Areas can have power drawn from capacitors
				popups += "[* Draining Local Capacitors: Power + 1 *\n";
			}
		}
		return popups;
	}
	
	public void addNeighbor(String direction, Room room) {
		Adjacents.put(direction, room);
	}
	
	public void clear() {
		ID = 2;
		Adjacents.clear();
		Entities.clear();
		//System.out.println("[! Orphan Room Deleted !");
	}
	
	public int neighborCount() {
		return Adjacents.size();
	}
	
	public boolean checkNeighbor(String input) {
		return Adjacents.containsKey(input);
	}
	
	public Room getNeighbor(String key) {
		return Adjacents.get(key);
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}
	
	public void addEntity(Entity E) {
		Entities.add(E);
	}
	
	public int entityCount() {
		return Entities.size();
	}
	
	public int unitCount() {
		int count = 0;
		for (Entity E : Entities) {
			if (E.Type == 2) {count +=1;}
		}
		return count;
	}
	
	public boolean playerHere() {
		boolean flag = false;
		for (Entity E : Entities) {
			if(E.Type == 1) {flag = true;}
		}
		return flag;
	}
	
	public boolean civilianHere() {
		boolean flag = false;
		for (Entity E : Entities) {
			if((E.Type == 2)&&(E.Hostile==false)) {flag = true;}
		}
		return flag;
	}
	
	public boolean hostileHere() {
		boolean flag = false;
		for (Entity E : Entities) {
			if((E.Type == 2)&&(E.Hostile==true)) {flag = true;}
		}
		return flag;
	}
	
	//public boolean
	
	public boolean hasEntity(String EntityName) {
		for (Entity E : Entities) {
			 if (E.Name == EntityName) {
				 return true;
			 }
		}
		return false;
	}
	
	public Entity getEntity(String EntityName) {
		for (Entity E : Entities) {
			 if (E.Name == EntityName) {
				 return E;
			 }
		}
		return null;
	}
	
	public boolean removeEntity(Entity E) {
		if (Entities.contains(E)) {
			Entities.remove(E);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean transferEntity(Room R, Entity E) {
		if (Entities.contains(E)) {
			R.addEntity(E);
			removeEntity(E);
			return true;
		} else {
			return false;
		}
	}
}
