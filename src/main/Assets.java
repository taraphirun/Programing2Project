package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assets {
	//Defining Variable
	protected static final int totalPlaceWithMonster = 20;
	
	//Resources
	protected static ArrayList<Ingredient> ingMasterList = new ArrayList<Ingredient>();
	protected static ArrayList<Integer> ingMasterListCount = new ArrayList<Integer>();
	
	protected static ArrayList<Weapon> weapMasterList = new ArrayList<Weapon>();
	protected static ArrayList<Integer> weapMasterListCount = new ArrayList<Integer>();
	
	protected static ArrayList<Monster> monMasterList = new ArrayList<Monster>();
	protected static ArrayList<Integer> monMasterListCount = new ArrayList<Integer>();
	
		//Stages
	protected static Place[][][] map = new Place [3][10][10];
	
	//Methods
	public static void load() throws FileNotFoundException {
		loadIngList();
		loadWeapList();
		loadMap();
	}
	private static void loadIngList() throws FileNotFoundException {		
		Scanner reader = new Scanner(new File("res/ingredient.csv"));
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			ingMasterList.add(new Ingredient((String)line[0],(String)line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Boolean.parseBoolean(line[5])));
			ingMasterListCount.add(Integer.parseInt(line[6]));
		}
	}
	private static void loadWeapList() throws FileNotFoundException {		
		Scanner reader = new Scanner(new File("res/weapon.csv"));
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			weapMasterList.add(new Weapon((String)line[0],(String)line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),line[4]));
			weapMasterListCount.add(Integer.parseInt(line[5]));
		}
	}
	private static void loadMap() throws FileNotFoundException {		
		Scanner reader = new Scanner(new File("res/map.csv"));
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			map[Integer.parseInt(line[0])][Integer.parseInt(line[1])][Integer.parseInt(line[2])] = new Place(line[3],line[4],line[5],Integer.parseInt(line[6]),Integer.parseInt(line[7]),Boolean.parseBoolean(line[8]),Boolean.parseBoolean(line[9]));
		}
	}

}
