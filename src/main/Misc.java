package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Misc {
	private static Scanner in;
	//Add to SafeStringNoun	
	//GAMEPLAY
	protected static void gameplay() {
		Place cPlace = Assets.player.getLocation();
		Monster cMonster = cPlace.getFightingMonster();
		
		if(cPlace.getFightingMonster()==null || cPlace.numFightMove==0){
			command();
		}else if(cPlace.numFightMove!=0) {
			System.out.println("damaged by " +cMonster.attack());
			Assets.player.decreaseHP(cMonster.attack());
			command();
		}
	}
	protected static void command() {
		Place cPlace = Assets.player.getLocation();
		String command  = safeInput2Str(Hero.safeVerb,Assets.player.safeNoun);
		in = new Scanner(command);
		String[] commandList = in.nextLine().split(" ");
		String verb;
		String noun;
		if(commandList.length==1) {
			cPlace.numFightMove++;
			verb=commandList[0];
			switch(verb) {
			case"map":System.out.println("X is where you are!");drawMap();System.out.println(Assets.player.getLocation().getDirection());gameplay();break;
			case"clear":System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");gameplay();break;
			case"inventory":Assets.player.checkInventory();gameplay();break;
			case"save":saveGame();gameplay();break;
			case"go":System.out.println("That's not a direction! Where do you want to go?");gameplay();break;
			case"look":case"examine":case"view":case"check":System.out.println("What do you want to look at?");gameplay();break;
			case"hp":case"health":System.out.println("Your current health is "+Assets.player.getHP());gameplay();break;
			case"quit":case"exit":case"end":Run.isPlaying=false;break;
			default:System.out.println("I don't understand...");gameplay();
			}
		}else if(commandList.length==2) {
			verb = commandList[0];
			noun = commandList[1];
			cPlace.numFightMove++;
			switch(verb) {
			case"eat":case"drink":case"consume":Assets.player.eat(noun);gameplay();break;
			case"pick":case"take":Assets.player.addItem(Assets.player.getLocation().takeFrom(noun));gameplay();break;
			case"use":Assets.player.changeWeapon(noun);gameplay();break;
			case"go":case"run":case"walk":case"move":Assets.player.go(noun);checkPlace();gameplay();break;
			case"save":saveGame();gameplay();break;
			case"drop":
				switch(noun) {
				case"all":case"inventory":Assets.player.dropItem();gameplay();break;
				default:Assets.player.dropItem(noun);gameplay();
				}break;
			case"look":case"examine":case"view":case"check":Place current = Assets.player.getLocation();
				switch(noun) {
				case"around":checkEnviron();gameplay();break;
				case"monster":case"enemy":case"danger":current.numFightMove--;System.out.println(current.getFightingMonster().getDescr());gameplay();break;
				case"inventory":case"bag":Assets.player.checkInventory();gameplay();break;
				case"hp":case"health":System.out.println("Your current health is "+Assets.player.getHP());gameplay();break;
				default:System.out.println("What do you want to look at?");gameplay();
				}break;
			case"quit":case"exit":case"end":Run.isPlaying=false;break;
			default:System.out.println("What do you mean...?");gameplay();
			}
		}else {
			System.out.println("Uh...what do you mean?");gameplay();
		}
	}
//Password Encryptiofn
//	protected static void encrypt(String passwd) {
//		String encrypted = "";
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(passwordToHash.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//Fight
	
//MAP
	protected static void drawMap() {
		 String hWall = "*****+";
		 String eWall = "      ";
		 String locat = "  X   "; 
		 String content ="******";
		 String vWall = "-----+";
		 int scaleHeight = Assets.map[0].length;
		 int scaleWidth = Assets.map[0][0].length;
		 
		 //MAP_HEAD
		 System.out.print("+");
		 for(int i = 0;i<scaleWidth;i++) {
			 System.out.print(hWall);
		 }
		 System.out.println();	 
		 //MAP_BODY
		 for (int i = 0; i < scaleHeight; i++) {
			 //START
			 System.out.print("|");
			 //endofSTART
			//Content
			 for(int j=0;j<scaleWidth;j++) {
				 if(Assets.map[0][i][j]==Assets.player.getLocation()) {
					 System.out.print(locat);
				 }else if(Assets.map[0][i][j]==null) {
					 System.out.print(content);
				 }else {
					 System.out.print(eWall);
				 }
			 }
			//END
			 System.out.println("|");
			//endofEND
			 //Border
			 System.out.print("+");
			 for(int j=0;j<scaleWidth;j++) {
				 System.out.print(vWall);
			 }
			 System.out.println(); 
		 }
	}
/*  TEMPLATE
 *  +*****+*****+*****+*****+*****+*****+*****+*****+*****+*****+
 *	|  X              |*****************|     |*****************|
 *	+     +*****+     +*****+*****+*****+     +*****+*****+*****+
 *	|     |*****|                             		      |*****|
 *	+     +*****+*****+     +*****+*****+     +*****+     +*****+
 *	|      		|*****|     |***********|     |*****|     |*****|
 *	+*****+     +*****+     +*****+*****+     +*****+     +*****+
 *	|*****|		|*****|                       |*****|     |*****|
 *	+*****+     +*****+*****+*****+*****+     +*****+     +*****+
 *	|*****|		|***********************|      		            |
 *	+*****+     +*****+*****+*****+*****+     +*****+*****+     +
 *	|*****|		                              |*****|*****|     |
 *	+*****+     +*****+*****+     +*****+*****+*****+*****+     +
 *	|*****|		|***********|     |***********************|     |
 *	+*****+     +*****+*****+     +*****+*****+*****+*****+     +
 *	|      		|*****|                 |*****************|     |
 *	+*****+     +*****+     +*****+     +*****+*****+*****+     +
 *	|*****|		            |*****|            		            |
 *	+*****+*****+*****+     +*****+*****+*****+*****+           + 
 *	|*****************|           |*****************|           |
 *	+*****+*****+*****+*****+*****+*****+*****+*****+*****+*****+ 
 */	
	
//CheckPlace
	private static void checkEnviron() {
		Place place = Assets.player.getLocation();
		System.out.println(place.getName().toUpperCase());
		System.out.println(place.getDescr());
		System.out.println(place.getDirection());
		checkItem();
	} 
	protected static void checkPlace() {
		Place place = Assets.player.getLocation();
		if(!place.isVisited()) {
			System.out.println(place.getName().toUpperCase());
			System.out.println(place.getDescr());
			System.out.println(place.getDirection());
			checkItem();
			checkMonster();
			place.visited();
		}else {
			System.out.println(place.getName().toUpperCase());			
		}
		
	}
	private static void checkItem() {
		Place current = Assets.player.getLocation(); 
		ArrayList<Item> inventory = current.getItemList();
		if(inventory.size()!=0) {
			String names = "";
			if(inventory.size()>1) {
				names +=" are ";
				for(int i=0;i<inventory.size()-1;i++){
					names+=inventory.get(i).getName().toLowerCase()+", ";
				}
				names+="and "+inventory.get(inventory.size()-1).getName().toLowerCase();
			}else {
				names +=" is ";
				names+=inventory.get(0).getName().toLowerCase()+" ";
			}
			System.out.println("There"+names+" here.");
		}
//		else {
//			System.out.println("There is nothing to pick up here.");
//		}
	}
	private static void checkMonster() {
		//Monster
		Place current = Assets.player.getLocation(); 
		if(current.getFightingMonster()!=null && current.getFightingMonster().getHP()!=0) {
			
		}else if(current.getMonsterList().size()>0) {
			current.numFightMove=0;
			current.setFightingMonster(current.getMonsterList().remove(0));
			System.out.println("There is a monster here! It's a "+current.getFightingMonster().getName()+"!");
			System.out.println(current.getFightingMonster().getBattleCry());
		}else {
			current.setFightingMonster(null);
		}
	}
	
//Save&Load Game 
	protected static void saveGame() {
		File player = new File("saves/"+Assets.player.getName()+"/save.dat");
		File map = new File("saves/"+Assets.player.getName()+"/map.dat");
		FileOutputStream fo=null;
		FileOutputStream fo2=null;
		try {
			fo = new FileOutputStream(player);
			fo2 = new FileOutputStream(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ObjectOutputStream output=null;
		ObjectOutputStream output2=null;
		try {
			output = new ObjectOutputStream(fo);
			output2 = new ObjectOutputStream(fo2);
			output.writeObject(Assets.player);
			output2.writeObject(Assets.map);
			System.out.println("Checkpoint created!");
			fo.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected static void newGame() {
		in = new Scanner(System.in);
		System.out.print("What would you like to be call? \n>>>");
		String name=in.next();
		File dir = new File("saves/"+name);
		if(dir.mkdirs()) {
			
			//CreateSaves
			File file = new File("saves/"+name+"/save.dat");
			File map = new File("saves/"+name+"/map.dat");
			Assets.player = new Hero(name);
// FATALITY +++> 2 days to find this BUG!!!!!
			//LoadResoures ==>>Must initialize after a user is create
			Assets.initilize();
			FileOutputStream fo=null;
			FileOutputStream fo2=null;
			try {
				fo = new FileOutputStream(file);
				fo2 = new FileOutputStream(map);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ObjectOutputStream output=null;
			ObjectOutputStream output2=null;
			try {
				output = new ObjectOutputStream(fo);
				output2 = new ObjectOutputStream(fo2);
				output.writeObject(Assets.player);
				output2.writeObject(Assets.map);
				fo.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Already existed!");
			newGame();
		}
//		gamePlay();
	}
	protected static void loadGame() {
		int input=0;
		int[] options;
		in = new Scanner(System.in);
		File dir = new File("saves/");
		String[] saveList = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File current,String name) {
				return new File(current,name).isDirectory();
			}
		});
		options=new int[saveList.length];
		if(options.length!=0) {
			for(int i=0;i<saveList.length;i++) {
				System.out.println(i+1+". "+saveList[i]);
				options[i]=i+1;
			}
			System.out.print("Select your saved game \n>>>");
			input=Misc.safeInput1Int(options);
			System.out.println();
			File file = new File("saves/"+saveList[input-1]+"/save.dat");
			File map = new File("saves/"+saveList[input-1]+"/map.dat");
			FileInputStream fi=null;
			FileInputStream fi2=null;
			try {
				fi = new FileInputStream(file);
				fi2 = new FileInputStream(map);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			ObjectInputStream loadIn=null;
			ObjectInputStream loadIn2=null;
			try {
				loadIn = new ObjectInputStream(fi);
				loadIn2 = new ObjectInputStream(fi2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				Assets.player = (Hero)loadIn.readObject();
				Assets.map = (Place[][][])loadIn2.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("You never played this game before! Welcome to Foodland!");
			newGame();
		}
		
//		gamePlay();
	}
	
//Safe Keeping                   
	public static int safeInput1Int(int... arr) {
		 in= new Scanner(System.in);
		int input=0;
		try {
			input = in.nextInt();
		} catch (Exception e) {
			in.nextLine();
			System.out.println("Invalid Input Type. \n>>>");
			return safeInput1Int(arr);
		}
		for(int x:arr) {
			if(x==input) {
				in.nextLine();
				return x;
			}
		}
		System.out.println("Invalid Option. \n>>>");
		return safeInput1Int(arr);
			
	}
		public static String safeInput1Str(String... arr) {
			in= new Scanner(System.in);
			String result="";
			String input=in.next();
			for(String x:arr) {
				if(x.equalsIgnoreCase(input)) {
					result+=input;
				}
			}
			if(result.equalsIgnoreCase("")) {
				System.out.println("Invalid Option. \n>>>");
				return safeInput1Str(arr);
			}
			in.nextLine();
			return result.toLowerCase();
		}
		public static String safeInput2Str(String[]... arr) {
			System.out.print("\n>>>");
			in= new Scanner(System.in);
			String result="";
			String input=in.nextLine().toLowerCase();
			Scanner reader = new Scanner(input);
			if(reader.hasNext()) {
				String input1 = reader.next();
				boolean isInput1Ready = false;
				boolean isInput2Ready = false;
				String input2="";
				if(reader.hasNext()) {
					input2 = reader.next();
				}		
				for(String x:arr[0]) {
					if(x.equalsIgnoreCase(input1) && !isInput1Ready) {
						result+=input1+" ";
						isInput1Ready=true;
					}
				}
				for(String x:arr[1]) {
					if(x.equalsIgnoreCase(input2) && !isInput2Ready) {
						result+=input2+" ";
						isInput2Ready=true;
					}
				}
				if(!isInput1Ready && result.equalsIgnoreCase("")) {//A verb must be there to tell the program what to do
					System.out.println("Invalid Option. \n>>>");
					reader.close();
					return safeInput2Str(arr);
				}
				reader.close();
				return result;	
			}else {
				reader.close();
				return "%empty%";
			}			
		}	
//Typing Effect
	public static void typePrint(String sent) {
		in = new Scanner(sent);
		while(in.hasNext()) {
			String word = in.next();
			for(int i = 0;i<word.length();i++) {
				System.out.print(word.charAt(i));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.print(" ");
		} 
		System.out.println("");
	}
}
