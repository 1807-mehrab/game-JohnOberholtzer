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
				String prefix = "";
				String middle = "";
				String suffix = "";
				if(R.getID() == 0) { //Regular Room
					prefix = "[";
					suffix = "]"; 
				} else if(R.getID() == 1) { //Damaged Room
					prefix = "{";
					suffix = "}";
				} else if(R.getID() == 2) { //Open Space
					prefix = " ";
					suffix = " ";
				} else { //Should not exist
					prefix = "(";
					suffix = ")"; 
				}
				if (R.entityCount() == 0) {
					middle = " ";
				} else {
					middle = "X";
				}
				Textblock += prefix + middle + suffix + " ";
			}
		}
		String Userblock ="Health: " + P.getHealth() + "/" + P.getHealthM() +
				"   Oxygen: " + P.getOxygen() + "/" + P.getOxygenM() +
				"   Power: " + P.getPower() + "/" + P.getPowerM();
		System.out.println(Textblock);
		System.out.println(Userblock);
	}
}
