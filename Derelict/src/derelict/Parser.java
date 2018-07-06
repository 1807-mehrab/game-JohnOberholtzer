package derelict;
import java.util.Scanner;
import java.util.HashSet;;

public class Parser {
	private Scanner reader;
	private HashSet<String> validCommands;
	
	public Parser() {
		validCommands = new HashSet<String>();
		reader = new Scanner(System.in);
		validCommands.add("move");
		validCommands.add("scan");
		validCommands.add("cut");
		validCommands.add("repair");
		validCommands.add("salvage");
		validCommands.add("rescue");
		validCommands.add("drop");
		validCommands.add("quit");
		
	}
	
	public void close() {
		reader.close();
	}
	
	public Command Parse() {
		String inputLine, inputWord;
		System.out.print("[> ");
		inputLine = reader.nextLine();
		
		Scanner tk = new Scanner(inputLine);
		if(tk.hasNext()) {
			inputWord = tk.next();
		}
		
		if (isCommand(inputLine)){
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

}
