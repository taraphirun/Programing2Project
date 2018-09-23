package main;
import java.util.*;
public class Run {
	private static String userInput="";
	protected static boolean isPlaying=true;
	public static void main(String[] args) {

//		Misc.loadGame();
//		Hero newH = Assets.player;
		
//		System.out.println(newH);
//		newH.go('e');
//		newH.go('s');
//		newH.go('e');
//		System.out.println(newH.getLocation());
//		newH.getLocation().checkInventory();
//		newH.checkInventory();
//		newH.dropItem();
//		newH.checkInventory();
//		System.out.println(newH.getLocation());
//		newH.getLocation().checkInventory();
//		Misc.saveGame();
		String command="";
		Scanner in = new Scanner(System.in);
		while(isPlaying) {
			command=in.nextLine();
			switch(command){
			case("now"):case("start"):loadingScreen();break;
			case("end"):isPlaying=false;break;
			default:System.out.println("Wrong");
			}
		}	
	}
	private static void loadingScreen() {
		Misc.typePrint("Welcome to Foodland!");
		System.out.print(" 1. Start New Game\n 2. Load Game \n 3. Exit \nINPUT: ");
		userInput = Integer.toString(Misc.safeInput1Int(1,2,3));
		switch(userInput) {
		case("1"):Misc.newGame();break;
		case("2"):Misc.loadGame();Misc.command();break;
		case("3"):isPlaying=false;break;
		default:System.out.println("Wrong Input");
		}
	}
}
