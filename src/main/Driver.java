package main;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		System.out.println(Functions.new1);
		Functions.go('s');
		Functions.go('e');
		Functions.go('e');
		Functions.go('e');
		Functions.go('e');
		Functions.go('e');
		Functions.go('w');
		System.out.println(Functions.new1);
		System.out.println(Functions.stages[Functions.s][Functions.x][Functions.y]);
		String placeDesc = Functions.stages[Functions.s][Functions.x][Functions.y].getDescr();
		Functions.typePrint(placeDesc);	
	}

	
}
