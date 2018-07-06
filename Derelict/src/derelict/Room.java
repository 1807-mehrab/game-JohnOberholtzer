package derelict;
import java.util.List;

public class Room {
//Container of entities
	private boolean visited = false;
	private List<Entity> Entities;
	private int xloc, yloc, ID;
	
	public Room(int xloc, int yloc) {
		this.xloc = xloc;
		this.yloc = yloc;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void addEntity(Entity E) {
		Entities.add(E);
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
