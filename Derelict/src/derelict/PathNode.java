package derelict;
import java.util.*;

public class PathNode {
	private Integer x, y;
	private Boolean Blocked;
	private Boolean Checked;
	private PathNode Parent;
	private Vector <PathNode> AdjacentRooms;
	private double localgoal, globalgoal;

	public PathNode(int x, int y) {
		this.x = x;
		this.y = y;
		Blocked = false;
		Checked = false;
		AdjacentRooms = new Vector <PathNode>();
	}
	
	public Vector <PathNode> getAdjacentRooms() {
		return AdjacentRooms;
	}
	
	public boolean isBlocked() {
		return Blocked;
	}
	
	public boolean isChecked() {
		return Checked;
	}
	
	public void setBlocked(boolean b) {
		Blocked = b;
	}
	
	public void setChecked(boolean b) {
		Checked = b;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public PathNode getParent() {
		return Parent;
	}
	
	public void nullParent() {
		Parent = null;
	}
	
	public void setParent(PathNode P) {
		Parent = P;
	}
	
	public void addAdjacent(PathNode P) {
		AdjacentRooms.add(P);
	}
	
	public void setLocalGoal(double d) {
		localgoal = d;
	}
	
	public void setGlobalGoal(double d) {
		globalgoal = d;
	}
	
	public double getLocalGoal() {
		return localgoal;
	}
	
	public double getGlobalGoal() {
		return globalgoal;
	}
}
