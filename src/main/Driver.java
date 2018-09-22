package main;

import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		Assets.initilize();
		
		Hero jean = new Hero("Jean");
		jean.go('e');
		System.out.println(jean);
	}
}