package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Misc {
	private static Scanner in;
	private static String[] safeStringVerb = {"go","run","walk","move","view","check","look","examine","fight","attack","change","inventory","drop","quit","end"};
	private static String[] safeStringNoun = {"north","east","south","west","n","s","e","w","monster","inventory","around","game"};
//GAMEPLAY
	protected static void command() {
		String command  = safeInput2Str(safeStringVerb,safeStringNoun);
		in = new Scanner(command);
		String[] commandList = in.nextLine().split(" ");
		String verb;
		String noun;
		if(commandList.length==1) {
			verb=commandList[0];
			switch(verb) {
			case"inventory":Assets.player.checkInventory();command();break;
			case"quit":case"exit":case"end":Run.isPlaying=false;break;
			default:System.out.println("I don't understand...");command();
			}			
		}else {
			verb = commandList[0];
			noun = commandList[1];
			
			switch(verb) {
			case"go":case"run":case"walk":case"move":Assets.player.go(noun);command();break;
			case"quit":case"exit":case"end":Run.isPlaying=false;break;
			default:System.out.println("I don't understand...");command();
			}
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
			fo.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected static void newGame() {
		in = new Scanner(System.in);
		System.out.print("What would you like to be call? \nINPUT: ");
		String name=in.next();
		File dir = new File("saves/"+name);
		if(dir.mkdirs()) {
			//LoadResoures
			Assets.initilize();
			//CreateSaves
			File file = new File("saves/"+name+"/save.dat");
			File map = new File("saves/"+name+"/map.dat");
			Assets.player = new Hero(name);
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
			System.out.print("Select your saved game \nINPUT: ");
			input=Misc.safeInput1Int(options);
			
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
			System.out.println("Invalid Input Type. \n INPUT: ");
			return safeInput1Int(arr);
		}
		for(int x:arr) {
			if(x==input) {
				in.nextLine();
				return x;
			}
		}
		System.out.println("Invalid Option. \n INPUT: ");
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
				System.out.println("Invalid Option. \n INPUT: ");
				return safeInput1Str(arr);
			}
			in.nextLine();
			return result;
		}
		public static String safeInput2Str(String[]... arr) {
			in= new Scanner(System.in);
			String result="";
			String input=in.nextLine();
			Scanner reader = new Scanner(input);
			
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
				System.out.println("Invalid Option. \n INPUT: ");
				reader.close();
				return safeInput2Str(arr);
			}
			reader.close();
			return result;
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
