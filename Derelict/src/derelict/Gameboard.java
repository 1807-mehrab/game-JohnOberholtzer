package derelict;

public class Gameboard {
//Container of Rooms
	Room[][] Board;
	
	public Gameboard(int w, int h) {
		Board = new Room[w][h];
	}
}
