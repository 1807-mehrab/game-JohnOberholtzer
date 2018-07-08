package derelict;
import java.util.Scanner;
import java.util.HashSet;;

public class Parser {
	private Scanner reader;
	private HashSet<String> validCommands;
	private HashSet<String> validCommandsM;
	//private HashSet<String> validCommandsR;
	
	public Parser() {
		validCommands = new HashSet<String>();
		validCommandsM = new HashSet<String>();
		//validCommandsR = new HashSet<String>();
		reader = new Scanner(System.in);
		validCommands.add("move");
		validCommands.add("scan");
		validCommands.add("shoot");
		//validCommands.add("cut");
		//validCommands.add("repair");
		//validCommands.add("salvage");
		validCommands.add("rescue");
		//validCommands.add("drop");
		validCommands.add("search");
		validCommands.add("quit");
		validCommands.add("help");
		validCommands.add("wait");
		
		validCommandsM.add("up");
		validCommandsM.add("down");
		validCommandsM.add("left");
		validCommandsM.add("right");
		validCommandsM.add("cancel");
		
	}
	
	public String commandList() {
		String out = "";
		for (String cmd : validCommands) {
			out += cmd + " ";
		}
		return out;
	}
	
	public void close() {
		reader.close();
	}
	
	public void Intro() {
		String inputLine;
		System.out.println("[< Welcome to Derelict, a text adventure game. ");
		System.out.println("   You are the captain of an interstellar salvage tug, and you have come across ");
		System.out.println("   a derelict vessel. Before you can salvage the wreck, it is your duty to search ");
		System.out.println("   the inside for survivors. Be alert, for you do not know why this ship has taken ");
		System.out.println("   damage, and the cause may still be lurking inside... ");
		
		System.out.println("[< Enter any input to continue ");
		System.out.print("[> ");
		inputLine = reader.nextLine();
	}
	
	public void Intro2() {
		String inputLine;
		System.out.println("[< Your position is represented by the \"O\" ");
		System.out.println("   Rescue all civilians to win. Civilians are unconscious and will be stationary. ");
		System.out.println("   You can move up, down, left or right. Using your scanner or sidearm requires Power. ");
		System.out.println("   Power is siphoned from undamaged rooms [ ] upon entering, but can only be done ");
		System.out.println("   once per room. Damaged compartments { } will deplete your suit Oxygen.");
		
		System.out.println("[< Enter any input to start ");
		System.out.print("[> ");
		inputLine = reader.nextLine();
	}
	
	public Command Parse() {
		String inputLine;
		System.out.print("[> ");
		inputLine = reader.nextLine();
		
		if (isCommand(inputLine)){
			System.out.println("[< Accepted: "+inputLine);
			return new Command(inputLine);
		} else {
			System.out.println("[< invalid command");
			return new Command("nothing");
		}
	}
	
	public Command ParseM() {
		String inputLine;
		System.out.println("[< What Direction? (left, right, up, down, cancel) >");
		System.out.print("[> ");
		inputLine = reader.nextLine();
		
		if (isCommandM(inputLine)){
			System.out.println("[< Accepted: "+inputLine);
			return new Command(inputLine);
		} else {
			System.out.println("[< invalid command");
			return new Command("nothing");
		}
	}
	
	public boolean isCommand(String input) {
		if (validCommands.contains(input)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCommandM(String input) {
		if (validCommandsM.contains(input)) {
			return true;
		} else {
			return false;
		}
	}

}
