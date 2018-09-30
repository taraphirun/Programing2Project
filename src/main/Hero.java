/////////////////////////////////////////////////ALL defining Variables must be CHECK to adjust game difficulties

package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hero extends All implements Skills,comActions,Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> inventory= new ArrayList<Item>();
	private Armor armor;			
	private Weapon currentWeapon;
	private String name;
	private int cWeight;
	private double HP,MP,XP; //use double to keep a more accurate data when updating
	
	
	
	//Tracking Variable
		//Location
	private int s;
	private int x;
	private int y;
	protected int[][] locationHistory = new int[2][2];
		//General
	protected int numMove=0;
	private final int maxXP=500;////THIS is the ultimate constant that will define how high the other can be.
	private int maxWeight=100;
	private int maxHP=100;
	private int maxMP=100;
	
	//CONSTANT
	private static final int minMagic=15;
	//CommandsOption
	protected static String[] safeVerb = {"map","eat","drink","consume","clear","go","run","walk","move","view","check","look","examine","use","take","pick","fight","attack","save","change","inventory","drop","quit","end","health","HP","surrounding"};
	protected static String[] safeDefaultNoun = {"all","hp","north","east","south","west","n","s","e","w","bag","monster","inventory","around","game","dagger","around","health"};
	protected String[] safeNoun= new String[1000];
	protected static int safeNounCount =-1;
	//Constructor
	public Hero(String name) {
		super("You are a cook and adventurer! Your mission is to kill all monsters and be the best cook!");
		this.name = name;
		HP=100;
		MP=10;
		XP=10;
		s=0;x=0;y=0;
		cWeight=50;
		armor=null;
		Weapon defaultWP = new Weapon("Dagger","It's an old, rusted dagger.",15,15,"Epic");
		inventory.add(defaultWP);
		cWeight+=defaultWP.getWeight();
	}
	//For cloning purpose to track Hero previous location.
	public Hero(int x,int y) {
		super("Clone");
		this.x=x;
		this.y=y;
		s=0;
	}
//CAN_DO
	//MOVEMENT
	public void go(String d) {
		Hero cloneHero = new Hero(this.x,this.y);
		cloneHero.safeMove(d);
		if(this.getLocation().getMonsterList().isEmpty()) {
			System.out.println(this.safeMove(d));
		}else if(this.locationHistory[0][0]==cloneHero.getX() && this.locationHistory[0][1]==cloneHero.getY()) {
			getLocation().resetFMove();//To allow user 1 move when move back in to the place
			System.out.println(this.safeMove(d));
		}else {
			System.out.println("The road that way is guarded by the monster! Kill it first or run back! \n");
		}
	}
	private String safeMove(String d) {	
		switch(d.toLowerCase()) {
		case "s" :case "south": x+=1;break;
		case "e" :case "east": y+=1;break;
		case "n" :case "north": x-=1;break;
		case "w" :case "west": y-=1;break;
		case "cantgo":break;
		default: return "I don't know what direction that is...";
		}
		//Move To the NEXT Assets
		if(y>=Assets.map[0][0].length && Assets.map[s][x][y-1].isConnected()) {
			s++;
			x=0;
			y=0;
		//Move back to the PREVIOUS Assets
		}else if(y<0 && Assets.map[s][x][y+1].isConnected()){
			s--;
			x=Assets.map[0].length-1;
			y=Assets.map[0][0].length-1;
		//Move around in one stage			
		}else if(x<0 || x>Assets.map[0].length-1 || y<0 || y>Assets.map[0][0].length-1 || s<0|| s> Assets.map.length-1 || Assets.map[s][x][y]==null) {//Prevent player from moving to places that is not exited
			switch(d.toLowerCase()) {
			case "s" :case "south": x-=1;break;
			case "e" :case "east": y-=1;break;
			case "n" :case "north": x+=1;break;
			case "w" :case "west": y+=1;break;
			case "cantgo":break;
			default: return "Invalid input";
			}	
			return "There is no road down that way!";
		}		
		recLocation(x,y);
		return "";
	}
	private void recLocation(int x,int y) { //Can be used to restricted player movement
		if(locationHistory[1][0] != x || locationHistory[1][1] != y) { //Will only record new info when hero change location
			locationHistory[0][0] = locationHistory[1][0];
			locationHistory[0][1] = locationHistory[1][1];
			
			locationHistory[1][0] = x;
			locationHistory[1][1] = y;
		}	
	}
	public void eat(String str) {
		Ingredient eatable;
		boolean isEaten = false;
		for(int i=0;i<inventory.size();i++) {
			Item x = inventory.get(i);
			String fName=x.getName().split(" ")[0];
			if(fName.equalsIgnoreCase(str) && x instanceof Ingredient) {
				eatable = (Ingredient)inventory.get(i);
				if(eatable.isConsumable() || !isEaten) {
					System.out.println("You consume "+str+". Your health was "+HP+" and your MP was "+MP+".");
					HP+=eatable.getHP();
					MP+=eatable.getMP();
					if(HP>maxHP) {
						HP=maxHP;
					}
					if(MP>maxMP) {
						MP=maxMP;
					}
					inventory.remove(i);
					isEaten=true;
					System.out.println("Now your health is "+HP+" and your MP is "+MP+".");
				}
			}
		}
		if(!isEaten) {
			System.out.println("You can't consume "+str+".");
		}
	}
	public void changeWeapon(String str) {
		boolean isExist= false;
		for(Item x: inventory) {
			if(x.getName().equalsIgnoreCase(str)) {
				if(x instanceof Weapon) {
					currentWeapon=(Weapon)x;
					System.out.println("Your current weapon is "+currentWeapon.getName().toLowerCase()+".");
					isExist=true;
				}else {
					System.out.println(str +" is not a weapon!");
					isExist=true;
				}
			}
		}
		if(!isExist) {
			System.out.println("You don't have this weapon!");
		}
	}
	public void changeArmor(Armor x) {
		if(inventory.contains(x)) {
			armor=x;
			System.out.println("armor changed to "+armor);
		}else {
			System.out.println("You don't have this armor!");
		}	
	}
	public void addItem(Item x) {
		if(x!=null) {
			cWeight+=x.getWeight();
			if(cWeight>maxWeight) {
				System.out.println("The load is too heavy!");
				Assets.player.getLocation().addItem(x);
				cWeight-=x.getWeight();
			}else {
				System.out.println("You have pick up a "+x.getName().toLowerCase()+".");
				inventory.add(x);
			}
		}
	}
	public void dropItem(String x) {
		ArrayList<Item> tempt = new ArrayList<Item>();
		for(Item y:inventory) {
			if(y.getName().equalsIgnoreCase(x)) {
				getLocation().addItem(y);
				tempt.add(y);
			}
		}
		if(tempt.size()==0) {
			System.out.println("You don't have any "+x +".");
		}else {
			for(Item item:tempt) {
				inventory.remove(item);
				System.out.println("You just dropped a "+x+".");
			}
		}
//		
//		if(inventory.contains(x)) {
//			inventory.remove(x);
//			System.out.println("You dropped "+x.getName());
//			return x;
//		}else {
//			System.out.println("You CAN'T drop what you don't have!");
//			return null;
//		}
	}
	@Override
	public void dropItem() {
		if(inventory.size()!=0) {
			getLocation().addItemList(inventory);
			inventory.clear();
			System.out.println("You just drop all items!");
		}else {
			System.out.println("Your inventory is empty. There is nothing to drop!");
		}
		
	}
	public void checkInventory() {
		if(inventory.size()!=0) {
			String names = "";
			if(inventory.size()>1) {
				for(int i=0;i<inventory.size()-1;i++){
					names+=inventory.get(i).getName().toLowerCase()+", ";
				}
				names+="and "+inventory.get(inventory.size()-1).getName().toLowerCase();
			}else {
				names+=inventory.get(0).getName().toLowerCase()+" ";
			}
			System.out.println("You have "+names+"in your inventory.");
		}else {
			System.out.println("Your inventory is empty. Pick up something!");
		}
		
	}
	public Place getLocation() {
		return Assets.map[s][x][y];
	}
	//CanBeDoneOn
	public void decreaseHP(int damage) { /////////Need tests to see if work as needed ==>Work
		if(armor!=null) {
			int aHP =armor.getArmorHP();
			if(aHP>0) {
				aHP-=damage;
				armor.damageArmor(damage);
			}else if(armor.getArmorHP()==0) {
				HP+=aHP;
			}else{
				HP-=damage;
			}
		}else {
			HP-=damage;
		}
		if(HP<=0){
			HP=0;
			System.out.print(".....Wasted.....\n\n\n\nContinue from last saved location? \n>>>");
			getLocation().resetFMove();
//			String input = Misc.safeInput1Str("Yes","Sure","Sure!","Yup","No","Nope");
			Scanner in = new Scanner(System.in);
			Assets.player.numMove=0;
			boolean isRightInput = false;
			while(!isRightInput) {
				String input = in.nextLine();
				switch(input) {
				case"yes":case"sure":case"sure!":case"yup":Misc.loadGame();isRightInput=true;break;
				case"no":case"nope":Run.isPlaying=false;isRightInput=true;break;
				default:System.out.print("uh...I don't understand. Do you want to continue? \n>>>");
				}
			}
		}
	}
	protected void getExperience(int xP) {
		if((XP+xP)<maxXP) {
			XP += xP;
			maxWeight+=(xP/10);
			maxHP+=(xP/10);
			maxMP+=(xP/5);
		}else {
			XP=maxXP;
			maxWeight=(maxXP/10);
			maxHP=(maxXP/10);
			maxMP=(maxXP/10);
		}	//for every 10 XP gained, player can carry 1 extra unit of weight....
	}
		//FIGHT
	@Override
	public int attack() {
		if(currentWeapon!=null) {
			System.out.println("Attacking monster with "+currentWeapon.getName());
			return weaponFight();
		}else {
			 return fistFight();
		}
	}
	public int fistFight() {
		int damage = (int)(XP/2);
		return damage;
	}
	public int weaponFight() {
		int damage = (int)(XP/2)+currentWeapon.getDamage();
		return damage;
	}
	
	@Override
	public int heavyAttack() {///////Mana will be randomly generated
		int mana = 5;
		int damage = (int)(XP/2);
		if(MP<mana) {
			System.out.println("You don't have enough MP!!!");
			return 0;
		}else{
			MP-=mana;
			return damage*(1+mana/2);
		}
	}
	@Override
	public int magicAttack(int mana) {
		int damage = (int)(XP/2);
		if(MP<mana) {
			System.out.println("You don't have enough MP!!!");
			return 0;
		}else if(mana<minMagic){
			System.out.println("You didn't use enough MP!!!!");
			return 0;
		}else{
			MP-=mana;
			return damage*(1+mana);
		}
	}
	
	public void addSafeNoun(String x) {
		if(safeNounCount==-1) {
			safeNounCount=0;
			Arrays.fill(safeNoun,"");
			for(String str:safeDefaultNoun) {
				safeNoun[safeNounCount]=str;
				safeNounCount++;
			}
		}
		if(safeNounCount<safeNoun.length) {
			safeNoun[safeNounCount]=x;
			safeNounCount++;
		}else {
			System.out.println("WARNING: safeNoun Overflow.");///Print out warning for developer in case there are more than 100 options
		}
	}
//	@Override//////////////////////////////////////////////////////////////make more story like
//	public String toString() {
//		return "Hero [cWeight=" + cWeight + ", HP=" + HP + ", MP=" + MP + ", XP=" + XP + "]";
//	}
	//toStringTest
	
	
//	//GET SET
	protected double getHP() {
		return HP;
	}
	protected String getName() {
		return name;
	}
	protected int[][] getHisLocation(){
		return locationHistory;
	}
	protected int getX(){
		return x;
	}
	protected int getY(){
		return y;
	}
	protected void modX(int x){
		this.x+=x;
	}
	protected void modY(int y){
		this.y+=y;
	}
//	protected double getMP() {
//		return MP;
//	}
//	protected double getXP() {
//		return XP;
//	}
//	protected int getArmor() {
//		return armor;
//	}
//	protected int getMaxWeight() {
//		return maxWeight;
//	}
//	protected int getcWeight() {
//		return cWeight;
//	}
//	protected Weapon getCurrentWeapon() {
//		return currentWeapon;
//	}
//Assets.player = (Hero)loadIn.readObject();
	@Override
	public String toString() {
		return "Hero [inventory=" + inventory + ", armor=" + armor + ", currentWeapon=" + currentWeapon + ", name="
				+ name + ", s=" + s + ", x=" + x + ", y=" + y + ", cWeight=" + cWeight + ", HP=" + HP + ", MP=" + MP
				+ ", XP=" + XP + ", maxXP=" + maxXP + ", maxWeight=" + maxWeight + ", maxHP=" + maxHP + ", maxMP="
				+ maxMP + "]";
	}
	@Override
	public int heavyAttack(int mana) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
