package derelict;
import java.util.List;

public class Room {
//Container of entities
	private boolean visited = false;
	private List<Entity> Entities;
	private int xloc, yloc;
	
	public Room(int xloc, int yloc) {
		this.xloc = xloc;
		this.yloc = yloc;
	}
}
