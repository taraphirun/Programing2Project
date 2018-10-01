package main;
import java.util.*;
public class Run {
	private static String userInput="";
	protected static boolean isPlaying=true;
	public static void main(String[] args) {
		mainMethod();
	}
	public static void mainMethod() {
		Misc.typePrint("When you are ready, type \'start\' to start the game!");
		System.out.print(">>>");
		String command="";
		Scanner in = new Scanner(System.in);
		while(isPlaying) {
			command=in.nextLine();
			System.out.println();
			switch(command){
			case("now"):case("start"):case"run":case"start game":case"run game":case"yes":case"sure":case"yup":loadingScreen();break;
			case("end"):case"exit":case"exit game":case"end game":case"no":case"nope":isPlaying=false;break;
			default:System.out.print("I don't understand what "+command+" mean. Do you want to start the game? \n>>>");
			}
		}
		in.close();
	}
	protected static void loadingScreen() {
		Scanner in = new Scanner(System.in);
		Misc.typePrint("Welcome to Foodland!");
		System.out.print(" 1. Start New Game\n 2. Load Game \n 3. Exit \n>>> ");
		userInput = in.nextLine();
		switch(userInput) {
		case("1"):System.out.println();Misc.newGame();System.out.println("Welcome to Foodland,"+ Assets.player.getName()+"! This is a magical world where food will eat you if you don't eat them first!");Misc.checkPlace();Misc.gameplay();break;
		case("2"):System.out.println();Misc.loadGame();System.out.println("Welcome back "+Assets.player.getName()+"!");Misc.checkEnviron();Misc.gameplay();break;
		case("3"):isPlaying=false;break;
		default:System.out.println("Please choose one of the option above!");
		}
		System.out.println();
		in.close();
	}
}
