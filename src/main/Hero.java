/////////////////////////////////////////////////ALL defining Variables must be CHECK to adjust game difficulties

package main;

import java.util.*;

public class Hero extends All implements Skills{
	private ArrayList<Item> inventory;
	private Armor armor;			
	private Weapon currentWeapon;
	private String name;
	
	private int cWeight;
	private double HP,MP,XP; //use double to keep a more accurate data when updating
	
	
	//Tracking Variable
	private int maxXP=500;////THIS is the ultimate constant that will define how high the other can be. WILL NOT BE ALTERED.
	private int maxWeight=100;
	private int maxHP=100;
	private int maxMP=100;
	
	//CONSTANT
	private static final int minHeavy=5;
	private static final int minMagic=15;
	public Hero(String name, String descr) {
		super(descr);
		this.name = name;
		HP=100;
		MP=10;
		XP=10;
		armor=null;
	}
	//CAN_DO
	public void go(char d) {	
		switch(d) {
		case 's' : Functions.x+=1;break;
		case 'e' : Functions.y+=1;break;
		case 'n' : Functions.x-=1;break;
		case 'w' : Functions.y-=1;break;
		default: System.out.print("Invalid input");
		}
		//Move To the NEXT Stage
		if(Functions.y>=Functions.stages[0][0].length && Functions.stages[Functions.s][Functions.x][Functions.y-1].isStageConnect()) {
			Functions.s++;
			Functions.x=0;
			Functions.y=0;
		//Move back to the PREVIOUS Stage
		}else if(Functions.y<0 && Functions.stages[Functions.s][Functions.x][Functions.y+1].isStageConnect()){
			Functions.s--;
			Functions.x=Functions.stages[0].length-1;
			Functions.y=Functions.stages[0][0].length-1;
		//Move around in one stage
		} else if(Functions.x<0 || Functions.x>Functions.stages[0].length-1 || Functions.y<0 || Functions.y>Functions.stages[0][0].length-1 || Functions.s<0|| Functions.s>Functions.stages.length-1 || Functions.stages[Functions.s][Functions.x][Functions.y]==null) {//Prevent player from moving to places that is not exited
			switch(d) {
			case 's' : Functions.x-=1;break;
			case 'e' : Functions.y-=1;break;
			case 'n' : Functions.x+=1;break;
			case 'w' : Functions.y+=1;break;
			default: System.out.print("Invalid input");
			}	
			System.out.println("There is no road going that way!");
		}
	}
	public void changeWeapon(Weapon x) {////////////////////////////////////////MAY Cause issue here. instanceof
		if(inventory.contains(x)) {
			currentWeapon=x;
			System.out.println("Weapon changed to "+currentWeapon);
		}else {
			System.out.println("You don't have this weapon!");
		}	
	}
	public void changeArmor(Armor x) {////////////////////////////////////////MAY Cause issue here. instanceof
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
	public void checkInventory() {
		int i =1;
		for(Item x: inventory) {
			System.out.println(i+". "+x);i++;
		}
	}
	//CanBeDoneOn
	public void decreaseHP(int damage) {/////////////////////////////////////////Need tests to see if work as needed
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
	public int fistFight() {
		int damage = (int)(XP/2);
		return damage;
	}
	public int weaponFight() {
		int damage = (int)(XP/2)+currentWeapon.getDamage();
		return damage;
	}
	
	@Override//////////////////////////////////////////////////////////////make more story like
	public String toString() {
		return "Hero [cWeight=" + cWeight + ", HP=" + HP + ", MP=" + MP + ", XP=" + XP + "]";
	}
///IMPLEMENT

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
	public int magicAttack(int mana) {///////Mana will be randomly generated
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
	
	
	
//	//GET SET
//	protected double getHP() {
//		return HP;
//	}
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
//	protected String getName() {
//		return name;
//	}
//	//SET
//	protected void setHP(int hP) {
//		HP = hP;
//	}
//	protected void setMP(int mP) {
//		MP = mP;
//	}
//	
//	protected void setArmor(int armor) {
//		this.armor = armor;
//	}
	

}
