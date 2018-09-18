package main;


public class Driver {
	private static int numMonster;

	public static void main(String[] args) {
		//ToBeUpdated at each place
		Hero new1 = new Hero("Jackson","A randomn chef in the seven kingdoms.");
		numMonster = 0;
		System.out.println(numMonster);
		System.out.println(new1);
		new1.go('s');
		new1.go('e');
		new1.go('e');
		new1.go('e');
		new1.go('e');
		new1.go('e');
		new1.go('w');
		System.out.println(new1);
		System.out.println(Functions.stages[Functions.s][Functions.x][Functions.y]);
		String placeDesc = Functions.stages[Functions.s][Functions.x][Functions.y].getDescr();
		Functions.typePrint(placeDesc);	
	}

	
}
