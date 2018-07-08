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
		parser.Intro();
		System.out.println("/----------------------------------------------\\");
		display.Show();
		parser.Intro2();
		Cycle(false, false, false, true);
		System.out.println("[< Available Commands: ");
		System.out.println(parser.commandList());
	}
	
	public void Cycle(boolean visit, boolean resetscan, boolean iterate, boolean show) {
		if (show) {System.out.println("/----------------------------------------------\\");}
		if (iterate) {
			gameboard.iterate(player);
		}
		if (resetscan) {
			gameboard.resetScan();
		}
		if (show) {
			display.Show();
			if (visit) {
				String popups = currentRoom.visit(player);
				display.showStats(player,gameboard.civCheck());
				System.out.print(popups);
			} else {
				display.showStats(player,gameboard.civCheck());
			}
			display.ShowRoomID(currentRoom);
		}
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
				Cycle(false, false, false, true);
				System.out.println("[< Available Commands: ");
				System.out.println(parser.commandList());
			} else if (C.toString().equals("move")){
				Cycle(false, true, false, true);
				Command CM = parser.ParseM();
				if (CM.toString().equals("cancel")){
					Cycle(false, true, false, true);
				} else {
					String direction = CM.toString();
					if (currentRoom.checkNeighbor(direction)){ // Can move this way
						Room targetRoom = currentRoom.getNeighbor(direction);
						currentRoom.transferEntity(targetRoom, player);
						currentRoom = targetRoom;
						Cycle(true, true, true, true);
						if (currentRoom.entityCount() > 1) {
							System.out.println("[! You are not alone !");
						}
					
					} else { //No room that way
						Cycle(false, true, false, true);
						System.out.println("[< You cannot move in that direction.");
					}
				}
			} else if (C.toString().equals("scan")) {
				if(player.getPower() > 0) {
					int count = gameboard.scanRoom(currentRoom);
					player.drainPower(1);
					Cycle(false, false, false, true);
					System.out.println("[< Adjacent Rooms Scanned. Lifesigns Detected: " + count);
				} else {
					Cycle(false, false, false, true);
					System.out.println("[! Not Enough Power: <Scanner> !");
				}
			} else if (C.toString().equals("wait")) {
				Cycle(true, true, true, true);
				System.out.println("[* Idling *");
			} else if (C.toString().equals("salvage")) {
				//Salvage
			} else if (C.toString().equals("repair")) {
				//Repair
			} else if (C.toString().equals("shoot")) {
				if (player.getPower() != 0) {
					if (currentRoom.entityCount() > 1) {
						if(currentRoom.hasEntity("Creature")) {
							player.attack(currentRoom.getEntity("Creature"));
							System.out.println("[! Energy Discharge !");
							Cycle(false,false,false,true);
							Cycle(true, true, true, false);
						} else {
							Cycle(false, false, false, true);
							System.out.println("[* No Hostiles to Shoot *");
						}
					} else {
						Cycle(false, false, false, true);
						System.out.println("[* No Targets to Shoot *");
					}
				} else {
					Cycle(false, false, false, true);
					System.out.println("[! Not Enough Power: <Laser Sidearm> !");
				}
			} else if (C.toString().equals("drop")) {
				//Drop
			} else if (C.toString().equals("gear")) {
				//Equipment Menu
			} else if (C.toString().equals("rescue")) {
				if (currentRoom.hasEntity("Civilian")) {
					currentRoom.removeEntity(currentRoom.getEntity("Civilian"));
					Cycle(true, true, true, true);
					System.out.println("[* Civilian Rescued *");
				} else {
					Cycle(false, false, false, true);
					System.out.println("[* No Civilians Detected *");	
				}
			}
		}
	}
}
