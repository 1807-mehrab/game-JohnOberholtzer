package derelict;

public class Main {

	public static void main(String[] args) {
		Game G = new Game();
		Runnable D = new DisplayRunnable(G);
		Runnable C = new CommandRunnable(G);
		Thread DisplayThread = new Thread(D);
		Thread CommandThread = new Thread(C);
		DisplayThread.start();
		CommandThread.start();

	}

}

class DisplayRunnable implements Runnable {
	Game G;
	DisplayRunnable(Game Gr){G = Gr;}
	public void run() {
		while (!G.Quit) {
			G.Cycle();
		}
	}
}

class CommandRunnable implements Runnable{
	Game G;
	CommandRunnable(Game Gr){G = Gr;}
	public void run() {
		while (!G.Quit) {
			G.Scan();
		}
	}
}