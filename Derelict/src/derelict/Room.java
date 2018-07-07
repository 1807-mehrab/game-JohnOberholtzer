package derelict;
import java.util.LinkedList;
import java.util.HashMap;

public class Room {
//Container of entities
	private boolean visited = false;
	private LinkedList<Entity> Entities;
	private int xloc, yloc, ID;
	private HashMap<String,Room> Adjacents;
	
	public Room(int xloc, int yloc) {
		Entities = new LinkedList<>();
		Adjacents = new HashMap<>();
		this.xloc = xloc;
		this.yloc = yloc;
	}
	
	public void addNeighbor(String direction, Room room) {
		Adjacents.put(direction, room);
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
	
	//public boolean
	
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
