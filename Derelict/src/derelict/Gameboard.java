package derelict;

public class Gameboard {
//Container of Rooms
	Room[][] Board;
	
	public Gameboard(int w, int h) {
		Board = new Room[w][h];
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Board[x][y] = new Room(x,y);
				Board[x][y].setID(0);
			}
		}
	}
}
