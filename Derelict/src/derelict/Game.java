package derelict;

public class Game {
	private Display display;
	private Player player;
	private Parser parser;
	private Gameboard gameboard;
	public Boolean Quit = false;
	int defaultsize = 10;
	String playername;
	private Room currentRoom;
	
	public Game() {
		parser = new Parser();
		gameboard = new Gameboard(defaultsize,defaultsize);
		display = new Display(gameboard);
		playername = "Joe";
		player = new Player(playername);
		gameboard.createEntity(gameboard.startlocation.getX(), gameboard.startlocation.getY(), player);
		currentRoom = gameboard.startlocation;
		Cycle();
	}
	
	public void Cycle() {
		gameboard.iterate();
		display.Show(player);
	}
	
	public void Scan() {
		Command C = parser.Parse();
		if (C.toString().equals("quit")){
			System.out.println("[< EXITING PROGRAM");
			parser.close();
			Quit = true;
		} else if (C.toString().equals("help")){
			Cycle();
			System.out.println("[< Available Commands: ");
			System.out.println(parser.commandList());
		} else if (C.toString().equals("move")){
			Cycle();
			Command CM = parser.ParseM();
			if (CM.toString().equals("cancel")){
				Cycle();
			} else {
				String direction = CM.toString();
				if (currentRoom.checkNeighbor(direction)){ // Can move this way
					currentRoom.transferEntity(currentRoom.getNeighbor(direction), player);
					currentRoom = currentRoom.getNeighbor(direction);
					Cycle();
				} else { //No room that way
					Cycle();
					System.out.println("[< You cannot move in that direction.");
				}
			}
		}
		
	}
}
