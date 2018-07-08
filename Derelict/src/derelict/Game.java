package derelict;

import derelict.entities.Player;

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
		player = new Player();
		gameboard.createEntity(gameboard.startlocation.getX(), gameboard.startlocation.getY(), player);
		currentRoom = gameboard.startlocation;
		Cycle(0);
		System.out.println("[< Available Commands: ");
		System.out.println(parser.commandList());
	}
	
	public void Cycle(int var) {
		System.out.println("/----------------------------------------------\\");
		display.Show();
		if (var !=3) {
			gameboard.iterate(player);
		}
		if (var == 1) {
			gameboard.resetScan();
			String popups = currentRoom.visit(player);
			display.showStats(player,gameboard.civCheck());
			System.out.print(popups);
		} else if (var == 2 ) {
			display.showStats(player,gameboard.civCheck());
		} else {
			gameboard.resetScan();
			display.showStats(player,gameboard.civCheck());
		}
		display.ShowRoomID(currentRoom);
	}
	
	public boolean deathcheck() {
		if((player.getHealth() == 0)|(player.getOxygen() == 0)){
			return true;
		}
		return false;
	}
	
	public boolean wincheck() {
		if(gameboard.civCheck() == 0){
			return true;
		}
		return false;
	}
	
	public void Scan() {
		if (deathcheck()) {
			System.out.println("[< GAME OVER: YOU HAVE DIED");
			parser.close();
			Quit = true;
		} else if (wincheck()) {
			System.out.println("[< YOU HAVE RESCUED ALL CIVILIANS");
			parser.close();
			Quit = true;
		} else {
			Command C = parser.Parse();
			if (C.toString().equals("quit")){
				System.out.println("[< EXITING PROGRAM");
				parser.close();
				Quit = true;
			} else if (C.toString().equals("help")){
				Cycle(2);
				System.out.println("[< Available Commands: ");
				System.out.println(parser.commandList());
			} else if (C.toString().equals("move")){
				Cycle(3);
				Command CM = parser.ParseM();
				if (CM.toString().equals("cancel")){
					Cycle(0);
				} else {
					String direction = CM.toString();
					if (currentRoom.checkNeighbor(direction)){ // Can move this way
						Room targetRoom = currentRoom.getNeighbor(direction);
						currentRoom.transferEntity(targetRoom, player);
						currentRoom = targetRoom;
						Cycle(1);
						if (currentRoom.entityCount() > 1) {
							System.out.println("[! You are not alone !");
						}
					
					} else { //No room that way
						Cycle(0);
						System.out.println("[< You cannot move in that direction.");
					}
				}
			} else if (C.toString().equals("scan")) {
				if(player.getPower() > 0) {
					int count = gameboard.scanRoom(currentRoom);
					player.drainPower(1);
					Cycle(2);
					System.out.println("[< Adjacent Rooms Scanned. Lifesigns Detected: " + count);
				} else {
					Cycle(0);
					System.out.println("[! Not Enough Power: <Scanner> !");
				}
				
			} else if (C.toString().equals("salvage")) {
				//Salvage
			} else if (C.toString().equals("repair")) {
				//Repair
			} else if (C.toString().equals("shoot")) {
				if (player.getPower() != 0) {
					if (currentRoom.entityCount() > 1) {
						
					} else {
						Cycle(0);
						System.out.println("[* No Targets to Shoot *");
					}
				} else {
					Cycle(0);
					System.out.println("[! Not Enough Power: <Laser Sidearm> !");
				}
			} else if (C.toString().equals("drop")) {
				//Drop
			} else if (C.toString().equals("gear")) {
				//Equipment Menu
			} else if (C.toString().equals("rescue")) {
				if (currentRoom.hasEntity("Civilian")) {
					currentRoom.removeEntity(currentRoom.getEntity("Civilian"));
					Cycle(0);
					System.out.println("[* Civilian Rescued *");
				} else {
					Cycle(0);
					System.out.println("[* No Civilians Detected *");	
				}
			}
		}
	}
}
