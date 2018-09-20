package main;
import java.util.Scanner;

public class Misc {
	private static Scanner in;
	public static void typePrint(String sent) {
		in = new Scanner(sent);
		while(in.hasNext()) {
			String word = in.next();
			for(int i = 0;i<word.length();i++) {
				System.out.print(word.charAt(i));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.print(" ");
		} 
		System.out.println("");
	}
}
