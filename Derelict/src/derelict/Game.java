package derelict;

public class Game {
	private Display display;
	private Player player;
	private Parser parser;
	private Gameboard gameboard;
	public Boolean Quit = false;
	
	public Game() {
		gameboard = new Gameboard(20,20);
		display = new Display(gameboard);
	

	}
	
	public void Cycle() {
		display.Show();
	}
	
	public void Scan() {
		parser.Parse();
	}
}
