package derelict;

public class Display {
	private Gameboard boardref;
	private Parser parser;
	private int w,h;
	
	public Display(Gameboard boardref) {
		this.boardref = boardref;
		this.w = boardref.getW();
		this.h = boardref.getH();
		this.parser = new Parser();
	}
	
	public void Show(Player P) {
		String Textblock ="";
		for (int y = h-1; y > 0; y--) {
			Textblock += "\n";
			for (int x = 0; x < w; x++) {
				Room R = boardref.getRoom(x, y);
				if(R.getID() == 0) {
					
					Textblock += "[ ] "; //Regular Room
				} else if(R.getID() == 1) {
					Textblock += "{ } "; //Damaged Room
				} else if(R.getID() == 2) {
					Textblock += "    "; //Open Space
				} else {
					Textblock += "( ) "; //Should not exist
				}
			}
		}
		String Userblock ="Health: " + P.getHealth() + "/" + P.getHealthM() +
				"   Oxygen: " + P.getOxygen() + "/" + P.getOxygenM() +
				"   Power: " + P.getPower() + "/" + P.getPowerM();
		System.out.println(Textblock);
		System.out.println(Userblock);
	}
}
