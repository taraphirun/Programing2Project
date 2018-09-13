package main;

import java.util.Scanner;

public class Driver {
	private static Hero new1 = new Hero("Jackson","A randomn chef in the seven kingdoms.");
	private static int s,x,y =0;
	private static Place[][][] stages = {{
		  {new Place("Place 1"),new Place("Place 2"),null,new Place("Place 3"),new Place("Place 4")},
		  {new Place("Place 11"),new Place("Place 12"),new Place("Place 13"),new Place("Place 22"),new Place("Place 33",true)}},
			
		  {{new Place("Place A",true),null,new Place("Place B"),new Place("Place C"),null},
		  {new Place("Place aa"),null,null,new Place("Place bb"),new Place("Place cc")}}};
	
	public static void main(String[] args) {
		
		
//		Place[][][] stage2 = new Place[2][4][4];
//		stage2[1][1][1] = new Place("Random");
//		System.out.println(stages[1][0][0]);
	
		
		System.out.println(new1);
//		System.out.println(stages[new1.getS()][new1.getX()][new1.getY()]);
		go('s');
		go('e');
		go('e');
		go('e');
		go('e');
		go('e');
		go('w');
		
		System.out.println(new1);
		System.out.println(stages[s][x][y]);
		String placeDesc = stages[s][x][y].getDescr();
		typePrint(placeDesc);

			
	}
	
//HERO ACTIONS
	//Walking
	public static void go(char d) {		
		switch(d) {
		case 's' : x+=1;break;
		case 'e' : y+=1;break;
		case 'n' : x-=1;break;
		case 'w' : y-=1;break;
		default: System.out.print("Invalid input");
		}
		//Move To the NEXT Stage
		if(y>=stages[0][0].length && stages[s][x][y-1].isStageConnect()) {
			s++;
			x=0;
			y=0;
		//Move back to the PREVIOUS Stage
		}else if(y<0 && stages[s][x][y+1].isStageConnect()){
			s--;
			x=stages[0].length-1;
			y=stages[0][0].length-1;
		//Move around in one stage
		} else if(x<0 || x>stages[0].length-1 || y<0 || y>stages[0][0].length-1 || s<0|| s>stages.length-1 || stages[s][x][y]==null) {//Prevent player from moving to places that is not exited
			switch(d) {
			case 's' : x-=1;break;
			case 'e' : y-=1;break;
			case 'n' : x+=1;break;
			case 'w' : y+=1;break;
			default: System.out.print("Invalid input");
			}	
			System.out.println("There is no road going that way!");
		}
	}
	
	////OTHER
	public static void typePrint(String sent) {
		Scanner in = new Scanner(sent);
		while(in.hasNext()) {
			String word = in.next();
			for(int i = 0;i<word.length();i++) {
				System.out.print(word.charAt(i));
				wait(100);
			}
			System.out.print(" ");
			
		} 
		System.out.println("");
	}
	
	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
