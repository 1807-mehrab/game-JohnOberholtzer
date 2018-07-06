package derelict;
import java.util.Random;

public class Gameboard {
//Container of Rooms
	private int w,h;
	private Room[][] Board;
	public Room startlocation;
	
	public Gameboard(int w, int h) {
		this.w = w;
		this.h = h;
		Board = new Room[w][h];
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Random rand = new Random();
				Board[x][y] = new Room(x,y);
				
				if ((x > 3)&&(x<7)&&(y>3)&&(y<7)) {
					Board[x][y].setID(rand.nextInt(2));
				} else  if ((x >8)|(x<2)|(y>8)|(y<2)){
					Board[x][y].setID(2);
				} else {
					Board[x][y].setID(rand.nextInt(3));
				}
				
			}
		}
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Room R = Board[x][y];
				if(x > 0) {R.addNeighbor("L", Board[x-1][y]);}
				if(x < w-1) {R.addNeighbor("R", Board[x+1][y]);}
				if(y > 0) {R.addNeighbor("D", Board[x][y-1]);}
				if(y < h-1) {R.addNeighbor("U", Board[x][y+1]);}
			}
		}
		Random rand = new Random();
		Room startlocation = Board[rand.nextInt(w)][rand.nextInt(h)];
		while (startlocation.neighborCount() == 0) {
			rand = new Random();
			startlocation = Board[rand.nextInt(w)][rand.nextInt(h)];
		}
		
		
	}
	
	public void iterate() {
		//Do stuff, move units, etc.
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public Room getRoom(int x, int y) {
		return Board[x][y];
	}
	
}
