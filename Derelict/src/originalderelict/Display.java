package originalderelict;

import originalderelict.entities.Player;

public class Display {
	private Gameboard boardref;
	private int w,h;
	
	public Display(Gameboard boardref) {
		this.boardref = boardref;
		this.w = boardref.getW();
		this.h = boardref.getH();
	}
	
	public void ShowRoomID(Room R) {
		String Textblock ="[< (";
		if (R.getID() == 0) { Textblock += "This room is undamaged"; }
		else if (R.getID() == 1) { Textblock += "This compartment has a hull breach"; }
		else if (R.getID() == 2){ Textblock += "This is open space"; }
		else { Textblock += "Location Unrecognizable"; }
		Textblock += ")";
		System.out.println(Textblock);
		
		
	}
	
	public void Show() {
		String Textblock ="";
		for (int y = h-1; y >= 0; y--) {
			Textblock += "\n";
			for (int x = 0; x < w; x++) {
				Room R = boardref.getRoom(x, y);
				String prefix = "";
				String middle = "";
				String suffix = "";
				if(R.getID() == 0) { //Regular Room
					if (!R.visited()) {
						prefix = "[";
						suffix = "]";
					} else {
						prefix = "(";
						suffix = ")";
					}
				} else if(R.getID() == 1) { //Damaged Room
					prefix = "{";
					suffix = "}";
				} else if(R.getID() == 2) { //Open Space
					prefix = " ";
					suffix = " ";
				} else { //Should not exist
					prefix = "&";
					suffix = "&"; 
				}
				if (R.playerHere()) {
					middle = "O";
				} else {
					
					if ((R.scanned())&&(R.getID()!= 2 )) {
						if (R.unitCount() != 0)
							middle = "X";
						else {
							middle = "_";
						}
					} else {
						middle = " ";
					}
				}
				Textblock += prefix + middle + suffix + " ";
			}
		}
		System.out.println(Textblock);

	}
	
	public void showStats(Player P, int CivCount) {
		String Userblock ="Health: " + P.getHealth() + "/" + P.getHealthM() +
				"   Oxygen: " + P.getOxygen() + "/" + P.getOxygenM() +
				"   Power: " + P.getPower() + "/" + P.getPowerM() +
				"   Civilians Remaining: " + CivCount;
		System.out.println(Userblock);
	}
}
