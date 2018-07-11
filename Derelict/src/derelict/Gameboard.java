package derelict;
import java.util.Random;
import derelict.entities.*;

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
					if (Board[x][y].getID() !=2) {
						Random rand2 = new Random();
						int chance = rand2.nextInt(100);
						if (chance < 25) {
							Board[x][y].addEntity(new Creature());
						}else if (chance < 35) {
							Board[x][y].addEntity(new Civilian());
						}
					}
				}
				
			}
		}		
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Room R = Board[x][y];
				if(x > 0) {
					if (Board[x-1][y].getID() != 2) {R.addNeighbor("left", Board[x-1][y]);}
				}
				if(x < w-1) {
					if (Board[x+1][y].getID() != 2) {R.addNeighbor("right", Board[x+1][y]);}
				}
				if(y > 0) {
					if (Board[x][y-1].getID() != 2) {R.addNeighbor("down", Board[x][y-1]);}
				}
				if(y < h-1) {
					if (Board[x][y+1].getID() != 2) {R.addNeighbor("up", Board[x][y+1]);}
				}
			}
		}
		clearOrphans();
		
		Random rand = new Random();
		startlocation = Board[rand.nextInt(w)][rand.nextInt(h)];
		while ((startlocation.neighborCount() == 0)|(startlocation.getID() != 0)) {
			rand = new Random();
			startlocation = Board[rand.nextInt(w)][rand.nextInt(h)];
		}
		if (startlocation.entityCount() != 0) {
			startlocation.removeEntity(startlocation.entityFirst());
		}
		
	}
	
	public void clearOrphans() {
		Room center = Board[w/2][h/2];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Room R =Board[x][y];
				if (R != center) {
					Path RPath = new Path(R,center,this);
					if (RPath.Length == -1) {
						R.clear();
					}
				}
				
			}
		}
	}
	
	public int civCheck() {
		int count = 0;
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Room R = Board[x][y];
				if (R.civilianHere()) {count += 1;}	
			}
		}
		return count;
	}
	
	public void resetScan() {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Board[x][y].setScan(false);
			}
		}
	}
	
	public int scanRoom(Room R) {
		int x = R.getX();
		int y = R.getY();
		int count = 0;
		R.setScan(true);
		Room Z;
		if(x > 0) {
			Z = Board[x-1][y];
			Z.setScan(true); 
			count += Z.entityCount();
			if (y > 0) {
				Z = Board[x-1][y-1];
				Z.setScan(true);
				count += Z.entityCount();
			}
			if (y < h-1) {
				Z = Board[x-1][y+1];
				Z.setScan(true);
				count += Z.entityCount();
			}
		}
		if(x < w-1) {
			Z = Board[x+1][y];
			Z.setScan(true);
			count += Z.entityCount();
			if (y > 0) {
				Z = Board[x+1][y-1];
				Z.setScan(true);
				count += Z.entityCount();
			}
			if (y < h-1) {
				Z = Board[x+1][y+1];
				Z.setScan(true);
				count += Z.entityCount();
			}
		}
		if(y > 0) {
			Z = Board[x][y-1];
			Z.setScan(true);
			count += Z.entityCount();
		}
		if(y < h-1) {
			Z = Board[x][y+1];
			Z.setScan(true);
			count += Z.entityCount();
		}
		return count;
	}
	
	public void iterate(Player P) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Room C = Board[x][y];
				if (C.entityCount() != 0) {
					C.iterate(P);
				}
			}
		}
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
	
	public void createEntity(int x, int y, Entity E) {
		Board[x][y].addEntity(E);
	}
	
	public boolean deleteEntity(int x, int y, Entity E) {
		return Board[x][y].removeEntity(E);
	}
	
	public int entityCount(int x, int y) {
		return Board[x][y].entityCount();
	}
	
}
