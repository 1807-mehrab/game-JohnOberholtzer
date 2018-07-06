package derelict;

public class Game {
	private Display display;
	private Player player;
	private Parser parser;
	private Gameboard gameboard;
	public Boolean Quit = false;
	int defaultsize = 20;
	String playername;
	
	public Game() {
		parser = new Parser();
		gameboard = new Gameboard(defaultsize,defaultsize);
		display = new Display(gameboard);
		playername = "Joe";
		player = new Player(playername);
	

	}
	
	public void Cycle() {
		display.Show();
	}
	
	public void Scan() {
		Command C = parser.Parse();
		if (C.toString().equals("quit")){
			System.out.println("[< EXITING PROGRAM");
			parser.close();
			Quit = true;
		} else if (C.toString().equals("help")){
			System.out.println("[< EXITING PROGRAM");
		}
		Cycle();
	}
}
