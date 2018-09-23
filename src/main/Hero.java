/////////////////////////////////////////////////ALL defining Variables must be CHECK to adjust game difficulties

package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Hero extends All implements Skills,comActions,Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Item> inventory= new ArrayList<Item>();
	private Armor armor;			
	private Weapon currentWeapon;
	private String name;
	private int s;
	private int x;
	private int y;
	private int cWeight;
	private double HP,MP,XP; //use double to keep a more accurate data when updating
	
	
	//Tracking Variable
	private final int maxXP=500;////THIS is the ultimate constant that will define how high the other can be. WILL NOT BE ALTERED.
	private int maxWeight=100;
	private int maxHP=100;
	private int maxMP=100;
	
	//CONSTANT
	private static final int minHeavy=5;
	private static final int minMagic=15;
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
	}
	
	
	
	//CAN_DO
	public void go(String d) {	
		switch(d.toLowerCase()) {
		case "s" :case "south": x+=1;break;
		case "e" :case "east": y+=1;break;
		case "n" :case "north": x-=1;break;
		case "w" :case "west": y-=1;break;
		default: System.out.print("Invalid input");
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
		} else if(x<0 || x>Assets.map[0].length-1 || y<0 || y>Assets.map[0][0].length-1 || s<0|| s> Assets.map.length-1 || Assets.map[s][x][y]==null) {//Prevent player from moving to places that is not exited
			switch(d) {
			case "s" :case "south": x-=1;break;
			case "e" :case "east": y-=1;break;
			case "n" :case "north": x+=1;break;
			case "w" :case "west": y+=1;break;
			default: System.out.print("Invalid input");
			}	
			System.out.println("There is no road going that way!");
		}
	}
	public void changeWeapon(Weapon x) {//////////////MAY Cause issue here. instanceof
		if(inventory.contains(x)) {
			currentWeapon=x;
			System.out.println("Weapon changed to "+currentWeapon);
		}else {
			System.out.println("You don't have this weapon!");
		}	
	}
	public void changeArmor(Armor x) {//////////////MAY Cause issue here. instanceof
		if(inventory.contains(x)) {
			armor=x;
			System.out.println("armor changed to "+armor);
		}else {
			System.out.println("You don't have this armor!");
		}	
	}
	public void addItem(Item x) {
		cWeight+=x.getWeight();
		if(cWeight>maxWeight) {
			System.out.println("The load is too heavy!");
			cWeight-=x.getWeight();
		}else {
			System.out.println("You have pick up "+x.getName());
			inventory.add(x);
		}
	}
	public Item removeItem(Item x) {
		if(inventory.contains(x)) {
			inventory.remove(x);
			System.out.println("You dropped "+x.getName());
			return x;
		}else {
			System.out.println("You CAN'T drop what you don't have!");
			return null;
		}
	}
	@Override
	public void dropItem() {
		getLocation().addItemList(inventory);
		inventory.clear();
	}
	public void checkInventory() {
		int i =1;
		for(Item x: inventory) {
			System.out.println(i+". "+x);i++;
		}
	}
	public Place getLocation() {
		return Assets.map[s][x][y];
	}
	//CanBeDoneOn
	public void decreaseHP(int damage) { //////////////////Need tests to see if work as needed
		int aHP =armor.getArmorHP();
		if(aHP>0) {
			aHP-=damage;
			armor.damageArmor(damage);
		}else if(armor.getArmorHP()==0) {
			HP+=aHP;
		}else{
			HP-=damage;
		}
		if(HP<=0){
			HP=0;
			System.out.println(".....Wasted.....");
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
		// TODO Auto-generated method stub
		return 0;
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
	public int heavyAttack(int mana) {///////Mana will be randomly generated
		int damage = (int)(XP/2);
		if(MP<mana) {
			System.out.println("You don't have enough MP!!!");
			return 0;
		}else if(mana<minHeavy){
			System.out.println("You didn't use enough MP!!!!");
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
//	@Override//////////////////////////////////////////////////////////////make more story like
//	public String toString() {
//		return "Hero [cWeight=" + cWeight + ", HP=" + HP + ", MP=" + MP + ", XP=" + XP + "]";
//	}
	//toStringTest
	
	
//	//GET SET
//	protected double getHP() {
//		return HP;
//	}
	protected String getName() {
		return name;
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
	

}
