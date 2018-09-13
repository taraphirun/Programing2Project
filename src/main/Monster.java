package main;

import java.util.ArrayList;

public abstract class Monster extends All{
	private int HP;
	private int damage;
	private String name;
	private ArrayList<Item> inventory;
	public Monster(String descr, String name,int damage,ArrayList<Item> itemList) {
		super(descr);
		inventory=itemList;
		this.damage=damage;
		this.name=name;
	}
	
	public void decreaseHP(int damage) {
		HP-=damage;
		if(HP<=0) {
			HP=0;
			System.out.println("Monster "+name+" is dead!");
			dropItem();////so when dead, drop items
		}
	}
	public abstract int attack();////////////////////////////////////////////Could allow monster to attack with special attack to increase difficulty
	private ArrayList<Item> dropItem(){
		return inventory;////not needed to check if mon = dead, since this method is only call when HP <=0
	}
	//GET SET
	public int getDamage() {
		return damage;
	}
	
	
	
}
