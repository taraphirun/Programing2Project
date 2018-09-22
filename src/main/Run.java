package main;
public class Run {
	private static String userInput="";
	private static boolean isPlaying=true;
	public static void main(String[] args) {
		Assets.initilize();
//		while(isPlaying) {
//			loadingScreen();
//		}	
	}
	private static void loadingScreen() {
		Misc.typePrint("Welcome to Foodland!");
		System.out.print(" 1. Start New Game\n 2. Load Game \n 3. Exit \nINPUT: ");
		userInput = Integer.toString(Misc.safeInput1Int(1,2,3));
		if(userInput.equalsIgnoreCase("1")) {
			Misc.newGame();
		}else if(userInput.equalsIgnoreCase("2")) {
			Misc.loadGame();
		}else if(userInput.equalsIgnoreCase("3")) {
			isPlaying=false;
		}
	}
}
