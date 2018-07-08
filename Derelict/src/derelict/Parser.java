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
		//validCommands.add("cut");
		validCommands.add("repair");
		//validCommands.add("salvage");
		validCommands.add("rescue");
		//validCommands.add("drop");
		validCommands.add("quit");
		validCommands.add("help");
		
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
