package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Assets {
//Defining Variable
	//HERO
	protected static Hero player = null;
	
	
	//Map
	protected static final int totalPlaceWithMonster = 20;
	protected static int totalisMonsterNest =0;
	protected static int[][] monsterNestLocation = new int[totalPlaceWithMonster][3];
	
//Resources
	protected static ArrayList<Ingredient> ingMasterList = new ArrayList<Ingredient>();
	protected static ArrayList<Integer> ingMasterListCount = new ArrayList<Integer>();
	protected static int totalIngredient=0;
	
	protected static ArrayList<Weapon> weapMasterList = new ArrayList<Weapon>();
	protected static ArrayList<Integer> weapMasterListCount = new ArrayList<Integer>();
	protected static int totalWeapons=0;
	
	protected static ArrayList<Monster> monMasterList = new ArrayList<Monster>();
	protected static ArrayList<Integer> monMasterListCount = new ArrayList<Integer>();
	protected static int totalMonsters=0;
	
	//Stages
	protected static Place[][][] map = new Place [1][3][3];
	
//Methods
	public static void initilize(){
		loadIngList();
		loadWeapList();
		loadMonList();
		loadMap();
		for(Monster x: monMasterList) {
			System.out.println(x);
		}
		for(int x: monMasterListCount) {
			System.out.println(x);
		}
		
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
		}
		reader.close();
	}
	protected static void loadMonList(){		
		//public Monster(String descr, String name,int damage,int HP) {
		Scanner reader=null;
		try {
			reader = new Scanner(new File("res/monster.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int kk =0;
		while(reader.hasNextLine()) {
			String[] line = reader.nextLine().split("~");
//			System.out.println(line.length);
//			for(String x:line) {
//				System.out.println(x);
//			}
			Scanner innerReader = new Scanner(line[5]);
			String[] ingList = innerReader.nextLine().split("`");
//			for(String x:ingList) {
//				System.out.println(x);
//			}
			ArrayList<Item> ingArr = new ArrayList<Item>();
			
			for(String x:ingList) {
				Ingredient tempt = findIng(x);
				if(tempt!=null) {
					ingArr.add(tempt);
				}else {
					System.out.println(kk);
					kk++;
				}
			}
			monMasterList.add(new Monster(line[0],line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),ingArr));
			monMasterListCount.add(Integer.parseInt(line[4]));
			totalMonsters+=Integer.parseInt(line[4]);
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
			map[Integer.parseInt(line[0])][Integer.parseInt(line[1])][Integer.parseInt(line[2])] = new Place(line[3],line[4],line[5],Integer.parseInt(line[6]),Integer.parseInt(line[7]),Boolean.parseBoolean(line[8]),Boolean.parseBoolean(line[9]));
			if(Boolean.parseBoolean(line[8])) {
				monsterNestLocation[totalisMonsterNest][0]= Integer.parseInt(line[0]);
				monsterNestLocation[totalisMonsterNest][1]= Integer.parseInt(line[1]);
				monsterNestLocation[totalisMonsterNest][2]= Integer.parseInt(line[2]);
				totalisMonsterNest++;
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
		
		for(Monster x: monMasterList) {
			System.out.println(x);
		}
		for(int x: monMasterListCount) {
			System.out.println(x);
		}
		
		for(int i=0;i<numOfMonsterNest;i++) {
			ArrayList<Monster> placeMonster = new ArrayList<Monster>();
			for(int k=0;k<numMonster;k++) {
				placeMonster.add(findMonster());
			}
			int index = finalMonsterLocation.get(i);
			map[monsterNestLocation[index][0]][monsterNestLocation[index][1]][monsterNestLocation[index][2]].setMonsterList(placeMonster);
		}
		reader.close();
	}
	private static Monster findMonster() {
		int index = (int)(Math.random()*monMasterListCount.size());
		if(monMasterListCount.get(index)!=0) {
			monMasterListCount.set(index, monMasterListCount.get(index)-1);
			return monMasterList.get(index);
		}
		return findMonster();
	}
	private static Ingredient findIng(String name) {
		for (int i = 0; i < ingMasterList.size(); i++) {
			if(ingMasterList.get(i).getName().equalsIgnoreCase(name)) {
				return ingMasterList.get(i);
			}
		}
		return null;		
	}

}
