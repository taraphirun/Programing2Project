package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Assets {
//Defining Variable
	//HERO
	protected static Hero player = new Hero("Default"); ;
	//Map
	protected static final int totalPlaceWithMonster = 20;
	protected static int totalisMonsterNest =0;
	protected static int[][] monsterNestLocation = new int[totalPlaceWithMonster][3];
	
//Resources
	protected static final int totalPlaceWithIngredient = 20;
	protected static int totalisIngredient=0;
	protected static int[][] IngredientLocation = new int[totalPlaceWithIngredient][3];
	protected static ArrayList<Ingredient> ingMasterList = new ArrayList<Ingredient>();
	protected static ArrayList<Integer> ingMasterListCount = new ArrayList<Integer>();
	protected static int totalIngredient=0;
	
	protected static final int totalPlaceWithWeapon = 20;
	protected static int totalisWeapon =0;
	protected static int[][] WeaponLocation = new int[totalPlaceWithWeapon][3];
	protected static ArrayList<Weapon> weapMasterList = new ArrayList<Weapon>();
	protected static ArrayList<Integer> weapMasterListCount = new ArrayList<Integer>();
	protected static int totalWeapons=0;
	
	protected static ArrayList<Monster> monMasterList = new ArrayList<Monster>();
	protected static ArrayList<Integer> monMasterListCount = new ArrayList<Integer>();
	protected static int totalMonsters=0;
	//Stages
	protected static Place[][][] map = new Place [1][10][10];
	protected static int totalPlaces=0;
	
//Methods
	public static void initilize(){
		loadIngList();
		loadWeapList();
		loadMonList();
		loadMap();
		Hero.safeNounCount=-1;
		
	}
	private static void loadIngList() {		
		Scanner reader=null;
		try {
			reader = new Scanner(new File("res/ingredient.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			ingMasterList.add(new Ingredient(line[0],line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Boolean.parseBoolean(line[5])));
			ingMasterListCount.add(Integer.parseInt(line[6]));
			totalIngredient+=Integer.parseInt(line[6]);
			String[] name = line[0].split(" ");
			for(String x:name) {
				player.addSafeNoun(x);			
			}
		}
		reader.close();
	}
	private static void loadWeapList(){		
		Scanner reader=null;
		try {
			reader = new Scanner(new File("res/weapon.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			weapMasterList.add(new Weapon(line[0],line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),line[4]));
			weapMasterListCount.add(Integer.parseInt(line[5]));
			totalWeapons+=Integer.parseInt(line[5]);
			String[] name = line[0].split(" ");
			for(String x:name) {
				player.addSafeNoun(x);
			}
		}
		reader.close();
	}
	protected static void loadMonList(){
		Scanner reader=null;
		try {
			reader = new Scanner(new File("res/monster.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			Scanner innerReader = new Scanner(line[6]);
			String[] ingList = innerReader.nextLine().split("`");
			ArrayList<Item> ingArr = new ArrayList<Item>();
			
			for(String x:ingList) {
				Ingredient tempt = findIng(x);
				if(tempt!=null) {
					ingArr.add(tempt);
				}
			}
			monMasterList.add(new Monster(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt(line[4]),ingArr));
			monMasterListCount.add(Integer.parseInt(line[5]));
			totalMonsters+=Integer.parseInt(line[5]);
			innerReader.close();
		}
		reader.close();
	}
	private static void loadMap(){		
		Scanner reader=null;
		try {
			reader = new Scanner(new File("res/map.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
			map[Integer.parseInt(line[0])][Integer.parseInt(line[1])][Integer.parseInt(line[2])] = new Place(line[3],line[4],line[5],Boolean.parseBoolean(line[6]),Boolean.parseBoolean(line[7]),Boolean.parseBoolean(line[8]),Boolean.parseBoolean(line[9]));
			if(Boolean.parseBoolean(line[8])) {
				monsterNestLocation[totalisMonsterNest][0]= Integer.parseInt(line[0]);
				monsterNestLocation[totalisMonsterNest][1]= Integer.parseInt(line[1]);
				monsterNestLocation[totalisMonsterNest][2]= Integer.parseInt(line[2]);
				totalisMonsterNest++;
			}
			//public Place(String name,String descr,String direction,boolean isWeapon,boolean isIngredient,boolean isMonsterNest,boolean isConnected) {
			if(Boolean.parseBoolean(line[7])) {
				IngredientLocation[totalisIngredient][0]= Integer.parseInt(line[0]);
				IngredientLocation[totalisIngredient][1]= Integer.parseInt(line[1]);
				IngredientLocation[totalisIngredient][2]= Integer.parseInt(line[2]);
				totalisIngredient++;
			}
			if(Boolean.parseBoolean(line[6])) {
				WeaponLocation[totalisWeapon][0]= Integer.parseInt(line[0]);
				WeaponLocation[totalisWeapon][1]= Integer.parseInt(line[1]);
				WeaponLocation[totalisWeapon][2]= Integer.parseInt(line[2]);
				totalisWeapon++;
			}	
		}
//Initialize Map with Monster
		ArrayList<Integer> allMonsterLocation = new ArrayList<Integer>();
		ArrayList<Integer> finalMonsterLocation = new ArrayList<Integer>();
		int numOfMonsterNest;
		for(int i=0;i<totalisMonsterNest;i++) {
			allMonsterLocation.add(i,i);
		}
		Collections.shuffle(allMonsterLocation);
		//The number of place that can have monster can be up to totalPlaceWithMonster
		if(totalisMonsterNest<=totalPlaceWithMonster) {
			for(int i=0;i<totalisMonsterNest;i++) {
				finalMonsterLocation.add(i,allMonsterLocation.get(i));
			}
			numOfMonsterNest=totalisMonsterNest;
		}else {
			for(int i=0;i<totalPlaceWithMonster;i++) {
				finalMonsterLocation.set(i,allMonsterLocation.get(i));
			}
			numOfMonsterNest=totalPlaceWithMonster;
		}
		//Monster
		int numMonster =(int)totalMonsters/numOfMonsterNest;		
		for(int i=0;i<numOfMonsterNest;i++) {
			ArrayList<Monster> placeMonster = new ArrayList<Monster>();
			for(int k=0;k<numMonster;k++) {
				placeMonster.add(findMonster());
			}
			int index = finalMonsterLocation.get(i);
			map[monsterNestLocation[index][0]][monsterNestLocation[index][1]][monsterNestLocation[index][2]].setMonsterList(placeMonster);
			totalPlaces++;
		}
		
//Load the map with Item
		ArrayList<Integer> allIngredientLocation = new ArrayList<Integer>();
		ArrayList<Integer> finalIngredientLocation = new ArrayList<Integer>();
		int numOfIngredientLocation;
		for(int i=0;i<totalisIngredient;i++) {
			allIngredientLocation.add(i,i);
		}
		Collections.shuffle(allIngredientLocation);
		//The number of place that can have monster can be up to totalPlaceWithMonster
		if(totalisIngredient<=totalPlaceWithIngredient) {
			for(int i=0;i<totalisIngredient;i++) {
				finalIngredientLocation.add(i,allIngredientLocation.get(i));
			}
			numOfIngredientLocation=totalisIngredient;
		}else {
			for(int i=0;i<totalPlaceWithIngredient;i++) {
				finalIngredientLocation.set(i,allIngredientLocation.get(i));
			}
			numOfIngredientLocation=totalPlaceWithIngredient;
		}
		//Ingredient
		int numIngredient =(int)totalIngredient/numOfIngredientLocation;		
		for(int i=0;i<numOfIngredientLocation;i++) {
			ArrayList<Item> placeIngredient = new ArrayList<Item>();
			for(int k=0;k<numIngredient;k++) {
				placeIngredient.add(findIngredient());
			}
			int index = finalIngredientLocation.get(i);
//			System.out.println(map[IngredientLocation[index][0]][IngredientLocation[index][1]][IngredientLocation[index][2]]);
			map[IngredientLocation[index][0]][IngredientLocation[index][1]][IngredientLocation[index][2]].addItemList(placeIngredient);
		}
//Initialize map with Weapon
		ArrayList<Integer> allWeaponLocation = new ArrayList<Integer>();
		ArrayList<Integer> finalWeaponLocation = new ArrayList<Integer>();
		int numOfWeaponLocation;
		for(int i=0;i<totalisWeapon;i++) {
			allWeaponLocation.add(i,i);
		}
		Collections.shuffle(allWeaponLocation);
		//The number of place that can have monster can be up to totalPlaceWithMonster
		if(totalisWeapon<=totalPlaceWithWeapon) {
			for(int i=0;i<totalisWeapon;i++) {
				finalWeaponLocation.add(i,allWeaponLocation.get(i));
			}
			numOfWeaponLocation=totalisWeapon;
		}else {
			for(int i=0;i<totalPlaceWithWeapon;i++) {
				finalWeaponLocation.set(i,allWeaponLocation.get(i));
			}
			numOfWeaponLocation=totalPlaceWithWeapon;
		}
		//Weapon
		int numWeapon =(int)totalWeapons/numOfWeaponLocation;		
		for(int i=0;i<numOfWeaponLocation;i++) {
			ArrayList<Item> placeWeapon= new ArrayList<Item>();
			for(int k=0;k<numWeapon;k++) {
				placeWeapon.add(findWeapon());
			}
			int index = finalWeaponLocation.get(i);
			map[WeaponLocation[index][0]][WeaponLocation[index][1]][WeaponLocation[index][2]].addItemList(placeWeapon);
		}
		reader.close();
	}
	
	private static Item findWeapon() {
		if(totalWeapons!=0) {
			int index = (int)(Math.random()*weapMasterListCount.size());
			if(weapMasterListCount.get(index)!=0) {
				weapMasterListCount.set(index, weapMasterListCount.get(index)-1);
				totalWeapons--;
				return weapMasterList.get(index);
			}
			return findWeapon();
		}
		return null;
	}
	private static Item findIngredient() {
		if(totalIngredient!=0) {
			int index = (int)(Math.random()*ingMasterListCount.size());
			if(ingMasterListCount.get(index)!=0) {
				ingMasterListCount.set(index, ingMasterListCount.get(index)-1);
				totalIngredient--;
				return ingMasterList.get(index);
			}
			return findIngredient();
		}
		return null;
	}
	private static Monster findMonster() {
		if(totalMonsters!=0) {
			int index = (int)(Math.random()*monMasterListCount.size());
			if(monMasterListCount.get(index)!=0) {
				monMasterListCount.set(index, monMasterListCount.get(index)-1);
				totalMonsters--;
				return monMasterList.get(index);
			}
			return findMonster();
		}
		return null;			
	}
	private static Ingredient findIng(String name) {
		for (int i = 0; i < ingMasterList.size(); i++) {
			if(ingMasterList.get(i).getName().equalsIgnoreCase(name) && ingMasterListCount.get(i)!=0) {
				ingMasterListCount.set(i, ingMasterListCount.get(i)-1);
				totalIngredient--;
				return ingMasterList.get(i);
			}
		}
		return null;		
	}
}
