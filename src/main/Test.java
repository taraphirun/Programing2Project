package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Test {

	public static void main(String[] args) throws FileNotFoundException,IOException, ClassNotFoundException {
//		Scanner in = new Scanner(System.in);
//		String path="";
//		System.out.print("INPUT PATH: ");path=in.next();
//		File dir = new File("saves/"+path);
//		System.out.println(dir.mkdirs());
//		File file = new File("saves/"+path+"/herolistLIST.txt");
//		Hero Jack = new Hero("Jack");
//		Hero clone = null;
//		
//		FileOutputStream fo = new FileOutputStream(file);
//		ObjectOutputStream output = new ObjectOutputStream(fo);
//		output.writeObject(Jack);
//		fo.close();
//		output.close();
//		
//		FileInputStream fi = new FileInputStream(file);
//		ObjectInputStream input = new ObjectInputStream(fi);
//		
//		try {
//			clone = (Hero)input.readObject();
//		} catch (EOFException e) {}
//		clone.go('n');
//		clone.go('e');
//		clone.go('s');
////		clone.go('w');
//		System.out.println(clone);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		System.out.println(list);
//		Collections.shuffle(list);
//		System.out.println(list);
		int index = (int)(Math.random()*list.size());
		System.out.println(index);
		
		
	}

}
