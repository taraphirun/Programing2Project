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
	//NewGAME
	protected static void newGame() {
		in = new Scanner(System.in);
		String name="";
		System.out.print("What would you like to be call? \nINPUT: ");
		name=in.next();
		File dir = new File("saves/"+name);
		if(dir.mkdirs()) {
			File file = new File("saves/"+name+"/save.dat");
			Assets.player = new Hero(name);
			
			FileOutputStream fo=null;
			try {
				fo = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ObjectOutputStream output=null;
			try {
				output = new ObjectOutputStream(fo);
				output.writeObject(Assets.player);
				fo.close();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//LoadResoures
			Assets.initilize();
			
			
		}else {
			System.out.println("Already existed!");
			newGame();
		}
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
		for(int i=0;i<saveList.length;i++) {
			System.out.println(i+1+". "+saveList[i]);
			options[i]=i+1;
		}
		System.out.print("Select your saved game \nINPUT: ");
		input=Misc.safeInput1Int(options);
		
		File file = new File("saves/"+saveList[input-1]+"/save.dat");
		FileInputStream fi=null;
		try {
			fi = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ObjectInputStream loadIn=null;
		try {
			loadIn = new ObjectInputStream(fi);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			Assets.player = (Hero)loadIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		public static String safeInput2Str(String... arr) {
			in= new Scanner(System.in);
			String result="";
			String input=in.nextLine();
			Scanner reader = new Scanner(input);
			
			String input1 = reader.next();
			String input2="";
			if(reader.hasNext()) {
				input2 = reader.next();
			}		
			for(String x:arr) {
				if(x.equalsIgnoreCase(input1)) {
					result+=input1+" ";
				}
				if(x.equalsIgnoreCase(input2)) {
					result+=input2+" ";
				}
			}
			if(result.equalsIgnoreCase("")) {
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
